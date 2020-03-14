package com.MohamedTaha.Imagine.New.mvp.presenter;

import com.MohamedTaha.Imagine.New.mvp.view.ExitAppSplashView;

public interface ExitAppSplashPresenter {
    void onDestroy();

    void setView(ExitAppSplashView exitAppView);

    void exitApp();

}
