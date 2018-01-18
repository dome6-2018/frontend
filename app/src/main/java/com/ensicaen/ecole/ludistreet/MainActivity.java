package com.ensicaen.ecole.ludistreet;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ensicaen.ecole.ludistreet.model.LoginModel;

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
                // bordel c'est vraiment de la m***** la gestion des droits maintenant
            } else {
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginModel loginModel = new LoginModel("thibaudsenalada", "dome6");
        //new LoginTask().execute(loginModel);
        //RegisterModel registerModel = new RegisterModel("JBG@gmail.com","JB", "Gomond", "toto","JBG");
        //new RegisterTask().execute(registerModel);

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
                        Vec posObjet = s.getPosition();
                        //GLCamera c = new GLCamera();

                        world.setMyScreenPosition(new Vec(0,2,-4));

                        world.add(s);
                    }
                    });
                }
            });
            setContentView(b);
    }
}
