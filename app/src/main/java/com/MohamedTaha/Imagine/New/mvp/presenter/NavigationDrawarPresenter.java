package com.MohamedTaha.Imagine.New.mvp.presenter;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public interface NavigationDrawarPresenter {
    void onDestroy();

    void exitApp(MaterialSearchView searchView, BottomNavigationView navView, DrawerLayout drawerLayout);

    void shareApp(String aboutString, String appPackageName);

    void sendUs();

    void actionRate(String appPackageName);

}
