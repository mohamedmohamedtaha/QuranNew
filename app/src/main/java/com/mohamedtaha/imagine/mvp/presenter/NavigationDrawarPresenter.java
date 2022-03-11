package com.mohamedtaha.imagine.mvp.presenter;

public interface NavigationDrawarPresenter {
    void onDestroy();

//    void exitApp(MaterialSearchView searchView, BottomNavigationView navView, DrawerLayout drawerLayout);

    void shareApp(String aboutString, String appPackageName);

    void sendUs();

    void actionRate(String appPackageName);
}
