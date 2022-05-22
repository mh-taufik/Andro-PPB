package com.example.apiconsume.data;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClients {
    // TODO menginisiasi MainInterface
    private static MainInterface service;
    public static final String BASE_URL = "https://reqres.in/";

    public static MainInterface getService() {
        if (service == null) {
            OkHttpClient.Builder httpclient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.client(httpclient.build()).build();
            service = retrofit.create(MainInterface.class);
        }
        return service;
    }
}
