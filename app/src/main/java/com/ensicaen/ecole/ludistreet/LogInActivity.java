package com.ensicaen.ecole.ludistreet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ImageView connectButton = (ImageView) findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("connected", true);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        final ImageView subscribeButton = (ImageView) findViewById(R.id.sub_button);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SubscribeActivity.class);
                startActivity(intent);
            }
        });
    }

}
