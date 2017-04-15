package com.tcaulk.notable.controller;

import com.tcaulk.notable.model.authorization.AuthorizationCachePackage;
import com.tcaulk.notable.model.error.NotableError;
import com.tcaulk.notable.model.request.notableapi.base.BaseNotableRequest;
import com.tcaulk.notable.model.response.notableapi.base.BaseNotableResponse;
import com.tcaulk.notable.service.spotifyapi.authorization.SpotifyAuthorization;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class ControllerUtil {
    private static final Logger log = Logger.getLogger(ControllerUtil.class);

    private SpotifyAuthorization spotifyAuthorization;

    @Autowired
    public ControllerUtil(SpotifyAuthorization spotifyAuthorization) {
        this.spotifyAuthorization = spotifyAuthorization;
    }

    public <T, R> BaseNotableResponse<R> processRequest(BaseNotableRequest<T> request, BiFunction<T, AuthorizationCachePackage, R> serviceFunc) {
        BaseNotableResponse<R> response = new BaseNotableResponse<R>();

        try {
            AuthorizationCachePackage authorizationCachePackage = spotifyAuthorization.getAuthorizationCachePackage(request.getAuthorizationCacheKey());
            if(authorizationCachePackage == null) {
                log.error("Could not retrieve Authorization Token from Spotify");
                response.setError(NotableError.ERROR_RETRIEVING_AUTHORIZATION_TOKEN);
            } else {
                R serviceResponse = serviceFunc.apply(request.getPayload(), authorizationCachePackage);
                if(serviceResponse != null) {
                    response.setPayload(serviceResponse);
                    response.setAuthorizationCacheKey(authorizationCachePackage.authorizationCacheKey.getKey());
                } else {
                    log.error("Response from service call is null");
                    response.setError(NotableError.GENERIC_INTERNAL_ERROR);
                }
            }
        } catch(Exception e) {
            log.error("Error while processing request", e);
            response.setError(NotableError.GENERIC_INTERNAL_ERROR);
        }

        return response;
    }
}