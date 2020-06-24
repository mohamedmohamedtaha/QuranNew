package com.MohamedTaha.Imagine.New.mvp.interactor;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.presenter.NavigationDrawarPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.NavigationDrawarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class NavigationDrawarInteractor implements NavigationDrawarPresenter {
    private NavigationDrawarView navigationDrawarView;
    private Boolean exitApp = false;
    public static final String GOOGLE_ACCOUNT_ID = "https://play.google.com/store/apps/details?id=";
    public static final String NAME_EMAIL = "mohamed01007919166@gmail.com";
    public static final String MARKET_ID = "market://details?id=";

    public NavigationDrawarInteractor(NavigationDrawarView navigationDrawarView) {
        this.navigationDrawarView = navigationDrawarView;
    }

    @Override
    public void onDestroy() {
        navigationDrawarView = null;

    }

    @Override
    public void exitApp(MaterialSearchView searchView, BottomNavigationView navView, DrawerLayout drawerLayout) {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (exitApp) {
                navigationDrawarView.exitApp();
                return;
            }
            exitApp = true;
            navigationDrawarView.showMessageExitApp();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exitApp = false;
                }
            }, 2000);
        }
    }

    @Override
    public void shareApp(String aboutString, String appPackageName) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, R.string.nameSora);
            String about = aboutString + GOOGLE_ACCOUNT_ID + appPackageName;
            intent.putExtra(Intent.EXTRA_TEXT, about);
            navigationDrawarView.getShareApp(intent);
        } catch (Exception e) {
            e.toString();
        }
    }

    @Override
    public void sendUs() {
        Intent intentEmail = new Intent(Intent.ACTION_SEND);
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.setType("message/rfc2822");
        intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{NAME_EMAIL});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intentEmail.putExtra(Intent.EXTRA_TEXT, "Message Body");
        intentEmail.createChooser(intentEmail, "Send mail...");
        navigationDrawarView.getSendUs(intentEmail);
    }

    @Override
    public void actionRate(String appPackageName) {
        Intent rateApp = null;
        try {
            //Open the Store and show the App
            rateApp = new Intent(Intent.ACTION_VIEW);
            rateApp.setData(Uri.parse(MARKET_ID + appPackageName));
            navigationDrawarView.getRateApp(rateApp);
        } catch (ActivityNotFoundException e) {
            //In state store there is not open by The browser
            //  Intent webRate = new Intent(Intent.ACTION_VIEW);
            rateApp.setData(Uri.parse(GOOGLE_ACCOUNT_ID + appPackageName));
            navigationDrawarView.getRateApp(rateApp);
        }
    }
}
