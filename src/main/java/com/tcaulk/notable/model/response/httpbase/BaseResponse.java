package com.tcaulk.notable.model.response.httpbase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse<R> {

    private int errorCode;
    private String errorDescription;
    private R payload;

    public BaseResponse() {
        this(null);
    }

    public BaseResponse(R payload) {
        this.payload = payload;
    }

    public BaseResponse(int errorCode, String errorDescription) {
        this(null, errorCode, errorDescription);
    }

    public BaseResponse(R payload, int errorCode, String errorDescription) {
        this.payload = payload;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    @JsonProperty("ErrorCode")
    public int getErrorCode() {
        return errorCode;
    }

    @JsonProperty("ErrorDescription")
    public String getErrorDescription() {
        return errorDescription;
    }

    @JsonProperty("Payload")
    public R getPayload() {
        return payload;
    }
}
