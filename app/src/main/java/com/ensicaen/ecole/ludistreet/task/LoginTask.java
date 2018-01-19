package com.ensicaen.ecole.ludistreet.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.ChoiceActivity;
import com.ensicaen.ecole.ludistreet.ModeActivity;
import com.ensicaen.ecole.ludistreet.RA.ARView;
import com.ensicaen.ecole.ludistreet.SelectWallActivity;
import com.ensicaen.ecole.ludistreet.model.LoginModel;
import com.ensicaen.ecole.ludistreet.model.WallModel;
import com.ensicaen.ecole.ludistreet.rest.LudiStreetRestClient;

import java.io.UnsupportedEncodingException;


/**
 * Created by Thibaud on 17/01/2018.
 */

public class LoginTask extends AsyncTask<LoginModel, Void , Boolean> {

    private Activity _activity;

    public LoginTask(Activity activity){
        _activity = activity;
    }

    @Override
    protected Boolean doInBackground(LoginModel... loginModels) {
        try{
            LudiStreetRestClient ludiStreetRestClient = new LudiStreetRestClient();
            ludiStreetRestClient.postLogin(loginModels[0]);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result){
        if(result) {

            Intent intent = new Intent(_activity, ChoiceActivity.class);
            _activity.startActivity(intent);
            return;
        }
        Toast.makeText(_activity, "Erreur lors de la connexion",
                Toast.LENGTH_LONG).show();

    }
}