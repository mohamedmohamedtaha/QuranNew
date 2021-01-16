package com.MohamedTaha.Imagine.New.mvp.presenter;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.MohamedTaha.Imagine.New.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.New.mvp.view.GridViewFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public interface GridViewFragmentPresenter {
    void getPosition(int position, Bundle bundle);

    void getAllNameSour();

    void getAllImages();

    void onDestroy();
    void onBind(GridViewFragmentView fragmentView, FragmentActivity context);

    void setOnSearchView(MaterialSearchView materialSearchView);

    void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar);

}
