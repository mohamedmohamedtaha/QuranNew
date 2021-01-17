package com.MohamedTaha.Imagine.New.dagger2.module;

import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.ReScrollUtil;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dagger.Module;
import dagger.Provides;

@Module
public class RescroUtilModule {
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;

    public RescroUtilModule(FloatingActionButton floatingActionButton, RecyclerView recyclerView) {
        this.floatingActionButton = floatingActionButton;
        this.recyclerView = recyclerView;
    }

    @Provides
    FloatingActionButton provideFloatingActionButton() {
        return floatingActionButton;
    }

    @Provides
    RecyclerView provideRecyclerView() {
        return recyclerView;
    }

    @ScopeFragment
    @Provides
    ReScrollUtil provideReScrollUtil(FloatingActionButton floatingActionButton, RecyclerView recyclerView) {
        return new ReScrollUtil(floatingActionButton, recyclerView);
    }
}
