package com.hadioz.inventorycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.hadioz.inventorycontrol.api.APIService;
import com.hadioz.inventorycontrol.api.APIUtil;
import com.hadioz.inventorycontrol.model.UserModel;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    UserModel userModel;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        userModel = new UserModel(this);
        userModel.deleteUser();

        EditText username = (EditText) findViewById(R.id.et_username);
        EditText password = (EditText) findViewById(R.id.et_password);
        Button signIn = (Button) findViewById(R.id.btn_login);

        signIn.setOnClickListener(v -> {
            Log.d("username", username.getText().toString());
            Log.d("password", password.getText().toString());
            user = new User();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            PostData(user);

        });

    }

    public void PostData(User user) {
        APIService apiService = new APIUtil().getAPIService();
        apiService.createPost(user).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    try {
                        Intent listProductIntent = new Intent(SignIn.this, ListProduct.class);
                        String json = response.body();
                        Log.d("response success", response.body());
                        JSONObject object = new  JSONObject(json);
                        user.setId(object.getString("user-id"));
                        long result = userModel.InsertUser(user.getId(), user.getUsername(), user.getPassword());
                        Log.d("result", Long.toString(result));
                        if (result > 0) {
                            startActivity(listProductIntent);
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        Log.d("response failure", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}

