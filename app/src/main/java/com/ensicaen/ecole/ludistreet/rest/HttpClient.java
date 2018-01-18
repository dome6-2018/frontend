package com.ensicaen.ecole.ludistreet.rest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Gestion des appels HTTP
 */
public class HttpClient {
    private static final String BASE_URL = "https://dome6.ensicaen.fr/api/v1/";

    private static String authorizationHeader = "Authorization";
    public static String token = null;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        addToken();
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        addToken();
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, StringEntity entity, AsyncHttpResponseHandler responseHandler){
        addToken();
        client.post(null, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    public static void put(String url, StringEntity entity, AsyncHttpResponseHandler responseHandler){
        addToken();
        client.put(null, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    public static void patch(String url, StringEntity entity, AsyncHttpResponseHandler responseHandler){
        addToken();
        client.patch(null, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    public static void delete(String url, StringEntity entity, AsyncHttpResponseHandler responseHandler){
        addToken();
        client.delete(null, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static void addToken() {
        if (token != null) {
            client.addHeader(authorizationHeader, token);
        }
    }
}
