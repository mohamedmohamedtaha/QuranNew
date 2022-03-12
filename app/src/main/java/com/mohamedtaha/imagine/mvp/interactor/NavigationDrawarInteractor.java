package com.mohamedtaha.imagine.mvp.interactor;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.helper.HelperClass;
import com.mohamedtaha.imagine.mvp.presenter.NavigationDrawarPresenter;
import com.mohamedtaha.imagine.mvp.view.NavigationDrawaberView;

import java.util.List;

//@ScopeActivity
public class NavigationDrawarInteractor implements NavigationDrawarPresenter {
    Intent intent, intentEmail, IntentRateApp;
    private NavigationDrawaberView navigationDrawaberView;
    private Boolean exitApp = false;
    Activity context;
    private static final String TAG = "NavigationDrawarInterac";
    public static final String GOOGLE_ACCOUNT_ID = "https://play.google.com/store/apps/details?id=";
    public static final String NAME_EMAIL = "mohamed01007919166@gmail.com";
    public static final String MARKET_ID = "market://details?id=";
    public NavigationDrawarInteractor(NavigationDrawaberView navigationDrawaberView, Activity context) {
        this.navigationDrawaberView = navigationDrawaberView;
        this.context = context;
    }

    @Override
    public void onDestroy() {
        navigationDrawaberView = null;
    }

//    @Override
//    public void exitApp(MaterialSearchView searchView, BottomNavigationView navView, DrawerLayout drawerLayout) {
//        if (searchView.isSearchOpen()) {
//            searchView.closeSearch();
//        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            if (exitApp) {
//                navigationDrawaberView.exitApp();
//                return;
//            }
//            exitApp = true;
//            navigationDrawaberView.showMessageExitApp();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    exitApp = false;
//                }
//            }, 2000);
//        }
//    }

    @Override
    public void shareApp(String aboutString, String appPackageName) {
        intent.setAction(Intent.ACTION_SEND);
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if (resolveInfos.size() > 1) {
            try {
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, R.string.nameSora);
                String about = aboutString + GOOGLE_ACCOUNT_ID + appPackageName;
                intent.putExtra(Intent.EXTRA_TEXT, about);
                Intent intentChooser = Intent.createChooser(intent, context.getString(R.string.shareApp));
                navigationDrawaberView.getShareApp(intentChooser);
            } catch (Exception e) {
                e.toString();
            }
        } else if (intent.resolveActivity(context.getPackageManager()) != null) {
            navigationDrawaberView.getShareApp(intent);
        } else {
            HelperClass.customToast(context, context.getString(R.string.notShare));
        }
    }

    @Override
    public void sendUs() {
        intentEmail.setAction(Intent.ACTION_SEND);
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(intentEmail, PackageManager.MATCH_ALL);
        if (resolveInfos.size() > 1) {
            intentEmail.setData(Uri.parse("mailto:"));
            intentEmail.setType("message/rfc2822");
            intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{NAME_EMAIL});
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.subject));
            intentEmail.putExtra(Intent.EXTRA_TEXT, context.getString(R.string.messageBody));
            Intent intentChooser = Intent.createChooser(intentEmail, context.getString(R.string.sendTo));
            navigationDrawaberView.getSendUs(intentChooser);
        } else if (intentEmail.resolveActivity(context.getPackageManager()) != null) {
            navigationDrawaberView.getSendUs(intentEmail);
        } else {
            HelperClass.customToast(context, context.getString(R.string.notSupport));
        }
    }

    @Override
    public void actionRate(String appPackageName) {
        IntentRateApp.setAction(Intent.ACTION_VIEW);
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(IntentRateApp, PackageManager.MATCH_ALL);
        if (resolveInfos.size() > 1) {
            try {
                //Open the Store and show the App
                IntentRateApp.setData(Uri.parse(MARKET_ID + appPackageName));
                Intent intentChooser = Intent.createChooser(IntentRateApp, context.getString(R.string.rateOur));
                navigationDrawaberView.getRateApp(intentChooser);
            } catch (ActivityNotFoundException e) {
                //In state store there is not open by The browser
                IntentRateApp.setData(Uri.parse(GOOGLE_ACCOUNT_ID + appPackageName));
                navigationDrawaberView.getRateApp(IntentRateApp);
            }
        } else if (IntentRateApp.resolveActivity(context.getPackageManager()) != null) {
            navigationDrawaberView.getRateApp(IntentRateApp);
        } else {
            HelperClass.customToast(context, context.getString(R.string.notSupport));
        }
    }
}
