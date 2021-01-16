package com.MohamedTaha.Imagine.New.dagger2.component;

import android.app.AppComponentFactory;

import com.MohamedTaha.Imagine.New.dagger2.module.RetrofitModule;
import com.MohamedTaha.Imagine.New.dagger2.named.APIServiceBase;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;
import com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    void inject(AzanFragment azanFragment);
    AppComponent.Factory appComponentFactory();

}
