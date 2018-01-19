package com.ensicaen.ecole.ludistreet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.models.Login;
import com.ensicaen.ecole.ludistreet.rest.HttpClient;
import com.ensicaen.ecole.ludistreet.rest.SecurityRestClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

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

                // Envoie des données au serveur
                SecurityRestClient securityRestClient = new SecurityRestClient();
                securityRestClient.postLogin(loginModel, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        HttpClient.token = null;
                        for (Header header : headers) {
                            if ("Authorization".equals(header.getName())) {
                                HttpClient.token = header.getValue();
                            }
                        }

                        if (HttpClient.token == null) {
                            Toast.makeText(LogInActivity.this, "Erreur lors de la connexion", Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(LogInActivity.this, ChoiceWallActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        Toast.makeText(LogInActivity.this, "Erreur lors de la connexion", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        final ImageView registerButton = (ImageView) findViewById(R.id.sub_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
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
