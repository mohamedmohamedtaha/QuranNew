package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.ActivityModule;
import com.MohamedTaha.Imagine.New.dagger2.module.ModuleSplashFragment;
import com.MohamedTaha.Imagine.New.dagger2.module.TimerModule;
import com.MohamedTaha.Imagine.New.mvp.interactor.SplashFragmentInteractor;
import com.MohamedTaha.Imagine.New.scope.ScopeActivity;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment;

import dagger.Component;
@ScopeFragment
@Component(modules = {ModuleSplashFragment.class, TimerModule.class,ActivityModule.class})
public interface ComponentSplashFragment {
    //ComponentSplashActivity componentSplashActivity();
    void inject(SplashFragment splashFragment);
}
