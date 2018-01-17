package com.ensicaen.ecole.ludistreet;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import gl.Color;
import gl.GL1Renderer;
import gl.GLFactory;
import gl.scenegraph.Shape;
import system.ArActivity;
import system.DefaultARSetup;
import util.Vec;
import worldData.World;

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
                        Shape s2 = objectFactory.newSquare(c);
                        ArrayList<Shape> formes = new ArrayList();
                        formes.add(objectFactory.newSquare(new Color("blue")));
                        formes.add(objectFactory.newSquare(new Color("green")));
                        formes.add(objectFactory.newSquare(new Color("yellow")));
                        formes.add(objectFactory.newSquare(new Color("red")));

                        formes.add(objectFactory.newSquare(new Color("black")));
                        formes.add(objectFactory.newSquare(new Color("white")));
                        formes.add(objectFactory.newSquare(new Color("blue")));
                        formes.add(objectFactory.newSquare(new Color("green")));

                        formes.add(objectFactory.newSquare(new Color("blue")));
                        formes.add(objectFactory.newSquare(new Color("green")));
                        formes.add(objectFactory.newSquare(new Color("yellow")));
                        formes.add(objectFactory.newSquare(new Color("red")));

                        for(int i = 0; i < 4; i++){
                            formes.get(i).setRotation(new Vec(0,90,0));
                            formes.get(i).setPosition(new Vec(0,2*i,0));
                            world.add(formes.get(i));
                        }

                        for(int i = 4; i < 8; i++){
                            formes.get(i).setRotation(new Vec(0,90,0));
                            formes.get(i).setPosition(new Vec(0,2*(i-4),2));
                            world.add(formes.get(i));
                        }

                        Vec posObjet = s.getPosition();

                        world.setMyScreenPosition(new Vec(0,2,-15));
                        //world.add(s2);
                        //world.getMyCamera().changeNewPosition(1,1,1);
                    }
                    });
                }
            });
            setContentView(b);
    }
}
