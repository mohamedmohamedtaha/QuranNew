package com.MohamedTaha.Imagine.New.mvp.view;

import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;

import java.util.ArrayList;

public interface AzkarFragmentView {
    void showAfterQueryText(ArrayList<ModelAzkar> stringList);

    void isEmpty();

    void thereData();

    void showProgress();

    void hideProgress();

    void showAllINameAzkar(ArrayList<ModelAzkar> strings);

    void showAnimation();

    void showAfterSearch();

}
