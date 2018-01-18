package com.ensicaen.ecole.ludistreet.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.LogInActivity;
import com.ensicaen.ecole.ludistreet.RA.ARView;
import com.ensicaen.ecole.ludistreet.model.LoginModel;
import com.ensicaen.ecole.ludistreet.model.WallModel;
import com.ensicaen.ecole.ludistreet.rest.LudiStreetRestClient;

import java.io.UnsupportedEncodingException;

import system.ArActivity;
import system.DefaultARSetup;

/**
 * Created by Thibaud on 17/01/2018.
 */

public class LoginTask extends AsyncTask<LoginModel, Void , Boolean> {

    private Activity _activity;

    public LoginTask(LogInActivity activity){
        _activity = activity;
    }

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
        if(result) {
            WallModel w = new WallModel(2,2);
            ARView arv = new ARView(w);
            DefaultARSetup ar = arv.getSetup();
            ArActivity.startWithSetup(_activity, ar);
            return;
        }
        Toast.makeText(_activity, "Erreur lors de la connexion",
                Toast.LENGTH_LONG).show();

    }
}
