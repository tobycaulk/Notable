package com.tcaulk.notable.model.request.notableapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcaulk.notable.model.recommendations.SpotifyRecommendationSeed;

import java.util.ArrayList;
import java.util.List;

public class GetRecommendationsRequest {

    private List<SpotifyRecommendationSeed> spotifyRecommendationSeeds = new ArrayList<>();

    @JsonProperty("Seeds")
    public List<SpotifyRecommendationSeed> getSpotifyRecommendationSeeds() {
        return spotifyRecommendationSeeds;
    }

    public void setSpotifyRecommendationSeeds(List<SpotifyRecommendationSeed> spotifyRecommendationSeeds) {
        this.spotifyRecommendationSeeds = spotifyRecommendationSeeds;
    }
}
