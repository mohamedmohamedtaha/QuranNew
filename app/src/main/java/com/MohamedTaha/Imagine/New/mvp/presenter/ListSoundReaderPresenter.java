package com.MohamedTaha.Imagine.New.mvp.presenter;

import android.content.Context;

import com.MohamedTaha.Imagine.New.mvp.model.ImageModel;
import com.MohamedTaha.Imagine.New.mvp.view.ListSoundReaderView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public interface ListSoundReaderPresenter {
    void onDestroy();

    void getAllData();
    void onBind(ListSoundReaderView listSoundReaderView, Context context);

    void setOnSearchViewListener(MaterialSearchView searchView);

    void setOnQueryTextListener(MaterialSearchView searchView, List<ImageModel> imageModel);

}
