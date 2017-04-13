package com.tcaulk.notable.model.request.httpbase;

import com.tcaulk.notable.model.authorization.AuthorizationType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public abstract class BaseHttpRequest<T> {

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";

    private HttpHeaders httpHeaders = new HttpHeaders();

    public BaseHttpRequest(String accessToken, AuthorizationType authorizationType) {
        httpHeaders.setContentType(getContentType());
        httpHeaders.set(AUTHORIZATION_HEADER_KEY, authorizationType.tokenType + " " + accessToken);
    }

    public abstract T getBody();
    public abstract MediaType getContentType();
    public abstract String getUrl();
    public abstract HttpMethod getHttpMethod();

    public HttpEntity<T> getHttpEntity() {
        HttpEntity<T> httpEntity = null;

        if(getHttpMethod() == HttpMethod.GET) {
            httpEntity = new HttpEntity<T>(getHttpHeaders());
        } else if(getHttpMethod() == HttpMethod.POST) {
            httpEntity = new HttpEntity<T>(getBody(), getHttpHeaders());
        }

        return httpEntity;
    }

    public void addHttpHeader(String key, String value) {
        httpHeaders.set(key, value);
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }
}