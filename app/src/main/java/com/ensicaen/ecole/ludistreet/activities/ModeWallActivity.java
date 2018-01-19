package com.ensicaen.ecole.ludistreet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.ar.ARImageView;
import com.ensicaen.ecole.ludistreet.ar.ARView;
import com.ensicaen.ecole.ludistreet.models.TicTacToe;
import com.ensicaen.ecole.ludistreet.models.Wall;
import com.ensicaen.ecole.ludistreet.rest.WallsRestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;

import cz.msebera.android.httpclient.Header;
import util.Log;

public class ModeWallActivity extends AppCompatActivity {

    String wallUuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_wall);

        wallUuid = getIntent().getStringExtra("WALL_UUID");
        ImageView paint = (ImageView)findViewById(R.id.mode_paint);
        ImageView game = (ImageView)findViewById(R.id.mode_game);

        paint.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Récupération des données du mur
                WallsRestClient wallsRestClient = new WallsRestClient();
                wallsRestClient.getWall(wallUuid, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        Gson gson = new Gson();
                        Wall wall = gson.fromJson(res, new TypeToken<Wall>(){}.getType());

                        // Chargement de l'image initiale
                        if (wall.getDrawingArray() == null || wall.getDrawingArray().length == 0) {
                            wall.initBlankDrawing();
                        }

                        // Chargement de l'image en base de données
                        if (wall.getDrawing() != null) {
                            try {
                                wall.loadDrawingFromString();
                            } catch (JSONException e) {
                                Toast.makeText(ModeWallActivity.this,
                                        "Erreur lors du chargement du mur", Toast.LENGTH_LONG).show();
                            }
                        }

                        ARView arv = new ARView(wall);
                        ArActivity.startWithSetup(ModeWallActivity.this, arv.getSetup());
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        Toast.makeText(ModeWallActivity.this,
                                "Erreur lors de la récupération du mur", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO : Travailler le mode jeu

                /*TicTacToe wall = new TicTacToe();
                ARView arv = new ARView(wall);
                ArActivity.startWithSetup(ModeWallActivity.this, arv.getSetup());*/
                ARImageView ari = new ARImageView(ModeWallActivity.this, R.drawable.logo_image);
                ArActivity.startWithSetup(ModeWallActivity.this, ari.getSetup());
            }
        });
    }
}
