package com.hadioz.inventorycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.hadioz.inventorycontrol.model.UserModel;

public class MainActivity extends AppCompatActivity {
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        // Intent loginIntent = new Intent(MainActivity.this, DashboardActivity.class);
        Intent signInIntent = new Intent(MainActivity.this, SignIn.class);
        Intent listProductIntent = new Intent(MainActivity.this, ListProduct.class);

        userModel = new UserModel(this);
        Cursor cursor = userModel.getUser();
        int count = cursor.getCount();
        Log.d("count", Integer.toString(count));
        boolean verified = count > 0 ? true : false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (verified) {
                    startActivity(listProductIntent);
                } else {
                    startActivity(signInIntent);
                }
            }
        }, 3000);

    }

}