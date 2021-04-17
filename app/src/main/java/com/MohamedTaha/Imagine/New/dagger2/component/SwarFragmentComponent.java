package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.CompositeDisposableModule;
import com.MohamedTaha.Imagine.New.dagger2.module.ListModelSoraModule;
import com.MohamedTaha.Imagine.New.dagger2.module.RescroUtilModule;
import com.MohamedTaha.Imagine.New.dagger2.module.SwarFragmentModule;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.SwarFragment;

import dagger.Component;
@ScopeFragment
@Component(modules = {SwarFragmentModule.class, RescroUtilModule.class, CompositeDisposableModule.class, ListModelSoraModule.class})
public interface SwarFragmentComponent {
    void inject(SwarFragment swarFragment);
}
