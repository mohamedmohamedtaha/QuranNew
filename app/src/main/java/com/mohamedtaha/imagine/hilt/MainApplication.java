package com.mohamedtaha.imagine.hilt;

import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
          }

    }
