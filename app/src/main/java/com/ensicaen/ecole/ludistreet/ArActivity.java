package com.ensicaen.ecole.ludistreet;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.ensicaen.ecole.ludistreet.beacon.ThreadBeacon;

import com.estimote.coresdk.observation.region.RegionUtils;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;
import java.util.UUID;

import system.Setup;
import util.Log;

public class ArActivity extends Activity {

    private static final String LOG_TAG = "ArActivity";

    private static Setup staticSetupHolder;

    private Setup mySetupToUse;
    private ThreadBeacon threadBeacon;
    private BeaconManager beaconManager;
    private BeaconRegion regionR, regionJ, regionB;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        threadBeacon = new ThreadBeacon();
        threadBeacon.start();

        /****************************************************/

        beaconManager = new BeaconManager(this);

        regionR = new BeaconRegion("ranged region1",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 121, 6227);
        regionJ = new BeaconRegion("ranged region2",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 44321, 38148);
        regionB = new BeaconRegion("ranged region3",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), 43021, 46729);


        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(regionJ);
                beaconManager.startRanging(regionB);
                beaconManager.startRanging(regionR);
            }
        });


        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    double distanceR, distanceJ, distanceB;
                    for(Beacon b : list){
                        if(b.getMajor() == 121){
                            distanceR = RegionUtils.computeAccuracy(b);
                            threadBeacon.setDistanceR(distanceR);
                        }else if (b.getMajor() == 44321){
                            distanceJ = RegionUtils.computeAccuracy(b);
                            threadBeacon.setDistanceJ(distanceJ);
                        }else if (b.getMajor() == 43021){
                            distanceB = RegionUtils.computeAccuracy(b);
                            threadBeacon.setDistanceB(distanceB);
                        }
                    }
                }
                else{
                    Log.d("EMPTY","EMPTY");
                }
            }
        });

        /****************************************************/


        Log.d(LOG_TAG, "main onCreate");
        if (staticSetupHolder != null) {
            mySetupToUse = staticSetupHolder;
            staticSetupHolder = null;
            runSetup();
        } else {
            Log.e(LOG_TAG, "There was no Setup specified to use. "
                    + "Please use ArActivity.show(..) when you "
                    + "want to use this way of starting the AR-view!");
            this.finish();
        }
    }

    public static void startWithSetup(Activity currentActivity, Setup setupToUse) {
        staticSetupHolder = setupToUse;
        currentActivity.startActivity(new Intent(currentActivity,
                ArActivity.class));
    }

    public static void startWithSetupForResult(Activity currentActivity,
                                               Setup setupToUse, int requestCode) {
        staticSetupHolder = setupToUse;
        currentActivity.startActivityForResult(new Intent(currentActivity,
                ArActivity.class), requestCode);
    }

    private void runSetup() {
        threadBeacon.setSetup(mySetupToUse);
        mySetupToUse.run(this);
    }

    @Override
    protected void onRestart() {
        if (mySetupToUse != null)
            mySetupToUse.onRestart(this);
        super.onRestart();
    }

    @Override
    protected void onResume() {
        if (mySetupToUse != null)
            mySetupToUse.onResume(this);
        super.onResume();
    }

    @Override
    protected void onStart() {
        if (mySetupToUse != null)
            mySetupToUse.onStart(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (mySetupToUse != null)
            mySetupToUse.onStop(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mySetupToUse != null)
            mySetupToUse.onDestroy(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mySetupToUse != null)
            mySetupToUse.onPause(this);
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((mySetupToUse != null)
                && (mySetupToUse.onKeyDown(this, keyCode, event)))
            return true;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (((mySetupToUse != null) && mySetupToUse.onCreateOptionsMenu(menu)))
            return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (mySetupToUse != null)
        return mySetupToUse.onMenuItemSelected(featureId, item);
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(LOG_TAG, "main onConfigChanged");
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            Log.d(LOG_TAG, "orientation changed to landscape");
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            Log.d(LOG_TAG, "orientation changed to portrait");
        super.onConfigurationChanged(newConfig);
    }



}