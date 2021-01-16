package com.MohamedTaha.Imagine.New.mvp.presenter;

import android.app.Activity;

import com.MohamedTaha.Imagine.New.mvp.view.SplashFragmentView;

public interface SplashFragmentPresenter {
    void goToSlider();

    void onBind(SplashFragmentView splashFragmentView, Activity context);

    void onDestroy();
}
