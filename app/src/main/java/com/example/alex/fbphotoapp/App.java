package com.example.alex.fbphotoapp;

import android.app.Application;

import com.example.alex.fbphotoapp.data.storage.SessionSharedPreferences;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SessionSharedPreferences.init(this);
    }


    public static App getInstance() {
        return instance;
    }


}
