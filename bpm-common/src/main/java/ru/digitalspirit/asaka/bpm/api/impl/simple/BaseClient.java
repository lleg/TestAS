package ru.digitalspirit.asaka.bpm.api.impl.simple;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;

@Immutable
abstract class BaseClient {

    private static Logger logger = LoggerFactory.getLogger(BaseClient.class.getName());

    private static final RequestConfig DEFAULT_CONFIG = RequestConfig.custom()
            .setCookieSpec(CookieSpecs.DEFAULT)
            .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
            .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();

    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String FORM_URL_CONTENT_TYPE = "application/x-www-form-urlencoded";
    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    protected static final String DEFAULT_SEPARATOR = ",";
    protected static final int DEFAULT_TIMEOUT = 120000; // 120 seconds

    protected void setRequestTimeOut(HttpRequestBase request, int timeOut) {
        RequestConfig requestConfig = RequestConfig.copy(DEFAULT_CONFIG).setSocketTimeout(timeOut).setConnectTimeout(timeOut).setConnectionRequestTimeout(timeOut).build();
        request.setConfig(requestConfig);
    }

    protected void setHeadersGet(HttpRequestBase request) {
        request.addHeader(HttpHeaders.CONTENT_TYPE, JSON_CONTENT_TYPE);
        request.setHeader(HttpHeaders.ACCEPT, JSON_CONTENT_TYPE);
    }

    protected void setHeadersPut(HttpRequestBase request) {
        setHeadersGet(request); // The same one
    }


    protected void setHeadersPost(HttpRequestBase request) {
        request.addHeader(HttpHeaders.CONTENT_TYPE, FORM_URL_CONTENT_TYPE);
        request.setHeader(HttpHeaders.ACCEPT, JSON_CONTENT_TYPE);
    }

    protected void logRequest(HttpRequest request, String body) {
        logger.info("Prepared Request for uri: {}", request.getRequestLine().getUri());
        logger.debug("HTTP Request headers: {}", Arrays.toString(request.getAllHeaders()));
        if (logger.isDebugEnabled()) {
            logger.debug("Request body: {}", body);
        }
    }

    protected void logResponse(HttpResponse response, String body) {
        logger.debug("HTTP Response had a {} status code.", response.getStatusLine().getStatusCode());
        logger.debug("Reason: {}", response.getStatusLine().getReasonPhrase());
        if (logger.isDebugEnabled()) {
            logger.debug("Response headers: {}", Arrays.toString(response.getAllHeaders()));
            logger.debug("Response: {}", response);
            logger.debug("Response body: {}", body);
        }
    }

    protected Gson getGson() {
        Gson gson = new GsonBuilder().setDateFormat(DATE_TIME_FORMAT).create();
        TypeAdapter<Date> dateTypeAdapter = gson.getAdapter(Date.class);
        TypeAdapter<Date> safeDateTypeAdapter = dateTypeAdapter.nullSafe();
        return new GsonBuilder()
                .setDateFormat(DATE_TIME_FORMAT)
                .registerTypeAdapter(Date.class, safeDateTypeAdapter)
                .create();
    }

    //For avoid null checks
    protected <T> T nonNull(T obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }


}
