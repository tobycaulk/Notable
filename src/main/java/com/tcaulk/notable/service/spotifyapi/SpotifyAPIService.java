package com.tcaulk.notable.service.spotifyapi;

import com.tcaulk.notable.model.authorization.AuthorizationToken;
import com.tcaulk.notable.model.authorization.AuthorizationType;
import com.tcaulk.notable.model.recommendations.SpotifyRecommendationSeed;
import com.tcaulk.notable.model.request.spotifyapi.GetAuthorizationTokenRequest;
import com.tcaulk.notable.model.request.spotifyapi.GetSpotifyRecommendationsRequest;
import com.tcaulk.notable.model.response.base.BaseResponse;
import com.tcaulk.notable.model.response.spotifyapi.GetSpotifyRecommendationsResponse;
import com.tcaulk.notable.service.http.HttpClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpotifyAPIService {
    private static final Logger log = Logger.getLogger(SpotifyAPIService.class);

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";

    private final String clientId;
    private final String clientSecret;

    @Autowired
    public SpotifyAPIService(@Value("${spotifyAPIClientId}") String clientId,
                             @Value("${spotifyAPIClientSecret}") String clientSecret){

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public BaseResponse<AuthorizationToken> getAuthorizationToken() {
        GetAuthorizationTokenRequest request = new GetAuthorizationTokenRequest(new String(Base64.encodeBase64(new String(clientId + ":" + clientSecret).getBytes())));
        BaseResponse<AuthorizationToken> response = HttpClient.handleRequest(request, AuthorizationToken.class);

        return response;
    }

    public BaseResponse<GetSpotifyRecommendationsResponse> getSpotifyRecommendations() {
        SpotifyRecommendationSeed genreSeed = new SpotifyRecommendationSeed("seed_genres", "pop");
        SpotifyRecommendationSeed marketSeed = new SpotifyRecommendationSeed("market", "US");
        GetSpotifyRecommendationsRequest request = new GetSpotifyRecommendationsRequest(getAuthorizationToken().getPayload().getAccessToken(), genreSeed, marketSeed);

        BaseResponse<GetSpotifyRecommendationsResponse> response = HttpClient.handleRequest(request, GetSpotifyRecommendationsResponse.class);

        return response;
    }

    private String getAuthorizationHeaderValue() {
        String authorizationHeaderValue = AuthorizationType.BASIC.tokenType;

        String encodedClientKey = new String(Base64.encodeBase64(new String(clientId + ":" + clientSecret).getBytes()));
        if(encodedClientKey != null) {
            authorizationHeaderValue += " " + encodedClientKey;
        }

        return authorizationHeaderValue;
    }
}