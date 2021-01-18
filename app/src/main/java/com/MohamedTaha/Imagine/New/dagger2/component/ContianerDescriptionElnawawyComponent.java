package com.MohamedTaha.Imagine.New.dagger2.component;

import com.MohamedTaha.Imagine.New.dagger2.module.BundleModule;
import com.MohamedTaha.Imagine.New.ui.activities.ContianerDescriptionElnawawyActivity;

import dagger.Component;

@Component ( modules = BundleModule.class)
public interface ContianerDescriptionElnawawyComponent {
    void inject(ContianerDescriptionElnawawyActivity contianerDescriptionElnawawyActivity);
}
