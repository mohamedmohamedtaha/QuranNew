package com.MohamedTaha.Imagine.New.ui.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.MohamedTaha.Imagine.New.Adapter.AdapterAzanVP;
import com.MohamedTaha.Imagine.New.AppConstants;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.databinding.FragmentAzanBinding;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Datum;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.room.DatabaseCallback;
import com.MohamedTaha.Imagine.New.room.TimingsAppDatabase;
import com.MohamedTaha.Imagine.New.room.TimingsViewModel;
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

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.LOCATION_SERVICE;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClient.getRetrofit;
import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.data_today;

/**
 * A simple {@link Fragment} subclass.
 */
public class AzanFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener, DatabaseCallback {
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
    Location location;
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
    public static final int MY_PERIMISSIONS_LOCATION = 10;
    private Location lastLocation;
    private static final int MY_PERMISSIONS_WRITE_STORAGE = 90;
    APIServices apiServices;
    List<Datum> datumList = new ArrayList<>();
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    String language_name;
    String city_name = null;
    private CompositeDisposable disposable = new CompositeDisposable();
    private FragmentAzanBinding fragmentAzanBinding;
    private List<Timings> getAllData = new ArrayList<>();

    public AzanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAzanBinding = FragmentAzanBinding.inflate(inflater, container, false);
        View viewBuinding = fragmentAzanBinding.getRoot();
        language_name = Locale.getDefault().getLanguage();
        if (!language_name.equals("ar")) {
            HelperClass.change_language("ar", getActivity());
        }
        apiServices = getRetrofit().create(APIServices.class);
        Log.i("TAG", "onCreateView");
        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);

        Flowable<List<Timings>> flowableGetAllPrayerTimingFromDatabase = timingsViewModel.getAllTimingsRxjava();
        flowableGetAllPrayerTimingFromDatabase.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(all_Data -> {
                    getAllData = all_Data;
                    Log.i("TAG", "Navigation Drawaer : " + data_today);

                    if (data_today > 0) {
                        //conusme Timings prayer here which is a list of Timings
                        if (getAllData == null && getAllData.size() <= 0) {
                            //The data is null
                            fragmentAzanBinding.progressBar.setVisibility(View.GONE);
                            fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                            fragmentAzanBinding.TVShowError.setText(getString(R.string.no_data));
                            fragmentAzanBinding.AzanFragmentVP.setVisibility(View.GONE);
                            Log.i("TAG", "The data is null : " + getAllData.size());
                        } else {
                            fragmentAzanBinding.progressBar.setVisibility(View.GONE);
                            fragmentAzanBinding.TVShowError.setVisibility(View.GONE);
                            fragmentAzanBinding.AzanFragmentVP.setVisibility(View.VISIBLE);
                            AdapterAzanVP adapterAzan = new AdapterAzanVP(getActivity());
                            adapterAzan.setAzanList(getAllData);
                            fragmentAzanBinding.AzanFragmentVP.setAdapter(adapterAzan);
                            fragmentAzanBinding.AzanFragmentVP.setCurrentItem(data_today);
                            Log.i("TAG", "all data " + getAllData.size());
                        }
                    } else {
                        fragmentAzanBinding.progressBar.setVisibility(View.GONE);
                        fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                        fragmentAzanBinding.TVShowError.setText(getString(R.string.no_data));
                        fragmentAzanBinding.AzanFragmentVP.setVisibility(View.GONE);
                        Log.i("TAG", "The data is null : " + getAllData.size());
                    }
                }, e -> {
                    Log.i("TAG", "Error RXJava" + e.getMessage());
                });
        if (data_today <= 0) {
            if (isStoragePermissionGranted()) {
                if (checkGPS()) {
                    turnGPSOn();
                }
            }
            //    getLocation();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TAG", "onActivityResult");
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE:
                save_request_code_back_from_turn_gps = requestCode;
                Log.i("TAG", "requestCode");

                isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (isGPSEnabled) {
                } else {
                    Log.i("TAG", "false");
                    fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                    fragmentAzanBinding.TVShowError.setText(getActivity().getString(R.string.not_allow));
                    fragmentAzanBinding.progressBar.setVisibility(View.GONE);
                }
                break;
            case AppConstants.GPS_REQUEST:
                Log.i("TAG", "Return from GPS_REQUEST");
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
                break;
            default:
                Log.i("TAG", "result no");
        }
    }

    private void getPrayerTimes(double latitude, double longitude) {
        //24.788626, 46.777509
        Call<Azan> azanCall = apiServices.getPrayerTimes(latitude, longitude, false);
        azanCall.enqueue(new Callback<Azan>() {
            @Override
            public void onResponse(Call<Azan> call, Response<Azan> response) {
                Azan azan = response.body();
                try {
                    if (azan.getStatus().equals("OK")) {
                        TimingsAppDatabase.getInstance(getActivity()).AddPrayerTimes(AzanFragment.this, azan, city_name);
                    } else {
                        Toast.makeText(getActivity(), "NO", Toast.LENGTH_SHORT).show();
                        fragmentAzanBinding.progressBar.setVisibility(View.GONE);
                        //recyclerView.setVisibility(View.GONE);
                        fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                        fragmentAzanBinding.TVShowError.setText(getActivity().getString(R.string.cant));
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Azan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                fragmentAzanBinding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.i("TAG", " Granted fisrt");
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
                    checkGPS();
                } else {
                    SnackbarPermissionStorage(getString(R.string.grand_permission), getString(R.string.allow));
                }
                return;
            }
            case MY_PERIMISSIONS_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    turnGPSOn();
                } else {
                    Log.i("TAG", "Not Graunted Location");
                    SnackbarPermissionLocation(getString(R.string.grand_permission), getString(R.string.allow));
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
        Log.i("TAG", "Location Changed is : " + location);
        if (location != null && data_today == 0) {
            city_name = getCityName(location, location.getLatitude(), location.getLongitude());
            Log.i("TAG", "City name : " + city_name);
            getPrayerTimes(location.getLatitude(), location.getLongitude());
        } else {
            Log.i("TAG", "onLocationChanged : " + location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("TAG", "onStatusChanged");

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i("TAG", "onProviderEnabled");

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i("TAG", "onProviderDisabled");
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
                    dialog.dismiss();
                    fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                    fragmentAzanBinding.TVShowError.setText(getActivity().getString(R.string.not_allow));
                    fragmentAzanBinding.progressBar.setVisibility(View.GONE);
                }
            });

            alertDialog.show();
        } else {
            Log.i("TAG", "Activity is null....");

        }
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
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERIMISSIONS_LOCATION);
                return false;
            }
            return true;
        }
        return true;
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            Log.i("TAG", "try location");
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (isGPSEnabled) {
                this.isGPSTrackingEnabled = true;
                Log.i("TAG", "isGPSEnabled");
                provider_info = LocationManager.GPS_PROVIDER;
            } else if (isNetworkEnabled) {
                this.isGPSTrackingEnabled = true;
                Log.i("TAG", "isNetworkEnabled");
                provider_info = LocationManager.NETWORK_PROVIDER;
            } else {
                provider_info = null;
                Log.i("TAG", "provider_info is null");
            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                Log.i("TAG", "enter check ");
//                if (ContextCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && ContextCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    Log.i("TAG", "not Permission");
//                    requestPermissions(new String[]{
//                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERIMISSIONS_LOCATION);
//                    isGPSPermission = false;
//                } else if (provider_info != null) {
//                    Log.i("TAG", "provider_info");
//                    locationManager.requestLocationUpdates(
//                            provider_info,
//                            MIN_TIME_BW_UPDATES,
//                            MIN_DISTANCE_CHANGE_FOR_UPDATES,
//                            this
//                    );
//                    if (locationManager != null) {
//                        location = locationManager.getLastKnownLocation(provider_info);
//                        updateGPSCoordinates();
//                        city_name = getCityName(location, getLatitude(), getLongitude());
//                        Log.i("TAG", ":location is :" + getLatitude() + "" + getLongitude() + ":City name is :" + city_name);
//                        Log.i("TAG", ":City name is :" + city_name);
//                        fragmentAzanBinding.progressBar.setVisibility(View.VISIBLE);
//                    } else {
//                        Log.i("TAG", "locationManager is null");
//                    }
//                } else {
//                    if (save_request_code == 1001) {
//                        Log.i("TAG", "save_request_code" + save_request_code);
//                        showSettingsAlert();
//                    } else {
//                        turnGPSOn();
//                    }
//                }
//            }
                        updateGPSCoordinates();
                        city_name = getCityName(location, getLatitude(), getLongitude());
                        Log.i("TAG", ":location is :" + getLatitude() + "" + getLongitude() + ":City name is :" + city_name);
                        Log.i("TAG", ":City name is :" + city_name);
                        fragmentAzanBinding.progressBar.setVisibility(View.VISIBLE);
                    if (save_request_code_back_from_turn_gps == 1001) {
                        Log.i("TAG", "save_request_code" + save_request_code_back_from_turn_gps);
                        showSettingsAlert();
                    } else {
                        turnGPSOn();
                }

        } catch (NullPointerException e) {
            Log.e("TAG", "Impossible to connect to LocationManager", e);
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

    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        return longitude;
    }

    public void turnGPSOn() {
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        mSettingsClient = LocationServices.getSettingsClient(getActivity());
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationRequest.setFastestInterval(2 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        mLocationSettingsRequest = builder.build();
        builder.setAlwaysShow(true);
        builder.setNeedBle(true);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            isGPSEnabled = true;
            Log.i("TAG", "onSuccess.");
        } else {
            mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                            Log.i("TAG", "onSuccess.....");
                        }
                    }).addOnFailureListener(getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    int statusCode = ((ApiException) e).getStatusCode();
                    switch (statusCode) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.i("TAG", "RESOLUTION_REQUIRED.");
                            try {
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
                            String errorMessage = "Location settings are inadequate, and cannot be " + "fixed here. Fix in Settings.";
                            Log.e("TAG", errorMessage);
                            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                    }
                }
            });
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

    @Override
    public void onPrayerTimesAdded() {
        Toast.makeText(getActivity(), "all data Saved", Toast.LENGTH_SHORT).show();
        if (fragmentAzanBinding.progressBar != null) {
            fragmentAzanBinding.progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPrayerTimesDeleted() {
        Toast.makeText(getActivity(), "all data Deleted ", Toast.LENGTH_SHORT).show();
        if (fragmentAzanBinding.progressBar != null) {
            fragmentAzanBinding.progressBar.setVisibility(View.GONE);
        }
//        if (isStoragePermissionGranted()) {
//            getLocation();
//        }
    }

    @Override
    public void onPreayerTimesError() {
        if (fragmentAzanBinding.progressBar != null) {
            fragmentAzanBinding.progressBar.setVisibility(View.GONE);
        }
    }
}

