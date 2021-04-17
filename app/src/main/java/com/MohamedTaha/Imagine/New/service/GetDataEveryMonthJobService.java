package com.MohamedTaha.Imagine.New.service;

import android.Manifest;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.MohamedTaha.Imagine.New.DatabaseCallbackEveryMonth;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.getCity.GetCity;
import com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryMonth;
import com.MohamedTaha.Imagine.New.receiver.bootDevice.PrayerTimeEveryMonthAlarmBootRecevier;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.room.TimingsAppDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection.isInternet;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClient.getRetrofit;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClientCity.getRetrofitForCity;
import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.CHECKISDATAORNOTINDATABASE;

public class GetDataEveryMonthJobService extends JobService  implements DatabaseCallbackEveryMonth {
    public static final String EXTRA_DATA_SEND = "com.MohamedTaha.Imagine.New.service.DATA_SEND";
   // private Context context;
    APIServices apiServicesForCity;
    String city_name = null;
    int geocoderMaxResults = 1;
    APIServices apiServices;
    private String repear;
    private SharedPreferences sharedPreferences;
    public GetDataEveryMonthJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        apiServicesForCity = getRetrofitForCity().create(APIServices.class);
        apiServices = getRetrofit().create(APIServices.class);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        repear = sharedPreferences.getString(getString(R.string.settings_method_key),
                getString(R.string.settings_method_default));
            int isData = params.getExtras().getInt(CHECKISDATAORNOTINDATABASE,-1);
            if (isData <= 0) {
                //isNetworkConnected();
            //    if (isInternet()){
                TimingsAppDatabase.getInstance(GetDataEveryMonthJobService.this).DeletePrayerTimes(GetDataEveryMonthJobService.this);
                jobFinished(params,true);
                    Log.i("TAG", "GetDataEveryMonthJobService jobFinished DeletePrayerTimes");
               // }
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        isInternet = false;
        Log.i("TAG", "GetDataEveryMonthJobService stop DeletePrayerTimes");

        return true;
    }

    @Override
    public void onPrayerTimesError() {
    }

    @Override
    public void onPrayerTimesDeleted() {
        if (isStoragePermissionGranted()) {
            getCity();
        }
    }
    private void isNetworkConnected() {
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
                        Log.i("TAG", "GetDataEveryMonthJobService start DeletePrayerTimes");
                        TimingsAppDatabase.getInstance(GetDataEveryMonthJobService.this).DeletePrayerTimes(GetDataEveryMonthJobService.this);
                    }
                }
            }
        }, 1000);
    }
    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(GetDataEveryMonthJobService.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void getCity() {
        Call<GetCity> getCityCall = apiServicesForCity.getCity();
        getCityCall.enqueue(new Callback<GetCity>() {
            @Override
            public void onResponse(Call<GetCity> call, Response<GetCity> response) {
                GetCity city = response.body();
                try {
                    if (city.getStatus().equals("success")) {
                        Log.d("TAG", city.getCity() + " : " + city.getCountry());
                        city_name = getCityNameWithoutLocation(city.getLat(), city.getLon());
                        Log.i("TAG", " getPrayerTimesByCity Every Month" );
                        getPrayerTimesByCity(GetDataEveryMonthJobService.this, city.getCity(), city.getCountry(), Integer.valueOf(repear), city_name);

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
                        Log.d("TAG", "TimingsAppDatabase DeletePrayerTimes AddPrayerTimesEveryMonth ");
                        TimingsAppDatabase.getInstance(context).AddPrayerTimesEveryMonth(GetDataEveryMonthJobService.this, azan, city_name);
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
                        break;
                    }
                }
            }
            return cityName;
        } catch (IOException e) {
        }
        return null;
    }
    //Enable boot receiver to persist alarms set for notification across device reboots
    public static void enableBootReceiverEveryMonth(Context context) {
        ComponentName receiver = new ComponentName(context, PrayerTimeEveryMonthAlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
