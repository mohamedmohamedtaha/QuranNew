package com.mohamedtaha.imagine.ui.home.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.*
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import butterknife.BindView
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.material.snackbar.Snackbar
import com.mohamedtaha.imagine.AppConstants
import com.mohamedtaha.imagine.GpsUtils
import com.mohamedtaha.imagine.GpsUtils.onGpsListener
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.adapter.AdapterAzanVP
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentAzanBinding
import com.mohamedtaha.imagine.helper.HelperClass
import com.mohamedtaha.imagine.helper.SharedPerefrenceHelper
import com.mohamedtaha.imagine.helper.checkConnection.NetworkConnection
import com.mohamedtaha.imagine.helper.checkConnection.NoInternetConnection
import com.mohamedtaha.imagine.util.ConvertTimes
import com.mohamedtaha.imagine.mvp.model.azan.Timings
import com.mohamedtaha.imagine.receiver.ConnectivityReceiver
import com.mohamedtaha.imagine.receiver.GetPrayerTimesEveryMonth
import com.mohamedtaha.imagine.receiver.NoInternetReceiver
import com.mohamedtaha.imagine.room.DatabaseCallback
import com.mohamedtaha.imagine.room.TimingsAppDatabase
import com.mohamedtaha.imagine.room.TimingsViewModel
import com.mohamedtaha.imagine.service.MediaPlayerService
import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity
import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity.Companion.ScheduleGetDataEveryMonth
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class AzanFragment : BaseFragment(), LocationListener, onGpsListener {
    // DatabaseCallback,
    private lateinit var binding: FragmentAzanBinding

    //    @BindView(R.id.AzanFragment_VP)
    //    RtlViewPager AzanFragmentVP;
//    @JvmField
//    @BindView(R.id.progressBar)
//    var progressBar: ProgressBar? = null
//
//    @JvmField
//    @BindView(R.id.TV_Show_Error)
//    var TVShowError: TextView? = null
    private var timingsViewModel: TimingsViewModel? = null
    private val locationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL: Long = 15000 /* 15 secs */
    private val FASTER_INTERVAL: Long = 5000
    private val permissionsToRequest: ArrayList<*>? = null
    private val permissionsRejected: ArrayList<*> = ArrayList<Any?>()
    private val permissions: ArrayList<*> = ArrayList<Any?>()
    private var locationManager: LocationManager? = null

    // protected LocationManager locationManager;
    // flag for GPS Status
    var isGPSEnabled = false

    // flag for network status
    var isNetworkEnabled = false

    // flag for GPS Tracking is enabled
    var isGPSTrackingEnabled = false
    var isGPSPermission = true
    private var location_user: Location? = null
    var latitude = 0.0
    var longitude = 0.0

    // How many Geocoder should return our GPSTracker
    var geocoderMaxResults = 1

    // Store LocationManager.GPS_PROVIDER or LocationManager.NETWORK_PROVIDER information
    private val save_request_code_back_from_turn_gps = 0

    //    @Inject
    //    @APIServiceBase
    //    APIServices apiServices;
    //    @Inject
    //    @APIServiceCity
    //    APIServices apiServicesForCity;
    var language_name: String? = null
    var city_name: String? = null

    //     private FragmentAzanBinding fragmentAzanBinding;
    private var getAllData: List<Timings>? = ArrayList()
    private var connectivityReceiver: ConnectivityReceiver? = null
    private var noInternetReceiver: NoInternetReceiver? = null
    var sharedPreferences: SharedPreferences? = null
    private var Prayer_timing_default: String? = null
    private var compare_methods: String? = null
    private var number_azan_default: String? = null
    private var gpsUtils: GpsUtils? = null
    private var bundle: Bundle? = null
    private var isValueForPrayerTimesChanged = false
    private var adapterAzan: AdapterAzanVP? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAzanBinding.inflate(inflater, container, false)

        language_name = Locale.getDefault().language
        if (language_name != "ar") {
            HelperClass.change_language("ar", activity)
        }
        setHasOptionsMenu(true)
        gpsUtils = GpsUtils(activity)
        timingsViewModel = ViewModelProvider(this).get(TimingsViewModel::class.java)
        if (timingsViewModel!!.isNewlyCreated && savedInstanceState != null) {
            timingsViewModel!!.restoreState(savedInstanceState)
            Log.i("TAG", " onSuccess timingsViewModel " + TimingsViewModel.store_date_today)
        }
        timingsViewModel!!.isNewlyCreated = false
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        bundle = arguments
        if (bundle != null) {
            val bundle1 = bundle!!.getInt("bundle")
            if (bundle1 == -1) {
                //   turnGPS();
                gpsUtils!!.turnGPSOn(this)
                //getLocation();
            } else {
                if (isGPSEnabled) {
                    Log.i("TAG", "  isGPSEnabled $isGPSEnabled")
                    gpsUtils!!.turnGPSOn(this)
                } else {
                    Log.i("TAG", "  isGPSEnabled $isGPSEnabled")
                    showSettingsAlert()
                }
            }
        }
        connectivityReceiver = ConnectivityReceiver()
        noInternetReceiver = NoInternetReceiver()
        registerNoConnection()
        registerNoInternet()
        //apiServices = getRetrofit().create(APIServices.class);
        Log.i("TAG", "onCreateView")
        //flowableGetAllPrayerTimingFromDatabase()
        //  for avoid start show way using
        if (SharedPerefrenceHelper.getBooleanForWayUsing(
                activity,
                NavigationDrawaberActivity.IS_FIRST_TIME_WAY_USING,
                false
            )
        ) {
            Log.i(
                "TAG",
                "timingsViewModel.store_date_today before" + TimingsViewModel.store_date_today
            )
            if (TimingsViewModel.store_date_today <= 0) {
                Log.i(
                    "TAG",
                    "timingsViewModel.store_date_today if" + TimingsViewModel.store_date_today
                )
                isNetworkConnected
            }
        }


//        if (GPSTracker.isServicesOk(getActivity())){
//            GPSTracker gpsTracker = new GPSTracker(getContext(),getActivity());
//
//            if (gpsTracker.getIsGPSTrackingEnabled()){
//                double latitude = gpsTracker.getLatitude();
//                double longitude = gpsTracker.getLongitude();
//                Toast.makeText(getActivity(), "lat/ long" + latitude + "\n" + longitude, Toast.LENGTH_SHORT).show();
//
//            }else {
//                gpsTracker.showSettingsAlert();
//
//            }
        //check if GPS enabled or not
//        if ( isGPSPermission && !isGPSEnabled){
//            showSettingsAlert();
//        }else {
        //     getLocation();
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       toolbar.hideSearchIcon()
        super.onCreateOptionsMenu(menu, inflater)
    }//   store_date_today = date_today;

    //   Log.i("TAG", "date today from data base : " + store_date_today);
    //____________________________ Get prayer times from internet every month
    // if (store_date_today <= 0) {
    //  getPrayerTimesEveryMonth(getActivity());
// Add RXAndroid2 for support with Room because still RXjava3 don't support Room
    //store_date_today = integer;
    //  getPrayerTimesEveryMonth(getActivity());
//    private val dateTodayFromDatabase: Unit
//        private get() {
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
//                                " timingsViewModel.store_date_today " + TimingsViewModel.store_date_today
//                            )
//                            //store_date_today = integer;
//                            ScheduleGetDataEveryMonth(activity!!)
//                            //  getPrayerTimesEveryMonth(getActivity());
//                            GetPrayerTimesEveryMonth.enableBootReceiverEveryMonth(activity)
//                        }
//
//                        override fun onError(e: Throwable) {
//                            Log.i("TAG", "  onError $e")
//                            if (e is EmptyResultSetException) {
//                                isNetworkConnected
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
//                    //   store_date_today = date_today;
//                    TimingsViewModel.store_date_today = (date_today)!!
//                    //   Log.i("TAG", "date today from data base : " + store_date_today);
//                    //____________________________ Get prayer times from internet every month
//                    // if (store_date_today <= 0) {
//                    ScheduleGetDataEveryMonth(requireActivity())
//                    //  getPrayerTimesEveryMonth(getActivity());
//                    GetPrayerTimesEveryMonth.enableBootReceiverEveryMonth(requireActivity())
//                }) { e: Throwable? -> }
//        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            timingsViewModel!!.saveState(outState)
        }
    }

//    private fun flowableGetAllPrayerTimingFromDatabase() {
//        val flowableGetAllPrayerTimingFromDatabase = timingsViewModel!!.allTimingsRxjava
//        flowableGetAllPrayerTimingFromDatabase.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ all_Data: List<Timings>? ->
//                getAllData = all_Data
//                Log.i(
//                    "TAG",
//                    "Azan fragment Navigation Drawaber : " + TimingsViewModel.store_date_today
//                )
//                if (TimingsViewModel.store_date_today > 0) {
//                    //if (store_date_today > 0) {
//                    if (getAllData == null && getAllData!!.size <= 0) {
//                        //The data is null
//                        binding.TVShowError!!.visibility = View.VISIBLE
//                        binding.TVShowError!!.text = getString(R.string.no_data)
//                        //   AzanFragmentVP.setVisibility(View.GONE);
//                        Log.i("TAG", "The data is : " + getAllData!!.size)
//                        clearFlagForInteractiveUser()
//                    } else {
//                        binding.TVShowError!!.visibility = View.GONE
//                        //   AzanFragmentVP.setVisibility(View.VISIBLE);
//                        adapterAzan = AdapterAzanVP(object: ClickListener<Int> {
//                            override fun onClick(view: View?, position: Int) {
//                                isRefresh = true
//                                isNetworkConnected
//                            }
//
//                        })
//                        adapterAzan!!.submitList(getAllData)
//                        //                            AzanFragmentVP.setAdapter(adapterAzan);
////                            AzanFragmentVP.setCurrentItem(store_date_today - 1);
//                        Log.i("TAG", "setCurrentItem " + TimingsViewModel.store_date_today)
//
//                        // fragmentAzanBinding.AzanFragmentVP.setCurrentItem(store_date_today - 1);
//                        clearFlagForInteractiveUser()
//                        Log.i("TAG", "all data " + getAllData!!.size)
//                    }
//                } else {
//                    binding.TVShowError!!.visibility = View.VISIBLE
//                    binding.TVShowError!!.text = getString(R.string.no_data)
//                    //   AzanFragmentVP.setVisibility(View.GONE);
//                    Log.i("TAG", "The data is null : " + getAllData!!.size)
//                    clearFlagForInteractiveUser()
//                }
//            }) { e: Throwable -> Log.i("TAG", "Error RXJava : " + e.message) }
//    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "the timer is cancel")
        if (connectivityReceiver != null) {
            requireActivity().unregisterReceiver(connectivityReceiver)
        }
        if (noInternetReceiver != null) {
            requireActivity().unregisterReceiver(noInternetReceiver)
        }
        gpsUtils!!.stopLocationUpdtaes()
        AdapterAzanVP.cancelTimer()
        AdapterAzanVP.cancelTimerForTextView()
    }

    private fun checkBeforeGetDataFromInternetTest() {
        if (isStoragePermissionGranted) {
            if (checkGPS()) {
                gpsUtils!!.turnGPSOn(this)
            }
        }
    }

    private fun checkBeforeGetData() {
        if (isStoragePermissionGranted) {
            getCity(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> if (checkGPS()) {
                //  turnGPS();
                gpsUtils!!.turnGPSOn(this)
                //getLocation();
                Log.i("TAG", "LOCATION_PERMISSION_REQUEST_CODE OK")
            } else {
                Log.i("TAG", "LOCATION_PERMISSION_REQUEST_CODE CANCELED")
                showTextError()
            }
            MY_PERMISSIONS_WRITE_STORAGE -> {
                Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE")
                if (isStoragePermissionGranted) {
                   // TimingsAppDatabase.getInstance(activity).DeletePrayerTimes(this@AzanFragment)
                    //  getCity();
                    Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE OK")
                } else {
                    Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE CANCELED")
                    showTextError()
                }
            }
            else -> {}
        }
    }

    private fun getPrayerTimesByCity(
        city: String,
        country: String,
        method: Int,
        city_name: String
    ) {
//        Call<Azan> azanCall = apiServices.getPrayerTimesByCity(city, country, false, method);
//        azanCall.enqueue(new Callback<Azan>() {
//            @Override
//            public void onResponse(Call<Azan> call, Response<Azan> response) {
//                Azan azan = response.body();
//                try {
//                    if (azan.getStatus().equals("OK")) {
//                        TimingsAppDatabase.getInstance(getActivity()).AddPrayerTimesForMonth(AzanFragment.this, azan, city_name);
//                        TVShowError.setVisibility(View.GONE);
//                    } else {
//                        TVShowError.setVisibility(View.VISIBLE);
//                        TVShowError.setText(getActivity().getString(R.string.cant));
//                        clearFlagForInteractiveUser();
//                    }
//                } catch (Exception e) {
//                    Log.i("TAG", " Error " + e.getMessage());
//                    if (progressBar != null) {
//                        clearFlagForInteractiveUser();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Azan> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.i("TAG", " onFailure " + t.getMessage());
//                if (progressBar != null) {
//                    clearFlagForInteractiveUser();
//                }
//            }
//        });
    }

    private fun getPrayerTimes(latitude: Double, longitude: Double) {
//        // if (getMethodPrefrences())
//        Call<Azan> azanCall = apiServices.getPrayerTimes(latitude, longitude, false, Integer.valueOf(Prayer_timing_default));
//        azanCall.enqueue(new Callback<Azan>() {
//            @Override
//            public void onResponse(Call<Azan> call, Response<Azan> response) {
//                Azan azan = response.body();
//                try {
//                    if (azan.getStatus().equals("OK")) {
//                        Log.i("TAG", " OK AddPrayerTimesForMonth");
//
//                        TimingsAppDatabase.getInstance(getActivity()).AddPrayerTimesForMonth(AzanFragment.this, azan, city_name);
//                    } else {
//                        TVShowError.setVisibility(View.VISIBLE);
//                        TVShowError.setText(getActivity().getString(R.string.cant));
//                        clearFlagForInteractiveUser();
//                    }
//                } catch (Exception e) {
//                    Log.i("TAG", " Error AddPrayerTimesForMonth" + e.getMessage());
//                    if (progressBar != null) {
//                        clearFlagForInteractiveUser();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Azan> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.i("TAG", " Error " + t.getMessage());
//                if (progressBar != null) {
//                    clearFlagForInteractiveUser();
//                }
//            }
//        });
    }

    private fun getCity(listener: onGpsListener) {
        disInteractiveUSer()
        //        Call<GetCity> getCityCall = apiServicesForCity.getCity();
//        getCityCall.enqueue(new Callback<GetCity>() {
//            @Override
//            public void onResponse(Call<GetCity> call, Response<GetCity> response) {
//                GetCity city = response.body();
//                try {
//                    if (city.getStatus().equals("success")) {
//                        city_name = getCityNameByLatitudeAndLongitude(city.getLat(), city.getLon());
//                        Log.d("TAG", "City name in arabic is : " + city_name);
//                        if (!isValueForPrayerTimesChanged) {
//                            getMethodPreferences(city.getCountry());
//                        }
//                        if (city_name != null) {
//                            getPrayerTimesByCity(city.getCity(), city.getCountry(), Integer.valueOf(Prayer_timing_default), city_name);
//                        } else {
//                            getPrayerTimesByCity(city.getCity(), city.getCountry(), Integer.valueOf(Prayer_timing_default), city.getCity());
//                        }
//                    } else {
//                        clearFlagForInteractiveUser();
//                        if (checkGPS()) {
//                            if (bundle == null) {
//                                gpsUtils.turnGPSOn(listener);
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    Log.i("TAG", " Error " + e.getMessage());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetCity> call, Throwable t) {
//                Log.i("TAG", " onFailure " + t.getMessage());
//                checkBeforeGetDataFromInternetTest();
//            }
//        });
    }

    //permission is automatically granted on sdk<23 upon installation
    private val isStoragePermissionGranted: Boolean
        private get() {
            Log.i("TAG", " isStoragePermissionGranted")
            try {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(
                            requireActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        Log.i("TAG", " Granted fisrt")
                        disInteractiveUSer()
                        true
                    } else {
                        requestPermissions(
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            MY_PERMISSIONS_WRITE_STORAGE
                        )
                        Log.i("TAG", " not Granted first ")
                        clearFlagForInteractiveUser()
                        false
                    }
                } else {
                    //permission is automatically granted on sdk<23 upon installation
                    true
                }
            } catch (e: Exception) {
            }
            return false
        }

    private fun SnackbarPermissionStorage(title: String, text_button: String) {
        val snackbar = Snackbar.make(requireView(), title, Snackbar.LENGTH_LONG)
            .setAction(text_button) {
                Log.i("TAG", "isStoragePermissionGranted third")
                isStoragePermissionGranted
            }
        snackbar.setActionTextColor(ContextCompat.getColor(requireActivity(), R.color.colorAccent))
        snackbar.show()
    }

    private fun SnackbarForTextPermission(title: String) {
        val snackbar = Snackbar.make(requireView(), title, Snackbar.LENGTH_LONG)
        // snackbar.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        snackbar.show()
    }

    private fun SnackbarPermissionLocation(title: String, text_button: String) {
        val snackbar = Snackbar.make(requireView(), title, Snackbar.LENGTH_LONG)
            .setAction(text_button) { checkGPS() }
        snackbar.setActionTextColor(ContextCompat.getColor(requireActivity(), R.color.colorAccent))
        snackbar.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_WRITE_STORAGE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("TAG", " Grnted second")
                getCity(this)
            } else {
                if (shouldShowRequestPermissionRationale(permissions[0])) {
                    Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE if")
                    SnackbarPermissionStorage(
                        getString(R.string.grand_permission),
                        getString(R.string.allow)
                    )
                } else {
                    openSettingsIfUserDenyNeverPermissionForStorage()
                    Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE else")
                }
                clearFlagForInteractiveUser()
            }
            LOCATION_PERMISSION_REQUEST_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("TAG", "LOCATION_PERMISSION_REQUEST_CODE turnGPS ")
                //turnGPS();
                gpsUtils!!.turnGPSOn(this)
            } else {
                if (shouldShowRequestPermissionRationale(permissions[0])) {
                    Log.i("TAG", "MY_PERIMISSIONS_LOCATION if")
                    SnackbarPermissionLocation(
                        getString(R.string.grand_permission),
                        getString(R.string.allow)
                    )
                } else {
                    openSettingsIfUserDenyNeverPermissionForLocation()
                    Log.i("TAG", "MY_PERIMISSIONS_LOCATION else")
                }
                Log.i("TAG", "Not Graunted Location")
                clearFlagForInteractiveUser()
                // break
            }
            else -> {}
        }
    }

    override fun onLocationChanged(location: Location) {
        Log.i("TAG", "onLocationChanged : $location")
    }

    fun showSettingsAlert() {
        if (activity != null) {
            val alertDialog = AlertDialog.Builder(activity)
            alertDialog.setTitle(R.string.settings_gps)
            alertDialog.setCancelable(false)
            alertDialog.setMessage(R.string.is_open_gps)
            alertDialog.setPositiveButton(R.string.settings) { dialog, which ->
                try {
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    //     startActivityForResult(intent, LOCATION_PERMISSION_REQUEST_CODE);
                    startActivityForResult(intent, AppConstants.GPS_REQUEST)
                } catch (e: Exception) {
                    Log.i("TAG", "Activity e is :" + e.message)
                }
            }
            alertDialog.setNegativeButton(R.string.cancel) { dialog, which ->
                showTextError()
                dialog.dismiss()
                clearFlagForInteractiveUser()
            }
            val dialogCreator = alertDialog.create()
            dialogCreator.show()
            val neagtive_button = dialogCreator.getButton(DialogInterface.BUTTON_NEGATIVE)
            val positive_button = dialogCreator.getButton(DialogInterface.BUTTON_POSITIVE)
            val params_for_space_between_buttons = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params_for_space_between_buttons.setMargins(0, 0, 30, 0)
            neagtive_button.layoutParams = params_for_space_between_buttons
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                positive_button.setBackgroundColor(requireContext().getColor(R.color.colorAccent))
                neagtive_button.setBackgroundColor(requireContext().getColor(R.color.colorAccent))
            } else {
                positive_button.setBackgroundColor(resources.getColor(R.color.colorAccent))
                neagtive_button.setBackgroundColor(resources.getColor(R.color.colorAccent))
            }
        } else {
            Log.i("TAG", "Activity is null....")
        }
    }

    fun openSettingsIfUserDenyNeverPermissionForStorage() {
        customForOpenSettings(MY_PERMISSIONS_WRITE_STORAGE, R.string.get_permission_storage)
    }

    fun openSettingsIfUserDenyNeverPermissionForLocation() {
        customForOpenSettings(LOCATION_PERMISSION_REQUEST_CODE, R.string.get_permission_location)
    }

    private fun customForOpenSettings(type_permission: Int, text_permision: Int) {
        if (activity != null) {
            val alertDialog = AlertDialog.Builder(activity)
            alertDialog.setTitle(R.string.go_settings)
            alertDialog.setCancelable(false)
            alertDialog.setMessage(text_permision)
            alertDialog.setPositiveButton(R.string.settings) { dialog, which ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts(
                        getString(R.string.package_string),
                        requireActivity().packageName,
                        null
                    )
                    intent.data = uri
                    startActivityForResult(intent, type_permission)
                } catch (e: Exception) {
                    Log.i("TAG", "Activity e is :" + e.message)
                }
            }
            alertDialog.setNegativeButton(R.string.cancel) { dialog, which ->
                showTextError()
                dialog.dismiss()
                clearFlagForInteractiveUser()
            }
            val dialogCreator = alertDialog.create()
            dialogCreator.show()
            val neagtive_button = dialogCreator.getButton(DialogInterface.BUTTON_NEGATIVE)
            val positive_button = dialogCreator.getButton(DialogInterface.BUTTON_POSITIVE)
            val params_for_space_between_buttons = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params_for_space_between_buttons.setMargins(0, 0, 30, 0)
            neagtive_button.layoutParams = params_for_space_between_buttons
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                positive_button.setBackgroundColor(requireContext().getColor(R.color.colorAccent))
                neagtive_button.setBackgroundColor(requireContext().getColor(R.color.colorAccent))
            } else {
                positive_button.setBackgroundColor(resources.getColor(R.color.colorAccent))
                neagtive_button.setBackgroundColor(resources.getColor(R.color.colorAccent))
            }
        } else {
            Log.i("TAG", "Activity is null....")
        }
    }

    private fun showDialogBoxForCompareMethod() {
        if (activity != null) {
            val alertDialog = AlertDialog.Builder(activity)
            alertDialog.setTitle(R.string.change_method)
            alertDialog.setCancelable(false)
            alertDialog.setMessage(R.string.is_want_new_data)
            alertDialog.setPositiveButton(R.string.textYes) { dialog, which ->
                isValueForPrayerTimesChanged = true
                isNetworkConnected
            }
            alertDialog.setNegativeButton(R.string.cancel) { dialog, which -> dialog.dismiss() }
            val dialogCreator = alertDialog.create()
            dialogCreator.show()
            val neagtive_button = dialogCreator.getButton(DialogInterface.BUTTON_NEGATIVE)
            val positive_button = dialogCreator.getButton(DialogInterface.BUTTON_POSITIVE)
            val params_for_space_between_buttons = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params_for_space_between_buttons.setMargins(0, 0, 30, 0)
            neagtive_button.layoutParams = params_for_space_between_buttons
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                positive_button.setBackgroundColor(requireContext().getColor(R.color.colorAccent))
                neagtive_button.setBackgroundColor(requireContext().getColor(R.color.colorAccent))
            } else {
                positive_button.setBackgroundColor(resources.getColor(R.color.colorAccent))
                neagtive_button.setBackgroundColor(resources.getColor(R.color.colorAccent))
            }
        } else {
            Log.i("TAG", "Activity is null....")
        }
    }

    private fun showTextError() {
        SnackbarForTextPermission(getString(R.string.not_allow))
        binding.progressBar!!.visibility = View.GONE
    }

    private fun checkGPS(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i("TAG", " checkGPS ")
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.i("TAG", "not Permission")
                requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ), LOCATION_PERMISSION_REQUEST_CODE
                )
                return false
            }
            return true
        }
        return true
    }

    fun updateGPSCoordinates() {
        if (location_user != null) {
            latitude = location_user!!.latitude
            longitude = location_user!!.longitude
        }
    }

//    fun getLatitude(): Double {
//        if (location_user != null) {
//            latitude = location_user!!.latitude
//        }
//        return latitude
//    }

//    fun getLongitude(): Double {
//        if (location_user != null) {
//            longitude = location_user!!.longitude
//        }
//        return longitude
//    }

    override fun onResume() {
        super.onResume()
        // adapterAzan.notifyDataSetChanged();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            requireActivity()
        )
//        Prayer_timing_default = sharedPreferences.getString(
//            getString(R.string.settings_method_key),
//            getString(R.string.settings_method_default)
//        )
//        number_azan_default = sharedPreferences.getString(
//            getString(R.string.settings_azan_key),
//            getString(R.string.settings_azan_default)
//        )
        compare_methods =
            SharedPerefrenceHelper.getStringCompareMethod(activity, COMPARE_METHOD, "5")
        if (SharedPerefrenceHelper.getBooleanForWayUsing(
                activity,
                NavigationDrawaberActivity.IS_FIRST_TIME_WAY_USING,
                false
            )
        ) {
            if (compare_methods != Prayer_timing_default) {
                showDialogBoxForCompareMethod()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun gpsStatus(isGPSEnable: Boolean) {
        isGPSEnabled = isGPSEnable
    }

    override fun getLocation(location: Location) {
        Log.d(
            "TAG",
            "Azan location is locationCallback " + location.latitude + " : " + location.longitude
        )
        if (location != null) {
            binding.progressBar!!.visibility = View.VISIBLE
            //Update UI with location data
            location_user = location
            //Toast.makeText(getActivity(), " " + location_user.getLongitude() + " :  " + location_user.getLatitude(), Toast.LENGTH_SHORT).show();
            updateGPSCoordinates()
            city_name = getCityName(location)
            // if (!store_city_name.equals(null) && store_city_name.equals(city_name)) {
            if (isValueForPrayerTimesChanged) {
//                TimingsAppDatabase.getInstance(activity)
//                    .DeletePrayerTimesForGetDataWithLocation(this@AzanFragment)
            } else {
                if (NavigationDrawaberActivity.store_city_name != null && NavigationDrawaberActivity.store_city_name == city_name) {
                    Snackbar.make(
                        requireView(),
                        "بالفعل انت في مدينة $city_name",
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                    clearFlagForInteractiveUser()
                    gpsUtils!!.stopLocationUpdtaes()
                    return
                } else {
//                    TimingsAppDatabase.getInstance(activity)
//                        .DeletePrayerTimesForGetDataWithLocation(this@AzanFragment)
                }
            }
        }
    }

    fun getCityName(location: Location?): String? {
        var cityName: String? = null
        if (location != null) {
            val locale = Locale("ar")
            //For change language to English
            // Geocoder geocoder = new Geocoder(getActivity(), Locale.ENGLISH);
            val geocoder = Geocoder(activity, locale)
            try {
                val addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    geocoderMaxResults
                )
                if (addresses.size > 0) {
                    for (adr in addresses) {
                        if (adr.locality != null && adr.locality.length > 0) {
                            cityName = adr.locality
                            break
                        } else {
                            cityName = adr.subAdminArea
                            break
                        }
                    }
                }
                return cityName
            } catch (e: IOException) {
            }
        }
        return null
    }

    private fun getCityNameByLatitudeAndLongitude(latitude: Double, longitude: Double): String? {
        var cityName: String? = null
        //For change language to English
        val locale = Locale("ar")
        val geocoder = Geocoder(activity, locale)
        //  Geocoder geocoder = new Geocoder(getActivity(), Locale.ENGLISH);
        //Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null && addresses.size > 0) {
                for (adr in addresses) {
                    if (adr.locality != null && adr.locality.length > 0) {
                        cityName = adr.locality
                        Log.d("TAG", " cityName getCityNameTest : $cityName")
                        break
                    } else {
                        cityName = adr.subAdminArea
                    }
                }
            }
            return cityName
        } catch (e: IOException) {
            Log.d("TAG", " E : " + e.message)
        }
        return null
    }

//    override fun onPrayerTimesAdded() {
//        HelperClass.customToast(activity, getString(R.string.save_data))
//        if (binding.progressBar != null) {
//            clearFlagForInteractiveUser()
//            gpsUtils!!.stopLocationUpdtaes()
//        }
//        SharedPerefrenceHelper.putStringCompareMethod(
//            activity,
//            COMPARE_METHOD,
//            Prayer_timing_default
//        )
//        changeValueInListPreference()
//        SharedPerefrenceHelper.putStringAzan(activity, AZAN_DEFUALT, number_azan_default)
//        changeValueInListPreferenceForAzan()
//    }

    private fun changeValueInListPreferenceForAzan() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            requireActivity()
        )
//        val editor = sharedPreferences.edit()
//        editor.putString(getString(R.string.settings_azan_key), number_azan_default)
//        editor.commit()
    }

    private fun changeValueInListPreference() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            requireActivity()
        )
//        val editor = sharedPreferences.edit()
//        editor.putString(getString(R.string.settings_method_key), Prayer_timing_default)
//        editor.commit()
    }

//    override fun onPrayerTimesDeleted() {
//        checkBeforeGetData()
//        Log.d("TAG", "onPrayerTimesDeleted")
//    }
//
//    override fun getDataFromLocationAfterDeleteData() {
//        if (binding.progressBar != null) {
//            clearFlagForInteractiveUser()
//        }
//        // getPrayerTimes(getLatitude(), getLongitude())
//        // checkBeforeGetDataFromInternet();
//        Log.d("TAG", "getDataFromLocationAfterDeleteData")
//    }
//
//    override fun onPrayerTimesError(e: Throwable) {
//        if (binding.progressBar != null) {
//            clearFlagForInteractiveUser()
//            Toast.makeText(activity, "Eror is : " + e.message, Toast.LENGTH_SHORT).show()
//        }
//    }// a potentially time consuming task//send BroadcastReceiver to the Service -> Not Internet

    //check Internet
    private val isNetworkConnected: Unit
        private get() {
            disInteractiveUSer()
            val noInternetConnection = NoInternetConnection()
            noInternetConnection.execute("http://clients3.google.com/generate_204")
            val isConnected = NetworkConnection.networkConnectivity(activity)
            Handler().postDelayed({
                if (!isConnected) {
                    val broadcastIntent = Intent(MediaPlayerService.BROADCAST_NOT_CONNECTION)
                    requireActivity().sendBroadcast(broadcastIntent)
                    Log.d("TAG", "the internet is not connected")
                    clearFlagForInteractiveUser()
                } else {
                    if (!NoInternetConnection.isInternet()) {
                        //send BroadcastReceiver to the Service -> Not Internet
                        val broadcastIntent = Intent(MediaPlayerService.BROADCAST_NOT_INTERNET)
                        requireActivity().sendBroadcast(broadcastIntent)
                        clearFlagForInteractiveUser()
                    } else {
                        if (isRefresh) {
                            checkBeforeGetDataFromInternetTest()
                        } else {
                            Log.d("TAG", "TimingsAppDatabase DeletePrayerTimes second")
                            Thread { // a potentially time consuming task
                                if (!NavigationDrawaberActivity.checkIsGetData) {
//                                    TimingsAppDatabase.getInstance(activity)
//                                        .DeletePrayerTimes(this@AzanFragment)
                                    Log.d(
                                        "TAG",
                                        "City name is from store_city_name" + NavigationDrawaberActivity.store_city_name
                                    )
                                    Log.d(
                                        "TAG",
                                        "isDataTrue " + NavigationDrawaberActivity.checkIsGetData
                                    )
                                }
                            }.start()
                        }
                    }
                }
            }, 1000)
        }

    private fun registerNoConnection() {
        //Register no internet receiver
        val filter = IntentFilter(MediaPlayerService.BROADCAST_NOT_CONNECTION)
        requireActivity().registerReceiver(connectivityReceiver, filter)
    }

    private fun registerNoInternet() {
        //Register no internet receiver
        val filter = IntentFilter(MediaPlayerService.BROADCAST_NOT_INTERNET)
        requireActivity().registerReceiver(noInternetReceiver, filter)
    }

    private fun clearFlagForInteractiveUser() {
        binding.progressBar!!.visibility = View.GONE
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun disInteractiveUSer() {
        binding.progressBar!!.visibility = View.VISIBLE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
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
        return Prayer_timing_default
    }

    private fun customForPreferencesAzan(method_key: String, method_default: String): String? {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            requireActivity()
        )
        // number_azan_default = sharedPreferences.getString(method_key, method_default)
        return number_azan_default
    }

    private fun customForPreferences(method_key: String, method_default: String): String? {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(
            requireActivity()
        )
        // Prayer_timing_default = sharedPreferences.getString(method_key, method_default)
        return Prayer_timing_default
    }

    companion object {
        const val COMPARE_METHOD = "compare_method"
        const val AZAN_DEFUALT = "azan_defualt"
        const val LOCATION_PERMISSION_REQUEST_CODE = 15
        private const val VALUE_ZERO = 10
        private const val VALUE_MINS_ONE = -1
        const val MY_PERMISSIONS_WRITE_STORAGE = 90
        private var isRefresh = false
    }
}