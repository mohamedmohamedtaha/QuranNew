package com.mohamedtaha.imagine.mvp.presenter;

import android.os.Bundle;

public interface AzkarFragmentPresenter {
    void getAllData();
    void onDestroy();

    void saveState(Bundle outState);

    void restoreState(Bundle outState);

//    void setOnSearchView(MaterialSearchView materialSearchView);
//
//    void setOnQueryTextForAzkar(MaterialSearchView materialSearchView, ArrayList<ModelAzkar> name_azkar);

}
