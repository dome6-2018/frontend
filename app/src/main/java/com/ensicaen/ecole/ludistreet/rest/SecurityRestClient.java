package com.ensicaen.ecole.ludistreet.rest;

import com.ensicaen.ecole.ludistreet.models.Login;
import com.ensicaen.ecole.ludistreet.models.Register;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Envoie des requêtes à l'API SECURITY
 */
public class SecurityRestClient extends AbstractRestClient {

    public void postLogin(Login login, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.post("login", getEntity(login), asyncHttpResponseHandler);
    }

    public void postLogout(AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.post("logout", (RequestParams) null, asyncHttpResponseHandler);
    }

    public void postRegister(Register register, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.post("register", getEntity(register), asyncHttpResponseHandler);
    }
}
