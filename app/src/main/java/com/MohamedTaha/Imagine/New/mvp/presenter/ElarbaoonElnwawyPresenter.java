package com.MohamedTaha.Imagine.New.mvp.presenter;

import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public interface ElarbaoonElnwawyPresenter {
    void getAllData();

    void onDestroy();

    void setOnSearchView(MaterialSearchView materialSearchView);

    void setOnQueryTextForElhadeth(MaterialSearchView materialSearchView, List<ElarbaoonElnawawyModel> name_azkar);

}
