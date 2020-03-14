package com.MohamedTaha.Imagine.New.mvp.interactor;

import android.os.Handler;

import com.MohamedTaha.Imagine.New.mvp.presenter.ExitAppSplashPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.ExitAppSplashView;
public class ExitAppSplashInteractor implements ExitAppSplashPresenter {
    private ExitAppSplashView exitAppView;
    private Boolean exitApp = false;

    public ExitAppSplashInteractor() {
    }

    @Override
    public void onDestroy() {
        this.exitAppView = null;
    }

    @Override
    public void setView(ExitAppSplashView exitAppView) {
        this.exitAppView = exitAppView;
    }

    @Override
    public void exitApp() {


        if (exitApp) {
            exitAppView.exitApp();
            return;
        }
        exitApp = true;
        exitAppView.showMessageExitApp();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitApp = false;
            }
        }, 2000);
    }
}