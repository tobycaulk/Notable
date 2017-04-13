package com.tcaulk.notable.model.request.httpbase;

import com.tcaulk.notable.model.authorization.AuthorizationType;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public abstract class BaseGetRequest<T> extends BaseRequest<T> {

    private BiFunction<Integer, Integer, String> urlStartProcessor = (index, size) -> {
        return index == 0 ? "?" : "";
    };

    private BiFunction<Integer, Integer, String> urlVariableJoinProcessor = (index, size) -> {
        return index < size ? "&" : "";
    };

    private Map<String, String> urlVariables = new HashMap<>();

    public BaseGetRequest(String accessToken, AuthorizationType authorizationType) {
        super(accessToken, authorizationType);
    }

    public void addUrlVariable(String key, String value) {
        urlVariables.put(key, value);
    }

    public String getExpandedUrl() {
        StringBuilder urlBuilder = new StringBuilder(getUrl());

        List<String> keySet = new ArrayList<>(urlVariables.keySet());
        IntStream.range(0, keySet.size()).forEach(index -> {
            urlBuilder.append(urlStartProcessor.apply(index, keySet.size()));
            urlBuilder.append(urlVariableJoinProcessor.apply(index, keySet.size()));

            String key = keySet.get(index);
            urlBuilder.append(key + "={" + key + "}");
        });

        return urlBuilder.toString();
    }

    @Override
    public T getBody() {
        return null;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public Map<String, String> getUrlVariables() {
        return urlVariables;
    }
}