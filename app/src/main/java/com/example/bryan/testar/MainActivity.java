package com.example.bryan.testar;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.microedition.khronos.opengles.GL;

import gl.Color;
import gl.GL1Renderer;
import gl.GLCamera;
import gl.GLFactory;
import gl.scenegraph.Shape;
import system.ArActivity;
import system.DefaultARSetup;
import system.Setup;
import util.Vec;
import worldData.Entity;
import worldData.RenderableEntity;
import worldData.World;


public class MainActivity extends AppCompatActivity {






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // bordel c'est vraiment de la m***** la gestion des droits maintenant
            } else {
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button b = new Button(this);

        if( ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }
        }




        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ArActivity.startWithSetup(MainActivity.this, new DefaultARSetup() {
                    @Override
                    public void addObjectsTo(GL1Renderer renderer, World world, GLFactory objectFactory) {
                        Color c = new Color("red");
                        Shape s = objectFactory.newPyramid(new Vec(0,0,0), 2, c);
                        Location l = world.getMyCamera().getGPSLocation();
                        Vec posObjet = s.getPosition();
                        //GLCamera c = new GLCamera();

                        Context context = getApplicationContext();
                        CharSequence text = "Position de l'objet : " + posObjet.toString();
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        world.setMyScreenPosition(new Vec(0,2,-4));
                        world.add(s);
                    }
                    });
                }
            });
            setContentView(b);



    }
}
