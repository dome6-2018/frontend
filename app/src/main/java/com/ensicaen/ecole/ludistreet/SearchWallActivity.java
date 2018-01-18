package com.ensicaen.ecole.ludistreet;

import com.ensicaen.ecole.ludistreet.rest.WallsRestClient;
import com.google.android.gms.maps.*;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SearchWallActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_wall);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);

        WallsRestClient wallsRestClient = new WallsRestClient(SearchWallActivity.this);
        wallsRestClient.getWalls();
    }

    public GoogleMap getMap() {
        return map;
    }

    public void setMap(GoogleMap map) {
        this.map = map;
    }
}