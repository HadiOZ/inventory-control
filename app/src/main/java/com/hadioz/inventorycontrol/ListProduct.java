package com.hadioz.inventorycontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hadioz.inventorycontrol.model.UserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListProduct extends AppCompatActivity {

    UserModel userModel;
    private RecyclerView listProduct;
    private ProductAdapter adapter;
    private ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_product);
        LoadData loadData = new LoadData();
        loadData.execute();

        listProduct = (RecyclerView) findViewById(R.id.rcv_product);
        adapter = new ProductAdapter(products, this);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(ListProduct.this);
        listProduct.setLayoutManager(layoutManager);
        listProduct.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutOpt:
                Log.d("about", "clicked");
                Intent signInIntent = new Intent(ListProduct.this, SignIn.class);
                userModel = new UserModel(this);
                userModel.deleteUser();
                startActivity(signInIntent);
                return true;

            case R.id.languageOpt:
                Intent intentLang = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intentLang);
                Log.d("language", "clicked");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadData loadData = new LoadData();
        loadData.execute();

    }

    public class LoadData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            try {
                URL url;
                HttpURLConnection connection = null;

                try {
                    url = new URL("http://117.53.46.220:3000/products");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoOutput(true);
                    connection.connect();

                    BufferedReader buff = new BufferedReader( new InputStreamReader( url.openStream()));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = buff.readLine()) != null) {
                        stringBuffer.append(line + "\n");
                    }
                    buff.close();

                    current = stringBuffer.toString();
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }  catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            products.clear();
            try {
                android.util.Log.d("data", s);
                JSONArray arrayJSON = new JSONArray(s);
                android.util.Log.d("length :", String.valueOf(arrayJSON.length()));
                for (int i = 0; i < arrayJSON.length(); i++) {
                    JSONObject object = (JSONObject) arrayJSON.get(i);
                    Log.d("name", object.getString("name"));

                    Product prd = new Product();
                    prd.setName(object.getString("name"));
                    prd.setCode( object.getString("code"));
                    prd.setPrice(object.getInt("price"));
                    prd.setStock(object.getInt("stock"));
                    prd.setId(object.getString("id"));
                    prd.setImage(object.getString("path-image"));
                    products.add(prd);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            adapter.notifyDataSetChanged();

        }


    }
}