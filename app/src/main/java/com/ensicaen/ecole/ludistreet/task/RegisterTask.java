package com.ensicaen.ecole.ludistreet.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.LogInActivity;
import com.ensicaen.ecole.ludistreet.SubscribeActivity;
import com.ensicaen.ecole.ludistreet.model.RegisterModel;
import com.ensicaen.ecole.ludistreet.rest.LudiStreetRestClient;

import java.io.UnsupportedEncodingException;


/**
 * Created by Thibaud on 17/01/2018.
 */

public class RegisterTask extends AsyncTask<RegisterModel, Void, Boolean> {

    private SubscribeActivity _activity;

    public RegisterTask(SubscribeActivity activity) {
        _activity = activity;
    }

    @Override
    protected Boolean doInBackground(RegisterModel... registerModels) {
        try {
            LudiStreetRestClient ludiStreetRestClient = new LudiStreetRestClient();
            ludiStreetRestClient.postRegister(registerModels[0]);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result){
        if(result) {
            Intent intent = new Intent(_activity, LogInActivity.class);
            _activity.startActivity(intent);
//            WallModel w = new WallModel(2,2);
//            ARView arv = new ARView(w);
//            ArActivity.startWithSetup(_activity, arv.getSetup());
            return;
        }
        Toast.makeText(_activity, "Erreur lors de la connexion",
                Toast.LENGTH_LONG).show();
    }
}
