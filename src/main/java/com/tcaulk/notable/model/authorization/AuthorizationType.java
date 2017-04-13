package com.tcaulk.notable.model.authorization;

public enum AuthorizationType {

	BEARER("Bearer"),
	BASIC("Basic");
	
	public String tokenType;
	
	AuthorizationType(String tokenType) {
		this.tokenType = tokenType;
	}
}
