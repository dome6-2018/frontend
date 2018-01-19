package com.ensicaen.ecole.ludistreet.activities;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.models.Wall;
import com.ensicaen.ecole.ludistreet.rest.WallsRestClient;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SearchWallActivity extends FragmentActivity implements OnMapReadyCallback {

    private LocationManager locationManager;
    private GoogleMap map;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ensicaen.ecole.ludistreet.R.layout.activity_search_wall);
        context = this;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true);

        // Centrage de la carte en fonction des coordonnées GPS
        /*LocationListener locationListenerGPS = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                double latitude=location.getLatitude();
                double longitude=location.getLongitude();
                String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                2000,
                10, locationListenerGPS);*/

        // Mise à jour des marqueurs sur la carte
        WallsRestClient wallsRestClient = new WallsRestClient();
        wallsRestClient.getWalls(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                Gson gson = new Gson();
                List<Wall> walls = gson.fromJson(res, new TypeToken<Wall>(){}.getType());

                for (Wall wall: walls) {
                    LatLng marker = new LatLng(wall.getLatitude(), wall.getLongitude());

                    getMap().addMarker(new MarkerOptions()
                            .title(wall.getName())
                            .position(marker));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                Toast.makeText(SearchWallActivity.this, "Erreur lors de la récupération des murs", Toast.LENGTH_LONG).show();
            }
        });
    }

    public GoogleMap getMap() {
        return map;
    }

    public void setMap(GoogleMap map) {
        this.map = map;
    }
}