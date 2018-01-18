package com.ensicaen.ecole.ludistreet.rest;

import android.app.Activity;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

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
}
