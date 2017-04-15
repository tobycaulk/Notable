package com.tcaulk.notable.model.recommendations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpotifyRecommendationSeed {

    private String property;
    private String value;

    public SpotifyRecommendationSeed() { }

    public SpotifyRecommendationSeed(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @JsonProperty("Property")
    public String getProperty() {
        return property;
    }

    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setValue(String value) {
        this.value = value;
    }
}