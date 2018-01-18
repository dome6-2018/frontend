package com.ensicaen.ecole.ludistreet.rest;

import com.ensicaen.ecole.ludistreet.model.LoginModel;
import com.ensicaen.ecole.ludistreet.model.RegisterModel;
import com.ensicaen.ecole.ludistreet.model.WallModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    private WallModel wallModel;

    public LudiStreetRestClient(){}

    /**
     * Send postLogin/password
     */
    public void postLogin(LoginModel loginModel) throws UnsupportedEncodingException {

        Gson gson = new Gson();
        String loginGson = gson.toJson(loginModel);

        StringEntity entity = new StringEntity(loginGson);

        HttpUtils.post("postLogin", entity, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                if (statusCode == 200) {
                    for(Header header : headers)
                    {
                        if("Authorization".equals(header.getName())){
                            HttpUtils.token = header.getValue();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {

                System.out.println(res);
            }
        });
    }

    public void postLogout(){

        RequestParams params = null;

        HttpUtils.post("postLogout", params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                HttpUtils.token = null;
                System.out.println(res);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                System.out.println(res);
            }
        });
    }

    public void postRegister(RegisterModel user) throws UnsupportedEncodingException {

        Gson gson = new Gson();
        String loginGson = gson.toJson(user);

        StringEntity entity = null;

        entity = new StringEntity(loginGson);

        HttpUtils.post("postRegister", entity, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                if (statusCode == 200) {
                    System.out.println(res);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                System.out.println("toto" + t.toString());
                // called when response HTTP status is "4XX" (eg.

            }
        });

    }

    public void getWalls(String uuid){
        RequestParams params = null;
        final Gson gson = new Gson();

        HttpUtils.get("getWalls/"+uuid, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                wallModel = gson.fromJson(res, new TypeToken<WallModel>(){}.getType());
                System.out.println(res);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                wallModel = null;
                System.out.println(res);
            }
        });
    }

    public void patchDrawing(String uuid, WallModel wallModel) throws UnsupportedEncodingException {

        Gson gson = new Gson();
        String wallGson = gson.toJson(wallModel);

        StringEntity entity = new StringEntity(wallGson);

        HttpUtils.patch("getWalls/" + uuid + "/patchDrawing", entity, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                System.out.println(res);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                System.out.println(res);
            }
        });
    }

    public void getMe(){
        
    }
}
