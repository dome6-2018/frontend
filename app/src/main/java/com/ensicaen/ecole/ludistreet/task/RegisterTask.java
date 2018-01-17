package com.ensicaen.ecole.ludistreet.task;

import android.os.AsyncTask;

import com.ensicaen.ecole.ludistreet.model.RegisterModel;
import com.ensicaen.ecole.ludistreet.rest.LudiStreetRestClient;

import java.io.UnsupportedEncodingException;

/**
 * Created by Thibaud on 17/01/2018.
 */

public class RegisterTask extends AsyncTask<RegisterModel, Void, Boolean> {
    @Override
    protected Boolean doInBackground(RegisterModel... registerModels) {
        try {
            LudiStreetRestClient ludiStreetRestClient = new LudiStreetRestClient();
            ludiStreetRestClient.Register(registerModels[0]);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result){

    }
}
