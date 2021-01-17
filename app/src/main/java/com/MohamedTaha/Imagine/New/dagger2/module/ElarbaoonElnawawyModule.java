package com.MohamedTaha.Imagine.New.dagger2.module;

import android.content.Context;

import com.MohamedTaha.Imagine.New.mvp.interactor.ElarbaoonElnwawyInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.ElarbaoonElnwawyPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.ElarbaoonElnwawyView;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ElarbaoonElnawawyModule {
    private ElarbaoonElnwawyView elarbaoonElnwawyView;
    private Context context;

    public ElarbaoonElnawawyModule(ElarbaoonElnwawyView elarbaoonElnwawyView, Context context) {
        this.elarbaoonElnwawyView = elarbaoonElnwawyView;
        this.context = context;
    }

    @Provides
    Context provideContext(){
        return  context;
    }

    @Provides
    ElarbaoonElnwawyView provideElarbaoonElnwawyView() {
        return elarbaoonElnwawyView;
    }

    @ScopeFragment
    @Provides
    ElarbaoonElnwawyPresenter provideElarbaoonElnwawyPresenter(ElarbaoonElnwawyView elarbaoonElnwawyView, Context context) {
        return new ElarbaoonElnwawyInteractor(elarbaoonElnwawyView,context);

    }
}
