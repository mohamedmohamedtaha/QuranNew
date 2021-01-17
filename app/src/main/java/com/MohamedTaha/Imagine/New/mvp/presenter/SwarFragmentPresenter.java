package com.MohamedTaha.Imagine.New.mvp.presenter;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.MohamedTaha.Imagine.New.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.New.mvp.view.SwarFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public interface SwarFragmentPresenter {
    void getPosition(int position, Bundle bundle);

    void getAllNameSour();

    void getAllImages();

    void onDestroy();

    void setOnSearchView(MaterialSearchView materialSearchView);

    void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar);

}
