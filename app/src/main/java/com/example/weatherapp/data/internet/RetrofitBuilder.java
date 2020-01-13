package com.example.weatherapp.data.internet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static RetrofitService retrofitService;


    public static RetrofitService getService(){

        if (retrofitService == null) retrofitService = buildRetrofit();

        return retrofitService;
    }

    private static RetrofitService buildRetrofit() {
        return  new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitService.class);
    }
}
