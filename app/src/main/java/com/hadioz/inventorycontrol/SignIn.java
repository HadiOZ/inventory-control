package com.hadioz.inventorycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {

    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        Auth auth = new Auth();
        EditText username = (EditText) findViewById(R.id.et_username);
        EditText password = (EditText) findViewById(R.id.et_password);
        Button signIn = (Button) findViewById(R.id.btn_login);

        signIn.setOnClickListener(v -> {
            Log.d("username", username.getText().toString());
            Log.d("password", password.getText().toString());
            user = new User(username.getText().toString(), password.getText().toString());
            auth.execute(user);

        });

    }

    public class Auth extends AsyncTask<User, String, String> {

        @Override
        protected String doInBackground(User... users) {
            Log.d("usr", users[0].getUsername());
            Log.d("pass", users[0].getPassword());
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            boolean verified = true;

            Intent listProductIntent = new Intent(SignIn.this, ListProduct.class);
            if (verified) {
                startActivity(listProductIntent);
            }

        }


    }


}

