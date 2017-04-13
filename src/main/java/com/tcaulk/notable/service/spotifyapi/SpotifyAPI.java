package com.tcaulk.notable.service.spotifyapi;

import com.tcaulk.notable.model.authorization.AuthorizationCachePackage;
import com.tcaulk.notable.model.authorization.AuthorizationToken;
import com.tcaulk.notable.model.recommendations.SpotifyRecommendationSeed;
import com.tcaulk.notable.model.request.notableapi.GetRecommendationsRequest;
import com.tcaulk.notable.model.request.notableapi.base.BaseNotableRequest;
import com.tcaulk.notable.model.request.spotifyapi.GetSpotifyRecommendationsRequest;
import com.tcaulk.notable.model.response.httpbase.BaseResponse;
import com.tcaulk.notable.model.response.notableapi.base.BaseNotableResponse;
import com.tcaulk.notable.model.response.spotifyapi.GetSpotifyRecommendationsResponse;
import com.tcaulk.notable.service.http.HttpClient;
import com.tcaulk.notable.service.spotifyapi.authorization.SpotifyAuthorization;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotifyAPI {
    private static final Logger log = Logger.getLogger(SpotifyAPI.class);

    private SpotifyAuthorization spotifyAuthorization;

    @Autowired
    public SpotifyAPI(SpotifyAuthorization spotifyAuthorization){
        this.spotifyAuthorization = spotifyAuthorization;
    }

    public BaseNotableResponse<GetSpotifyRecommendationsResponse> getSpotifyRecommendations(BaseNotableRequest<GetRecommendationsRequest> request) {
        BaseNotableResponse<GetSpotifyRecommendationsResponse> response = new BaseNotableResponse<>();

        AuthorizationCachePackage authorizationCachePackage = spotifyAuthorization.getAuthorizationCachePackage(request.getAuthorizationCacheKey());

        SpotifyRecommendationSeed genreSeed = new SpotifyRecommendationSeed("seed_genres", "pop");
        SpotifyRecommendationSeed marketSeed = new SpotifyRecommendationSeed("market", "US");

        GetSpotifyRecommendationsRequest recommendationsRequest = new GetSpotifyRecommendationsRequest(
                authorizationCachePackage.authorizationToken.getAccessToken(),
                genreSeed,
                marketSeed
        );

        BaseResponse<GetSpotifyRecommendationsResponse> recommendationsResponse
                = HttpClient.handleRequest(recommendationsRequest, GetSpotifyRecommendationsResponse.class);
        if(recommendationsResponse != null) {
            response.setAuthorizationCacheKey(authorizationCachePackage.authorizationCacheKey.getKey());
            response.setPayload(recommendationsResponse.getPayload());
        }

        return response;
    }
}