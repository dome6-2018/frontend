package com.ensicaen.ecole.ludistreet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.models.Login;
import com.ensicaen.ecole.ludistreet.rest.HttpClient;
import com.ensicaen.ecole.ludistreet.rest.SecurityRestClient;

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
                Login loginModel = new Login(login, password);

                // Envoie des donnees au serveur
                SecurityRestClient securityRestClient = new SecurityRestClient(LogInActivity.this);
                securityRestClient.postLogin(loginModel);
            }
        });

        final ImageView subscribeButton = (ImageView) findViewById(R.id.sub_button);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                LogInActivity.this.startActivity(intent);
                LogInActivity.this.finish();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (HttpClient.token != null){
            Intent intent = new Intent(this, StartActivity.class);
            this.startActivity(intent);
        }
    }

}
