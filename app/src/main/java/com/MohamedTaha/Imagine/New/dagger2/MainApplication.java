package com.MohamedTaha.Imagine.New.dagger2;

import android.app.Activity;
import android.app.Application;

import com.MohamedTaha.Imagine.New.dagger2.component.AppComponent;
import com.MohamedTaha.Imagine.New.dagger2.component.ComponentSplashFragment;
import com.MohamedTaha.Imagine.New.dagger2.component.DaggerRetrofitComponent;
import com.MohamedTaha.Imagine.New.dagger2.component.RetrofitComponent;
import com.MohamedTaha.Imagine.New.dagger2.module.NavigationDrawaberPresenterModule;
import com.MohamedTaha.Imagine.New.dagger2.module.SharedPreferencesModule;
import com.MohamedTaha.Imagine.New.mvp.view.SplashFragmentView;


public class MainApplication extends Application {
    private RetrofitComponent retrofitComponent;
    private AppComponent appComponent;
    private ComponentSplashFragment componentSplashFragment;
    private Activity activity;
    private SplashFragmentView splashFragmentView;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitComponent = DaggerRetrofitComponent.create();

//        appComponent = DaggerAppComponent.builder().sharedPreferencesModule(new SharedPreferencesModule(this)).
//                navigationDrawaberPresenterModule(new NavigationDrawaberPresenterModule(
//                this, this)).build();
        //  appComponent = DaggerAppCom.builder().navigationDrawaberPresenterModule(new NavigationDrawaberPresenterModule(this)).build();


        //  componentSplashFragment = DaggerComponentSplashFragment.builder().moduleSplashFragment(new ModuleSplashFragment(splashFragmentView,activity)).build();
    }

    public RetrofitComponent getRetrofitComponent() {
        return retrofitComponent;
    }

    public void setRetrofitComponent(RetrofitComponent retrofitComponent) {
        this.retrofitComponent = retrofitComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ComponentSplashFragment getComponentSplashFragment() {
        return componentSplashFragment;
    }
}
