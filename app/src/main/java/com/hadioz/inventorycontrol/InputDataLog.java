package com.hadioz.inventorycontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hadioz.inventorycontrol.api.APIService;
import com.hadioz.inventorycontrol.api.APIUtil;
import com.hadioz.inventorycontrol.model.LogInsert;
import com.hadioz.inventorycontrol.model.UserModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputDataLog extends AppCompatActivity {
    EditText amount;
    RadioGroup action;
    RadioButton radioButton;
    Button save;

    UserModel userModel;

    LogInsert logInsert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_log);
        getSupportActionBar().setTitle(getIntent().getStringExtra(ProductAdapter.EXTRA_PRODUCT_NAME));

        amount = (EditText) findViewById(R.id.et_quantity);
        action = (RadioGroup) findViewById(R.id.radio_action);
        save = (Button) findViewById(R.id.btn_save);

        save.setOnClickListener(v -> {
            userModel = new UserModel(this);
            logInsert = new LogInsert();
            int selected = action.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selected);

            Cursor cursor = userModel.getUser();
            cursor.moveToFirst();
            String id = cursor.getString(cursor.getColumnIndex(UserModel.C1_ID));
            String productID = getIntent().getStringExtra(ProductAdapter.EXTRA_ID);
            int quantity = Integer.parseInt(amount.getText().toString());


            String action;
            if (radioButton.getText().toString().equals("Input")) {
                action = "i";
            } else {
                action = "o";
            }
            Log.d("action", action);
            logInsert.setAdmin(id);
            logInsert.setProduct(productID);
            logInsert.setAmount(quantity);
            logInsert.setAction(action);
            SaveDAta(logInsert);
        });
    }

    private void SaveDAta(LogInsert logInsert) {
        APIService apiService = new APIUtil().getAPIService();
        apiService.insertLog(logInsert).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("data result", response.body());
                } else {
                    try {
                        Log.d("response failure", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}