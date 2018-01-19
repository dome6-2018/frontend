package com.ensicaen.ecole.ludistreet.rest;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.activities.ChoiceWallActivity;
import com.ensicaen.ecole.ludistreet.activities.LogInActivity;
import com.ensicaen.ecole.ludistreet.models.Login;
import com.ensicaen.ecole.ludistreet.models.Register;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Envoie des requêtes à l'API SECURITY
 */
public class SecurityRestClient {

    private Activity activity;

    public SecurityRestClient(Activity activity) {
        this.activity = activity;
    }

    public void postLogin(Login login) {
        Gson gson = new Gson();
        String loginJson = gson.toJson(login);
        StringEntity entity = null;

        try {
            entity = new StringEntity(loginJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpClient.post("login", entity, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                for (Header header : headers) {
                    if ("Authorization".equals(header.getName())) {
                        HttpClient.token = header.getValue();
                    }
                }

                Intent intent = new Intent(activity, ChoiceWallActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(activity, "Erreur lors de la connexion", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void postLogout() {
        RequestParams params = null;

        HttpClient.post("logout", params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                HttpClient.token = null;
                System.out.println(res);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(activity, "Erreur lors de la deconnexion", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void postRegister(Register user) {
        Gson gson = new Gson();
        String loginJson = gson.toJson(user);
        StringEntity entity = null;

        try {
            entity = new StringEntity(loginJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpClient.post("register", entity, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                Intent intent = new Intent(activity, LogInActivity.class);
                activity.startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(activity, "Erreur lors de l'inscription", Toast.LENGTH_LONG).show();
            }
        });
    }
}
