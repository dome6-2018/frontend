package com.ensicaen.ecole.ludistreet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.models.User;
import com.ensicaen.ecole.ludistreet.models.Wall;
import com.ensicaen.ecole.ludistreet.rest.MeRestClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        MeRestClient meRestClient = new MeRestClient();
        meRestClient.getMe(new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                Gson gson = new Gson();
                User user = gson.fromJson(res, new TypeToken<User>(){}.getType());

                ((EditText) findViewById(R.id.editText_id)).setText(Integer.toString(user.getId()));
                ((EditText) findViewById(R.id.editText_username)).setHint(user.getUsername());
                ((EditText) findViewById(R.id.editText_points)).setHint(Integer.toString(user.getPoints()));

                ((EditText) findViewById(R.id.editText_lastname)).setText(user.getLastname());
                ((EditText) findViewById(R.id.editText_firstname)).setText(user.getFirstname());
                ((EditText) findViewById(R.id.editText_email)).setText(user.getEmail());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(ProfileActivity.this,
                        "Erreur lors de la récupération des données du profil", Toast.LENGTH_LONG).show();
            }
        });

        final ImageView save_button = ( (ImageView)findViewById(R.id.save_button));

        save_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int id = Integer.parseInt(((EditText) findViewById(R.id.editText_id)).getText().toString());
                final String lastname = ((EditText) findViewById(R.id.editText_lastname)).getText().toString();
                final String firstname = ((EditText) findViewById(R.id.editText_firstname)).getText().toString();
                final String password = ((EditText) findViewById(R.id.editText_password)).getText().toString();
                final String passwordConfirm = ((EditText) findViewById(R.id.editText_password_confirm)).getText().toString();
                final String email = ((EditText) findViewById(R.id.editText_email)).getText().toString();

                if (password.equals(passwordConfirm)) {
                    User user = new User(id, lastname, firstname, password, email);

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
