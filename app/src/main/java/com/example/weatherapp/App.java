package com.example.weatherapp;

import android.app.Application;

import com.example.weatherapp.data.PreferenceHelper;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new PreferenceHelper(this);
    }
}
