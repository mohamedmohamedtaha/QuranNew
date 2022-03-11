package com.mohamedtaha.imagine.mvp.view;

import android.content.Intent;

public interface NavigationDrawaberView {
    void showMessageExitApp();

    void exitApp();

    void getShareApp(Intent intent);

    void getSendUs(Intent intentEmail);

    void getRateApp(Intent rateApp);
}
