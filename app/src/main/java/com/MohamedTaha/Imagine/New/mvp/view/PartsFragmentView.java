package com.MohamedTaha.Imagine.New.mvp.view;

import com.MohamedTaha.Imagine.New.mvp.model.ModelSora;

import java.util.List;

public interface PartsFragmentView {
    void showAfterSearch();
    void showAfterQueryText(List<ModelSora> stringList);
    void isEmpty();
    void thereData();
    void showProgress();
    void hideProgress();
    void showAllINamePart(List<ModelSora> strings);
    void showAllImages(List<Integer> integers);
    void showAnimation();
}
