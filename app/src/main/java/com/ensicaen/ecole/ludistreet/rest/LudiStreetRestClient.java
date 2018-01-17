package com.ensicaen.ecole.ludistreet.rest;

import com.ensicaen.ecole.ludistreet.model.LoginModel;
import com.ensicaen.ecole.ludistreet.model.UserModel;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Thibaud on 16/01/2018.
 */

public class LudiStreetRestClient {

    private String TAG = "LUDISTREET REST CLIENT";

    public LudiStreetRestClient(){}

    /**
     * Send login/password
     */
    public void login(LoginModel loginModel){

        Gson gson = new Gson();
        String loginGson = gson.toJson(loginModel);

        StringEntity entity = null;
        try {
           entity = new StringEntity(loginGson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpUtils.post("login", entity, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                if (statusCode == 200) {
                    System.out.println(res);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                System.out.println(res);
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
        });
    }

    public void logout(){

        RequestParams params = null;

        HttpUtils.post("logout", params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                System.out.println(res);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                System.out.println(res);
            }
        });
    }

    public void Register(UserModel user){

        Gson gson = new Gson();
        String loginGson = gson.toJson(user);

        StringEntity entity = null;
        try {
            entity = new StringEntity(loginGson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpUtils.post("register", entity, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                if (statusCode == 200) {
                    System.out.println(res);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                System.out.println(res);
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }
        });

    }

}
