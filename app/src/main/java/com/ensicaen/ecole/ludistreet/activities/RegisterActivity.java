package com.ensicaen.ecole.ludistreet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.models.Register;
import com.ensicaen.ecole.ludistreet.rest.SecurityRestClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView subButton = (ImageView) findViewById(R.id.sub_validate);

        subButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String login = ((EditText) findViewById(R.id.sub_id)).getText().toString();
                final String password = ((EditText) findViewById(R.id.sub_password)).getText().toString();
                final String passwordConfirm = ((EditText) findViewById(R.id.sub_confirm_password)).getText().toString();
                final String name = ((EditText) findViewById(R.id.sub_name)).getText().toString();
                final String surname = ((EditText) findViewById(R.id.sub_surname)).getText().toString();
                final String email = ((EditText) findViewById(R.id.sub_email)).getText().toString();

                if (password.equals(passwordConfirm)) {
                    Register register = new Register(email, name, surname, password, login);

                    SecurityRestClient securityRestClient = new SecurityRestClient();
                    securityRestClient.postRegister(register, new TextHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String res) {
                            Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                            Toast.makeText(RegisterActivity.this,
                                    "Erreur lors de l'inscription", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "Les mots de passe sont differents !", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
