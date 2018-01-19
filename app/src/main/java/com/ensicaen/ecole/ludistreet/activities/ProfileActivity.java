package com.ensicaen.ecole.ludistreet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.models.User;
import com.ensicaen.ecole.ludistreet.rest.MeRestClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        User userModel = new User(1, "Pierre", "Pierre", "Fleury", "pierre@fleury.fr", 1);

        id = userModel.getId();

        ((EditText) findViewById(R.id.loginEdit)).setText(userModel.getUsername());
        ((EditText) findViewById(R.id.textView_point)).setText(userModel.getPoint());

        ((EditText) findViewById(R.id.textView_lastname)).setText(userModel.getFirstname());
        ((EditText) findViewById(R.id.textView_firstname)).setText(userModel.getLastname());
        ((EditText) findViewById(R.id.textView_mail)).setText(userModel.getEmail());

        final ImageView save_button = ( (ImageView)findViewById(R.id.save_button));

        save_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int id = Integer.parseInt(((EditText) findViewById(R.id.textView_id)).getText().toString());
                final String username = ((EditText) findViewById(R.id.loginEdit)).getText().toString();
                final String lastname = ((EditText) findViewById(R.id.textView_lastname)).getText().toString();
                final String firstname = ((EditText) findViewById(R.id.textView_firstname)).getText().toString();
                final String password = ((EditText) findViewById(R.id.textView_password)).getText().toString();
                final String passwordConfirm = ((EditText) findViewById(R.id.textView_password_confirm)).getText().toString();
                final String email = ((EditText) findViewById(R.id.textView_mail)).getText().toString();
                final int score = Integer.parseInt(((EditText) findViewById(R.id.textView_point)).getText().toString());

                if (password.equals(passwordConfirm)) {
                    User user = new User(id, username, lastname, firstname, email, score);

                    MeRestClient meRestClient = new MeRestClient();
                    meRestClient.patchMe(user, new TextHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String res) {
                            Toast.makeText(ProfileActivity.this,
                                    "Le profil a bien été mis à jour", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String res, Throwable throwable) {
                            Toast.makeText(ProfileActivity.this,
                                    "Erreur lors de l'envoi du profil", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(ProfileActivity.this,
                            "Les deux mots de passe ne sont pas identiques", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
