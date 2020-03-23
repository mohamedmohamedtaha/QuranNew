package com.MohamedTaha.Imagine.New;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MohamedTaha.Imagine.New.Adapter.AdapterAzanVP;
import com.MohamedTaha.Imagine.New.helper.GPSTracker;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Datum;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.room.TimingsAppDatabase;
import com.MohamedTaha.Imagine.New.room.TimingsViewModel;
import com.booking.rtlviewpager.RtlViewPager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;
import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClient.getRetrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class AzanFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    @BindView(R.id.AzanFragment_VP)
    RtlViewPager AzanFragmentVP;
    private TimingsViewModel timingsViewModel;
    GoogleApiClient googleApiClient;

    private LocationRequest locationRequest;
    private long UPDATE_INTERVAL = 15000; /* 15 secs */
    private long FASTER_INTERVAL = 5000;
    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();
    private final static int ALL_PERMISSIONS_RESULT = 101;


    public static final int LOCATION_PERMISSION_REQUEST_CODE = 15;
    protected LocationManager locationManager;
    // flag for GPS Status
    boolean isGPSEnabled = false;
    // flag for network status
    boolean isNetworkEnabled = false;
    // flag for GPS Tracking is enabled
    boolean isGPSTrackingEnabled = false;
    boolean isGPSPermission = true;
    Location location;
    double latitude;
    double longitude;
    // How many Geocoder should return our GPSTracker
    int geocoderMaxResults = 1;
    // Store LocationManager.GPS_PROVIDER or LocationManager.NETWORK_PROVIDER information
    private String provider_info = null;
    public static final int ERROR_DIALOG_REQUEST = 9001;
    private int save_request_code;

    // The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute
    // This int for permission location
    public static final int MY_PERIMISSIONS_LOCATION = 10;
    // Get Class Name
    private static String TAG = GPSTracker.class.getName();

    int data_today;

    private static final int MY_PERMISSIONS_WRITE_STORAGE = 90;
    APIServices apiServices;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    //    @BindView(R.id.recycler_view)
//    RecyclerView recyclerView;
    @BindView(R.id.TV_Show_Error)
    TextView TVShowError;
    List<Datum> datumList = new ArrayList<>();
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    String language_name;
    String city_name = null;


    public AzanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_azan, container, false);
        ButterKnife.bind(this, view);
        //to Check before change Language
        language_name = Locale.getDefault().getLanguage();
        if (!language_name.equals("ar")) {
            HelperClass.change_language("ar", getActivity());
        }
//        if (convertDate().equals("23-03-2020")){
//            Toast.makeText(context, "Same", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(context, "Same:  " +convertDate(), Toast.LENGTH_SHORT).show();
//
//        }
        //For get data from database Room
        //----------------------------------------------------------------------------------
        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        // For get Date today
        timingsViewModel.getTimingsByDataToday(convertDate()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(date_today -> {
                    data_today = date_today;
                  //  Toast.makeText(getActivity(), "date today is " + date_today, Toast.LENGTH_SHORT).show();
                }, e -> {
                    Toast.makeText(getActivity(), "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });
        //For get all data
        timingsViewModel.getAllTimingsRxjava().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(all_Data -> {
                    //conusme Timings prayer here which is a list of Timings
                    if (all_Data.size() <= 0) {
                        //The data is null
                        progressBar.setVisibility(View.GONE);
                        TVShowError.setVisibility(View.VISIBLE);
                        TVShowError.setText(getString(R.string.no_data));
                        Log.i("TAG", "The data is null : " + all_Data.size());
                    } else {
                        progressBar.setVisibility(View.GONE);
                        TVShowError.setVisibility(View.GONE);
                        AdapterAzanVP adapterAzan = new AdapterAzanVP(getActivity());
                        adapterAzan.setAzanList(all_Data);
                        AzanFragmentVP.setAdapter(adapterAzan);
                        AzanFragmentVP.setCurrentItem(data_today);
                        Log.i("TAG", "all data " + all_Data.size());
                    }
                }, e -> {
                    Log.i("TAG", "Error RXJava" + e.getMessage());
                });

//        timingsViewModel.getAllTimings().observe(getActivity(), new Observer<List<Timings>>() {
//            @Override
//            public void onChanged(List<Timings> timings) {
//                progressBar.setVisibility(View.GONE);
//             //   recyclerView.setVisibility(View.VISIBLE);
//                TVShowError.setVisibility(View.GONE);
//                AdapterAzanVP adapterAzan = new AdapterAzanVP(getActivity());
//                adapterAzan.setAzanList(timings);
//
//                //   adapterAzan.setAzanList(azan.getData());
//                AzanFragmentVP.setAdapter(adapterAzan);
//            }
//        });
        //----------------------------------------------------------------------------------
        apiServices = getRetrofit().create(APIServices.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //recyclerView.setLayoutManager(linearLayoutManager);
        Log.i("TAG", "onCreateView");

//        Toast.makeText(getActivity(), " time :" + compareTwoTimes("12:12 pm")
//    + "\n" + compareTwoTimess("12:12 pm"), Toast.LENGTH_SHORT).show();
        //If service google finds or don't need to update


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
        if (isStoragePermissionGranted()) {
            getLocation();
        }
        //  getLocation();
        // turnGPSOn();

        //   Toast.makeText(getActivity(), "no ", Toast.LENGTH_SHORT).show();
        //Can't get location.
        //GPS or network is not enabled.
        // Ask user to enable GPS / network in settings.

        //   gpsTracker.getLocationTest();

        // }
        //If permission true do that

        // isStoragePermissionGranted();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", "onActivityResult");
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                save_request_code = requestCode;
                //getting GPS status
                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (isGPSEnabled) {
                    Log.i("TAG", "true");
                  //  if (location != null ){
                        getPrayerTimes(getLatitude(),getLongitude());

                    //}

                } else {
                    Log.i("TAG", "false");
                    TVShowError.setVisibility(View.VISIBLE);
                    TVShowError.setText(getActivity().getString(R.string.not_allow));
                    progressBar.setVisibility(View.GONE);
                    //  recyclerView.setVisibility(View.GONE);

                }
                break;
            case AppConstants.GPS_REQUEST:
                Log.i("TAG", "Return from GPS_REQUEST");
                switch (resultCode) {

                    case Activity.RESULT_OK:
                        Log.i("TAG", "RESULT_OK");
                        break;
                    case Activity.RESULT_CANCELED:
                        save_request_code = requestCode;

                        // The user was asked to change settings, but chose not to
                        Log.i("TAG", "RESULT_CANCELED");
                        return;
                    //   Toast.makeText(getActivity().getBaseContext(), getString(R.string.grand_permission), Toast.LENGTH_SHORT).show();
                    //SnackbarForGPS(getString(R.string.grand_permission),getString(R.string.allow));
                    default:
                        break;

                }
                break;
            default:
                Log.i("TAG", "result no");
        }
    }

    private void getPrayerTimes(double latitude,double longitude) {
        //24.788626, 46.777509
        Call<Azan> azanCall = apiServices.getPrayerTimes("46.777509", "24.788626", false);
        azanCall.enqueue(new Callback<Azan>() {
            @Override
            public void onResponse(Call<Azan> call, Response<Azan> response) {
                Azan azan = response.body();
                try {
                    if (azan.getStatus().equals("OK")) {
                        //      Delete_timings();
//                        for (int i = 0; i < azan.getData().size(); i++) {
//                            Timings timingsOne = new Timings();
//                            timingsOne.setFajr(azan.getData().get(i).getTimings().getFajr());
//                            timingsOne.setSunrise(azan.getData().get(i).getTimings().getSunrise());
//                            timingsOne.setDhuhr(azan.getData().get(i).getTimings().getDhuhr());
//                            timingsOne.setAsr(azan.getData().get(i).getTimings().getAsr());
//                            timingsOne.setMaghrib(azan.getData().get(i).getTimings().getMaghrib());
//                            timingsOne.setIsha(azan.getData().get(i).getTimings().getIsha());
//                            timingsOne.setDate_today(azan.getData().get(i).getDate().getGregorian().getDate());
//                            timingsOne.setId_seq(i);
//                            timingsOne.setCity(city_name);
//
//                            Save_timings(timingsOne); }
                    } else {
                        Toast.makeText(getActivity(), "NO", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        //recyclerView.setVisibility(View.GONE);
                        TVShowError.setVisibility(View.VISIBLE);
                        TVShowError.setText(getActivity().getString(R.string.cant));
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Azan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private boolean
    isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // ContextCompat.checkSelfPermission()
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.i("TAG", " Grnted fisrt");
                //    isStoragePermissionGranted();

                // getPrayerTimes();
                return true;
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_STORAGE);
                Log.i("TAG", " not Grnted first ");
                return false;

            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    private void Snackbar(String title, String text_button) {
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

    private void SnackbarPermissionSorage(String title, String text_button) {
        Snackbar snackbar = Snackbar.make(getView(), title, Snackbar.LENGTH_LONG)
                .setAction(text_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isGPSEnabled && !isGPSTrackingEnabled) {
                            getLocation();

                        }
                    }

                });
        snackbar.setActionTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        snackbar.show();
    }

    private void SnackbarForGPS(String title, String text_button) {
        Snackbar snackbar = Snackbar.make(getView(), title, Snackbar.LENGTH_LONG)
                .setAction(text_button, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSettingsAlert();
                    }

                });
        snackbar.setActionTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        snackbar.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_WRITE_STORAGE: {
                //If request is canceled , the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted
                    Log.i("TAG", " Grnted second");
                    //     47
                    //  getPrayerTimes();
                    if (!isGPSEnabled && !isGPSTrackingEnabled) {
                        getLocation();

                    }
                } else {
                    // Permission denied
                    Snackbar(getString(R.string.grand_permission), getString(R.string.allow));
                    Log.i("TAG", " not Grnted second");

                }
                return;
            }
            case MY_PERIMISSIONS_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //check if GPS enabled or not
                    if (!isGPSEnabled) {
                        if (save_request_code == 1001) {
                            Log.i("TAG", "save_request_code" + save_request_code);
                            showSettingsAlert();
                        } else {
                            turnGPSOn();

                        }
                        //  showSettingsAlert();
                    }

//                    locationManager.requestLocationUpdates(
//                            provider_info,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
//                            this
//                    );
                    Log.i("TAG", "Graunted Location");
                    if (save_request_code == 1001) {
                        Log.i("TAG", "save_request_code" + save_request_code);
                        showSettingsAlert();
                    } else {
                        turnGPSOn();

                    }                    //     openSettings();
                } else {
                    Log.i("TAG", "Not Graunted Location");
                    SnackbarPermissionSorage(getString(R.string.grand_permission), getString(R.string.allow));
                }
            }
            return;
//            case AppConstants.GPS_REQUEST:
//                Log.i("TAG", "Return from GPS_REQUEST");
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    isGPSEnabled = true;
//                    Log.i("TAG", "On GPS");
//                } else {
//                    Log.i("TAG", "On");
//
//                }
//                break;
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void showSettingsAlert() {
        if (getActivity() != null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            //Setting Dialog Title
            alertDialog.setTitle(R.string.settings_gps);
            alertDialog.setCancelable(false);

            //Setting Dialog Message
            alertDialog.setMessage(R.string.is_open_gps);
            //On Pressing Setting button
            alertDialog.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, LOCATION_PERMISSION_REQUEST_CODE);
                    } catch (Exception e) {
                        Log.i("TAG", "Activity e is :" + e.getMessage());

                    }
                    // mContext.startActivity(intent);
                }
            });
            //On pressing cancel button
            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    TVShowError.setVisibility(View.VISIBLE);
                    TVShowError.setText(getActivity().getString(R.string.not_allow));

                    progressBar.setVisibility(View.GONE);
                    //      recyclerView.setVisibility(View.GONE);
                }
            });

            alertDialog.show();
        } else {
            Log.i("TAG", "Activity is null....");

        }
    }

    public void getLocation() {
        Log.i("TAG", "Get location");
        try {
            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            Log.i("TAG", "try location");

            //getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            //getting network status
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            // Try to get location if you GPS Service is enabled
            if (isGPSEnabled) {
                this.isGPSTrackingEnabled = true;
                Log.i("TAG", "isGPSEnabled");

                /*
                 * This provider determines location using
                 * satellites. Depending on conditions, this provider may take a while to return
                 * a location fix.
                 */

                provider_info = LocationManager.GPS_PROVIDER;

            } else if (isNetworkEnabled) { // Try to get location if you Network Service is enabled
                this.isGPSTrackingEnabled = true;
                Log.i("TAG", "isNetworkEnabled");

                /*
                 * This provider determines location based on
                 * availability of cell tower and WiFi access points. Results are retrieved
                 * by means of a network lookup.
                 */
                provider_info = LocationManager.NETWORK_PROVIDER;

            } else {
                provider_info = null;
                Log.i("TAG", "provider_info is null");

                //showSettingsAlert();
            }

            Log.i("TAG", "Go away ");

            // Application can use GPS or Network Provider
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.i("TAG", "enter check ");
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", "not Permission");
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERIMISSIONS_LOCATION);
                    isGPSPermission = false;
                } else if (provider_info != null) {
                    Log.i("TAG", "provider_info");

                    locationManager.requestLocationUpdates(
                            provider_info,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
                            this
                    );
                    //      openSettings();
                    // showSettingsAlert();

                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(provider_info);
                        updateGPSCoordinates();
                      city_name =  getCityName(getLatitude(),getLongitude());
                        Log.i("TAG", ":location is :" + getLatitude() + " \n" + getLongitude());
                        Log.i("TAG", ":City name is :" + city_name);

//                        Log.i("TAG", "locationManager");

                        //    updateGPSCoordinates();
                        //  openSettings();

                  //      if (location != null ){
                            getPrayerTimes(getLatitude(),getLongitude());

                    //    }
                    } else {
                        Log.i("TAG", "locationManager is null");

                    }
                } else {
                    if (save_request_code == 1001) {
                        Log.i("TAG", "save_request_code" + save_request_code);
                        showSettingsAlert();
                    } else {
                        turnGPSOn();

                    }
                }
            }
        } catch (NullPointerException e) {
            //e.printStackTrace();
            Log.e(TAG, "Impossible to connect to LocationManager", e);
        }
    }

    public void updateGPSCoordinates() {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        return latitude;
    }

    /**
     * GPSTracker longitude getter and setter
     *
     * @return
     */
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        return longitude;
    }

    // method for turn on GPS
    public void turnGPSOn() {
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        mSettingsClient = LocationServices.getSettingsClient(getActivity());
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationRequest.setFastestInterval(2 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        mLocationSettingsRequest = builder.build();
//**************************
        builder.setAlwaysShow(true); //this is the key ingredient
//**************************
//        builder.setAlwaysShow(true); //this is the key ingredient
        builder.setNeedBle(true);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            isGPSEnabled = true;
            Log.i("TAG", "onSuccess.");
           // if (location != null && latitude <0 && longitude <0){
                getPrayerTimes(getLatitude(),getLongitude());

    //        }
    } else {
            mSettingsClient
                    .checkLocationSettings(mLocationSettingsRequest)
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                            //  GPS is already enable, callback GPS status through listener
                            Log.i("TAG", "onSuccess.....");
                        }
                    })
                    .addOnFailureListener(getActivity(), new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("TAG", "onFailure");
                            int statusCode = ((ApiException) e).getStatusCode();
                            switch (statusCode) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    Log.i("TAG", "RESOLUTION_REQUIRED.");

                                    try {
                                        // Show the dialog by calling startResolutionForResult(), and check the
                                        // result in onActivityResult().
                                        ResolvableApiException rae = (ResolvableApiException) e;
                                        Log.i("TAG", "not send.");
                                        rae.startResolutionForResult(getActivity(), AppConstants.GPS_REQUEST);
                                        Log.i("TAG", "Error." + rae.getMessage() + "\n" +
                                                rae.getStatusCode());
                                    } catch (IntentSender.SendIntentException sie) {
                                        Log.i("TAG", "PendingIntent unable to execute request.");
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                                    String errorMessage = "Location settings are inadequate, and cannot be " +
                                            "fixed here. Fix in Settings.";
                                    Log.e("TAG", errorMessage);
                                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void Save_timings(Timings timings_prayer) {
        class SaveTimings extends AsyncTask<Timings, Void, Void> {
            @Override
            protected Void doInBackground(Timings... timings) {
                TimingsAppDatabase.getInstance(getActivity()).timingsDao().insertTimings(timings_prayer);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity(), "Saved all data", Toast.LENGTH_SHORT).show();
            }
        }

        SaveTimings saveTimings = new SaveTimings();
        saveTimings.execute();
    }

    private void Delete_timings() {
        class Delete_timings extends AsyncTask<Timings, Void, Void> {

            @Override
            protected Void doInBackground(Timings... timings) {
                TimingsAppDatabase.getInstance(getActivity()).timingsDao().deleteAllTimings();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getActivity(), "Delete all data", Toast.LENGTH_SHORT).show();
            }
        }

        Delete_timings delete_timings = new Delete_timings();
        delete_timings.execute();
    }
    public String getCityName(double latitude, double longitude) {
        String cityName = "";
        if (location != null) {
            //For change language to Arabic
            Locale locale = new Locale("ar");
            //For change language to English
           // Geocoder geocoder = new Geocoder(getActivity(), Locale.ENGLISH);
            Geocoder geocoder = new Geocoder(getActivity(), locale);

            try {
                /**
                 * Geocoder.getFromLocation - Returns an array of Addresses
                 * that are known to describe the area immediately surrounding the given latitude and longitude.
                 */
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, this.geocoderMaxResults);
                if (addresses.size()> 0){
                    for (Address adr : addresses){
                        if (adr.getLocality() != null && adr.getLocality().length()>0){
                            cityName = adr.getLocality();
                            break;
                        }
                    }
                }

                return cityName;
            } catch (IOException e) {
                //e.printStackTrace();


            }
        }

        return null;
    }

}

