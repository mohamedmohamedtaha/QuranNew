package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.AzkarFragmentModule;
import com.MohamedTaha.Imagine.New.dagger2.module.CompositeDisposableModule;
import com.MohamedTaha.Imagine.New.dagger2.module.LinearLayoutManagerModlue;
import com.MohamedTaha.Imagine.New.dagger2.module.RescroUtilModule;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.AzkarFragment;

import dagger.Component;
@ScopeFragment
@Component(modules = {AzkarFragmentModule.class, RescroUtilModule.class, LinearLayoutManagerModlue.class, CompositeDisposableModule.class})
public interface AzkarFragmentComponent {
    void inject(AzkarFragment azkarFragment);
}
