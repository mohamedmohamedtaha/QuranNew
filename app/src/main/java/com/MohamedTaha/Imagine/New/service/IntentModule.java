package com.MohamedTaha.Imagine.New.service;

import android.content.Intent;

import com.MohamedTaha.Imagine.New.scope.ScopeActivity;
import com.MohamedTaha.Imagine.New.scope.ScopeNavigationDrawaberActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class IntentModule {
    //@ScopeActivity
    @Provides
    public Intent getIntent(){
        return new Intent();
    }
}
