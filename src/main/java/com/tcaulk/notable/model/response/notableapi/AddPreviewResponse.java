package com.tcaulk.notable.model.response.notableapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcaulk.notable.model.response.ResponseStatus;

public class AddPreviewResponse {

    private ResponseStatus status;

    @JsonProperty("ResponseStatus")
    public ResponseStatus getResponseStatus() {
        return status;
    }

    public void setResponseStatus(ResponseStatus status) {
        this.status = status;
    }
}