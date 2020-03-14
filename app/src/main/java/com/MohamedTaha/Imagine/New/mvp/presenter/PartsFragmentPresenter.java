package com.MohamedTaha.Imagine.New.mvp.presenter;

import android.os.Bundle;

import com.MohamedTaha.Imagine.New.mvp.model.ModelSora;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public interface PartsFragmentPresenter {
    void getAllImages();

    void getPosition(int position, Bundle bundle);

    void getAllPartSoura();

    void onDestroy();

    void setOnSearchView(MaterialSearchView materialSearchView);

    void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar);

}
