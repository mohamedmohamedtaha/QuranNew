package com.MohamedTaha.Imagine.New.dagger2.module;

import android.app.Activity;
import android.content.Context;

import com.MohamedTaha.Imagine.New.mvp.interactor.AzkarFragmentInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.AzkarFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.AzkarFragmentView;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class AzkarFragmentModule {
    private AzkarFragmentView azkarFragmentView;
    private Context context;

    public AzkarFragmentModule(AzkarFragmentView azkarFragmentView, Context context) {
        this.azkarFragmentView = azkarFragmentView;
        this.context = context;
    }
    @Provides
    public AzkarFragmentView provideAzkarFragmentView(){
        return azkarFragmentView;
    }
    @Provides
    public AzkarFragmentPresenter provideAzkarFragmentPresenter(AzkarFragmentView azkarFragmentView,Context context){
        return new AzkarFragmentInteractor(azkarFragmentView,context);
    }
}
