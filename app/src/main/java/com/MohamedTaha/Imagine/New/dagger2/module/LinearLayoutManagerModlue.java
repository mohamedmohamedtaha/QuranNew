package com.MohamedTaha.Imagine.New.dagger2.module;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class LinearLayoutManagerModlue {

    @Provides
    LinearLayoutManager linearLayoutManager(Context context){
        return new LinearLayoutManager(context.getApplicationContext());
    }

}
