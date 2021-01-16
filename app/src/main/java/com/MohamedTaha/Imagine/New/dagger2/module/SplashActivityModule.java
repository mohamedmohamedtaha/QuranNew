package com.MohamedTaha.Imagine.New.dagger2.module;

import com.MohamedTaha.Imagine.New.mvp.interactor.SplashactivityInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.SplashActivityPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.SplashActivityView;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {
    private SplashActivityView splashactivityView;
    public SplashActivityModule(SplashActivityView splashactivityView) {
        this.splashactivityView = splashactivityView;
    }

    @Provides
    public SplashActivityView provideSplashActivityView(){
        return splashactivityView;
    }

    @Provides
    public SplashActivityPresenter provideSplashActivityPresenter(SplashActivityView splashactivityView){
        return new SplashactivityInteractor(splashactivityView);
    }
}
