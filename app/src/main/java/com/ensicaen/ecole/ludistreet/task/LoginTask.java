package com.ensicaen.ecole.ludistreet.task;

import android.os.AsyncTask;

import com.ensicaen.ecole.ludistreet.model.LoginModel;
import com.ensicaen.ecole.ludistreet.rest.LudiStreetRestClient;

import java.io.UnsupportedEncodingException;

/**
 * Created by Thibaud on 17/01/2018.
 */

public class LoginTask extends AsyncTask<LoginModel, Void , Boolean> {


    @Override
    protected Boolean doInBackground(LoginModel... loginModels) {
        try{
            LudiStreetRestClient ludiStreetRestClient = new LudiStreetRestClient();
            ludiStreetRestClient.login(loginModels[0]);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result){
        // Use for the redirection
    }
}
