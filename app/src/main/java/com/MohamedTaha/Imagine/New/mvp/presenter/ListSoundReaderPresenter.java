package com.MohamedTaha.Imagine.New.mvp.presenter;

import com.MohamedTaha.Imagine.New.mvp.model.ImageModel;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public interface ListSoundReaderPresenter {
    void onDestroy();

    void getAllData();

    void setOnSearchViewListener(MaterialSearchView searchView);

    void setOnQueryTextListener(MaterialSearchView searchView, List<ImageModel> imageModel);

}
