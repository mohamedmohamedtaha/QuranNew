package com.MohamedTaha.Imagine.New.mvp.interactor;

import android.app.Activity;

import com.MohamedTaha.Imagine.New.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.New.helper.ShowDialog;
import com.MohamedTaha.Imagine.New.mvp.presenter.SplashPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.SplashView;

import java.util.Timer;
import java.util.TimerTask;

import static com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity.IS_TRUE;

public class SplashInteractor implements SplashPresenter {
    private SplashView splashView;
    private Activity context;
    private static final int WAIT_TIME = 2500;
    private Timer waitTimer;

    public SplashInteractor(SplashView splashView, Activity context) {
        this.splashView = splashView;
        this.context = context;

    }

    @Override
    public void goToSlider() {
        if (splashView != null){
         splashView.showAnimation();
             waitTimer = new Timer();
             waitTimer.schedule(new TimerTask() {
                 @Override
                 public void run() {
                     context.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {

                             splashView.goToSlider();
                         }
                     });
                 }
             }, WAIT_TIME);
        }
    }

    @Override
    public void onDestroy() {
        splashView = null;
    }
}
