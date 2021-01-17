package com.MohamedTaha.Imagine.New.dagger2.module;

import android.content.Context;

import com.MohamedTaha.Imagine.New.mvp.interactor.ListSoundReaderInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.ListSoundReaderPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.ListSoundReaderView;
import com.MohamedTaha.Imagine.New.scope.ScopeFragment;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class FragmentSoundModule {
    private ListSoundReaderView listSoundReaderView;
    private Context context;

    public FragmentSoundModule(ListSoundReaderView listSoundReaderView, Context context) {
        this.listSoundReaderView = listSoundReaderView;
        this.context = context;
    }
    @Provides
    public ListSoundReaderView provideListSoundReaderView(){
        return listSoundReaderView;
    }
    @ScopeFragment
    @Provides
    public ListSoundReaderPresenter listSoundReaderPresenter(ListSoundReaderView listSoundReaderView,Context context){
        return new ListSoundReaderInteractor(listSoundReaderView,context);
    }
}
