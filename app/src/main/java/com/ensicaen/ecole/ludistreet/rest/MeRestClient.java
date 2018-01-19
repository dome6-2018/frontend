package com.ensicaen.ecole.ludistreet.rest;

import android.app.Activity;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.models.User;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Envoie des requêtes à l'API ME
 */
public class MeRestClient {

    private Activity activity;

    public MeRestClient(Activity activity) {
        this.activity = activity;
    }

    public void getMe() {
        HttpClient.get("me", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                // TODO
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(activity, "Erreur lors de la récupération du profil", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void patchMe(User user) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        StringEntity entity = null;

        try {
            entity = new StringEntity(userJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpClient.patch("me", entity, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                System.out.println(res);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                Toast.makeText(activity, "Erreur lors de la  du profil", Toast.LENGTH_LONG).show();
            }
        });
    }
}
