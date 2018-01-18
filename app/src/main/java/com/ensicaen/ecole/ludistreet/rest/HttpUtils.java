package com.ensicaen.ecole.ludistreet.rest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Thibaud on 26/10/2017.
 * Source : http://loopj.com/android-async-http/
 */

public class HttpUtils {
    private static final String BASE_URL = "https://dome6.ensicaen.fr/api/v1/";

    private static String authorizationHeader = "Authorization";
    public static String token = null;

    private static SyncHttpClient client = new SyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if(token != null)
            client.addHeader(authorizationHeader, token);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if(token != null)
            client.addHeader(authorizationHeader, token);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, StringEntity entity, AsyncHttpResponseHandler responseHandler){
        if(token != null)
            client.addHeader(authorizationHeader, token);
        client.post(null, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    public static void getByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if(token != null)
            client.addHeader(authorizationHeader, token);
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postByUrl(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if(token != null)
            client.addHeader(authorizationHeader, token);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void patch(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        if(token != null)
            client.addHeader(authorizationHeader, token);
        client.patch(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void patch(String url, StringEntity entity, AsyncHttpResponseHandler responseHandler){
        if(token != null)
            client.addHeader(authorizationHeader, token);
        client.patch(null, getAbsoluteUrl(url), entity, "application/json", responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
