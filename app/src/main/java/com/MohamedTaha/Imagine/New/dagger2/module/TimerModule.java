package com.MohamedTaha.Imagine.New.dagger2.module;

import android.app.Activity;

import com.MohamedTaha.Imagine.New.mvp.view.SplashFragmentView;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;

import java.util.Timer;

import dagger.Module;
import dagger.Provides;

@Module
public class TimerModule {
    @ScopeFragment
    @Provides
    public Timer getTimer() {
        return new Timer();
    }

}
