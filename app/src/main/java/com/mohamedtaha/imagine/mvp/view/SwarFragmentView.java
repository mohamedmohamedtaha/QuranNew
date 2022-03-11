package com.mohamedtaha.imagine.mvp.view;

import com.mohamedtaha.imagine.mvp.model.ModelSora;

import java.util.List;

public interface SwarFragmentView {
    void showAfterSearch();
    void showAfterQueryText(List<ModelSora> stringList);
    void hideProgress();
    void showProgress();
    void isEmpty();
    void thereData();
    void showAllINameSour(List<ModelSora> strings);
    //void showAllImages(List<ModelSora>  integers);
    void showAllImages(List<Integer>  integers);

    void showAnimation();

}
