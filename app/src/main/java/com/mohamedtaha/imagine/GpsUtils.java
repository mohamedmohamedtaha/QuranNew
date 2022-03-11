package com.mohamedtaha.imagine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class GpsUtils {
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationManager locationManager;
    private LocationRequest locationRequest;
    private static int count_cancled = 0;
    private Activity activity;
    private LocationCallback locationCallback;
    FusedLocationProviderClient fusedLocationProviderClient;
    // The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10 * 1000; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute

    public GpsUtils(Activity activity) {
        //this.context = context;
        this.activity = activity;
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        mSettingsClient = LocationServices.getSettingsClient(activity);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(MIN_DISTANCE_CHANGE_FOR_UPDATES);
        locationRequest.setFastestInterval(2 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        mLocationSettingsRequest = builder.build();
        builder.setAlwaysShow(true); //this is the key ingredient
    }

    // method for turn on GPS
    public void turnGPSOn(onGpsListener onGpsListener) {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (onGpsListener != null) {
                onGpsListener.gpsStatus(true);
                Log.i("TAG", "GpsUtils onSuccess.");
                //onGpsListener.disInteractiveUSer();
                Log.i("TAG", "GpsUtils getLocationRequest1.");
                getLocationRequest(onGpsListener);
                startLocationUpdates();
            }
        } else {
            mSettingsClient
                    .checkLocationSettings(mLocationSettingsRequest)
                    .addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                            //  GPS is already enable, callback GPS status through listener
                            Log.i("TAG", " GpsUtils onSuccess.....");
                            if (onGpsListener != null) {
                                onGpsListener.gpsStatus(true);
                                //onGpsListener.disInteractiveUSer();
                                getLocationRequest(onGpsListener);

                                startLocationUpdates();
                                Log.i("TAG", "GpsUtils getLocationRequest2.");

                                Log.i("TAG", "GPSUtils gpsStatus true.....");
                            }
                        }
                    })
                    .addOnFailureListener(activity, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("TAG", "GpsUtils onFailure");
                            if (e instanceof ResolvableApiException) {
                                try {
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    Log.i("TAG", "not send.");
                                    rae.startResolutionForResult(activity, AppConstants.GPS_REQUEST);
                                    Log.i("TAG", "Error." + rae.getMessage() + "\n" +
                                            rae.getStatusCode());
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i("TAG", "PendingIntent unable to execute request.");
                                }
                            }
                        }
                    });
        }
    }

    private void getLocationRequest(onGpsListener onGpsListener) {
        Log.d("TAG", "getLocation ");
        mSettingsClient = LocationServices.getSettingsClient(activity);
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
                    onGpsListener.getLocation(location);
                }
            }
        };
    }

    public void startLocationUpdates() {
        Log.d("TAG", " GpsUtils startLocationUpdates ");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (fusedLocationProviderClient != null) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    public void stopLocationUpdtaes() {
        Log.d("TAG", "stopLocationUpdtaes ");
        if (fusedLocationProviderClient != null) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }

    public interface onGpsListener {
        void gpsStatus(boolean isGPSEnable);

        void getLocation(Location location);
    }

//    public void showSettingsAlert() {
//        if (activity.getCallingActivity() != null) {
//            AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
//            alertDialog.setTitle(R.string.settings_gps);
//            alertDialog.setCancelable(false);
//            alertDialog.setMessage(R.string.is_open_gps);
//            alertDialog.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                    activity.startActivityForResult(intent, LOCATION_PERMISSION_REQUEST_CODE);
//                }
//            });
//            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            alertDialog.show();
//        } else {
//            Log.i("TAG", "Activity 2 is null....");
//        }
//    }
}
