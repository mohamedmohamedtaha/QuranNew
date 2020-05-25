package com.MohamedTaha.Imagine.New.ui.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.MohamedTaha.Imagine.New.Adapter.AdapterAzanVP;
import com.MohamedTaha.Imagine.New.AppConstants;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.databinding.FragmentAzanBinding;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Datum;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;
import com.MohamedTaha.Imagine.New.mvp.model.getCity.GetCity;
import com.MohamedTaha.Imagine.New.receiver.ConnectivityReceiver;
import com.MohamedTaha.Imagine.New.receiver.NoInternetReceiver;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.room.DatabaseCallback;
import com.MohamedTaha.Imagine.New.room.TimingsAppDatabase;
import com.MohamedTaha.Imagine.New.room.TimingsViewModel;
import com.MohamedTaha.Imagine.New.service.MediaPlayerService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;
import static com.MohamedTaha.Imagine.New.Adapter.AdapterAzanVP.cancelTimer;
import static com.MohamedTaha.Imagine.New.Adapter.AdapterAzanVP.cancelTimerForTextView;
import static com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection.isInternet;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClient.getRetrofit;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClientCity.getRetrofitForCity;
import static com.MohamedTaha.Imagine.New.service.MediaPlayerService.BROADCAST_NOT_CONNECTION;
import static com.MohamedTaha.Imagine.New.service.MediaPlayerService.BROADCAST_NOT_INTERNET;
import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.IS_FIRST_TIME_WAY_USING;
import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.store_city_name;
import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.store_date_today;

/**
 * A simple {@link Fragment} subclass.
 */
public class AzanFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, DatabaseCallback {
    public static final String COMPARE_METHOD = "compare_method";
    private TimingsViewModel timingsViewModel;
    GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private long UPDATE_INTERVAL = 15000; /* 15 secs */
    private long FASTER_INTERVAL = 5000;
    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 15;
    protected LocationManager locationManager;
    // flag for GPS Status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;
    // flag for GPS Tracking is enabled
    boolean isGPSTrackingEnabled = false;
    boolean isGPSPermission = true;
    private Location location_user;
    double latitude;
    double longitude;
    // How many Geocoder should return our GPSTracker
    int geocoderMaxResults = 1;
    // Store LocationManager.GPS_PROVIDER or LocationManager.NETWORK_PROVIDER information
    private String provider_info = null;
    public static final int ERROR_DIALOG_REQUEST = 9001;
    private int save_request_code_back_from_turn_gps;
    // The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute
   // public static final int MY_PERIMISSIONS_LOCATION = 10;
    private Location lastLocation;
    public static final int MY_PERMISSIONS_WRITE_STORAGE = 90;
    APIServices apiServices;
    APIServices apiServicesForCity;

    List<Datum> datumList = new ArrayList<>();
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    String language_name;
    String city_name = null;
    private CompositeDisposable disposable = new CompositeDisposable();
    private FragmentAzanBinding fragmentAzanBinding;
    private List<Timings> getAllData = new ArrayList<>();
    private ConnectivityReceiver connectivityReceiver = null;
    private NoInternetReceiver noInternetReceiver = null;
    private static boolean isRefresh = false;
    private Calendar getCalender;
    SharedPreferences sharedPreferences;
    String repear;
    FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private boolean requestingLocationUpdates = true;
    private String compare_methods = null;


    public AzanFragment() {
        // Required empty public constructor
    }

    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAzanBinding = FragmentAzanBinding.inflate(inflater, container, false);
        View viewBuinding = fragmentAzanBinding.getRoot();
        language_name = Locale.getDefault().getLanguage();
        getActivity().setTitle(getString(R.string.elslah));
        if (!language_name.equals("ar")) {
            HelperClass.change_language("ar", getActivity());
        }
//       sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//       repear = sharedPreferences.getString(getString(R.string.settings_method_key),
//                getString(R.string.settings_method_default));
//
//        compare_methods = SharedPerefrenceHelper.getStringCompareMethod(getActivity(), COMPARE_METHOD, "4");

        bundle = getArguments();
        if (bundle != null) {
            int bundle1 = bundle.getInt("bundle");
            if (bundle1 == -1) {
                turnGPS();
            } else {
                showSettingsAlert();
            }
        }
        connectivityReceiver = new ConnectivityReceiver();
        noInternetReceiver = new NoInternetReceiver();
        registerNoConnection();
        registerNoInternet();
        apiServices = getRetrofit().create(APIServices.class);
        apiServicesForCity = getRetrofitForCity().create(APIServices.class);
        Log.i("TAG", "onCreateView");

//        getPrayerTimesByCityForYear("Riyadh","Saudi Arabia",4,"Riyadh");

        flowableGetAllPrayerTimingFromDatabase();
//
        //  for avoid start show way using
        if (SharedPerefrenceHelper.getBooleanForWayUsing(getActivity(), IS_FIRST_TIME_WAY_USING, false)) {
            Log.d("TAG", "Repear is " + repear);
//            if ( !compare_methods.equals(repear)) {
//                showDialogBoxForCompareMethod();
//            }
            if (store_date_today <= 0) {
                isNetworkConnected();
            }    }


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
//        new GpsUtils(getActivity()).turnGPSOn(new GpsUtils.onGpsListener() {
//            @Override
//            public void gpsStatus(boolean isGPSEnable) {
//                // turn on GPS
//                isGPSEnabled = isGPSEnable;
//            }
//        });

        //   turnGPS();

        //  getLocation();
        // turnGPSOn();
        //Can't get location.
        //GPS or network is not enabled.
        // Ask user to enable GPS / network in settings.

        //   gpsTracker.getLocationTest();

        // }
        return viewBuinding;
    }

    private void flowableGetAllPrayerTimingFromDatabase() {
        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        Flowable<List<Timings>> flowableGetAllPrayerTimingFromDatabase = timingsViewModel.getAllTimingsRxjava();
        flowableGetAllPrayerTimingFromDatabase.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(all_Data -> {
                    getAllData = all_Data;
                    Log.i("TAG", "Navigation Drawaer : " + store_date_today);
                    if (store_date_today > 0) {
                        if (getAllData == null && getAllData.size() <= 0) {
                            //The data is null
                            fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                            fragmentAzanBinding.TVShowError.setText(getString(R.string.no_data));
                            fragmentAzanBinding.AzanFragmentVP.setVisibility(View.GONE);
                            Log.i("TAG", "The data is null : " + getAllData.size());
                            clearFlagForInteractiveUser();
                        } else {
                            fragmentAzanBinding.TVShowError.setVisibility(View.GONE);
                            fragmentAzanBinding.AzanFragmentVP.setVisibility(View.VISIBLE);
                            AdapterAzanVP adapterAzan = new AdapterAzanVP(getActivity(), new AdapterAzanVP.ClickListener() {
                                @Override
                                public void CheckCity() {

                                    isRefresh = true;
                                    isNetworkConnected();
                                }
                            });
                            adapterAzan.setAzanList(getAllData);
                            fragmentAzanBinding.AzanFragmentVP.setAdapter(adapterAzan);
                            fragmentAzanBinding.AzanFragmentVP.setCurrentItem(store_date_today - 1);
                            clearFlagForInteractiveUser();
                            Log.i("TAG", "all data " + getAllData.size());
                        }
                    } else {
                        fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                        fragmentAzanBinding.TVShowError.setText(getString(R.string.no_data));
                        fragmentAzanBinding.AzanFragmentVP.setVisibility(View.GONE);
                        Log.i("TAG", "The data is null : " + getAllData.size());
                        clearFlagForInteractiveUser();
                    }
                }, e -> {
                    Log.i("TAG", "Error RXJava : " + e.getMessage());
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "the timer is cancel");
        if (connectivityReceiver != null) {
            getActivity().unregisterReceiver(connectivityReceiver);
        }
        if (noInternetReceiver != null) {
            getActivity().unregisterReceiver(noInternetReceiver);
        }
        cancelTimer();
        cancelTimerForTextView();
    }

    private void checkBeforeGetDataFromInternetTest() {
        if (isStoragePermissionGranted()) {
            if (checkGPS()) {
                turnGPS();
            }

        }
    }

    private void checkBeforeGetData() {
        if (isStoragePermissionGranted()) {
            getCity();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                save_request_code_back_from_turn_gps = requestCode;
                Log.i("TAG", "requestCode");
                locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (isGPSEnabled) {
                    Log.i("TAG", "isGPSEnabled: " + isGPSEnabled);
                    turnGPS();
                } else {
                    disInteractiveUSer();
                    Log.i("TAG", "isGPSEnabled: " + isGPSEnabled);
                    getCity();
                  //  /
                }
                break;
            case AppConstants.GPS_REQUEST:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i("TAG", "RESULT_OK");
                        break;
                    case Activity.RESULT_CANCELED:
                        save_request_code_back_from_turn_gps = requestCode;
                        Log.i("TAG", "RESULT_CANCELED");
                        break;
                    default:
                        break;
                }
            case MY_PERMISSIONS_WRITE_STORAGE :
                if (isStoragePermissionGranted()) {
                    getCity();
                    Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE OK");
                }else {
                    Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE CANCELED");
                }
            default:
        }
    }

    private void getPrayerTimesByCity(String city, String country, int method, String city_name) {
        Call<Azan> azanCall = apiServices.getPrayerTimesByCity(city, country, false, method);
        azanCall.enqueue(new Callback<Azan>() {
            @Override
            public void onResponse(Call<Azan> call, Response<Azan> response) {
                Azan azan = response.body();
                try {
                    if (azan.getStatus().equals("OK")) {
                        TimingsAppDatabase.getInstance(getActivity()).AddPrayerTimesForMonth(AzanFragment.this, azan, city_name);
                    } else {
                        fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                        fragmentAzanBinding.TVShowError.setText(getActivity().getString(R.string.cant));
                        clearFlagForInteractiveUser();
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error " + e.getMessage());
                    if (fragmentAzanBinding.progressBar != null) {
                        clearFlagForInteractiveUser();
                    }
                }
            }

            @Override
            public void onFailure(Call<Azan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", " onFailure " + t.getMessage());
                if (fragmentAzanBinding.progressBar != null) {
                    clearFlagForInteractiveUser();
                }
            }
        });
    }


    private void getPrayerTimesByCityForYear(String city, String country, int method, String city_name) {
        Call<com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Azan> azanCall = apiServices.getPrayerTimesByCityForYear(city, country, true, method);
        azanCall.enqueue(new Callback<com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Azan>() {
            @Override
            public void onResponse(Call<com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Azan> call, Response<com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Azan> response) {
                com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Azan azan = response.body();
                try {
                    if (azan.getStatus().equals("OK")) {
                        Log.i("TAG", " OK ");

                        //         TimingsAppDatabase.getInstance(getActivity()).AddPrayerTimesForYear(AzanFragment.this, azan, city_name);
                    } else {
                        fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                        fragmentAzanBinding.TVShowError.setText(getActivity().getString(R.string.cant));
                        clearFlagForInteractiveUser();
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error " + e.getMessage());
                    if (fragmentAzanBinding.progressBar != null) {
                        clearFlagForInteractiveUser();
                    }
                }
            }

            @Override
            public void onFailure(Call<com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Azan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", " onFailure " + t.getMessage());
                if (fragmentAzanBinding.progressBar != null) {
                    clearFlagForInteractiveUser();
                }
            }
        });
    }

    private void getPrayerTimes(double latitude, double longitude) {
        Call<Azan> azanCall = apiServices.getPrayerTimes(latitude, longitude, false, Integer.valueOf(repear));
        azanCall.enqueue(new Callback<Azan>() {
            @Override
            public void onResponse(Call<Azan> call, Response<Azan> response) {
                Azan azan = response.body();
                try {
                    if (azan.getStatus().equals("OK")) {
                        TimingsAppDatabase.getInstance(getActivity()).AddPrayerTimesForMonth(AzanFragment.this, azan, city_name);
                    } else {
                        fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                        fragmentAzanBinding.TVShowError.setText(getActivity().getString(R.string.cant));
                        clearFlagForInteractiveUser();
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error " + e.getMessage());
                    if (fragmentAzanBinding.progressBar != null) {
                        clearFlagForInteractiveUser();
                    }
                }
            }

            @Override
            public void onFailure(Call<Azan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", " Error " + t.getMessage());
                if (fragmentAzanBinding.progressBar != null) {
                    clearFlagForInteractiveUser();
                }
            }
        });
    }

    private void getCity() {
        disInteractiveUSer();
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
                        //  getMethodPrefrences("Egypt");
                        Log.d("TAG", "City name in arabic is : " + city_name);
                        getPrayerTimesByCity(city.getCity(), city.getCountry(), Integer.valueOf(repear), city_name);
                    } else {
                        fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                        fragmentAzanBinding.TVShowError.setText(getActivity().getString(R.string.cant));
                        clearFlagForInteractiveUser();
                        if (save_request_code_back_from_turn_gps == 1001) {
                            Log.i("TAG", "save_request_code" + save_request_code_back_from_turn_gps);
                            showSettingsAlert();
                        } else {
                            turnGPS();
                            Log.i("TAG", " getCity turnGPS " + save_request_code_back_from_turn_gps);

                        }
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error " + e.getMessage());
//                    if (fragmentAzanBinding.progressBar != null) {
//                        clearFlagForInteractiveUser();
////                        if (save_request_code_back_from_turn_gps == 1001) {
////                            Log.i("TAG", "save_request_code" + save_request_code_back_from_turn_gps);
////                            showSettingsAlert();
////                        } else {
////                          //  turnGPSOn();
////                        }
//                    }
                }
            }

            @Override
            public void onFailure(Call<GetCity> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", " onFailure " + t.getMessage());
                if (fragmentAzanBinding.progressBar != null) {
                    clearFlagForInteractiveUser();
                    if (save_request_code_back_from_turn_gps == 1001) {
                        Log.i("TAG", "save_request_code" + save_request_code_back_from_turn_gps);
                        showSettingsAlert();
                    } else {
                        turnGPS();
                    }
                }
            }
        });
    }

    private boolean isStoragePermissionGranted() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", " Granted fisrt");
                    disInteractiveUSer();
                    return true;
                } else {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_STORAGE);
                    Log.i("TAG", " not Granted first ");
                    clearFlagForInteractiveUser();
                    return false;
                }
            } else {
                //permission is automatically granted on sdk<23 upon installation
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    private void SnackbarPermissionStorage(String title, String text_button) {
        Snackbar snackbar = Snackbar.make(getView(), title, Snackbar.LENGTH_LONG)
                .setAction(text_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isStoragePermissionGranted();
                    }
                });
        snackbar.setActionTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        snackbar.show();
    }

    private void SnackbarForTextPermission(String title) {
        Snackbar snackbar = Snackbar.make(getView(), title, Snackbar.LENGTH_LONG);
        snackbar.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        snackbar.show();
    }

    private void SnackbarPermissionLocation(String title, String text_button) {
        Snackbar snackbar = Snackbar.make(getView(), title, Snackbar.LENGTH_LONG)
                .setAction(text_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkGPS();
                    }

                });
        snackbar.setActionTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        snackbar.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_WRITE_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", " Grnted second");
                    getCity();

                    // checkGPS();
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE if");
                        SnackbarPermissionStorage(getString(R.string.grand_permission), getString(R.string.allow));
                    } else {
                        openSettingsIfUserDenyNeverPermissionForStorage();
                        Log.i("TAG", "MY_PERMISSIONS_WRITE_STORAGE else");

                    }
                    clearFlagForInteractiveUser();
                }
                return;
            }
            case LOCATION_PERMISSION_REQUEST_CODE:{
        // case MY_PERIMISSIONS_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //   getCity();
                    turnGPS();
                    //  turnGPSOn();
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        Log.i("TAG", "MY_PERIMISSIONS_LOCATION if");
                        SnackbarPermissionLocation(getString(R.string.grand_permission), getString(R.string.allow));
                    } else {
                        openSettingsIfUserDenyNeverPermissionForLocation();
                        Log.i("TAG", "MY_PERIMISSIONS_LOCATION else");
                    }
                    Log.i("TAG", "Not Graunted Location");
                    clearFlagForInteractiveUser();
                }
                return;
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("TAG", "onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("TAG", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("TAG", "onConnectionFailed");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("TAG", "onLocationChanged : " + location);
    }


    public void showSettingsAlert() {
        if (getActivity() != null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(R.string.settings_gps);
            alertDialog.setCancelable(false);
            alertDialog.setMessage(R.string.is_open_gps);
            alertDialog.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, LOCATION_PERMISSION_REQUEST_CODE);
                    } catch (Exception e) {
                        Log.i("TAG", "Activity e is :" + e.getMessage());
                    }
                }
            });
            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showTextError();
                    dialog.dismiss();
                    clearFlagForInteractiveUser();
                }
            });
            AlertDialog dialogCreator = alertDialog.create();
            dialogCreator.show();
            Button neagtive_button = dialogCreator.getButton(DialogInterface.BUTTON_NEGATIVE);
            Button positive_button = dialogCreator.getButton(DialogInterface.BUTTON_POSITIVE);
            LinearLayout.LayoutParams params_for_space_between_buttons = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params_for_space_between_buttons.setMargins(0, 0, 30, 0);
            neagtive_button.setLayoutParams(params_for_space_between_buttons);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                positive_button.setBackgroundColor(getContext().getColor(R.color.colorAccent));
                neagtive_button.setBackgroundColor(getContext().getColor(R.color.colorAccent));
            } else {
                positive_button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                neagtive_button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        } else {
            Log.i("TAG", "Activity is null....");
        }
    }
    public void openSettingsIfUserDenyNeverPermissionForStorage() {
        customForOpenSettings(MY_PERMISSIONS_WRITE_STORAGE);
    }
    public void openSettingsIfUserDenyNeverPermissionForLocation() {
        customForOpenSettings(LOCATION_PERMISSION_REQUEST_CODE);
    }
    private void customForOpenSettings(int type_permission) {
        if (getActivity() != null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(R.string.go_settings);
            alertDialog.setCancelable(false);
            alertDialog.setMessage(R.string.get_permission);
            alertDialog.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package",getActivity().getPackageName(),null);
                        intent.setData(uri);
                        startActivityForResult(intent, type_permission);
                    } catch (Exception e) {
                        Log.i("TAG", "Activity e is :" + e.getMessage());
                    }
                }
            });
            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showTextError();
                    dialog.dismiss();
                    clearFlagForInteractiveUser();
                }
            });
            AlertDialog dialogCreator = alertDialog.create();
            dialogCreator.show();
            Button neagtive_button = dialogCreator.getButton(DialogInterface.BUTTON_NEGATIVE);
            Button positive_button = dialogCreator.getButton(DialogInterface.BUTTON_POSITIVE);
            LinearLayout.LayoutParams params_for_space_between_buttons = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params_for_space_between_buttons.setMargins(0, 0, 30, 0);
            neagtive_button.setLayoutParams(params_for_space_between_buttons);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                positive_button.setBackgroundColor(getContext().getColor(R.color.colorAccent));
                neagtive_button.setBackgroundColor(getContext().getColor(R.color.colorAccent));
            } else {
                positive_button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                neagtive_button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        } else {
            Log.i("TAG", "Activity is null....");
        }
    }


    private void showDialogBoxForCompareMethod() {
        if (getActivity() != null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(R.string.change_method);
            alertDialog.setCancelable(false);
            alertDialog.setMessage(R.string.is_want_new_data);
            alertDialog.setPositiveButton(R.string.textYes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
               //     isRefresh = true;
                    isNetworkConnected();
                }
            });
            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialogCreator = alertDialog.create();
            dialogCreator.show();
            Button neagtive_button = dialogCreator.getButton(DialogInterface.BUTTON_NEGATIVE);
            Button positive_button = dialogCreator.getButton(DialogInterface.BUTTON_POSITIVE);
            LinearLayout.LayoutParams params_for_space_between_buttons = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params_for_space_between_buttons.setMargins(0, 0, 30, 0);
            neagtive_button.setLayoutParams(params_for_space_between_buttons);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                positive_button.setBackgroundColor(getContext().getColor(R.color.colorAccent));
                neagtive_button.setBackgroundColor(getContext().getColor(R.color.colorAccent));
            } else {
                positive_button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                neagtive_button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        } else {
            Log.i("TAG", "Activity is null....");
        }
    }

    private void showTextError() {
        SnackbarForTextPermission(getString(R.string.not_allow));
        fragmentAzanBinding.progressBar.setVisibility(View.GONE);
    }

    private boolean checkGPS() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i("TAG", " checkGPS ");
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.i("TAG", "not Permission");
                requestPermissions(new String[]{
                   //     Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERIMISSIONS_LOCATION);
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

            return false;
            }
            return true;
        }
        return true;
    }


    public void updateGPSCoordinates() {
        if (location_user != null) {
            latitude = location_user.getLatitude();
            longitude = location_user.getLongitude();
        }
    }

    public double getLatitude() {
        if (location_user != null) {
            latitude = location_user.getLatitude();
        }
        return latitude;
    }

    public double getLongitude() {
        if (location_user != null) {
            longitude = location_user.getLongitude();
        }
        return longitude;
    }


    public void turnGPS() {
        mSettingsClient = LocationServices.getSettingsClient(getActivity());
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    Log.d("TAG", "location is null ");
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    fragmentAzanBinding.progressBar.setVisibility(View.VISIBLE);
                    //Update UI with location data
                    Log.d("TAG", "location is locationCallback " + location.getLatitude() + " : " + location.getLongitude());
                    location_user = location;
                    updateGPSCoordinates();
                    Log.i("TAG", "location_user : " + getLongitude() + " : " + getLatitude());
                    city_name = getCityName(location, getLatitude(), getLongitude());
                    Log.i("TAG", ":City name is :" + city_name);
                    if (!store_city_name.equals(null) && store_city_name.equals(city_name)) {
                        Snackbar.make(getView(), "بالفعل انت في مدينة " + city_name, Snackbar.LENGTH_LONG).show();
                        clearFlagForInteractiveUser();
                        stopLocationUpdtaes();
                        return;
                    } else {
                        Log.i("TAG", "TimingsAppDatabase.getInstance");
                        TimingsAppDatabase.getInstance(getActivity()).DeletePrayerTimesForGetDataWithLocation(AzanFragment.this);
                    }
                }
            }
        };
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        mLocationSettingsRequest = builder.build();
        builder.setAlwaysShow(true);
        builder.setNeedBle(true);

        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i("TAG", "onSuccess.....");
                        disInteractiveUSer();
                        startLocationUpdates();
                    }
                }).addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException rae = (ResolvableApiException) e;
                        Log.i("TAG", "not send.");
                        rae.startResolutionForResult(getActivity(), AppConstants.GPS_REQUEST);
                        Log.i("TAG", "Error." + rae.getMessage() + "\n" +
                                rae.getStatusCode());
                    } catch (IntentSender.SendIntentException sie) {
                        Log.i("TAG", "PendingIntent unable to execute request.");
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        repear = sharedPreferences.getString(getString(R.string.settings_method_key),
                getString(R.string.settings_method_default));
        compare_methods = SharedPerefrenceHelper.getStringCompareMethod(getActivity(), COMPARE_METHOD, "4");
        if (SharedPerefrenceHelper.getBooleanForWayUsing(getActivity(), IS_FIRST_TIME_WAY_USING, false)) {
            Log.d("TAG", "Repear is " + repear);
            if (!compare_methods.equals(repear)) {
                showDialogBoxForCompareMethod();
            }
//            if (store_date_today <= 0) {
//                isNetworkConnected();
//            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void startLocationUpdates() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void stopLocationUpdtaes() {
        if (fusedLocationProviderClient != null) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    public String getCityName(Location location, double latitude, double longitude) {
        String cityName = "";
        if (location != null) {
            Locale locale = new Locale("ar");
            //For change language to English
            // Geocoder geocoder = new Geocoder(getActivity(), Locale.ENGLISH);
            Geocoder geocoder = new Geocoder(getActivity(), locale);
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, this.geocoderMaxResults);
                if (addresses.size() > 0) {
                    for (Address adr : addresses) {
                        if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                            cityName = adr.getLocality();
                            break;
                        }
                    }
                }
                return cityName;
            } catch (IOException e) {
            }
        }

        return null;
    }

    private String getCityNameWithoutLocation(double latitude, double longitude) {
        String cityName = "";
        Locale locale = new Locale("ar");
        Geocoder geocoder = new Geocoder(getActivity(), locale);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, this.geocoderMaxResults);
            if (addresses.size() > 0) {
                for (Address adr : addresses) {
                    if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                        cityName = adr.getLocality();
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
    public void onPrayerTimesAdded() {
        HelperClass.customToast(getActivity(), getString(R.string.save_data));
        if (fragmentAzanBinding.progressBar != null) {
            clearFlagForInteractiveUser();
            stopLocationUpdtaes();
        }
        SharedPerefrenceHelper.putStringCompareMethod(getActivity(), COMPARE_METHOD, repear);
    }

    @Override
    public void onPrayerTimesDeleted() {
//        if (fragmentAzanBinding.progressBar != null) {
//            fragmentAzanBinding.progressBar.setVisibility(View.GONE);
//            clearFlagForInteractiveUser();
//        }
        checkBeforeGetData();
        Log.d("TAG", "onPrayerTimesDeleted");

    }

    @Override
    public void getDataFromLocationAfterDeleteData() {
        if (fragmentAzanBinding.progressBar != null) {
            clearFlagForInteractiveUser();
        }
        getPrayerTimes(getLatitude(), getLongitude());
        // checkBeforeGetDataFromInternet();
        Log.d("TAG", "getDataFromLocationAfterDeleteData");
    }

    @Override
    public void onPrayerTimesError() {
        if (fragmentAzanBinding.progressBar != null) {
            clearFlagForInteractiveUser();
        }
    }

    //check Internet
    private void isNetworkConnected() {
        disInteractiveUSer();
        NoInternetConnection noInternetConnection = new NoInternetConnection();
        noInternetConnection.execute("http://clients3.google.com/generate_204");
        boolean isConnected = NetworkConnection.networkConnectivity(getActivity());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isConnected) {
                    Intent broadcastIntent = new Intent(BROADCAST_NOT_CONNECTION);
                    getActivity().sendBroadcast(broadcastIntent);
                    Log.d("TAG", "the internet is not connected");
                    clearFlagForInteractiveUser();
                } else {
                    if (!isInternet()) {
                        //send BroadcastReceiver to the Service -> Not Internet
                        Intent broadcastIntent = new Intent(BROADCAST_NOT_INTERNET);
                        getActivity().sendBroadcast(broadcastIntent);
                        clearFlagForInteractiveUser();
                    } else {
                        if (isRefresh) {
                            checkBeforeGetDataFromInternetTest();
                        } else {
                            TimingsAppDatabase.getInstance(getActivity()).DeletePrayerTimes(AzanFragment.this);
                            Log.d("TAG", "City name is" + store_city_name);
                        }
                    }
                }
            }
        }, 1000);
    }

    private void registerNoConnection() {
        //Register no internet receiver
        IntentFilter filter = new IntentFilter(MediaPlayerService.BROADCAST_NOT_CONNECTION);
        getActivity().registerReceiver(connectivityReceiver, filter);
    }

    private void registerNoInternet() {
        //Register no internet receiver
        IntentFilter filter = new IntentFilter(MediaPlayerService.BROADCAST_NOT_INTERNET);
        getActivity().registerReceiver(noInternetReceiver, filter);
    }

    private void clearFlagForInteractiveUser() {
        fragmentAzanBinding.progressBar.setVisibility(View.GONE);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void disInteractiveUSer() {
            fragmentAzanBinding.progressBar.setVisibility(View.VISIBLE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    private void getMethodPrefrences(String country_name) {
        if (country_name.equals("Egypt")) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            //getString Retrieve a String value from the Preference
            repear = sharedPreferences.getString("3", "3");
            Log.d("TAG", "sharedPreferences :  " + repear);
        } else {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            //getString Retrieve a String value from the Preference
            repear = sharedPreferences.getString(getString(R.string.settings_method_key),
                    getString(R.string.settings_method_default));
            Log.d("TAG", "sharedPreferences :  " + repear);

        }

    }
}

