package com.tcaulk.notable.model.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    private double x;
    private double y;

    @JsonProperty("loc_x")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @JsonProperty("loc_y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
