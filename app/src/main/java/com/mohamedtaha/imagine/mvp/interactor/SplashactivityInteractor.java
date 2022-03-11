package com.mohamedtaha.imagine.mvp.interactor;

import com.mohamedtaha.imagine.mvp.presenter.SplashActivityPresenter;
import com.mohamedtaha.imagine.mvp.view.SplashActivityView;

public class SplashactivityInteractor implements SplashActivityPresenter {
    private SplashActivityView splashactivityView = null;
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
