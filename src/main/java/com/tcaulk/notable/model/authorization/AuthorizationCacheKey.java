package com.tcaulk.notable.model.authorization;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AuthorizationCacheKey {

    private String key;
    private Date cacheDate;

    public String getKey() {
        return key;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
