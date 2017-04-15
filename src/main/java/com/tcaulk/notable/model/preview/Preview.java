package com.tcaulk.notable.model.preview;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcaulk.notable.model.location.Location;
import org.springframework.data.annotation.Id;

public class Preview {

    private String id;
    private String trackId;
    private Location location;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("TrackId")
    public String getSpotifyTrackId() {
        return trackId;
    }

    public void setSpotifyTrackId(String trackId) {
        this.trackId = trackId;
    }

    @JsonProperty("Location")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
