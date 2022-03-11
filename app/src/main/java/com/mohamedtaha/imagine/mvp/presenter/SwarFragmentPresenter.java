package com.mohamedtaha.imagine.mvp.presenter;

import android.os.Bundle;


public interface SwarFragmentPresenter {
    void getPosition(int position, Bundle bundle);

    void getAllNameSour();

    void getAllImages();

    void onDestroy();

//    void setOnSearchView(MaterialSearchView materialSearchView);
//
//    void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar);

}
