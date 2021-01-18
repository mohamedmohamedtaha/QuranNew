package com.MohamedTaha.Imagine.New.ui.activities;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
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
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.EmptyResultSetException;

import com.MohamedTaha.Imagine.New.AppConstants;
import com.MohamedTaha.Imagine.New.BuildConfig;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ShowGuide;
import com.MohamedTaha.Imagine.New.YoutubeActivity;
import com.MohamedTaha.Imagine.New.dagger2.MainApplication;
import com.MohamedTaha.Imagine.New.dagger2.component.RetrofitComponent;
import com.MohamedTaha.Imagine.New.dagger2.module.NavigationDrawaberPresenterModule;
import com.MohamedTaha.Imagine.New.dagger2.module.SharedPreferencesModule;
import com.MohamedTaha.Imagine.New.dagger2.named.APIServiceBase;
import com.MohamedTaha.Imagine.New.dagger2.named.APIServiceCity;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection;
import com.MohamedTaha.Imagine.New.mvp.interactor.NavigationDrawarInteractor;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.getCity.GetCity;
import com.MohamedTaha.Imagine.New.mvp.view.NavigationDrawaberView;
import com.MohamedTaha.Imagine.New.notification.morningAzkar.MorningAzkarNotificationHelper;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.AlarmUtils;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime;
import com.MohamedTaha.Imagine.New.notification.quran.NotificationHelper;
import com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryMonth;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.room.DatabaseCallback;
import com.MohamedTaha.Imagine.New.room.TimingsAppDatabase;
import com.MohamedTaha.Imagine.New.room.TimingsViewModel;
import com.MohamedTaha.Imagine.New.service.GetDataEveryMonthJobService;
import com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.AzkarFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.SoundFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.PartsFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.SwarFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.MohamedTaha.Imagine.New.helper.Images.IMAGES;
import static com.MohamedTaha.Imagine.New.helper.Images.addImagesList;
import static com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection.isInternet;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;
import static com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryMonth.enableBootReceiverEveryMonth;
import static com.MohamedTaha.Imagine.New.room.TimingsViewModel.store_date_today;
import static com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity.IS_TRUE;
import static com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment.AZAN_DEFUALT;
import static com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment.COMPARE_METHOD;

public class NavigationDrawaberActivity extends AppCompatActivity implements NavigationDrawaberView, DatabaseCallback, NavigationView.OnNavigationItemSelectedListener {
    private static final String SAVE_STATE_VIEW_PAGER = "save_state_view_pager";
    public static final String IS_FIRST_TIME_WAY_USING = "way_sueing";
    public static final String IS_FIRST_TIME_PRAYER_TIME_EVERYDAY = "prayer_time_everyday";
    public static final String SAVE_PAGE = "savepage";
    public static final String SAVE_ALL_IMAGES = "save_all_images";
    public static final String FOR_GET_FRAGMENT_AZAN = "fragemnt_azan";
    public static final String CHECKISDATAORNOTINDATABASE = "store_date_today";
    private static final String TAG = "NavigationDrawaberActiv";
    @BindView(R.id.toolbar)
    Toolbar toobar;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_header)
    NavigationView navigationView;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    SharedPreferences.Editor editor;
    @Inject
    ShowGuide showGuide;
    @Inject
    NavigationDrawarInteractor presenter;
    private int current_fragment;
    public static MaterialSearchView searchView;
    String appPackageName;
    private TimingsViewModel timingsViewModel;
    public static String store_city_name = null;
    private String repear;
    private String number_azan_default;
    String city_name = null;
    int geocoderMaxResults = 1;
    @Inject
    @APIServiceBase
    APIServices apiServices;
    @Inject
    @APIServiceCity
    APIServices apiServicesForCity;

    public static boolean checkIsGetData = false;

    private Boolean isAzkarTrue;
    private CompositeDisposable disposable;
    String repearAsync = null;
    boolean isFirstTime;

    private void enableStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawable);
        ButterKnife.bind(this);

        RetrofitComponent retrofitComponent = ((MainApplication)getApplication()).getRetrofitComponent();
        retrofitComponent.appComponentFactory().create(new NavigationDrawaberPresenterModule(this,this),new SharedPreferencesModule(this)).inject(this);

//        AppComponent appComponent = DaggerAppComponent.factory().create(retrofitComponent,new NavigationDrawaberPresenterModule(this,this),new SharedPreferencesModule(this));
//                appComponent.inject(this);
//        AppComponent showGiudeComponent = DaggerAppComponent.builder().sharedPreferencesModule(new SharedPreferencesModule(this))
//                .navigationDrawaberPresenterModule(new NavigationDrawaberPresenterModule(this, this)).build();
//
//        showGiudeComponent.inject(this);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        enableStrictMode();

        Log.d("TAG", "doInBackground - Thread On create " + Thread.currentThread().getId() + "Name : "
                + Thread.currentThread().getName());

//        //getString Retrieve a String value from the Preference
        repear = sharedPreferences.getString(getString(R.string.settings_method_key),
                getString(R.string.settings_method_default));
        //checkIsFragmentAzanIsOpen();
        //apiServicesForCity = getRetrofitForCity().create(APIServices.class);
        //apiServices = getRetrofit().create(APIServices.class);
        Log.i("TAG", " onSuccess apiServices " + apiServices);
        Log.i("TAG", " onSuccess apiServicesForCity " + apiServicesForCity);


        appPackageName = getPackageName();

        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        if (timingsViewModel.isNewlyCreated && savedInstanceState != null) {
            timingsViewModel.restoreState(savedInstanceState);
            Log.i("TAG", " onSuccess timingsViewModel navigation " + store_date_today);
        }

        timingsViewModel.isNewlyCreated = false;
        getDateTodayFromDatabase();
        getCityName();
        //for show way using
        boolean isFirstTimeWayUsing = SharedPerefrenceHelper.getBooleanForWayUsing(getApplicationContext(), IS_FIRST_TIME_WAY_USING, false);
        if (!isFirstTimeWayUsing) {
            Log.d(TAG, "onCreate: showGuide : " + showGuide);
            showGuide.getGuide(NavigationDrawaberActivity.this, toobar, navView);
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
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        SwarFragment swarFragment = (SwarFragment) fragmentManager.findFragmentByTag("TAG_WORKER");
        if (savedInstanceState != null) {
            current_fragment = savedInstanceState.getInt(SAVE_STATE_VIEW_PAGER);
            navView.setSelectedItemId(current_fragment);
            Log.d("TAG", "Current fragment  three is :" + current_fragment);
        } else {
            navView.setSelectedItemId(R.id.read_quran);
        }
        setSupportActionBar(toobar);
        //for change color text toolbar
        toobar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toobar, R.string.open_drawer
                , R.string.close_drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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

    private boolean getSharedPreferencesThreadSeperateBoolean() {
        disposable = new CompositeDisposable();
        Observable<Boolean> modelAzkarObservable = Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    //getString Retrieve a String value from the Preference
                    isFirstTime = SharedPerefrenceHelper.getBooleanForWayUsing(getApplicationContext(), IS_FIRST_TIME_WAY_USING, false);

                } catch (Exception e) {
                }
                return isFirstTime;
            }
        }).subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread());
        disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Boolean repearAsync) {
                if (repearAsync != null) {
                    isFirstTime = repearAsync;
                    Log.d("TAG", "onNext Prefrences  ");
                    Log.d("TAG", "doInBackground - Thread " + Thread.currentThread().getId() + "Name : "
                            + Thread.currentThread().getName());
                }
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                if (repearAsync != null) {
                    Log.d("TAG", "onError Prefrences ");

                }
            }

            @Override
            public void onComplete() {
                if (repearAsync != null) {
                    Log.d("TAG", "onComplete Prefrences ");
                    Log.d("TAG", "doInBackground - Thread " + Thread.currentThread().getId() + "Name : "
                            + Thread.currentThread().getName());
                }
            }
        }));
        return isFirstTime;
    }

    private void getDateTodayFromDatabase() {
        Log.i("TAG", " getDateTodayFromDatabase");
        String date = convertDate();
        Log.d("TAG", " date " + date);
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
                        Log.i("TAG", " timingsViewModel.store_date_today onSuccess" + store_date_today);
                        getPrayerTimesEveryMonth(getApplicationContext());

                        enableBootReceiverEveryMonth(getApplicationContext());

                        ScheduleGetDataEveryMonth();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("TAG", "  onError " + e);
                        if (e instanceof EmptyResultSetException) {
                            isNetworkConnected(getApplicationContext());
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
                    Log.i("TAG", " timingsViewModel.store_date_today second method" + store_date_today);

                    // if (store_date_today <= 0) {
                    getPrayerTimesEveryMonth(getApplicationContext());
                    enableBootReceiverEveryMonth(getApplicationContext());
                    // Log.i("TAG", "store_date_today yes : " + store_date_today);
                    //    isNetworkConnected(this);
                    //    }
                }, e -> {
                    // Log.i("TAG", "e yes : " + store_date_today);
                    Toast.makeText(getApplicationContext(), "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });


        NotificationHelper notificationHelper = new NotificationHelper(getApplicationContext());
        notificationHelper.sendNotificationEveryHalfDay();
        notificationHelper.enableBootRecieiver();

        NotificationHelperPrayerTime notificationHelperPrayerTime = new NotificationHelperPrayerTime(getApplicationContext());
        notificationHelperPrayerTime.getPrayerTimesEveryday();
        notificationHelperPrayerTime.enableBootRecieiver();


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
                    SwarFragment swarFragment = new SwarFragment();
                    HelperClass.replece(swarFragment, getSupportFragmentManager(), R.id.frameLayout, FOR_GET_FRAGMENT_AZAN);
                    break;
                case R.id.read_parts:
                    //  NavigationDrawaberActivityVPager.setCurrentItem(1);
                    PartsFragment partsFragment = new PartsFragment();
                    HelperClass.replece(partsFragment, getSupportFragmentManager(), R.id.frameLayout);
                    break;
                case R.id.sound_quran:
                    //    NavigationDrawaberActivityVPager.setCurrentItem(2);
                    SoundFragment soundFragment = new SoundFragment();
                    HelperClass.replece(soundFragment, getSupportFragmentManager(), R.id.frameLayout);
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
            Log.d("TAG", "Current fragment  two is :" + current_fragment);
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        presenter.exitApp(searchView, navView, drawer);
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
        isAzkarTrue = sharedPreferences.getBoolean(getString(R.string.azkar_key), true);
        if (isAzkarTrue) {
            MorningAzkarNotificationHelper morningAzkarNotificationHelper = new MorningAzkarNotificationHelper(getApplicationContext());
            morningAzkarNotificationHelper.getAzkarTimesEveryday();
            morningAzkarNotificationHelper.enableBootRecieiver();
        } else {
            AlarmUtils alarm = new AlarmUtils(getApplicationContext());
            alarm.cancelAllAlarmForBroadcastAzkar();
        }

    }

    public static void getPrayerTimesEveryMonth(Context context) {
        int ALARM_TYPE_ELAPSED = 11;
        AlarmManager alarmManager;
        PendingIntent alarmPendingIntent;
        Intent intent = new Intent(context, GetPrayerTimesEveryMonth.class);
        intent.putExtra(CHECKISDATAORNOTINDATABASE, store_date_today);
        Log.d("TAG", "getPrayerTimesEveryMonth " + store_date_today);
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, 1);
        // setTime.set(Calendar.MINUTE, 30);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                setTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);
    }

    private void ScheduleGetDataEveryMonth() {
        PersistableBundle extras = new PersistableBundle();
        extras.putInt(CHECKISDATAORNOTINDATABASE, store_date_today);
        ComponentName componentName = new ComponentName(this, GetDataEveryMonthJobService.class);
        JobInfo info = new JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setExtras(extras).build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.schedule(info);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_youtube:
                Intent intent = new Intent(NavigationDrawaberActivity.this, YoutubeActivity.class);
                startActivity(intent);
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
        startActivity(intent);
    }

    @Override
    public void getSendUs(Intent intentEmail) {
        startActivity(intentEmail);
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
        timingsViewModel.saveState(outState);
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
        }
//        else if (requestCode == AzanFragment.LOCATION_PERMISSION_REQUEST_CODE) {
//            Log.i("TAG", "LOCATION_PERMISSION_REQUEST_CODE");
//        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

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
                        Log.d("TAG", "TimingsAppDatabase DeletePrayerTimes AddPrayerTimes Every day ");
                        TimingsAppDatabase.getInstance(context).DeletePrayerTimes(NavigationDrawaberActivity.this);
                    }
                }
            }
        }, 1000);
    }

    @Override
    public void onPrayerTimesAdded() {
        SharedPerefrenceHelper.putStringCompareMethod(this, COMPARE_METHOD, repear);
        SharedPerefrenceHelper.putStringAzan(this, AZAN_DEFUALT, number_azan_default);
        changeValueInListPreference();
        changeValueInListPreferenceForAzan();

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
        Log.d("TAG", "getCity");
        // checkIsFragmentAzanIsOpen();
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
                        getMethodPreferences(city.getCountry());
                        getPrayerTimesByCity(context, city.getCity(), city.getCountry(), Integer.valueOf(repear), city_name);
                        Log.d("TAG", " repear is : " + repear);

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

    private String getMethodPreferences(String country_name) {
        if (country_name.equals(getString(R.string.saudi_arabia))) {
            customForPreferences(getString(R.string.settings_method_umm_al_qura_university_makkah_value), getString(R.string.settings_method_umm_al_qura_university_makkah_value));
            customForPreferencesAzan(getString(R.string.settings_azan_elharam_elmaky_value), getString(R.string.settings_azan_elharam_elmaky_value));
        } else if (country_name.equals(getString(R.string.qatar))) {
            customForPreferences(getString(R.string.settings_method_qatar_value), getString(R.string.settings_method_qatar_value));
            customForPreferencesAzan(getString(R.string.settings_azan_elharam_elmaky_value), getString(R.string.settings_azan_elharam_elmaky_value));
        } else if (country_name.equals(getString(R.string.kuwait))) {
            customForPreferences(getString(R.string.settings_method_kuwait_value), getString(R.string.settings_method_kuwait_value));
            customForPreferencesAzan(getString(R.string.settings_azan_elharam_elmaky_value), getString(R.string.settings_azan_elharam_elmaky_value));
        } else if (country_name.equals(getString(R.string.turkey))) {
            customForPreferences(getString(R.string.settings_method_diyanet_işleri_başkanlığı_turkey_value), getString(R.string.settings_method_diyanet_işleri_başkanlığı_turkey_value));
            customForPreferencesAzan(getString(R.string.settings_azan_elharam_elmaky_value), getString(R.string.settings_azan_elharam_elmaky_value));
        } else if (country_name.equals(getString(R.string.russia))) {
            customForPreferences(getString(R.string.settings_method_spiritual_administration_of_muslims_of_russia_value), getString(R.string.settings_method_spiritual_administration_of_muslims_of_russia_value));
            customForPreferencesAzan(getString(R.string.settings_azan_elharam_elmaky_value), getString(R.string.settings_azan_elharam_elmaky_value));
        } else {
            customForPreferences(getString(R.string.settings_method_key), getString(R.string.settings_method_default));
            customForPreferencesAzan(getString(R.string.settings_azan_abdelbaset_value), getString(R.string.settings_azan_abdelbaset_value));
        }
        return repear;
    }

    private String customForPreferences(String method_key, String method_default) {
        repear = sharedPreferences.getString(method_key, method_default);
        return number_azan_default;
    }

    private String customForPreferencesAzan(String method_key, String method_default) {
        number_azan_default = sharedPreferences.getString(method_key, method_default);
        return number_azan_default;
    }

    private void changeValueInListPreference() {
        //  sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //  SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.settings_method_key), repear);
        editor.commit();
    }

    private void changeValueInListPreferenceForAzan() {
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.settings_azan_key), number_azan_default);
        editor.commit();
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
                        Log.d("TAG", " cityName : " + cityName);

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
            checkIsGetData = true;
            getCity(this);
        }
    }

    @Override
    public void getDataFromLocationAfterDeleteData() {

    }

    @Override
    public void onPrayerTimesError(Throwable e) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.el_arbaoon_elnawawy) {
            HelperClass.startActivity(getApplicationContext(), ElarbaoonElnawawyActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        } else if (id == R.id.use_way) {
            SharedPerefrenceHelper.removeDataForWayUsing(this);
            HelperClass.startActivity(getApplicationContext(), SplashActivity.class);
        } else if (id == R.id.action_share) {
            presenter.shareApp(getString(R.string.about), appPackageName);
        } else if (id == R.id.action_rate) {
            presenter.actionRate(appPackageName);

        } else if (id == R.id.action_send_us) {
            presenter.sendUs();
        } else if (id == R.id.action_settings) {
            HelperClass.startActivity(getApplicationContext(), SettingsActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}