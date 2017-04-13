package com.tcaulk.notable.model.response.notableapi.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseNotableResponse<T> {

    private int errorCode;
    private String errorDescription;
    private String authorizationCacheKey;
    private T payload;

    @JsonProperty("ErrorCode")
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("ErrorDescription")
    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

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
