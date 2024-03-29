package com.mohamedtaha.imagine.ui.home.activity

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
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.mohamedtaha.imagine.AppConstants
import com.mohamedtaha.imagine.BuildConfig
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.HasToolbar
import com.mohamedtaha.imagine.base.SearchListener
import com.mohamedtaha.imagine.configfirebase.RemoteConfig
import com.mohamedtaha.imagine.databinding.ActivityMainDrawableBinding
import com.mohamedtaha.imagine.datastore.DataStoreViewModel
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.helper.SharedPerefrenceHelper
import com.mohamedtaha.imagine.helper.checkConnection.NetworkConnection
import com.mohamedtaha.imagine.helper.checkConnection.NoInternetConnection
import com.mohamedtaha.imagine.helper.images
import com.mohamedtaha.imagine.util.ConvertTimes
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
import com.mohamedtaha.imagine.ui.home.fragment.AzanFragment
import com.mohamedtaha.imagine.ui.youtube.YoutubeActivity
import com.mohamedtaha.imagine.util.RateApp.reteApp
import com.mohamedtaha.imagine.util.SearchBarUtils.setSearchIcons
import com.mohamedtaha.imagine.util.SearchBarUtils.setSearchTextColor
import com.mohamedtaha.imagine.util.SendUs.sendUs
import com.mohamedtaha.imagine.util.ShareApp.shareApp
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.security.SecureRandom
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class NavigationDrawaberActivity : AppCompatActivity(), HasToolbar,
    NavigationDrawaberView,
    NavigationView.OnNavigationItemSelectedListener {
    //    DatabaseCallback,
    private lateinit var binding: ActivityMainDrawableBinding
    private var searchListener:SearchListener?= null

    fun setCallbackSearch(searchListener: SearchListener){
        this.searchListener = searchListener
    }



    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var mMenu: Menu? = null

    @Inject
    lateinit var remoteConfig: RemoteConfig

    @Inject
    lateinit var intentShare: Intent

    @Inject
    lateinit var intentSendUs: Intent

    @Inject
    lateinit var intentRate: Intent

    private val datStoreViewModel: DataStoreViewModel by viewModels()

    private var current_fragment = 0
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
        if (BuildConfig.DEBUG) {
            val policy = ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
            StrictMode.setThreadPolicy(policy)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Handle the splash screen transition
        //  val splashScreen = installSplashScreen()
//        splashScreen.setOnExitAnimationListener{splashScreenView ->
//            //Create your custom animation
//
//            val slideUp = ObjectAnimator.ofFloat(splashScreenView, View.TRANSLATION_Y,0f,splashScreenView.height.toFloat())
//            slideUp.interpolator = AnticipateInterpolator()
//            slideUp.duration = 1000L
//
//
//            //Call splashScreenView. remove at the end of your custom animation
//            slideUp.doOnEnd{splashScreenView.remove()}
//            //Run your animation
//            slideUp.start()
//        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainDrawableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        remoteConfig.setConfigComplete()
        remoteConfig.youtubeChannel.observe(this) {
            datStoreViewModel.saveYouTubeChannel(it)
        }
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
//        dateTodayFromDatabase
//        cityName
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
            images.addImagesList()
            val intent = Intent(applicationContext, SwipePagesActivity::class.java)
            intent.putIntegerArrayListExtra(SAVE_ALL_IMAGES, images.IMAGES as ArrayList<Int?>)
            intent.putExtra(SAVE_PAGE, true)
            startActivity(intent)
            overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing)
        }

        setToolbar(binding.includeAppBarMain.toolbar)
        binding.includeAppBarMain.bottomNavigationView.setupWithNavController(navController)
        binding.navViewHeader.setupWithNavController(navController)
       // binding.navViewHeader.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (destination.id == R.id.swipeFragment || destination.id == R.id.elarbaoonElnawawyFragment || destination.id == R.id.descriptionElarbaoonFragment)
                    binding.includeAppBarMain.bottomNavigationView.visibility = View.GONE
                else
                    binding.includeAppBarMain.bottomNavigationView.visibility = View.VISIBLE
            }

        })
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.swarFragment,
                R.id.partsFragment,
                R.id.soundFragment,
                R.id.azanFragment,
                R.id.azkarFragment
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        //for change color text toolbar
        binding.includeAppBarMain.toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))
        binding.navViewHeader.itemIconTintList = null

//        binding.includeAppBarMain.bottomNavigationView.setOnNavigationItemSelectedListener(
//            mOnNavigationItemSelectedListener
//        )
//        if (savedInstanceState != null) {
//            current_fragment = savedInstanceState.getInt(SAVE_STATE_VIEW_PAGER)
//            binding.includeAppBarMain.bottomNavigationView.selectedItemId = current_fragment
//            Log.d("TAG", "Current fragment  three is :$current_fragment")
//        } else {
//            binding.includeAppBarMain.bottomNavigationView.selectedItemId = R.id.swarFragment
//        }


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

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // getPrayerTimesEveryMonth(getApplicationContext());
// Add RXAndroid2 for support with Room because still RXjava3 don't support Room
    //getPrayerTimesEveryMonth(getApplicationContext());
//    private val dateTodayFromDatabase: Unit
//        private get() {
//            Log.i("TAG", " getDateTodayFromDatabase")
//            val date = ConvertTimes.convertDate()
//            Log.d("TAG", " date $date")
//            timingsViewModel!!.checkIsDateTodayFind(ConvertTimes.convertDate()).subscribeOn(
//                Schedulers.io()
//            )
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(
//                    object : SingleObserver<Int> {
//                        override fun onSubscribe(d: Disposable) {}
//                        override fun onSuccess(integer: Int) {
//                            Log.i("TAG", " onSuccess $integer")
//                            TimingsViewModel.store_date_today = integer
//                            Log.i(
//                                "TAG",
//                                " timingsViewModel.store_date_today onSuccess" + TimingsViewModel.store_date_today
//                            )
//                            //getPrayerTimesEveryMonth(getApplicationContext());
//                            ScheduleGetDataEveryMonth(
//                                applicationContext
//                            )
//                            GetPrayerTimesEveryMonth.enableBootReceiverEveryMonth(
//                                applicationContext
//                            )
//                        }
//
//                        override fun onError(e: Throwable) {
//                            Log.i("TAG", "  onError $e")
//                            if (e is EmptyResultSetException) {
//                                isNetworkConnected(applicationContext)
//                            } else {
//                                Log.i("TAG", "  MonError $e")
//                            }
//                        }
//                    }
//                )
//            timingsViewModel!!.getTimingsByDataToday(ConvertTimes.convertDate()).subscribeOn(
//                Schedulers.trampoline()
//            ) // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ date_today: Int? ->
//                    TimingsViewModel.store_date_today = date_today!!
//                    Log.i(
//                        "TAG",
//                        " timingsViewModel.store_date_today second method" + TimingsViewModel.store_date_today
//                    )
//
//                    // if (store_date_today <= 0) {
//                    // getPrayerTimesEveryMonth(getApplicationContext());
//                    ScheduleGetDataEveryMonth(
//                        applicationContext
//                    )
//                    GetPrayerTimesEveryMonth.enableBootReceiverEveryMonth(applicationContext)
//                }) { e: Throwable ->
//                    // Log.i("TAG", "e yes : " + store_date_today);
//                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
//                }
//            val notificationHelper =
//                NotificationHelper(
//                    applicationContext
//                )
//            notificationHelper.sendNotificationEveryHalfDay()
//            notificationHelper.enableBootRecieiver()
//            val notificationHelperPrayerTime =
//                NotificationHelperPrayerTime(
//                    applicationContext
//                )
//            notificationHelperPrayerTime.getPrayerTimesEveryday()
//            notificationHelperPrayerTime.enableBootRecieiver()
//        }
//    private val cityName: Unit
//        private get() {
//            timingsViewModel = ViewModelProvider(this).get(TimingsViewModel::class.java)
//            val getCityName = timingsViewModel!!.cityName
//            getCityName.subscribeOn(Schedulers.io())
//                .subscribe({ city_name: String? ->
//                    store_city_name = city_name
//                    Log.d("TAG", "City name from database is :" + store_city_name)
//                }) { error: Throwable ->
//                    Log.d(
//                        "TAG",
//                        "City name from database is :" + error.message
//                    )
//                }
//        }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        inflate(menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                searchListener?.onSearch(p0)
                return true
            }

        })

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
            // HelperClass.replece(azanFragment, supportFragmentManager, R.id.frameLayout)
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
                 //   TimingsAppDatabase.getInstance(context)
                   //     .DeletePrayerTimes(this@NavigationDrawaberActivity)
                }
            }
        }, 1000)
    }

//    override fun onPrayerTimesAdded() {
//        SharedPerefrenceHelper.putStringCompareMethod(
//            this,
//            AzanFragment.COMPARE_METHOD,
//            prayer_timing_default
//        )
//        SharedPerefrenceHelper.putStringAzan(this, AzanFragment.AZAN_DEFUALT, number_azan_default)
//        changeValueInListPreference()
//        changeValueInListPreferenceForAzan()
//    }

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

//    override fun onPrayerTimesDeleted() {
//        if (isStoragePermissionGranted) {
//            Log.d("TAG", "onPrayerTimesDeleted")
//            checkIsGetData = true
//            getCity(this)
//        }
//    }
//
//    override fun getDataFromLocationAfterDeleteData() {}
//    override fun onPrayerTimesError(e: Throwable) {}
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.elarbaoonElnawawyFragment) {
            val options = navOptions {
                anim {
                    enter = android.R.anim.slide_in_left
                    exit = android.R.anim.slide_out_right
//                    popEnter =
//                        popExit =
                }
            }
//            findNavController(R.id.fragmentContainerView).navigate(
//                R.id.elarbaoonElnawawyActivity,
//                null,
//                options
//            )
        } else if (id == R.id.use_way) {
            SharedPerefrenceHelper.removeDataForWayUsing(this)
            findNavController(R.id.fragmentContainerView).navigate(R.id.splashActivity)
        } else if (id == R.id.action_share) {
            shareApp(this, intentShare)
        } else if (id == R.id.action_rate) {
            reteApp(this, intentRate)
        } else if (id == R.id.action_send_us) {
            sendUs(this, intentSendUs)
        } else if (id == R.id.action_settings) {
            val options = navOptions {
                anim {
                    enter = android.R.anim.slide_in_left
                    exit = android.R.anim.slide_out_right
                }
            }
            findNavController(R.id.fragmentContainerView).navigate(
                R.id.settingsActivity,
                null,
                options
            )
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

    override fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu)

    }

    override fun getToolbar(): Toolbar {
        return binding.includeAppBarMain.toolbar
    }

    override fun inflate(menu: Menu) {
        mMenu = menu
        binding.includeAppBarMain.toolbar.inflateMenu(R.menu.menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        setSearchIcons(searchView, R.id.search_button, R.drawable.ic_search)
        setSearchIcons(searchView, R.id.search_close_btn, R.drawable.ic_close_search)
        setSearchIcons(searchView, R.id.search_close_btn, R.drawable.ic_close_search)
        setSearchTextColor(searchView)
    }

    override fun showToolbar() {
        binding.includeAppBarMain.toolbar.visibility = View.VISIBLE
    }

    override fun hideYoutubeIcon() {
    mMenu?.findItem(R.id.action_youtube)?.isVisible = false
    }

    override fun showYoutubeIcon() {
        mMenu?.findItem(R.id.action_youtube)?.isVisible = true
    }

    override fun hideSearchIcon() {
        mMenu?.findItem(R.id.action_search)?.isVisible = false
    }

    override fun showSearchIcon() {
        mMenu?.findItem(R.id.action_search)?.isVisible = true
    }

    override fun hideToolbar() {
        binding.includeAppBarMain.toolbar.visibility = View.GONE
    }

}