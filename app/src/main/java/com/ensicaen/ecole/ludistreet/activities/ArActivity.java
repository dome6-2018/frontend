package com.ensicaen.ecole.ludistreet.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.ar.ThreadBeacon;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;

import system.Setup;
import util.Log;

public class ArActivity extends Activity {

    private static final String LOG_TAG = "ArActivity";

    private static Setup staticSetupHolder;

    private Setup mySetupToUse;
    private ThreadBeacon threadBeacon;
    private BeaconManager beaconManager;
    private List<BeaconRegion> regions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(LOG_TAG, "main onCreate");
        if (staticSetupHolder != null) {
            mySetupToUse = staticSetupHolder;
            staticSetupHolder = null;
            runSetup();

            Toolbar toolbar = new Toolbar(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 168);
            toolbar.setLayoutParams(layoutParams);
            toolbar.setPopupTheme(R.style.AppTheme);
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            toolbar.setTitle("This is the title");
            toolbar.setVisibility(View.VISIBLE);

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
        /****************************************************/
        /*
        if (mySetupToUse instanceof WallSetup) {
            threadBeacon.setSetup(mySetupToUse);
            threadBeacon = new ThreadBeacon();
            threadBeacon.start();

            beaconManager = new BeaconManager(this);
            beaconManager.setForegroundScanPeriod(10, 0);

            List<BeaconModel> beacons = ((WallSetup) mySetupToUse).getModel().getBeacons();


            for (BeaconModel i : beacons) {
                regions.add(new BeaconRegion("region" + i.getMinor(),
                        UUID.fromString(i.getUUID()), i.getMajor(), i.getMinor()));
            }

            beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
                @Override
                public void onServiceReady() {
                    for (BeaconRegion region : regions) {
                        beaconManager.startRanging(region);
                    }
                }
            });


            beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
                @Override
                public void onBeaconsDiscovered(BeaconRegion region, List<Beacon> list) {
                    if (!list.isEmpty()) {
                        ArrayList<Double> distances = new ArrayList<>();

                        for (Beacon b : list) {
                            distances.add(RegionUtils.computeAccuracy(b));
                        }

                        threadBeacon.setDistances(distances);
                    } else {
                        Log.d("EMPTY", "EMPTY");
                    }
                }
            });
        }
        */
        /****************************************************/

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