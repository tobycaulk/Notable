package com.tcaulk.notable.model.request.httpbase;

import com.tcaulk.notable.model.authorization.AuthorizationType;
import org.springframework.http.HttpMethod;

public abstract class BasePostHttpRequest<T> extends BaseHttpRequest<T> {

    public BasePostHttpRequest(String accessToken, AuthorizationType authorizationType) {
        super(accessToken, authorizationType);
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
