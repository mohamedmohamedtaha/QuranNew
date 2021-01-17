package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.CompositeDisposableModule;
import com.MohamedTaha.Imagine.New.dagger2.module.PartsFragmentModule;
import com.MohamedTaha.Imagine.New.dagger2.module.RescroUtilModule;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.PartsFragment;

import dagger.Component;
@ScopeFragment
@Component(modules = {PartsFragmentModule.class, CompositeDisposableModule.class, RescroUtilModule.class})
public interface PartsFragmentComponent {
    void inject(PartsFragment partsFragment);
}
