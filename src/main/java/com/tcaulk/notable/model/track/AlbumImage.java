package com.tcaulk.notable.model.track;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumImage {

	private int height;
	private int width;
	private String url;
	
	@JsonProperty("height")
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	@JsonProperty("width")
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	@JsonProperty("url")
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
