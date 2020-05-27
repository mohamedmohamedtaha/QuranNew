package com.MohamedTaha.Imagine.New.ui.activities;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.room.EmptyResultSetException;

import com.MohamedTaha.Imagine.New.AppConstants;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ShowGuide;
import com.MohamedTaha.Imagine.New.databinding.ActivityNavigationDrawaberBinding;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection;
import com.MohamedTaha.Imagine.New.informationInrto.TapTargetSequence;
import com.MohamedTaha.Imagine.New.mvp.interactor.NavigationDrawarInteractor;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.getCity.GetCity;
import com.MohamedTaha.Imagine.New.mvp.presenter.NavigationDrawarPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.NavigationDrawarView;
import com.MohamedTaha.Imagine.New.notification.quran.NotificationHelper;
import com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryDay;
import com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryMonth;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.room.DatabaseCallback;
import com.MohamedTaha.Imagine.New.room.TimingsAppDatabase;
import com.MohamedTaha.Imagine.New.room.TimingsViewModel;
import com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.AzkarFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.FragmentSound;
import com.MohamedTaha.Imagine.New.ui.fragments.GridViewFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.PartsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.MohamedTaha.Imagine.New.helper.Images.IMAGES;
import static com.MohamedTaha.Imagine.New.helper.Images.addImagesList;
import static com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection.isInternet;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.enableBootRecieiver;
import static com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryMonth.enableBootReceiverEveryMonth;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClient.getRetrofit;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClientCity.getRetrofitForCity;
import static com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity.IS_TRUE;
import static com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment.COMPARE_METHOD;
import static com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment.SAVE_ALL_IMAGES;
import static com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment.SAVE_PAGE;

public class NavigationDrawaberActivity extends AppCompatActivity implements NavigationDrawarView, DatabaseCallback {
    private static final String SAVE_STATE_VIEW_PAGER = "save_state_view_pager";
    public static final String IS_FIRST_TIME_WAY_USING = "way_sueing";
    public static final String FOR_GET_FRAGMENT_AZAN = "fragemnt_azan";
    public static final String CHECKISDATAORNOTINDATABASE = "store_date_today";

    private ActivityNavigationDrawaberBinding activityNavigationDrawaberBinding;
    private int current_fragment;
    public static MaterialSearchView searchView;
    String appPackageName;
    private NavigationDrawarPresenter presenter;
    MenuItem prevMenuItem;
    TapTargetSequence sequence;
    private TimingsViewModel timingsViewModel;
    public static int store_date_today = 0;
    public static String store_city_name = null;
    private SharedPreferences sharedPreferences;
    private String repear;
    private String compare_methods = null;
    APIServices apiServicesForCity;
    String city_name = null;
    int geocoderMaxResults = 1;
    APIServices apiServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNavigationDrawaberBinding = ActivityNavigationDrawaberBinding.inflate(getLayoutInflater());
        View view = activityNavigationDrawaberBinding.getRoot();
        setContentView(view);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //getString Retrieve a String value from the Preference
        repear = sharedPreferences.getString(getString(R.string.settings_method_key),
                getString(R.string.settings_method_default));


        apiServicesForCity = getRetrofitForCity().create(APIServices.class);
        apiServices = getRetrofit().create(APIServices.class);

        presenter = new NavigationDrawarInteractor(this);
        appPackageName = getPackageName();

        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        getDateTodayFromDatabase(this);
        getCityName();
        //for show way using
        if (!SharedPerefrenceHelper.getBooleanForWayUsing(getApplicationContext(), IS_FIRST_TIME_WAY_USING, false)) {
            ShowGuide showGuide = new ShowGuide(NavigationDrawaberActivity.this, activityNavigationDrawaberBinding.toobar,
                    activityNavigationDrawaberBinding.navView);
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        GridViewFragment gridViewFragment = (GridViewFragment) fragmentManager.findFragmentByTag("TAG_WORKER");
        if (savedInstanceState != null) {
            current_fragment = savedInstanceState.getInt(SAVE_STATE_VIEW_PAGER);
            activityNavigationDrawaberBinding.navView.setSelectedItemId(current_fragment);
            Log.d("TAG", "Current fragment  three is :" + current_fragment);

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

    private void getDateTodayFromDatabase(Context context) {
        timingsViewModel.checkIsDateTodayFind(convertDate()).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onSuccess(Integer integer) {
                        Log.i("TAG", " onSuccess " + integer);
                        store_date_today = integer;
                        getPrayerTimesEveryMonth(getApplicationContext());
                        enableBootReceiverEveryMonth(getApplicationContext());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "  onError " + e);
                        if (e instanceof EmptyResultSetException) {
                           isNetworkConnected(context);
                        } else {
                            Log.i("TAG", "  MonError " + e);
                        }

                    }
                }
        );

        timingsViewModel.getTimingsByDataToday(convertDate()).
                subscribeOn(Schedulers.trampoline())
                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(date_today -> {
                    store_date_today = date_today;
                    Log.i("TAG", "date today from data base : " + store_date_today);
                    //____________________________ Get prayer times from internet every month
                    // if (store_date_today <= 0) {
                    getPrayerTimesEveryMonth(getApplicationContext());
                    enableBootReceiverEveryMonth(getApplicationContext());
                    Log.i("TAG", "store_date_today yes : " + store_date_today);
                    //    isNetworkConnected(this);
                    //    }
                }, e -> {
                    Log.i("TAG", "e yes : " + store_date_today);

                    Toast.makeText(getApplicationContext(), "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });
    }

    private void getCityName() {
        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        Flowable<String> getCityName = timingsViewModel.getCityName();
        getCityName.subscribeOn(Schedulers.io())
                .subscribe(city_name -> {
                    store_city_name = city_name;
                    Log.d("TAG", "City name from database is :" + store_city_name);
                }, error -> {
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
            if (searchView.isSearchOpen()) {
                searchView.closeSearch();
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
                    HelperClass.replece(azanFragment, getSupportFragmentManager(), R.id.frameLayout, FOR_GET_FRAGMENT_AZAN);
                    break;
            }
            current_fragment = id;
            Log.d("TAG", "Current fragment  two is :" + current_fragment);
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
        NotificationHelper.sendNotificationEveryHalfDay(getApplicationContext());
        NotificationHelper.enableBootRecieiver(getApplicationContext());

        getPrayerTimesEveryday(getApplicationContext());
        enableBootRecieiver(getApplicationContext());
    }

    private void checkIsFragmentAzanIsOpen() {
        AzanFragment azanFragment = (AzanFragment) getSupportFragmentManager().findFragmentByTag(FOR_GET_FRAGMENT_AZAN);
        if (azanFragment != null && azanFragment.isVisible()) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            //getString Retrieve a String value from the Preference
            repear = sharedPreferences.getString(getString(R.string.settings_method_key),
                    getString(R.string.settings_method_default));
            compare_methods = SharedPerefrenceHelper.getStringCompareMethod(this, COMPARE_METHOD, null);

            if (compare_methods != null && !compare_methods.equals(repear)) {
                HelperClass.replece(azanFragment, getSupportFragmentManager(), R.id.frameLayout, FOR_GET_FRAGMENT_AZAN);
            }
            {
                Toast.makeText(this, "not " + repear + " : " + compare_methods, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void getPrayerTimesEveryday(Context context) {
        int ALARM_TYPE_ELAPSED = 10;
        AlarmManager alarmManager;
        PendingIntent alarmPendingIntent;
        Intent intent = new Intent(context, GetPrayerTimesEveryDay.class);
        //  Intent intent = new Intent(context, ServiceForNotificationImage.class);
        Log.d("TAG", "getPrayerTimesEveryday ");
        //Setting pending intent to respond to broadcast sent by AlarmManager every day at 8am
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, 1);
        //   setTime.set(Calendar.SECOND, 21);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                setTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);
    }

    public static void getPrayerTimesEveryMonth(Context context) {
        int ALARM_TYPE_ELAPSED = 11;
        AlarmManager alarmManager;
        PendingIntent alarmPendingIntent;
        Intent intent = new Intent(context, GetPrayerTimesEveryMonth.class);
        intent.putExtra(CHECKISDATAORNOTINDATABASE, store_date_today);
        Log.d("TAG", "getPrayerTimesEveryMonth ");
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, 10);
       // setTime.set(Calendar.MINUTE, 30);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                setTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);
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
                // SharedPerefrenceHelper.removeDataForCompareMethod(this);
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }
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
        Log.d("TAG", "Current fragment is :" + current_fragment);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.GPS_REQUEST) {
            Log.i("TAG Navigation drawaber", "Skipped");
            AzanFragment azanFragment = new AzanFragment();
            Bundle bundle = new Bundle();
            HelperClass.replece(azanFragment, getSupportFragmentManager(), R.id.frameLayout);
            bundle.putInt("bundle", resultCode);
            azanFragment.setArguments(bundle);
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

    private void isNetworkConnected(Context context) {
        NoInternetConnection noInternetConnection = new NoInternetConnection();
        noInternetConnection.execute("http://clients3.google.com/generate_204");
        boolean isConnected = NetworkConnection.networkConnectivity(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isConnected) {

                } else {
                    if (!isInternet()) {

                    } else {
                        Log.d("TAG", "TimingsAppDatabase");
                        TimingsAppDatabase.getInstance(context).DeletePrayerTimes(NavigationDrawaberActivity.this);
                    }
                }
            }
        }, 1000);
    }

    @Override
    public void onPrayerTimesAdded() {

    }

    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.i("TAG", " Granted fisrt");
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void getCity(Context context) {
        Log.d("TAG", "getPrayerTimesByCity");
        Call<GetCity> getCityCall = apiServicesForCity.getCity();
        getCityCall.enqueue(new Callback<GetCity>() {
            @Override
            public void onResponse(Call<GetCity> call, Response<GetCity> response) {
                GetCity city = response.body();
                try {
                    if (city.getStatus().equals("success")) {
                        Log.d("TAG", city.getCity() + " : " + city.getCountry());
                        city_name = getCityNameWithoutLocation(city.getLat(), city.getLon());
                        Log.d("TAG", "City name in arabic getCity is : " + city_name);
                        getPrayerTimesByCity(context, city.getCity(), city.getCountry(), Integer.valueOf(repear), city_name);
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error getCity" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<GetCity> call, Throwable t) {
                Log.i("TAG", " onFailure " + t.getMessage());
            }
        });
    }

    private void getPrayerTimesByCity(Context context, String city, String country, int method, String city_name) {
        Call<Azan> azanCall = apiServices.getPrayerTimesByCity(city, country, false, method);
        azanCall.enqueue(new Callback<Azan>() {
            @Override
            public void onResponse(Call<Azan> call, Response<Azan> response) {
                Azan azan = response.body();
                try {
                    if (azan.getStatus().equals("OK")) {

                        TimingsAppDatabase.getInstance(context).AddPrayerTimesForMonth(NavigationDrawaberActivity.this, azan, city_name);
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error getPrayerTimesByCity " + e.getMessage());

                }
            }

            @Override
            public void onFailure(Call<Azan> call, Throwable t) {

            }
        });
    }

    private String getCityNameWithoutLocation(double latitude, double longitude) {
        String cityName = "";
        Locale locale = new Locale("ar");
        Geocoder geocoder = new Geocoder(this, locale);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, this.geocoderMaxResults);
            if (addresses.size() > 0) {
                for (Address adr : addresses) {
                    if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                        cityName = adr.getLocality();
                        Log.d("TAG" , " cityName : " +  cityName);

                        break;
                    }
                }
            }
            return cityName;
        } catch (IOException e) {
        }
        return null;
    }

    @Override
    public void onPrayerTimesDeleted() {
        if (isStoragePermissionGranted()) {
            Log.d("TAG", "onPrayerTimesDeleted");
            getCity(this);
        }
    }

    @Override
    public void getDataFromLocationAfterDeleteData() {

    }

    @Override
    public void onPrayerTimesError() {

    }

}