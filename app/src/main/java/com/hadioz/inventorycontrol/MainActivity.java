package com.hadioz.inventorycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        // Intent loginIntent = new Intent(MainActivity.this, DashboardActivity.class);
        Intent signInIntent = new Intent(MainActivity.this, SignIn.class);
        Intent listProductIntent = new Intent(MainActivity.this, ListProduct.class);

        boolean verified = false;

        if (verified) {
            startActivity(listProductIntent);
        } else {
            startActivity(signInIntent);
        }


    }

}