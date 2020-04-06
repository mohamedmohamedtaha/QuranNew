package com.MohamedTaha.Imagine.New.mvp.view;

import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;

import java.util.List;

public interface ElarbaoonElnwawyView {
    void showDataAfterQueryText(List<ElarbaoonElnawawyModel> stringList);
    void isEmpty();
    void thereData();
    void showProgress();
    void hideProgress();
    void showAllElahadeth(List<ElarbaoonElnawawyModel> strings);
    void showAnimation();
    void showAfterSearch();

}
