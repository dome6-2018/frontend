package com.ensicaen.ecole.ludistreet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ensicaen.ecole.ludistreet.R;
import com.ensicaen.ecole.ludistreet.models.User;

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

        ((EditText) findViewById(R.id.textView_surname)).setText(userModel.getFirstname());
        ((EditText) findViewById(R.id.textView_name)).setText(userModel.getLastname());
        ((EditText) findViewById(R.id.textView_mail)).setText(userModel.getEmail());

        final ImageView save_button = ( (ImageView)findViewById(R.id.save_button));
        save_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String username = ((EditText) findViewById(R.id.loginEdit)).getText().toString();
                final String name = ((EditText) findViewById(R.id.textView_name)).getText().toString();
                final String surname = ((EditText) findViewById(R.id.textView_surname)).getText().toString();
                final int id = Integer.parseInt(((EditText) findViewById(R.id.textView_id)).getText().toString());
                final String password = ((EditText) findViewById(R.id.textView_password)).getText().toString();
                final String password_double_check = ((EditText) findViewById(R.id.textView_check_password)).getText().toString();
                final String email = ((EditText) findViewById(R.id.textView_mail)).getText().toString();
                final int score = Integer.parseInt(((EditText) findViewById(R.id.textView_point)).getText().toString());

                User model = new User(id, username, surname, name, email, score);
            }
        });
    }

}
