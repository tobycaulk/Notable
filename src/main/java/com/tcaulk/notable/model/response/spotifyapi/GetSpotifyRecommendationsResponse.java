package com.tcaulk.notable.model.response.spotifyapi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcaulk.notable.model.track.Track;

public class GetSpotifyRecommendationsResponse {

	private List<Track> tracks;

	@JsonProperty("tracks")
	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
}
