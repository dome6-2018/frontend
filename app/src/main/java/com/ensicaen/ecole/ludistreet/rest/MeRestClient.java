package com.ensicaen.ecole.ludistreet.rest;

import android.app.Activity;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.models.User;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Envoie des requêtes à l'API ME
 */
public class MeRestClient extends AbstractRestClient {

    public void getMe(AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.get("me", null, asyncHttpResponseHandler);
    }

    public void patchMe(User user, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.patch("me", getEntity(user), asyncHttpResponseHandler);
    }
}
