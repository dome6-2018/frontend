package com.ensicaen.ecole.ludistreet.task;

import android.os.AsyncTask;

import com.ensicaen.ecole.ludistreet.rest.LudiStreetRestClient;

/**
 * Created by Thibaud on 17/01/2018.
 */

public class WallsTask extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... strings) {
        LudiStreetRestClient ludiStreetRestClient = new LudiStreetRestClient();
        ludiStreetRestClient.walls(strings[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result){

    }
}
