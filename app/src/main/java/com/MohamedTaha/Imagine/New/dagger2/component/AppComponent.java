package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.NavigationDrawaberPresenterModule;
import com.MohamedTaha.Imagine.New.dagger2.module.SharedPreferencesModule;
import com.MohamedTaha.Imagine.New.dagger2.named.APIServiceBase;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.scope.ScopeActivity;
import com.MohamedTaha.Imagine.New.service.IntentModule;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;

import dagger.Component;
import dagger.Subcomponent;

@ScopeActivity
@Subcomponent( modules = {NavigationDrawaberPresenterModule.class, IntentModule.class, SharedPreferencesModule.class})
public interface AppComponent {
    @Subcomponent.Factory
    interface Factory {
        AppComponent create(NavigationDrawaberPresenterModule navigationDrawaberPresenterModule
                , SharedPreferencesModule sharedPreferencesModule);
    }

    void inject(NavigationDrawaberActivity navigationDrawaberActivity);

    // RetrofitComponent retrogitComponent();
    //NavigationDrawarPresenter getNavigationDrawaberPresenter();
}
