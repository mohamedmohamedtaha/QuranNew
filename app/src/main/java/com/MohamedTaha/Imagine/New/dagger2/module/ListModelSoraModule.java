package com.MohamedTaha.Imagine.New.dagger2.module;

import com.MohamedTaha.Imagine.New.mvp.model.ModelSora;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModelSoraModule {

    @Provides
    public List<ModelSora> provideModelSoraList() {
        return new ArrayList<>();
    }
}
