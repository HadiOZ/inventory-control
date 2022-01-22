package com.hadioz.inventorycontrol.api;

import retrofit2.Retrofit;

public class APIUtil {
    public APIService getAPIService() {
        return new RetrofitClient().getClient("http://117.53.46.220:3000").create(APIService.class);
    }
}
