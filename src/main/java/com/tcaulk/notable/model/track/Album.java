package com.tcaulk.notable.model.track;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Album {

	private String href;
	private String id;
	private List<AlbumImage> images;
	private String name;
	private String uri;

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
	
	@JsonProperty("images")
	public List<AlbumImage> getImages() {
		return images;
	}
	
	public void setImages(List<AlbumImage> images) {
		this.images = images;
	}
	
	@JsonProperty("name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("uri")
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
}
