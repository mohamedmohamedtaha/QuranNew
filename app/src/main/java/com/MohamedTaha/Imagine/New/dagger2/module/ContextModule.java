package com.MohamedTaha.Imagine.New.dagger2.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }
    @Provides
    Context provideContext(){
        return context;
    }
}
