package com.MohamedTaha.Imagine.New.mvp.view;

import android.content.Intent;

public interface NavigationDrawarView {
    void showMessageExitApp();

    void exitApp();

    void getShareApp(Intent intent);

    void getSendUs(Intent intentEmail);

    void getRateApp(Intent rateApp);
}
