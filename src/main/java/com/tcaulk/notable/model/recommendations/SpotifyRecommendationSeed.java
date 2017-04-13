package com.tcaulk.notable.model.recommendations;

public class SpotifyRecommendationSeed {

    private String property;
    private String value;

    public SpotifyRecommendationSeed(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }
}