package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.ListModelAzkarModule;
import com.MohamedTaha.Imagine.New.dagger2.module.SharedPreferencesModule;
import com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity;

import dagger.Component;

@Component(modules = {SharedPreferencesModule.class, ListModelAzkarModule.class})
public interface SwipePagesActivityComponent {
    void inject(SwipePagesActivity swipePagesActivity);
}
