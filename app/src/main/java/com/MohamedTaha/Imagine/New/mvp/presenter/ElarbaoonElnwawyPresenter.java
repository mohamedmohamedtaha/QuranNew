package com.MohamedTaha.Imagine.New.mvp.presenter;

import android.content.Context;

import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.MohamedTaha.Imagine.New.mvp.view.ElarbaoonElnwawyView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public interface ElarbaoonElnwawyPresenter {
    void getAllData();

    void onDestroy();
   // void onBind(ElarbaoonElnwawyView elarbaoonElnwawyView, Context context);

    void setOnSearchView(MaterialSearchView materialSearchView);

    void setOnQueryTextForElhadeth(MaterialSearchView materialSearchView, List<ElarbaoonElnawawyModel> name_azkar);

}
