package com.tcaulk.notable.model.authorization;

public class AuthorizationCachePackage {

    public final AuthorizationToken authorizationToken;
    public final AuthorizationCacheKey authorizationCacheKey;

    public AuthorizationCachePackage(AuthorizationToken authorizationToken, AuthorizationCacheKey authorizationCacheKey) {
        this.authorizationToken = authorizationToken;
        this.authorizationCacheKey = authorizationCacheKey;
    }
}
