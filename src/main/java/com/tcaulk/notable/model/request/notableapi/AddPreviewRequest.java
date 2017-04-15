package com.tcaulk.notable.model.request.notableapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tcaulk.notable.model.preview.Preview;

public class AddPreviewRequest {

    private Preview preview;

    @JsonProperty("Preview")
    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }
}