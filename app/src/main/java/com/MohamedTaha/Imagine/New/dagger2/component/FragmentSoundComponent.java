package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.CompositeDisposableModule;
import com.MohamedTaha.Imagine.New.dagger2.module.FragmentSoundModule;
import com.MohamedTaha.Imagine.New.dagger2.module.RescroUtilModule;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.FragmentSound;

import dagger.Component;
@ScopeFragment
@Component(modules = {RescroUtilModule.class, CompositeDisposableModule.class, FragmentSoundModule.class})
public interface FragmentSoundComponent {
    void inject(FragmentSound fragmentSound);
}
