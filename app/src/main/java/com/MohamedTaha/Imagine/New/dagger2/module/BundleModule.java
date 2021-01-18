package com.MohamedTaha.Imagine.New.dagger2.module;

import android.os.Bundle;

import dagger.Module;
import dagger.Provides;

@Module
public class BundleModule {
    @Provides
    public Bundle provideBundle(){
        return new Bundle();
    }
}
