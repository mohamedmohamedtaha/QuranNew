package com.MohamedTaha.Imagine.New.mvp.interactor;

import com.MohamedTaha.Imagine.New.mvp.presenter.SplashActivityPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.SplashActivityView;
import com.MohamedTaha.Imagine.New.scope.ScopeActivity;

import javax.inject.Inject;
@ScopeActivity
public class SplashactivityInteractor implements SplashActivityPresenter {
    private SplashActivityView splashactivityView = null;

    @Inject
    public SplashactivityInteractor(SplashActivityView splashactivityView) {
        this.splashactivityView = splashactivityView;
    }

    @Override
    public void goToSplashFragment() {
        if (splashactivityView != null) {
            splashactivityView.goToSplashFragment();
        }

    }

    @Override
    public void onDestory() {
        splashactivityView = null;

    }
}
