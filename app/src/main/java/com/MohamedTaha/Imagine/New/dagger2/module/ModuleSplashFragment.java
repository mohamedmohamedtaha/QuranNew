package com.MohamedTaha.Imagine.New.dagger2.module;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.MohamedTaha.Imagine.New.mvp.interactor.SplashFragmentInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.SplashFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.SplashFragmentView;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleSplashFragment {
    private SplashFragmentView splashFragmentView;
    private Activity activity;

    public ModuleSplashFragment(SplashFragmentView splashFragmentView, Activity activity) {
        this.splashFragmentView = splashFragmentView;
        this.activity = activity;
    }
    @Provides
    public SplashFragmentView provideplashFragmentView() {
        return splashFragmentView;
    }

//    @ScopeFragment
//    @Provides
//    public Activity getActivity() {
//        return activity;
//    }

    @ScopeFragment
    @Provides
    public SplashFragmentPresenter splashFragmentInteractor(SplashFragmentView splashFragmentView, Activity activity) {
        return new SplashFragmentInteractor(splashFragmentView, activity);
    }
//    @ScopeFragment
//    @Binds
//    public abstract SplashFragmentPresenter BindExitAppSplashPresenter(SplashFragmentInteractor splashFragmentInteractor);
//
//    @ScopeFragment
//    @Binds
//    public abstract SplashFragmentView BindSplashFragmentView(SplashFragment splashFragment);

}
