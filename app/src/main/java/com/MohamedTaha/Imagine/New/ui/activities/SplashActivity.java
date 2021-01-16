package com.MohamedTaha.Imagine.New.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.dagger2.component.ComponentSplashActivity;
import com.MohamedTaha.Imagine.New.dagger2.component.DaggerComponentSplashActivity;
import com.MohamedTaha.Imagine.New.dagger2.module.SplashActivityModule;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.mvp.interactor.SplashactivityInteractor;
import com.MohamedTaha.Imagine.New.mvp.view.SplashActivityView;
import com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashActivityView {
    @BindString(R.string.exit_app)
    String exit_app;
    SplashFragment splashFragment;
    @Inject
    SplashactivityInteractor presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ComponentSplashActivity componentSplashActivity = DaggerComponentSplashActivity.builder().
                splashActivityModule(new SplashActivityModule(this)).build();
        componentSplashActivity.inject(this);
        presenter.goToSplashFragment();
    }

    @Override
    public void goToSplashFragment() {
        splashFragment = new SplashFragment();
        HelperClass.replece(splashFragment, getSupportFragmentManager(), R.id.Cycle_Splash_contener);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }

    @Override
    public void onBackPressed() {
        //Do not do any thing
    }


}