package com.ensicaen.ecole.ludistreet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    static final int CONNECT_REQUEST = 1;
    private String TAG = "START ACTIVITY";

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
        //Remove title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start);
        final ImageView bottomImage = (ImageView) findViewById(R.id.click_to_start);

        bottomImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, LogInActivity.class);
                startActivityForResult(intent, CONNECT_REQUEST);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (CONNECT_REQUEST) : {
                if (resultCode == Activity.RESULT_OK && data.getBooleanExtra("connected", false)) {

                    final ImageView bottomImage = (ImageView) findViewById(R.id.click_to_start);
                    bottomImage.setImageResource(R.drawable.click_to_start);
                }
                break;
            }
        }
    }
}
