package com.ensicaen.ecole.ludistreet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.ar.ARView;
import com.ensicaen.ecole.ludistreet.models.Wall;

public class ModeWallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_wall);

        ImageView paint = (ImageView)findViewById(R.id.mode_paint);
        ImageView game = (ImageView)findViewById(R.id.mode_game);
        paint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 Wall w = new Wall(2,2);
                 ARView arv = new ARView(w);
                 ArActivity.startWithSetup(ModeWallActivity.this, arv.getSetup());
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Wall w = new Wall(2,2);
                ARView arv = new ARView(w);
                ArActivity.startWithSetup(ModeWallActivity.this, arv.getSetup());
            }
        });
    }
}
