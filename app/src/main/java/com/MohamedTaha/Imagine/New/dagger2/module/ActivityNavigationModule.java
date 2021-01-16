package com.MohamedTaha.Imagine.New.dagger2.module;

import android.app.Activity;

import com.MohamedTaha.Imagine.New.scope.ScopeActivity;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityNavigationModule {

    @ScopeActivity
    @Provides
    public Activity getActivity() {
        return new Activity();
    }
}
