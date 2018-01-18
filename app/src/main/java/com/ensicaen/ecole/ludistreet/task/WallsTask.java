package com.ensicaen.ecole.ludistreet.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.ensicaen.ecole.ludistreet.ModeActivity;
import com.ensicaen.ecole.ludistreet.SelectWallActivity;
import com.ensicaen.ecole.ludistreet.rest.LudiStreetRestClient;

/**
 * Created by Thibaud on 17/01/2018.
 */

public class WallsTask extends AsyncTask<String, Void, Boolean> {
    private Activity _activity;

    public WallsTask(Activity activity){
        _activity = activity;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        LudiStreetRestClient ludiStreetRestClient = new LudiStreetRestClient();
        ludiStreetRestClient.walls(strings[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result){
        Intent intent = new Intent(_activity, ModeActivity.class);
        _activity.startActivity(intent);
    }
}
