package com.mohamedtaha.imagine.ui.activities

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.os.*
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.room.EmptyResultSetException
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.helper.Images
import com.mohamedtaha.imagine.helper.SharedPerefrenceHelper
import com.mohamedtaha.imagine.helper.checkConnection.NetworkConnection
import com.mohamedtaha.imagine.helper.checkConnection.NoInternetConnection
import com.mohamedtaha.imagine.helper.util.ConvertTimes
import com.mohamedtaha.imagine.mvp.view.NavigationDrawaberView
import com.mohamedtaha.imagine.notification.morningAzkar.MorningAzkarNotificationHelper
import com.mohamedtaha.imagine.notification.prayerTimes.AlarmUtils
import com.mohamedtaha.imagine.notification.prayerTimes.NotificationHelperPrayerTime
import com.mohamedtaha.imagine.notification.quran.NotificationHelper
import com.mohamedtaha.imagine.receiver.GetPrayerTimesEveryMonth
import com.mohamedtaha.imagine.room.DatabaseCallback
import com.mohamedtaha.imagine.room.TimingsAppDatabase
import com.mohamedtaha.imagine.room.TimingsViewModel
import com.mohamedtaha.imagine.service.GetDataEveryMonthJobService
import com.mohamedtaha.imagine.ui.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.mohamedtaha.imagine.AppConstants
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.databinding.ActivityMainDrawableBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class NavigationDrawaberActivity : AppCompatActivity(),
    NavigationDrawaberView,
    DatabaseCallback,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainDrawableBinding

//    @JvmField
//    @Inject
//    var sharedPreferences: SharedPreferences? = null

//    @JvmField
//    @Inject
//    var editor: SharedPreferences.Editor? = null

//    @JvmField
//    @Inject
//    var showGuide: ShowGuide? = null

    //    @JvmField
//    @Inject
//    var presenter: NavigationDrawarInteractor? = null
    private var current_fragment = 0

    //public static MaterialSearchView searchView;
    private var timingsViewModel: TimingsViewModel? = null
    private var prayer_timing_default: String? = null
    private var number_azan_default: String? = null
    var city_name: String? = null
    var geocoderMaxResults = 1

//    @JvmField
//    @Inject
//    @APIServiceBase
//    var apiServices: APIServices? = null

    //@JvmField
//    @Inject
//    @APIServiceCity
//    var apiServicesForCity: APIServices? = null
    private var isAzkarTrue: Boolean = false
    private val disposable: CompositeDisposable? = null
    var repearAsync: String? = null
    var isFirstTime = false
    private fun enableStrictMode() {
//        if (BuildConfig.DEBUG) {
//            val policy = ThreadPolicy.Builder()
//                .detectAll()
//                .penaltyLog()
//                .build()
//            StrictMode.setThreadPolicy(policy)
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDrawableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // searchView = (MaterialSearchView) findViewById(R.id.search_view);
        enableStrictMode()
        Log.d(
            "TAG", "doInBackground - Thread On create " + Thread.currentThread().id + "Name : "
                    + Thread.currentThread().name
        )
//        //getString Retrieve a String value from the Preference
//        prayer_timing_default = sharedPreferences!!.getString(
//            getString(R.string.settings_method_key),
//            getString(R.string.settings_method_default)
//        )
        Log.i(
            "prayer_timing_default",
            "prayer_timing_default \$prayer_timing_default$prayer_timing_default"
        )
        timingsViewModel = ViewModelProvider(this).get(TimingsViewModel::class.java)
        if (timingsViewModel!!.isNewlyCreated && savedInstanceState != null) {
            timingsViewModel!!.restoreState(savedInstanceState)
            Log.i(
                "TAG",
                " onSuccess timingsViewModel navigation " + TimingsViewModel.store_date_today
            )
        }
        timingsViewModel!!.isNewlyCreated = false
        dateTodayFromDatabase
        cityName
        //for show way using
        val isFirstTimeWayUsing = SharedPerefrenceHelper.getBooleanForWayUsing(
            applicationContext,
            IS_FIRST_TIME_WAY_USING,
            false
        )
        if (!isFirstTimeWayUsing) {
            //   Log.d(TAG, "onCreate: showGuide : $showGuide")
            // showGuide!!.getGuide(this@NavigationDrawaberActivity, toobar, navView)
        }
        //For open on the save pages immediately
        if (SharedPerefrenceHelper.getBoolean(
                applicationContext,
                SwipePagesActivity.IS_TRUE,
                false
            )
        ) {
            Images.addImagesList()
            val intent = Intent(applicationContext, SwipePagesActivity::class.java)
            intent.putIntegerArrayListExtra(SAVE_ALL_IMAGES, Images.IMAGES as ArrayList<Int?>)
            intent.putExtra(SAVE_PAGE, true)
            startActivity(intent)
            overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing)
        }

        binding.includeAppBarMain.navView.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
        if (savedInstanceState != null) {
            current_fragment = savedInstanceState.getInt(SAVE_STATE_VIEW_PAGER)
            binding.includeAppBarMain.navView.selectedItemId = current_fragment
            Log.d("TAG", "Current fragment  three is :$current_fragment")
        } else {
            binding.includeAppBarMain.navView.selectedItemId = R.id.read_quran
        }
        setSupportActionBar(binding.includeAppBarMain.toolbar)
        //for change color text toolbar
        binding.includeAppBarMain.toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.includeAppBarMain.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.setDrawerListener(toggle)
        toggle.syncState()
        binding.navViewHeader.setNavigationItemSelectedListener(this)
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

    override fun onStop() {
        super.onStop()
    }
    // Log.i("TAG", "e yes : " + store_date_today);// if (store_date_today <= 0) {

    // getPrayerTimesEveryMonth(getApplicationContext());
// Add RXAndroid2 for support with Room because still RXjava3 don't support Room
    //getPrayerTimesEveryMonth(getApplicationContext());
    private val dateTodayFromDatabase: Unit
        private get() {
            Log.i("TAG", " getDateTodayFromDatabase")
            val date = ConvertTimes.convertDate()
            Log.d("TAG", " date $date")
            timingsViewModel!!.checkIsDateTodayFind(ConvertTimes.convertDate()).subscribeOn(
                Schedulers.io()
            )
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    object : SingleObserver<Int> {
                        override fun onSubscribe(d: Disposable) {}
                        override fun onSuccess(integer: Int) {
                            Log.i("TAG", " onSuccess $integer")
                            TimingsViewModel.store_date_today = integer
                            Log.i(
                                "TAG",
                                " timingsViewModel.store_date_today onSuccess" + TimingsViewModel.store_date_today
                            )
                            //getPrayerTimesEveryMonth(getApplicationContext());
                            ScheduleGetDataEveryMonth(
                                applicationContext
                            )
                            GetPrayerTimesEveryMonth.enableBootReceiverEveryMonth(
                                applicationContext
                            )
                        }

                        override fun onError(e: Throwable) {
                            Log.i("TAG", "  onError $e")
                            if (e is EmptyResultSetException) {
                                isNetworkConnected(applicationContext)
                            } else {
                                Log.i("TAG", "  MonError $e")
                            }
                        }
                    }
                )
            timingsViewModel!!.getTimingsByDataToday(ConvertTimes.convertDate()).subscribeOn(
                Schedulers.trampoline()
            ) // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ date_today: Int? ->
                    TimingsViewModel.store_date_today = date_today!!
                    Log.i(
                        "TAG",
                        " timingsViewModel.store_date_today second method" + TimingsViewModel.store_date_today
                    )

                    // if (store_date_today <= 0) {
                    // getPrayerTimesEveryMonth(getApplicationContext());
                    ScheduleGetDataEveryMonth(
                        applicationContext
                    )
                    GetPrayerTimesEveryMonth.enableBootReceiverEveryMonth(applicationContext)
                }) { e: Throwable ->
                    // Log.i("TAG", "e yes : " + store_date_today);
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }
            val notificationHelper =
                NotificationHelper(
                    applicationContext
                )
            notificationHelper.sendNotificationEveryHalfDay()
            notificationHelper.enableBootRecieiver()
            val notificationHelperPrayerTime =
                NotificationHelperPrayerTime(
                    applicationContext
                )
            notificationHelperPrayerTime.getPrayerTimesEveryday()
            notificationHelperPrayerTime.enableBootRecieiver()
        }
    private val cityName: Unit
        private get() {
            timingsViewModel = ViewModelProvider(this).get(TimingsViewModel::class.java)
            val getCityName = timingsViewModel!!.cityName
            getCityName.subscribeOn(Schedulers.io())
                .subscribe({ city_name: String? ->
                    store_city_name = city_name
                    Log.d("TAG", "City name from database is :" + store_city_name)
                }) { error: Throwable ->
                    Log.d(
                        "TAG",
                        "City name from database is :" + error.message
                    )
                }
        }
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val id = item.itemId
            if (id == current_fragment) {
                return@OnNavigationItemSelectedListener false
            }
            when (id) {
                R.id.read_quran -> {
                    //NavigationDrawaberActivityVPager.setCurrentItem(0);
                    val swarFragment = SwarFragment()
                    HelperClass.replece(
                        swarFragment,
                        supportFragmentManager,
                        R.id.frameLayout,
                        FOR_GET_FRAGMENT_AZAN
                    )
                }
                R.id.read_parts -> {
                    //  NavigationDrawaberActivityVPager.setCurrentItem(1);
                    val partsFragment =
                        PartsFragment()
                    HelperClass.replece(partsFragment, supportFragmentManager, R.id.frameLayout)
                }
                R.id.sound_quran -> {
                    //    NavigationDrawaberActivityVPager.setCurrentItem(2);
                    val soundFragment =
                        SoundFragment()
                    HelperClass.replece(soundFragment, supportFragmentManager, R.id.frameLayout)
                }
                R.id.azkar -> {
                    val azkarFragment =
                        AzkarFragment()
                    HelperClass.replece(azkarFragment, supportFragmentManager, R.id.frameLayout)
                }
                R.id.prayer_times -> {
                    val azanFragment =
                        AzanFragment()
                    HelperClass.replece(azanFragment, supportFragmentManager, R.id.frameLayout)
                }
            }
            current_fragment = id
            Log.d("TAG", "Current fragment  two is :$current_fragment")
            true
        }

    override fun onBackPressed() {
        //  presenter.exitApp(searchView, navView, drawer);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val SearchItem = menu.findItem(R.id.action_search)
        // searchView.setMenuItem(SearchItem);
        return true
    }

    override fun onResume() {
        super.onResume()
        //isAzkarTrue = sharedPreferences!!.getBoolean(getString(R.string.azkar_key), true)
        if (isAzkarTrue) {
            val morningAzkarNotificationHelper =
                MorningAzkarNotificationHelper(
                    applicationContext
                )
            morningAzkarNotificationHelper.getAzkarTimesEveryday()
            morningAzkarNotificationHelper.enableBootRecieiver()
        } else {
            val alarm =
                AlarmUtils(
                    applicationContext
                )
            alarm.cancelAllAlarmForBroadcastAzkar()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_youtube -> {
                val intent = Intent(this@NavigationDrawaberActivity, YoutubeActivity::class.java)
                startActivity(intent)
            }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showMessageExitApp() {
        HelperClass.customToast(this, getString(R.string.exit_app))
    }

    override fun exitApp() {
        HelperClass.closeApp(applicationContext)
    }

    override fun getShareApp(intent: Intent) {
        startActivity(intent)
    }

    override fun getSendUs(intentEmail: Intent) {
        startActivity(intentEmail)
    }

    override fun getRateApp(rateApp: Intent) {
        startActivity(rateApp)
    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter!!.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_STATE_VIEW_PAGER, current_fragment)
        timingsViewModel!!.saveState(outState)
        Log.d("TAG", "Current fragment is :$current_fragment")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppConstants.GPS_REQUEST) {
            Log.i("TAG Navigation drawaber", "Skipped")
            val azanFragment =
                AzanFragment()
            val bundle = Bundle()
            HelperClass.replece(azanFragment, supportFragmentManager, R.id.frameLayout)
            bundle.putInt("bundle", resultCode)
            azanFragment.arguments = bundle
            azanFragment.onActivityResult(requestCode, resultCode, data)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun isNetworkConnected(context: Context) {
        val noInternetConnection =
            NoInternetConnection()
        noInternetConnection.execute("http://clients3.google.com/generate_204")
        val isConnected = NetworkConnection.networkConnectivity(this)
        Handler().postDelayed({
            if (!isConnected) {
            } else {
                if (!NoInternetConnection.isInternet()) {
                } else {
                    Log.d("TAG", "TimingsAppDatabase DeletePrayerTimes AddPrayerTimes Every day ")
                    TimingsAppDatabase.getInstance(context)
                        .DeletePrayerTimes(this@NavigationDrawaberActivity)
                }
            }
        }, 1000)
    }

    override fun onPrayerTimesAdded() {
        SharedPerefrenceHelper.putStringCompareMethod(
            this,
            AzanFragment.COMPARE_METHOD,
            prayer_timing_default
        )
        SharedPerefrenceHelper.putStringAzan(this, AzanFragment.AZAN_DEFUALT, number_azan_default)
        changeValueInListPreference()
        changeValueInListPreferenceForAzan()
    }

    private val isStoragePermissionGranted: Boolean
        private get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                Log.i("TAG", " Granted fisrt")
                true
            } else {
                false
            }
        } else {
            true
        }

    private fun getCity(context: Context) {
        Log.d("TAG", "getCity")
        // checkIsFragmentAzanIsOpen();
        Log.d("TAG", "getPrayerTimesByCity")
        //  val getCityCall = apiServicesForCity!!.city
//        getCityCall.enqueue(object : Callback<GetCity?> {
//            override fun onResponse(call: Call<GetCity?>, response: Response<GetCity?>) {
//                val city = response.body()
//                try {
//                    if (city!!.status == "success") {
//                        Log.d("TAG", city.city + " : " + city.country)
//                        city_name = getCityNameWithoutLocation(city.lat, city.lon)
//                        Log.d("TAG", "City name in arabic getCity is : $city_name")
//                        getMethodPreferences(city.country)
//                        getPrayerTimesByCity(
//                            context,
//                            city.city,
//                            city.country,
//                            Integer.valueOf(prayer_timing_default),
//                            city_name
//                        )
//                        Log.d("TAG", " repear is : $prayer_timing_default")
//                    }
//                } catch (e: Exception) {
//                    Log.i("TAG", " Error getCity" + e.message)
//                }
//            }
//
//            override fun onFailure(call: Call<GetCity?>, t: Throwable) {
//                Log.i("TAG", " onFailure " + t.message)
//            }
//        })
    }

    private fun getPrayerTimesByCity(
        context: Context,
        city: String,
        country: String,
        method: Int,
        city_name: String?
    ) {
//        val azanCall = apiServices!!.getPrayerTimesByCity(city, country, false, method)
//        azanCall.enqueue(object : Callback<Azan?> {
//            override fun onResponse(call: Call<Azan?>, response: Response<Azan?>) {
//                val azan = response.body()
//                try {
//                    if (azan!!.status == "OK") {
//                        TimingsAppDatabase.getInstance(context).AddPrayerTimesForMonth(
//                            this@NavigationDrawaberActivity,
//                            azan,
//                            city_name
//                        )
//                    }
//                } catch (e: Exception) {
//                    Log.i("TAG", " Error getPrayerTimesByCity " + e.message)
//                }
//            }
//
//            override fun onFailure(call: Call<Azan?>, t: Throwable) {}
//        })
    }

    private fun getMethodPreferences(country_name: String): String? {
        if (country_name == getString(R.string.saudi_arabia)) {
            customForPreferences(
                getString(R.string.settings_method_umm_al_qura_university_makkah_value),
                getString(R.string.settings_method_umm_al_qura_university_makkah_value)
            )
            customForPreferencesAzan(
                getString(R.string.settings_azan_elharam_elmaky_value),
                getString(R.string.settings_azan_elharam_elmaky_value)
            )
        } else if (country_name == getString(R.string.qatar)) {
            customForPreferences(
                getString(R.string.settings_method_qatar_value),
                getString(R.string.settings_method_qatar_value)
            )
            customForPreferencesAzan(
                getString(R.string.settings_azan_elharam_elmaky_value),
                getString(R.string.settings_azan_elharam_elmaky_value)
            )
        } else if (country_name == getString(R.string.kuwait)) {
            customForPreferences(
                getString(R.string.settings_method_kuwait_value),
                getString(R.string.settings_method_kuwait_value)
            )
            customForPreferencesAzan(
                getString(R.string.settings_azan_elharam_elmaky_value),
                getString(R.string.settings_azan_elharam_elmaky_value)
            )
        } else if (country_name == getString(R.string.turkey)) {
            customForPreferences(
                getString(R.string.settings_method_diyanet_işleri_başkanlığı_turkey_value),
                getString(R.string.settings_method_diyanet_işleri_başkanlığı_turkey_value)
            )
            customForPreferencesAzan(
                getString(R.string.settings_azan_elharam_elmaky_value),
                getString(R.string.settings_azan_elharam_elmaky_value)
            )
        } else if (country_name == getString(R.string.russia)) {
            customForPreferences(
                getString(R.string.settings_method_spiritual_administration_of_muslims_of_russia_value),
                getString(R.string.settings_method_spiritual_administration_of_muslims_of_russia_value)
            )
            customForPreferencesAzan(
                getString(R.string.settings_azan_elharam_elmaky_value),
                getString(R.string.settings_azan_elharam_elmaky_value)
            )
        } else {
            customForPreferences(
                getString(R.string.settings_method_key),
                getString(R.string.settings_method_default)
            )
            customForPreferencesAzan(
                getString(R.string.settings_azan_abdelbaset_value),
                getString(R.string.settings_azan_abdelbaset_value)
            )
        }
        return prayer_timing_default
    }

    private fun customForPreferences(method_key: String, method_default: String): String? {
        //   prayer_timing_default = sharedPreferences!!.getString(method_key, method_default)
        return prayer_timing_default
    }

    private fun customForPreferencesAzan(method_key: String, method_default: String): String? {
        //   number_azan_default = sharedPreferences!!.getString(method_key, method_default)
        return number_azan_default
    }

    private fun changeValueInListPreference() {
//        editor!!.putString(getString(R.string.settings_method_key), prayer_timing_default)
//        editor!!.commit()
    }

    private fun changeValueInListPreferenceForAzan() {
//        editor!!.putString(getString(R.string.settings_azan_key), number_azan_default)
//        editor!!.commit()
    }

    private fun getCityNameWithoutLocation(latitude: Double, longitude: Double): String? {
        var cityName = ""
        val locale = Locale("ar")
        val geocoder = Geocoder(this, locale)
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, geocoderMaxResults)
            if (addresses.size > 0) {
                for (adr in addresses) {
                    if (adr.locality != null && adr.locality.length > 0) {
                        cityName = adr.locality
                        Log.d("TAG", " cityName : $cityName")
                        break
                    }
                }
            }
            return cityName
        } catch (e: IOException) {
        }
        return null
    }

    override fun onPrayerTimesDeleted() {
        if (isStoragePermissionGranted) {
            Log.d("TAG", "onPrayerTimesDeleted")
            checkIsGetData = true
            getCity(this)
        }
    }

    override fun getDataFromLocationAfterDeleteData() {}
    override fun onPrayerTimesError(e: Throwable) {}
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.el_arbaoon_elnawawy) {
            HelperClass.startActivity(applicationContext, ElarbaoonElnawawyActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
            }
        } else if (id == R.id.use_way) {
            SharedPerefrenceHelper.removeDataForWayUsing(this)
            HelperClass.startActivity(applicationContext, SplashActivity::class.java)
        } else if (id == R.id.action_share) {
            //    presenter!!.shareApp(getString(R.string.about), appPackageName)
        } else if (id == R.id.action_rate) {
            //  presenter!!.actionRate(appPackageName)
        } else if (id == R.id.action_send_us) {
            //   presenter!!.sendUs()
        } else if (id == R.id.action_settings) {
            HelperClass.startActivity(applicationContext, SettingsActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                overridePendingTransition(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
            }
        }
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    companion object {
        private const val SAVE_STATE_VIEW_PAGER = "save_state_view_pager"
        const val IS_FIRST_TIME_WAY_USING = "way_sueing"
        const val IS_FIRST_TIME_PRAYER_TIME_EVERYDAY = "prayer_time_everyday"
        const val SAVE_PAGE = "savepage"
        const val SAVE_ALL_IMAGES = "save_all_images"
        const val FOR_GET_FRAGMENT_AZAN = "fragemnt_azan"
        const val CHECKISDATAORNOTINDATABASE = "store_date_today"
        private const val TAG = "NavigationDrawaberActiv"

        @JvmField
        var store_city_name: String? = null

        @JvmField
        var checkIsGetData = false

        @JvmStatic
        fun getPrayerTimesEveryMonth(context: Context) {
            val ALARM_TYPE_ELAPSED = 11
            val alarmManager: AlarmManager
            val alarmPendingIntent: PendingIntent
            val intent = Intent(context, GetPrayerTimesEveryMonth::class.java)
            intent.putExtra(CHECKISDATAORNOTINDATABASE, TimingsViewModel.store_date_today)
            Log.d("TAG", "getPrayerTimesEveryMonth " + TimingsViewModel.store_date_today)
            alarmPendingIntent = PendingIntent.getBroadcast(
                context,
                ALARM_TYPE_ELAPSED,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val setTime = Calendar.getInstance()
            setTime.timeInMillis = System.currentTimeMillis()
            setTime[Calendar.HOUR_OF_DAY] = 1
            // setTime.set(Calendar.MINUTE, 30);
            alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                setTime.timeInMillis, AlarmManager.INTERVAL_DAY, alarmPendingIntent
            )
        }

        @JvmStatic
        fun ScheduleGetDataEveryMonth(context: Context) {
            val extras = PersistableBundle()
            extras.putInt(CHECKISDATAORNOTINDATABASE, TimingsViewModel.store_date_today)
            val componentName = ComponentName(context, GetDataEveryMonthJobService::class.java)
            val info = JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // .setRequiresBatteryNotLow(true)
                .setExtras(extras).build()
            val scheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            scheduler.schedule(info)
        }
    }
}