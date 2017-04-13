package com.tcaulk.notable.model.request.base;

import com.tcaulk.notable.model.authorization.AuthorizationType;
import org.springframework.http.HttpMethod;

public abstract class BasePostRequest<T> extends BaseRequest<T> {

    public BasePostRequest(String accessToken, AuthorizationType authorizationType) {
        super(accessToken, authorizationType);
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
