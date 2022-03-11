package com.mohamedtaha.imagine.mvp.presenter;

import android.app.Activity;

import com.mohamedtaha.imagine.mvp.view.SplashFragmentView;

public interface SplashFragmentPresenter {
    void goToSlider();

    void onBind(SplashFragmentView splashFragmentView, Activity context);

    void onDestroy();
}
