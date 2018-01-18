package com.ensicaen.ecole.ludistreet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        ImageView where = (ImageView)findViewById(R.id.where_to_go);
        ImageView here = (ImageView)findViewById(R.id.i_am_here);

        where.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, SearchWallActivity.class);
                ChoiceActivity.this.startActivity(intent);
            }
        });

        here.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this, SelectWallActivity.class);
                ChoiceActivity.this.startActivity(intent);
            }
        });
    }
}
