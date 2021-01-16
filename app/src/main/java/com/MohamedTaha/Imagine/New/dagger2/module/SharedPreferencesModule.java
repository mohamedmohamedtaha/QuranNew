package com.MohamedTaha.Imagine.New.dagger2.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {
    private Context context;

    public SharedPreferencesModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        return context;
    }

    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    SharedPreferences.Editor provideEditor(SharedPreferences sharedPreferences){
        return sharedPreferences.edit();
    }
}
