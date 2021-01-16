package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.SplashActivityModule;
import com.MohamedTaha.Imagine.New.scope.ScopeActivity;
import com.MohamedTaha.Imagine.New.ui.activities.SplashActivity;

import dagger.Component;

@ScopeActivity
@Component(modules = SplashActivityModule.class)
public interface ComponentSplashActivity {
    void inject(SplashActivity splashActivity);
}
