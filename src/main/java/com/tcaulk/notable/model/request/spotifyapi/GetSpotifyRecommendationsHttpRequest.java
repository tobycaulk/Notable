package com.tcaulk.notable.model.request.spotifyapi;

import com.tcaulk.notable.model.authorization.AuthorizationType;
import com.tcaulk.notable.model.recommendations.SpotifyRecommendationSeed;
import com.tcaulk.notable.model.request.httpbase.BaseGetHttpRequest;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

public class GetSpotifyRecommendationsHttpRequest extends BaseGetHttpRequest<Void> {

    public GetSpotifyRecommendationsHttpRequest(String accessToken, List<SpotifyRecommendationSeed> spotifyRecommendationSeeds) {
        super(accessToken, AuthorizationType.BEARER);

        spotifyRecommendationSeeds.stream().forEach(seed -> {
            addUrlVariable(seed.getProperty(), seed.getValue());
        });
    }

    @Override
    public MediaType getContentType() {
        return MediaType.APPLICATION_JSON;
    }

    @Override
    public String getUrl() {
        return "https://api.spotify.com/v1/recommendations";
    }
}
