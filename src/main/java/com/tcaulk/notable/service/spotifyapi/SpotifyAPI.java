package com.tcaulk.notable.service.spotifyapi;

import com.tcaulk.notable.model.authorization.AuthorizationCachePackage;
import com.tcaulk.notable.model.recommendations.SpotifyRecommendationSeed;
import com.tcaulk.notable.model.request.notableapi.GetRecommendationsRequest;
import com.tcaulk.notable.model.request.spotifyapi.GetSpotifyRecommendationsHttpRequest;
import com.tcaulk.notable.model.response.httpbase.BaseResponse;
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

    public GetSpotifyRecommendationsResponse getSpotifyRecommendations(GetRecommendationsRequest request, AuthorizationCachePackage authorizationCachePackage) {
        GetSpotifyRecommendationsResponse response = new GetSpotifyRecommendationsResponse();

        GetSpotifyRecommendationsHttpRequest recommendationsRequest = new GetSpotifyRecommendationsHttpRequest(
                authorizationCachePackage.authorizationToken.getAccessToken(),
                request.getSpotifyRecommendationSeeds()
        );
        response = HttpClient.handleRequest(recommendationsRequest, GetSpotifyRecommendationsResponse.class).getPayload();

        return response;
    }
}