package com.tcaulk.notable.model.response.notableapi.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseNotableResponse<T> {

    private String authorizationCacheKey;
    private T payload;

    @JsonProperty("AuthorizationCacheKey")
    public String getAuthorizationCacheKey() {
        return authorizationCacheKey;
    }

    public void setAuthorizationCacheKey(String authorizationCacheKey) {
        this.authorizationCacheKey = authorizationCacheKey;
    }

    @JsonProperty("Payload")
    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

}
