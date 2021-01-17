package com.MohamedTaha.Imagine.New.dagger2.module;

import android.content.Context;

import com.MohamedTaha.Imagine.New.mvp.interactor.PartsFragmentsInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.PartsFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.PartsFragmentView;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class PartsFragmentModule {
    private PartsFragmentView partsFragmentView;
    private Context context;

    public PartsFragmentModule(PartsFragmentView partsFragmentView, Context context) {
        this.partsFragmentView = partsFragmentView;
        this.context = context;
    }
    @Provides
    public PartsFragmentView providePartsFragmentView(){
        return partsFragmentView;
    }
    @ScopeFragment
    @Provides
    public PartsFragmentPresenter providePartsFragmentPresenter(PartsFragmentView partsFragmentView, Context context){
        return new PartsFragmentsInteractor(partsFragmentView,context);
    }
}
