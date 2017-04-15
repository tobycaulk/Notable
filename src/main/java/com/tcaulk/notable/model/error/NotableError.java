package com.tcaulk.notable.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

//Inform Jackson we want to marshall this enum to a JSON object
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum NotableError {

    GENERIC_INTERNAL_ERROR(1, "An internal error has occurred"),
    ERROR_RETRIEVING_AUTHORIZATION_TOKEN(2, "An error occurred while communicating with Spotify");

    private final int errorCode;
    private final String errorDescription;

    private NotableError(int errorCode, String errorDescription) {
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
}
