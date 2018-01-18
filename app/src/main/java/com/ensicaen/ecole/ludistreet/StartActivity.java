package com.ensicaen.ecole.ludistreet;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    private void checkPermission(){
        String[] array= new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this,array, 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();

        if( ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }
        }

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
