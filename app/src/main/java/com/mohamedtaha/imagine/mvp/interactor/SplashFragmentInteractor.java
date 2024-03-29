package com.mohamedtaha.imagine.mvp.interactor;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.mohamedtaha.imagine.mvp.presenter.SplashFragmentPresenter;
import com.mohamedtaha.imagine.mvp.view.SplashFragmentView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragmentInteractor extends ViewModel implements SplashFragmentPresenter {
    private static final String TAG = "SplashFragmentInteracto";
    Timer waitTimer;
    Activity context;
    SplashFragmentView splashFragmentView;
    private static final int WAIT_TIME = 2500;

    public SplashFragmentInteractor(SplashFragmentView splashFragmentView,Activity  context) {
        this.splashFragmentView = splashFragmentView;
        this.context = context;
        Log.d(TAG, " context is : " + context);

    }

    @Override
    public void goToSlider() {
        if (splashFragmentView != null) {
            Log.d(TAG, " splashFragmentView is : " + splashFragmentView);
            Log.d(TAG, " waitTimer is : " + waitTimer);
            Log.d(TAG, " context is : " + context);

            splashFragmentView.showAnimation();
            waitTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            splashFragmentView.goToSlider();
                        }
                    });
                }
            }, WAIT_TIME);
        } else {
            Log.d(TAG, "splashFragmentView else is : " + splashFragmentView);
        }
    }

    @Override
    public void onBind(SplashFragmentView splashFragmentView, Activity context) {
        this.splashFragmentView = splashFragmentView;
        this.context = context;
    }

    @Override
    public void onDestroy() {
        splashFragmentView = null;
    }
}
