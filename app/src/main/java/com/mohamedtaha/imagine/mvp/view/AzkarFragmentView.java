package com.mohamedtaha.imagine.mvp.view;

import com.mohamedtaha.imagine.mvp.model.ModelAzkar;

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
