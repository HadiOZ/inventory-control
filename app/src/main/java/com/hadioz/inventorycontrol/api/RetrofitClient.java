package com.hadioz.inventorycontrol.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    public Retrofit getClient(String baseURL) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return new  Retrofit.Builder().baseUrl(baseURL)
                .client(builder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}


