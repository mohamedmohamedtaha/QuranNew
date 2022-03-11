package com.mohamedtaha.imagine.mvp.view;

import com.mohamedtaha.imagine.mvp.model.ImageModel;

import java.util.List;

public interface ListSoundReaderView {
    void showProgress();
    void hideProgress();
    void noData();
    void thereData();
    void showAllData(List<ImageModel> imageModels);
    void showAfterSearch();
    void showAfterQueryText(List<ImageModel> lstFound);
    void showAnimation();
}
