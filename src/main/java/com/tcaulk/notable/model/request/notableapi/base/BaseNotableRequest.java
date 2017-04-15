package com.tcaulk.notable.model.request.notableapi.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseNotableRequest<T> {

    private String authorizationCacheKey;
    private T payload;

    @JsonProperty("AuthorizationKey")
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
