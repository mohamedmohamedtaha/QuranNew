package com.MohamedTaha.Imagine.New;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.ApiException;
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

import static com.MohamedTaha.Imagine.New.AzanFragment.LOCATION_PERMISSION_REQUEST_CODE;

public class GpsUtils extends Activity {

   // private Context context;
    private View view;
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationManager locationManager;
    private LocationRequest locationRequest;
    private  static int count_cancled = 0;
    private Activity activity;

    public GpsUtils(Activity activity) {
        //this.context = context;
        this.activity = activity;
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        mSettingsClient = LocationServices.getSettingsClient(activity);
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
    }

    // method for turn on GPS
    public void turnGPSOn(onGpsListener onGpsListener) {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (onGpsListener != null) {
                onGpsListener.gpsStatus(true);
                Log.i("TAG", "onSuccess.");

            }
        } else {
            mSettingsClient
                    .checkLocationSettings(mLocationSettingsRequest)
                    .addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                     //  GPS is already enable, callback GPS status through listener
                            Log.i("TAG", "onSuccess.....");

                            if (onGpsListener != null) {
                                onGpsListener.gpsStatus(true);
                            }
                        }
                    })
                    .addOnFailureListener(activity, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("TAG", "onFailure");
                            if (count_cancled == 1){
                                Log.i("TAG", "Cancledd to count 1");
                               // AzanFragment azanFragment =  new AzanFragment();
                              //  azanFragment.showSettingsAlert();
                                showSettingsAlert();
                             //   Toast.makeText((Activity) context, "Cancledd to count 1", Toast.LENGTH_LONG).show();

//                                SnackbarForGPS(context.getString(R.string.grand_permission),context.getString(R.string.allow),
//                                     getCurrentFocus());
                                return;
                            }

                            int statusCode = ((ApiException) e).getStatusCode();
                            switch (statusCode) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    Log.i("TAG", "RESOLUTION_REQUIRED.");

                                    try {
                                        // Show the dialog by calling startResolutionForResult(), and check the
                                        // result in onActivityResult().
                                        ResolvableApiException rae = (ResolvableApiException) e;
                                        Log.i("TAG", "not send.");
                                        rae.startResolutionForResult(activity, AppConstants.GPS_REQUEST);
                                        count_cancled ++;
                                    } catch (IntentSender.SendIntentException sie) {
                                        Log.i("TAG", "PendingIntent unable to execute request.");
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                                    String errorMessage = "Location settings are inadequate, and cannot be " +
                                            "fixed here. Fix in Settings.";
                                    Log.e("TAG", errorMessage);
                                    Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("TAG", "onActivityResult");

        if (requestCode == AppConstants.GPS_REQUEST) {
            Log.i("TAG", "Return from GPS_REQUEST");

            if (resultCode == Activity.RESULT_OK) {
                //  isGPSEnabled = true;
                Log.i("TAG", "On GPS");
            } else {
                Log.i("TAG", "On");

            }
        } else {
            Log.i("TAG", "result no");

        }
    }
    public interface onGpsListener {
        void gpsStatus(boolean isGPSEnable);
    }
    public void showSettingsAlert () {
        if (activity.getCallingActivity() != null){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        //Setting Dialog Title
        alertDialog.setTitle(R.string.settings_gps);
        alertDialog.setCancelable(false);

        //Setting Dialog Message
        alertDialog.setMessage(R.string.is_open_gps);
        //On Pressing Setting button
        alertDialog.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivityForResult(intent, LOCATION_PERMISSION_REQUEST_CODE);
                //context.startActivity(intent);
            }
        });
        //On pressing cancel button
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }else {
            Log.i("TAG", "Activity 2 is null....");

        }
    }

}
