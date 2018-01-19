package com.ensicaen.ecole.ludistreet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ensicaen.ecole.ludistreet.R;

public class ChoiceWallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_wall);

        ImageView whereToGo = (ImageView)findViewById(R.id.where_to_go);
        ImageView iAmHere = (ImageView)findViewById(R.id.i_am_here);

        whereToGo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceWallActivity.this, SearchWallActivity.class);
                ChoiceWallActivity.this.startActivity(intent);
            }
        });

        iAmHere.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceWallActivity.this, ScanWallActivity.class);
                ChoiceWallActivity.this.startActivity(intent);
            }
        });
    }
}
