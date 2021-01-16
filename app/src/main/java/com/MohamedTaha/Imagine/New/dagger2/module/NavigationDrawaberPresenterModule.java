package com.MohamedTaha.Imagine.New.dagger2.module;

import android.app.Activity;

import com.MohamedTaha.Imagine.New.mvp.interactor.NavigationDrawarInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.NavigationDrawarPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.NavigationDrawaberView;
import com.MohamedTaha.Imagine.New.scope.ScopeActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationDrawaberPresenterModule {
    private NavigationDrawaberView view;
    private Activity context;

    //@ScopeActivity
    @Provides
    public Activity getContext() {
        return context;
    }

    public NavigationDrawaberPresenterModule(NavigationDrawaberView view,Activity activity) {
        this.view = view;
        this.context = activity;
    }

 //   @ScopeActivity
    @Provides
    public NavigationDrawaberView provideNavigationDrawaberView() {
        return view;
    }

   // @ScopeActivity
    @Provides
    public NavigationDrawarPresenter provideNavigationDrawarPresenter(NavigationDrawaberView view, Activity activity) {
        return new NavigationDrawarInteractor(view, activity);
    }

//    @ScopeActivity
//    @Binds
//    public abstract NavigationDrawarPresenter navigationDrawarPresenter(NavigationDrawarInteractor navigationDrawarInteractor);

//    @ScopeActivity
//    @Binds
//    public abstract NavigationDrawarView navigationDrawarView(NavigationDrawaberActivity navigationDrawaberActivity);

//    @ScopeActivity
//    @Binds
//    public  NavigationDrawarInteractor getNavigationDrawarInteractor(NavigationDrawarPresenter navigationDrawarPresenter){
//        return navigationDrawarPresenter;
//    }
}
