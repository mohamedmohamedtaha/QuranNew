package com.mohamedtaha.imagine.mvp.presenter;

import android.os.Bundle;

public interface PartsFragmentPresenter {
    void getAllImages();

    void getPosition(int position, Bundle bundle);

    void getAllPartSoura();

    void onDestroy();

//    void setOnSearchView(MaterialSearchView materialSearchView);
//
//    void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar);

}
