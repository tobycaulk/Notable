package com.tcaulk.notable.model.response.notableapi.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcaulk.notable.model.error.NotableError;

public class BaseNotableResponse<T> {

    private NotableError error;
    private String authorizationCacheKey;
    private T payload;

    @JsonProperty("Error")
    public NotableError getError() {
        return error;
    }

    @JsonProperty("AuthorizationCacheKey")
    public String getAuthorizationCacheKey() {
        return authorizationCacheKey;
    }

    @JsonProperty("Payload")
    public T getPayload() {
        return payload;
    }

    public void setError(NotableError error) {
        this.error = error;
    }

    public void setAuthorizationCacheKey(String authorizationCacheKey) {
        this.authorizationCacheKey = authorizationCacheKey;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

}
