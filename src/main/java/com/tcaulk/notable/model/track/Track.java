package com.tcaulk.notable.model.track;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

	private Album album;
	private List<Artist> artists;
	private int durationMilliseconds;
	private boolean explicit;
	private String href;
	private String id;
	private String name;
	private int popularity;
	private String previewUrl;
	private String uri;
	
	@JsonProperty("album")
	public Album getAlbum() {
		return album;
	}
	
	public void setAlbum(Album album) {
		this.album = album;
	}
	
	@JsonProperty("artists")
	public List<Artist> getArtists() {
		return artists;
	}
	
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	
	@JsonProperty("duration_ms")
	public int getDurationMilliseconds() {
		return durationMilliseconds;
	}
	
	public void setDurationMilliseconds(int durationMilliseconds) {
		this.durationMilliseconds = durationMilliseconds;
	}
	
	@JsonProperty("explicit")
	public boolean isExplicit() {
		return explicit;
	}
	
	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}
	
	@JsonProperty("href")
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("popularity")
	public int getPopularity() {
		return popularity;
	}
	
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
	@JsonProperty("preview_url")
	public String getPreviewUrl() {
		return previewUrl;
	}
	
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	
	@JsonProperty("uri")
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
}
