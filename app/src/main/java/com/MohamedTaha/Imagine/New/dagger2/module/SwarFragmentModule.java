package com.MohamedTaha.Imagine.New.dagger2.module;

import android.content.Context;

import com.MohamedTaha.Imagine.New.mvp.interactor.SwarFragmentInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.SwarFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.SwarFragmentView;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class SwarFragmentModule {
    private SwarFragmentView swarFragmentView;
    private Context context;

    public SwarFragmentModule(SwarFragmentView swarFragmentView, Context context) {
        this.swarFragmentView = swarFragmentView;
        this.context = context;
    }
    @Provides
    public SwarFragmentView provideSwarFragmentView(){
        return swarFragmentView;
    }
    @ScopeFragment
    @Provides
    public SwarFragmentPresenter provideSwarFragmentPresenter(SwarFragmentView swarFragmentView,Context context){
        return new SwarFragmentInteractor(swarFragmentView,context);
    }
}
