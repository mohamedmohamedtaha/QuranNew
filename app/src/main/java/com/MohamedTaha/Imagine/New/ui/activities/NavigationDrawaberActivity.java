package com.MohamedTaha.Imagine.New.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.New.AppConstants;
import com.MohamedTaha.Imagine.New.databinding.ActivityNavigationDrawaberBinding;
import com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.New.informationInrto.TapTarget;
import com.MohamedTaha.Imagine.New.informationInrto.TapTargetSequence;
import com.MohamedTaha.Imagine.New.informationInrto.TapTargetView;
import com.MohamedTaha.Imagine.New.mvp.interactor.NavigationDrawarInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.NavigationDrawarPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.NavigationDrawarView;
import com.MohamedTaha.Imagine.New.notification.quran.NotificationHelper;
import com.MohamedTaha.Imagine.New.room.TimingsViewModel;
import com.MohamedTaha.Imagine.New.ui.fragments.AzkarFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.FragmentSound;
import com.MohamedTaha.Imagine.New.ui.fragments.GridViewFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.PartsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.helper.Images.IMAGES;
import static com.MohamedTaha.Imagine.New.helper.Images.addImagesList;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.enableBootRecieiver;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.sendNotificationForPrayerTime;
import static com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity.IS_TRUE;
import static com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment.SAVE_ALL_IMAGES;
import static com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment.SAVE_PAGE;

public class NavigationDrawaberActivity extends AppCompatActivity implements NavigationDrawarView {
    private static final String SAVE_STATE_VIEW_PAGER = "save_state_view_pager";
    public static final String IS_FIRST_TIME_WAY_USING = "way_sueing";
    private ActivityNavigationDrawaberBinding activityNavigationDrawaberBinding;

//    @BindView(R.id.nav_view)
//    BottomNavigationView navView;
//    @BindView(R.id.toobar)
//    Toolbar toobar;

//    @BindView(R.id.NavigationDrawaberActivity_VPager)
//    ViewPager NavigationDrawaberActivityVPager;
    private int current_fragment;
    public static MaterialSearchView searchView;
    String appPackageName;
    private NavigationDrawarPresenter presenter;
    MenuItem prevMenuItem;
    TapTargetSequence sequence;
    private TimingsViewModel timingsViewModel;
    public static  int data_today = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNavigationDrawaberBinding = ActivityNavigationDrawaberBinding.inflate(getLayoutInflater());
        View view = activityNavigationDrawaberBinding.getRoot();
        setContentView(view);

      //   setContentView(R.layout.activity_navigation_drawaber);
       // ButterKnife.bind(this);
        presenter = new NavigationDrawarInteractor(this);
        appPackageName = getPackageName();


//        Flowable<Integer> integerObservable = Flowable.just(1, 2, 3);
//        Completable completable = integerObservable.concatMapCompletable(x -> {
//            return Completable.timer(x, TimeUnit.MICROSECONDS)
//                    //    .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
//                    //  .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
//                    .doOnComplete(() -> Log.d("TOTO", x.toString() + Thread.currentThread().getName()));
//        });
//
//        completable.doOnComplete(() -> Log.d("TOTO", "All items fineshed"  + Thread.currentThread().getName()))
//        .blockingAwait();

        //-----------------------------------------------------------------------------------------------------------
        // check, Is data today is there or not in database ?
//       timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
//        io.reactivex.Flowable<Integer> integerFlowable = timingsViewModel.getTimingsByDataToday(convertDate());
//        io.reactivex.Completable completable1 = integerFlowable.concatMapCompletable(date_today ->{
//           return io.reactivex.Completable.timer(date_today,TimeUnit.MICROSECONDS)
//                   .doOnComplete(()->Log.d("TOTO" , date_today.toString() + Thread.currentThread().getName()));
//        });
//        completable1.doOnComplete(()-> Log.d("TOTO" , "All items fineshed" + Thread.currentThread().getName()))
//                .blockingAwait();

//
//        Observable<Integer>integerObservable = Observable.just(1,2);
//        Observable<String> stringObservable = integerObservable.flatMap(result1 -> Observable.just("Value is :" +
//                result1));
//        stringObservable.subscribe(finalResult ->Log.d("Final result : " ,"Final :" +  finalResult));


//        Flowable<Integer> integerObservable = timingsViewModel.getTimingsByDataToday(convertDate());
//                integerObservable.
//                subscribeOn(Schedulers.trampoline())
//                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(date_today -> {
//                    data_today = date_today;
//                    Log.i("TAG", "Navigation Drawaer : " + data_today);
//                    //  Toast.makeText(getActivity(), "date today is " + date_today, Toast.LENGTH_SHORT).show();
//                }, e -> {
//                    Toast.makeText(getApplicationContext(), "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                });
//
//        Flowable<Integer>integerObservable1 = Flowable.just(3,4);
//        Flowable.merge(integerObservable,integerObservable1)
//        .subscribe(finalResult ->
//
//                {
//                    if (data_today > 0){
//                        Log.d("Final result : " ," Flowable Final :" +  data_today +" "+finalResult);
//
//                    }else {
//                        Log.d("Final result : " ," Flowable Final :" +finalResult);
//
//                    }
//                }
//
//              );


        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        // For get Date today
        timingsViewModel.getTimingsByDataToday(convertDate()).
                subscribeOn(Schedulers.trampoline())
                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(date_today -> {
                    data_today = date_today;
                    Log.i("TAG", "Navigation Drawaer : " + data_today);
                    //  Toast.makeText(getActivity(), "date today is " + date_today, Toast.LENGTH_SHORT).show();
                }, e -> {
                    Toast.makeText(getApplicationContext(), "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });
        //-----------------------------------------------------------------------------------------------------------
        //for show way using
        if (!SharedPerefrenceHelper.getBooleanForWayUsing(getApplicationContext(), IS_FIRST_TIME_WAY_USING, false)) {
            showInformation();
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
                //  SharedPerefrenceHelper.putBooleanForWayUsing(this,IS_FIRST_TIME_WAY_USING,false);
                //SharedPerefrenceHelper.putFirstTime(getApplicationContext(), FIRST_TIME, false);
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
                HelperClass.startActivity(getApplicationContext(),ElarbaoonElnawawyActivity.class);
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

    public void showInformation() {
        activityNavigationDrawaberBinding.toobar.inflateMenu(R.menu.menu);

        sequence = new TapTargetSequence(this)
                .targets(
                        // This tap target will target the tool bar
                        //This for R.id.action_search
                        TapTarget.forToolbarMenuItem(activityNavigationDrawaberBinding.toobar, R.id.action_search, getString(R.string.spectial_button), getString(R.string.search_string))

                                .transparentTarget(true)
                                .outerCircleColor(R.color.blue)
                                .outerCircleAlpha(0.9f)
                                .textColor(android.R.color.white)
                                .targetCircleColor(R.color.colorAccent)
                                .titleTextSize(18)
                                .tintTarget(false)
                                .id(1),
                        //This for R.id.action_share
                        TapTarget.forToolbarMenuItem(activityNavigationDrawaberBinding.toobar, R.id.action_share, getString(R.string.spectial_button), getString(R.string.share_string))
                                .outerCircleColor(R.color.blue)
                                .outerCircleAlpha(0.9f)
                                .textColor(android.R.color.white)
                                .targetCircleColor(R.color.colorAccent)
                                .transparentTarget(true)
                                .tintTarget(false)
                                .id(2),
                        //This for R.id.spectial_button
                        TapTarget.forToolbarOverflow(activityNavigationDrawaberBinding.toobar, "   هذا الزر خاص", "      ضبط زمن الأشعارات  " +
                                "\n" +
                                "      عرض طريقة الاستخدام مرة أخرى    "
                                + "\n" +
                                "      وتقييم التطبيق     " +
                                "\n" +
                                "      ومراسلتنا    ")
                                .outerCircleColor(R.color.blue)
                                .outerCircleAlpha(0.9f)
                                .textColor(android.R.color.white)
                                .targetCircleColor(R.color.colorAccent)
                                .transparentTarget(true)
                                .tintTarget(false)
                ).listener(new TapTargetSequence.Listener() {
                    // This listener will tell us when interesting(tm) events happen in regards
                    // to the sequence
                    @Override
                    public void onSequenceFinish() {
                    }

                    @Override
                    public void onSequenceStep(TapTarget lastTarget, boolean targetClicked) {
                        Log.d("TapTargetView", "Clicked on " + lastTarget.id());
                    }

                    @Override
                    public void onSequenceCanceled(TapTarget lastTarget) {
                        final AlertDialog dialog = new AlertDialog.Builder(NavigationDrawaberActivity.this)
                                .setPositiveButton(getString(R.string.sorry), null).show();
                        TapTargetView.showFor(dialog,
                                TapTarget.forView(dialog.getButton(DialogInterface.BUTTON_POSITIVE), getString(R.string.end), getString(R.string.description_string))
                                        .cancelable(true)
                                        .transparentTarget(true)
                                        .textColor(android.R.color.white)
                                        .outerCircleColor(R.color.blue)
                                        .outerCircleAlpha(0.9f)
                                        .targetCircleColor(R.color.colorAccent)
                                        .tintTarget(false), new TapTargetView.Listener() {
                                    @Override
                                    public void onTargetClick(TapTargetView view) {
                                        super.onTargetClick(view);
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                                        dialog.dismiss();

                                    }
                                });
                    }
                });

        // You don't always need a sequence, and for that there's a single time tap target
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.read_quran), getString(R.string.spectial_button), getString(R.string.read_string))
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                //  sequence.start();
                activityNavigationDrawaberBinding.navView.setSelectedItemId(R.id.sound_quran);
                setTwoShow();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");
            }
        });

    }

    private void setTwoShow() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.sound_quran), getString(R.string.spectial_button), getString(R.string.sound_string))
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                activityNavigationDrawaberBinding.navView.setSelectedItemId(R.id.read_parts);
                setShowThreeItem();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        });
    }

    private void setShowThreeItem() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.read_parts), getString(R.string.spectial_button), getString(R.string.read_parts_string)
        )
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                activityNavigationDrawaberBinding.navView.setSelectedItemId(R.id.azkar);
                setShowFourItem();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        });
    }

    private void setShowFourItem() {
        TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.azkar), getString(R.string.spectial_button), getString(R.string.read_azkar))
                .cancelable(false)
                .drawShadow(true)
                .transparentTarget(true)
                .outerCircleColor(R.color.blue)
                .outerCircleAlpha(0.9f)
                .textColor(android.R.color.white)
                .targetCircleColor(R.color.colorAccent)
                .tintTarget(false), new TapTargetView.Listener() {
            @Override
            public void onTargetClick(TapTargetView view) {
                super.onTargetClick(view);
                // .. which evidently starts the sequence we defined earlier
                sequence.start();
            }

            @Override
            public void onOuterCircleClick(TapTargetView view) {
                super.onOuterCircleClick(view);
            }

            @Override
            public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
                Log.d("TapTargetViewSample", "You dismissed me :(");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // For get data from GpsUtlis.vaja to AzanFragment
        if (requestCode == AppConstants.GPS_REQUEST) {
            Log.i("TAG", "Skipped");
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