package com.MohamedTaha.Imagine.New.dagger2.module;

import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModelAzkarModule {
    @Provides
    public List<ModelAzkar> provideModelAzkarList() {
        return new ArrayList<>();
    }
    //@Provides

}
