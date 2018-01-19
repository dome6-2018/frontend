package com.ensicaen.ecole.ludistreet.rest;

import com.ensicaen.ecole.ludistreet.models.Wall;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Envoie des requêtes à l'API WALLS
 */
public class WallsRestClient extends AbstractRestClient {

    public void getWalls(AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.get("walls", null, asyncHttpResponseHandler);
    }

    public void getWall(String uuid, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.get("walls/" + uuid, null, asyncHttpResponseHandler);
    }

    public void patchWallDrawing(String uuid, Wall wall, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        HttpClient.patch("walls/" + uuid + "/drawing", getEntity(wall), asyncHttpResponseHandler);
    }
}
