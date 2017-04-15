package com.tcaulk.notable.service.spotifyapi.authorization;

import com.tcaulk.notable.model.authorization.AuthorizationCacheKey;
import com.tcaulk.notable.model.authorization.AuthorizationCachePackage;
import com.tcaulk.notable.model.authorization.AuthorizationToken;
import com.tcaulk.notable.model.request.spotifyapi.GetAuthorizationTokenHttpRequest;
import com.tcaulk.notable.model.response.httpbase.BaseResponse;
import com.tcaulk.notable.service.http.HttpClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpotifyAuthorization {
    private static final Logger log = Logger.getLogger(SpotifyAuthorization.class);

    private final String clientId;
    private final String clientSecret;

    private Map<AuthorizationCacheKey, AuthorizationToken> authorizationTokenCache = new HashMap<>();

    @Autowired
    public SpotifyAuthorization(@Value("${spotifyAPIClientId}") String clientId, @Value("${spotifyAPIClientSecret}") String clientSecret){
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    private AuthorizationCacheKey generateAuthorizationCacheKey() {
        AuthorizationCacheKey authorizationCacheKey = new AuthorizationCacheKey();
        authorizationCacheKey.setKey(UUID.randomUUID().toString());
        authorizationCacheKey.setCacheDate(new Date());

        return authorizationCacheKey;
    }

    private AuthorizationCacheKey findAuthorizationCacheKey(String key) {
        return key.equals("") ? null : authorizationTokenCache.keySet().stream().filter(cachedKey -> cachedKey.getKey().equals(key)).findFirst().orElseGet(() -> null);
    }

    private boolean isAuthorizationTokenValid(AuthorizationCacheKey authorizationCacheKey, AuthorizationToken authorizationToken) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(authorizationCacheKey.getCacheDate());
        calendar.add(Calendar.SECOND, authorizationToken.getExpiresIn());
        Date authorizationTokenExpirationDate = calendar.getTime();

        return new Date().after(authorizationTokenExpirationDate);
    }

    private AuthorizationToken requestAuthorizationToken() {
        GetAuthorizationTokenHttpRequest request = new GetAuthorizationTokenHttpRequest(new String(Base64.encodeBase64(new String(clientId + ":" + clientSecret).getBytes())));
        BaseResponse<AuthorizationToken> response = HttpClient.handleRequest(request, AuthorizationToken.class);

        return response.getPayload();
    }

    public AuthorizationCachePackage getAuthorizationCachePackage(String cacheKey) {
        AuthorizationCachePackage authorizationCachePackage = null;

        log.debug("Retrieving AuthorizationCachePackage for cacheKey[" + cacheKey + "]");

        AuthorizationToken authorizationToken = new AuthorizationToken();
        //First determine if there is already an authorization token cached for this cache key
        AuthorizationCacheKey authorizationCacheKey = findAuthorizationCacheKey(cacheKey);
        //If the cache key is null then there is no authorization token for this key.
        //This could either mean that the key sent in is invalid, or that someone accessing this API does not already
        //have a key and needs to generate one
        if(authorizationCacheKey == null) {
            log.debug("No Authorization Token found for cacheKey[" + cacheKey + "], Requesting new Authorization Token from Spotify");
            //Request a new Authorization Token from spotify
            authorizationToken = requestAuthorizationToken();
            if(authorizationToken != null) {
                //If a token has been successfully generated then cache it using a new cache key
                authorizationCacheKey = generateAuthorizationCacheKey();
                authorizationTokenCache.put(authorizationCacheKey, authorizationToken);
                authorizationCachePackage = new AuthorizationCachePackage(authorizationToken, authorizationCacheKey);
                log.debug("Received Authorization Token[" + authorizationToken.getAccessToken() + "] for cacheKey[" + authorizationCacheKey.getKey() + "]");
            }
        } else {
            //If the cache key is valid, retrieve the token corresponding to the key
            AuthorizationToken cachedAuthorizationToken = authorizationTokenCache.get(authorizationCacheKey);
            //Check to make sure the token hasn't expired
            if(isAuthorizationTokenValid(authorizationCacheKey, cachedAuthorizationToken)) {
                log.debug("Authorization Token for cacheKey[" + cacheKey + "] is valid, returning AuthorizationCachePackage");
                authorizationToken = cachedAuthorizationToken;
                authorizationCachePackage = new AuthorizationCachePackage(authorizationToken, authorizationCacheKey);
            } else {
                log.debug("Authorization Token for cacheKey[" + cacheKey + "] expired, requesting new Authorization Token");
                //The token has expired, generate a new one and update the cache key's date field to indicate
                //the time we cached the new key
                authorizationToken = requestAuthorizationToken();
                if(authorizationToken != null) {
                    authorizationCacheKey.setCacheDate(new Date());
                    authorizationTokenCache.put(authorizationCacheKey, authorizationToken);
                    authorizationCachePackage = new AuthorizationCachePackage(authorizationToken, authorizationCacheKey);
                    log.debug("Received Authorization Token[" + authorizationToken.getAccessToken() + "] for cacheKey[" + authorizationCacheKey.getKey() + "]");
                }
            }
        }

        return authorizationCachePackage;
    }
}