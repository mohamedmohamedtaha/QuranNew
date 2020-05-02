package com.MohamedTaha.Imagine.New.ui.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.MohamedTaha.Imagine.New.AppConstants;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ShowGuide;
import com.MohamedTaha.Imagine.New.databinding.ActivityNavigationDrawaberBinding;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.New.informationInrto.TapTargetSequence;
import com.MohamedTaha.Imagine.New.mvp.interactor.NavigationDrawarInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.NavigationDrawarPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.NavigationDrawarView;
import com.MohamedTaha.Imagine.New.notification.quran.AlarmReceiver;
import com.MohamedTaha.Imagine.New.notification.quran.NotificationHelper;
import com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryDay;
import com.MohamedTaha.Imagine.New.room.TimingsViewModel;
import com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.AzkarFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.FragmentSound;
import com.MohamedTaha.Imagine.New.ui.fragments.GridViewFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.PartsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Calendar;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.helper.Images.IMAGES;
import static com.MohamedTaha.Imagine.New.helper.Images.addImagesList;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;
import static com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity.IS_TRUE;
import static com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment.SAVE_ALL_IMAGES;
import static com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment.SAVE_PAGE;

public class NavigationDrawaberActivity extends AppCompatActivity implements NavigationDrawarView {
    private static final String SAVE_STATE_VIEW_PAGER = "save_state_view_pager";
    public static final String IS_FIRST_TIME_WAY_USING = "way_sueing";
    private ActivityNavigationDrawaberBinding activityNavigationDrawaberBinding;
    private int current_fragment;
    public static MaterialSearchView searchView;
    String appPackageName;
    private NavigationDrawarPresenter presenter;
    MenuItem prevMenuItem;
    TapTargetSequence sequence;
    private TimingsViewModel timingsViewModel;
    public static int store_date_today = 0;
    public static String store_city_name =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNavigationDrawaberBinding = ActivityNavigationDrawaberBinding.inflate(getLayoutInflater());
        View view = activityNavigationDrawaberBinding.getRoot();
        setContentView(view);

        presenter = new NavigationDrawarInteractor(this);
        appPackageName = getPackageName();

        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        getDateTodayFromDatabase();
        getCityName();
        //for show way using
        if (!SharedPerefrenceHelper.getBooleanForWayUsing(getApplicationContext(), IS_FIRST_TIME_WAY_USING, false)) {
            ShowGuide showGuide = new ShowGuide(NavigationDrawaberActivity.this, activityNavigationDrawaberBinding.toobar,
                    activityNavigationDrawaberBinding.navView);
            SharedPerefrenceHelper.putBooleanForWayUsing(getApplicationContext(), IS_FIRST_TIME_WAY_USING, true);
        }
        //For open on the save pages immediately
        if (SharedPerefrenceHelper.getBoolean(getApplicationContext(), IS_TRUE, false)) {
            addImagesList();
            Intent intent = new Intent(getApplicationContext(), SwipePagesActivity.class);
            intent.putIntegerArrayListExtra(SAVE_ALL_IMAGES, (ArrayList<Integer>) IMAGES);
            intent.putExtra(SAVE_PAGE, true);
            startActivity(intent);
            overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
        }
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        activityNavigationDrawaberBinding.navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState != null) {
            int save = savedInstanceState.getInt(SAVE_STATE_VIEW_PAGER);
            activityNavigationDrawaberBinding.navView.setSelectedItemId(save);
        } else {
            activityNavigationDrawaberBinding.navView.setSelectedItemId(R.id.read_quran);
        }
        setSupportActionBar(activityNavigationDrawaberBinding.toobar);
        //for change color text toolbar
        activityNavigationDrawaberBinding.toobar.setTitleTextColor(Color.parseColor("#FFFFFF"));

//        NavigationDrawaberActivityVPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                if (prevMenuItem != null){
//                    prevMenuItem.setChecked(false);
//                }else {
//                    navView.getMenu().getItem(0).setChecked(false);
//                }
//                navView.getMenu().getItem(position).setChecked(true);
//                prevMenuItem = navView.getMenu().getItem(position);
//                if (position != current_fragment) {
//                    current_fragment = position;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        setupViewPager(NavigationDrawaberActivityVPager);
//
    }
    private void getDateTodayFromDatabase(){
        // For get Date today
        timingsViewModel.getTimingsByDataToday(convertDate()).
                subscribeOn(Schedulers.trampoline())
                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(date_today -> {
                    store_date_today = date_today;
                    Log.i("TAG", "date today from data base : " + store_date_today);
                    //  Toast.makeText(getActivity(), "date today is " + date_today, Toast.LENGTH_SHORT).show();
                }, e -> {
                    Toast.makeText(getApplicationContext(), "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });
    }
    private void getCityName(){
        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        Flowable<String> getCityName = timingsViewModel.getCityName();
        getCityName.subscribeOn(Schedulers.io())
                .subscribe(city_name -> {
                    store_city_name = city_name;
                    Log.d("TAG", "City name from database is :" + store_city_name);
                },error -> {
                    Log.d("TAG", "City name from database is :" + error.getMessage());

                });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (id == current_fragment) {
                return false;
            }
            switch (id) {
                case R.id.read_quran:
                    //NavigationDrawaberActivityVPager.setCurrentItem(0);
                    GridViewFragment gridViewFragment = new GridViewFragment();
                    HelperClass.replece(gridViewFragment, getSupportFragmentManager(), R.id.frameLayout);
                    break;
                case R.id.read_parts:
                    //  NavigationDrawaberActivityVPager.setCurrentItem(1);
                    PartsFragment partsFragment = new PartsFragment();
                    HelperClass.replece(partsFragment, getSupportFragmentManager(), R.id.frameLayout);
                    break;
                case R.id.sound_quran:
                    //    NavigationDrawaberActivityVPager.setCurrentItem(2);
                    FragmentSound fragmentSound = new FragmentSound();
                    HelperClass.replece(fragmentSound, getSupportFragmentManager(), R.id.frameLayout);
                    break;
                case R.id.azkar:
                    AzkarFragment azkarFragment = new AzkarFragment();
                    HelperClass.replece(azkarFragment, getSupportFragmentManager(), R.id.frameLayout);
                    break;
                case R.id.prayer_times:
                    AzanFragment azanFragment = new AzanFragment();
                    HelperClass.replece(azanFragment, getSupportFragmentManager(), R.id.frameLayout);
                    break;
            }
            current_fragment = id;
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        presenter.exitApp(searchView, activityNavigationDrawaberBinding.navView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem SearchItem = menu.findItem(R.id.action_search);
        searchView.setMenuItem(SearchItem);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //For Settings Notifications
        NotificationHelper.sendNotificationEveryHalfDay(getApplicationContext());
        NotificationHelper.enableBootRecieiver(getApplicationContext());

//        sendNotificationForPrayerTime(getApplicationContext());
//        enableBootRecieiver(getApplicationContext());
        getPrayeeTimesEveryday(getApplicationContext());
    }
    public static void getPrayeeTimesEveryday(Context context){
        int ALARM_TYPE_ELAPSED = 101;
        AlarmManager alarmManager;
        PendingIntent alarmPendingIntent;
        Intent intent = new Intent(context, GetPrayerTimesEveryDay.class);
        //  Intent intent = new Intent(context, ServiceForNotificationImage.class);
        Log.d("TAG", "ServiceForNotificationImage ");
        //Setting pending intent to respond to broadcast sent by AlarmManager every day at 8am
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, 1);
        setTime.set(Calendar.MINUTE, 05);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                AlarmManager.INTERVAL_DAY,
                setTime.getTimeInMillis(), alarmPendingIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                presenter.shareApp(getString(R.string.about), appPackageName);
                break;
            case R.id.action_send_us:
                presenter.sendUs();
                break;
            case R.id.action_use_way:
                SharedPerefrenceHelper.removeDataForWayUsing(this);
                HelperClass.startActivity(getApplicationContext(), SplashActivity.class);
                break;
            case R.id.action_settings:
                HelperClass.startActivity(getApplicationContext(), SettingsActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
                break;
            case R.id.action_rate:
                presenter.actionRate(appPackageName);
                break;
            case R.id.action_elarbaoon_elnawawy:
                HelperClass.startActivity(getApplicationContext(), ElarbaoonElnawawyActivity.class);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMessageExitApp() {
        HelperClass.customToast(this, getString(R.string.exit_app));
    }

    @Override
    public void exitApp() {
        HelperClass.closeApp(getApplicationContext());
    }

    @Override
    public void getShareApp(Intent intent) {
        startActivity(Intent.createChooser(intent, getString(R.string.shareApp)));
    }

    @Override
    public void getSendUs(Intent intentEmail) {
        if (intentEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(intentEmail);
        } else {
            HelperClass.customToast(this, getString(R.string.notSupport));
        }
    }

    @Override
    public void getRateApp(Intent rateApp) {
        startActivity(rateApp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_VIEW_PAGER, current_fragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.GPS_REQUEST) {
            Log.i("TAG Navigation drawaber", "Skipped");
            AzanFragment azanFragment = new AzanFragment();
            HelperClass.replece(azanFragment, getSupportFragmentManager(), R.id.frameLayout);
            azanFragment.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == AzanFragment.LOCATION_PERMISSION_REQUEST_CODE) {
            Log.i("TAG", "LOCATION_PERMISSION_REQUEST_CODE");
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
//    private void setupViewPager(ViewPager viewPager){
//        AdapterForNavigation adapterForNavigation = new AdapterForNavigation(getSupportFragmentManager());
//        GridViewFragment gridViewFragment = new GridViewFragment();
//          PartsFragment partsFragment = new PartsFragment();
//           FragmentSound fragmentSound = new FragmentSound();
//           adapterForNavigation.addFragment(gridViewFragment);
//        adapterForNavigation.addFragment(partsFragment);
//        adapterForNavigation.addFragment(fragmentSound);
//        viewPager.setAdapter(adapterForNavigation);
//    }
}