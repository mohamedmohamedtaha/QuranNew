package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.ElarbaoonElnawawyModule;
import com.MohamedTaha.Imagine.New.dagger2.module.RescroUtilModule;
import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;
import com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity;

import dagger.Component;
@ScopeFragment
@Component(modules = {ElarbaoonElnawawyModule.class, RescroUtilModule.class})
public interface ElarbaoonElnawawyComponent {
    void inject(ElarbaoonElnawawyActivity elarbaoonElnawawyActivity);
}
