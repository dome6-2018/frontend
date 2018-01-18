package com.ensicaen.ecole.ludistreet.task;

import android.os.AsyncTask;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.RA.ARView;
import com.ensicaen.ecole.ludistreet.SubscribeActivity;
import com.ensicaen.ecole.ludistreet.model.RegisterModel;
import com.ensicaen.ecole.ludistreet.model.WallModel;
import com.ensicaen.ecole.ludistreet.rest.LudiStreetRestClient;

import java.io.UnsupportedEncodingException;

import system.ArActivity;
import system.DefaultARSetup;

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
            ludiStreetRestClient.register(registerModels[0]);
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
