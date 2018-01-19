package com.ensicaen.ecole.ludistreet.rest;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.activities.ModeWallActivity;
import com.ensicaen.ecole.ludistreet.activities.SearchWallActivity;
import com.ensicaen.ecole.ludistreet.models.Wall;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Envoie des requêtes à l'API WALLS
 */
public class WallsRestClient {

    private Activity activity;

    public WallsRestClient(Activity activity) {
        this.activity = activity;
    }

    public void getWalls() {
        HttpClient.get("walls", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                Gson gson = new Gson();
                List<Wall> walls = gson.fromJson(res, new TypeToken<Wall>(){}.getType());

                for (Wall wall: walls) {
                    LatLng marker = new LatLng(wall.getLatitude(), wall.getLongitude());

                    ((SearchWallActivity) activity).getMap().addMarker(new MarkerOptions()
                            .title(wall.getName())
                            .position(marker));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(activity, "Erreur lors de la récupération des murs", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getWall(String uuid, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.get("walls/" + uuid, null, asyncHttpResponseHandler);
    }

    public void patchWallDrawing(String uuid, Wall wall, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        Gson gson = new Gson();
        String wallJson = gson.toJson(wall);
        StringEntity entity = null;

        try {
            entity = new StringEntity(wallJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpClient.patch("walls/" + uuid + "/drawing", entity, asyncHttpResponseHandler);
    }
}
