package com.MohamedTaha.Imagine.New.mvp.presenter;

import android.content.Context;
import android.os.Bundle;

import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;
import com.MohamedTaha.Imagine.New.mvp.view.AzkarFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public interface AzkarFragmentPresenter {
    void getAllData();

    void onBind(AzkarFragmentView azkarFragmentView, Context context);

    void onDestroy();

    void saveState(Bundle outState);

    void restoreState(Bundle outState);

    void setOnSearchView(MaterialSearchView materialSearchView);

    void setOnQueryTextForAzkar(MaterialSearchView materialSearchView, ArrayList<ModelAzkar> name_azkar);

}
