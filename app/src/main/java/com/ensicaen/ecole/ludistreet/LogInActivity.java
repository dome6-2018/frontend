package com.ensicaen.ecole.ludistreet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ensicaen.ecole.ludistreet.model.LoginModel;
import com.ensicaen.ecole.ludistreet.task.LoginTask;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ImageView connectButton = (ImageView) findViewById(R.id.connect_button);

        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String login = ((EditText) findViewById(R.id.loginEdit)).getText().toString();
                final String password = ((EditText) findViewById(R.id.passEdit)).getText().toString();
                LoginModel loginModel = new LoginModel(login, password);
                new LoginTask(LogInActivity.this).execute(loginModel);
            }
        });

        final ImageView subscribeButton = (ImageView) findViewById(R.id.sub_button);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(LogInActivity.this, SubscribeActivity.class);
                LogInActivity.this.startActivity(intent);
            }
        });
    }

}
