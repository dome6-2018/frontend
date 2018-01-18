package com.ensicaen.ecole.ludistreet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ensicaen.ecole.ludistreet.RA.ARView;
import com.ensicaen.ecole.ludistreet.model.WallModel;

import system.ArActivity;

public class ModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        ImageView paint = (ImageView)findViewById(R.id.mode_paint);
        ImageView game = (ImageView)findViewById(R.id.mode_game);
        paint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 WallModel w = new WallModel(2,2);
                 ARView arv = new ARView(w);
                 ArActivity.startWithSetup(ModeActivity.this, arv.getSetup());
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WallModel w = new WallModel(2,2);
                ARView arv = new ARView(w);
                ArActivity.startWithSetup(ModeActivity.this, arv.getSetup());
            }
        });
    }
}
