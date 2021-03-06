package com.tcaulk.notable.model.request.spotifyapi;

import com.tcaulk.notable.model.authorization.AuthorizationType;
import com.tcaulk.notable.model.request.httpbase.BasePostHttpRequest;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class GetAuthorizationTokenHttpRequest extends BasePostHttpRequest<MultiValueMap<String, String>> {

    private static final String GRANT_TYPE_KEY = "grant_type";
    private static final String GRANT_TYPE_VALUE = "client_credentials";

    public GetAuthorizationTokenHttpRequest(String accessToken) {
        super(accessToken, AuthorizationType.BASIC);
    }

    @Override
    public MultiValueMap<String, String> getBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.set(GRANT_TYPE_KEY, GRANT_TYPE_VALUE);

        return body;
    }

    @Override
    public MediaType getContentType() {
        return MediaType.APPLICATION_FORM_URLENCODED;
    }

    @Override
    public String getUrl() {
        return "https://accounts.spotify.com/api/token";
    }
}
