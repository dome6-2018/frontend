package com.ensicaen.ecole.ludistreet;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ensicaen.ecole.ludistreet.RA.ARView;
import com.ensicaen.ecole.ludistreet.model.WallModel;

import system.ArActivity;
import system.DefaultARSetup;
import util.Vec;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MAIN ACTIVITY";


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if( ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }
        }
        /*
        DefaultARSetup ar2 = new DefaultARSetup() {
            @Override
            public void addObjectsTo(GL1Renderer renderer, World world, GLFactory objectFactory) {
                Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.accueil);
                MeshComponent s = objectFactory.newTexturedSquare("accueil.bmp", b);
                s.setPosition(new Vec(0,0,2));
                s.setRotation(new Vec(0,0,180));
                s.setScale(new Vec(4,4,4));
                world.add(s);

                world.setMyScreenPosition(new Vec(0,2,-15));
            }
        };
        ArActivity.startWithSetup(MainActivity.this, ar2);
        */


        WallModel wall = new WallModel(10,10);
        ARView view = new ARView(wall);
        DefaultARSetup ar = view.getSetup();
        ArActivity.startWithSetup(MainActivity.this, ar);
    }
}
