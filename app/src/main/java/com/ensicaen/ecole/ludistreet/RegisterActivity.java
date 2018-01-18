package com.ensicaen.ecole.ludistreet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.model.Register;
import com.ensicaen.ecole.ludistreet.rest.SecurityRestClient;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

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

                    SecurityRestClient securityRestClient = new SecurityRestClient(RegisterActivity.this);
                    securityRestClient.postRegister(register);
                } else {
                    Toast.makeText(RegisterActivity.this, "Les mots de passe sont differents !", Toast.LENGTH_LONG);
                }
            }
        });

    }

}