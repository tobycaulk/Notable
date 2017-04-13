package com.tcaulk.notable.service.http;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.tcaulk.notable.model.request.base.BaseGetRequest;
import com.tcaulk.notable.model.request.base.BaseRequest;
import com.tcaulk.notable.model.response.base.BaseResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClient {
	private static final Logger log = Logger.getLogger(HttpClient.class);

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static <R> BaseResponse<R> handleRequest(BaseRequest<?> request, Class<R> responseType) {
	    BaseResponse<R> response = null;

	    String requestId = generateRequestId();
	    log.debug("Sending request with " +
                "requestId[" + requestId + "] to " +
                "URL[" + request.getUrl() + "] " +
                "headers[" + request.getHttpEntity().getHeaders() + "] " +
                "body[" + request.getHttpEntity().getBody() + "]");

	    try {
            ResponseEntity<String> responseEntity = null;
            switch (request.getHttpMethod()) {
                case GET:
                    BaseGetRequest<?> getRequest = (BaseGetRequest<?>) request;
                    responseEntity = get(getRequest.getExpandedUrl(), request.getHttpEntity(), getRequest.getUrlVariables());
                    break;
                case POST:
                    responseEntity = post(request.getUrl(), request.getHttpEntity());
                    break;
            }

            if(responseEntity != null) {
                log.debug("Received response for requestId[" + requestId + "] " +
                        "headers[" + responseEntity.getHeaders() + "] " +
                        "body[" + responseEntity.getBody() + "]");

                response = new BaseResponse<R>(MAPPER.readValue(responseEntity.getBody(), responseType));
            } else {
                log.debug("No response received for requestId[" + requestId + "]");
            }

        } catch(Exception e) {
	        log.error("Error while sending request with requestId[" + requestId + "]", e);
        }

        return response;
    }

	private static <T> ResponseEntity<String> post(String url, HttpEntity<T> httpEntity) throws IOException {
	    return REST_TEMPLATE.postForEntity(url, httpEntity, String.class);
    }

    private static <T> ResponseEntity<String> get(String url, HttpEntity<T> httpEntity, Map<String, String> urlVariables) {
	    return REST_TEMPLATE.exchange(url, HttpMethod.GET, httpEntity, String.class, urlVariables);
    }
	
	private static String parseToJsonString(Object value) throws JsonProcessingException {
		return MAPPER.writeValueAsString(value);
	}
	
	private static String generateRequestId() {
		return UUID.randomUUID().toString();
	}
}