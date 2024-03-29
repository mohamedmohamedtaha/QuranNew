package com.mohamedtaha.imagine.receiver;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.mohamedtaha.imagine.DatabaseCallbackEveryMonth;
import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.helper.checkConnection.NetworkConnection;
import com.mohamedtaha.imagine.helper.checkConnection.NoInternetConnection;
import com.mohamedtaha.imagine.receiver.bootDevice.PrayerTimeEveryMonthAlarmBootRecevier;
import com.mohamedtaha.imagine.rest.APIServices;
import com.mohamedtaha.imagine.rest.RetrofitClient;
import com.mohamedtaha.imagine.rest.RetrofitClientCity;
import com.mohamedtaha.imagine.room.TimingsAppDatabase;
import com.mohamedtaha.imagine.mvp.model.azan.Azan;
import com.mohamedtaha.imagine.mvp.model.getCity.GetCity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamedtaha.imagine.helper.checkConnection.NoInternetConnection.isInternet;
import static com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity.CHECKISDATAORNOTINDATABASE;

public class GetPrayerTimesEveryMonth extends BroadcastReceiver implements DatabaseCallbackEveryMonth {
    private Context context;
    APIServices apiServicesForCity;
    String city_name = null;
    int geocoderMaxResults = 1;
    APIServices apiServices;
    private String repear;
    private SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        apiServicesForCity = RetrofitClientCity.getRetrofitForCity().create(APIServices.class);
        apiServices = RetrofitClient.getRetrofit().create(APIServices.class);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        repear = sharedPreferences.getString(context.getString(R.string.settings_method_key),
                context.getString(R.string.settings_method_default));
        if (intent != null){
           int isData = intent.getIntExtra(CHECKISDATAORNOTINDATABASE,-1);
            if (isData <= 0) {
                    isNetworkConnected(context);

            }
        }
    }
    private void isNetworkConnected(Context context) {
        NoInternetConnection noInternetConnection = new NoInternetConnection();
        noInternetConnection.execute("http://clients3.google.com/generate_204");
        boolean isConnected = NetworkConnection.networkConnectivity(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isConnected) {

                } else {
                    if (!NoInternetConnection.isInternet()) {

                    } else {

                        Log.i("TAG", "DeletePrayerTimes");
                        TimingsAppDatabase.getInstance(context).DeletePrayerTimes(GetPrayerTimesEveryMonth.this);
                    }
                }
            }
        }, 1000);
    }

    //Enable boot receiver to persist alarms set for notification across device reboots
    public static void enableBootReceiverEveryMonth(Context context) {
        ComponentName receiver = new ComponentName(context, PrayerTimeEveryMonthAlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
    @Override
    public void onPrayerTimesDeleted() {
        if (isStoragePermissionGranted()) {
            getCity(context);
        }
    }
    @Override
    public void onPrayerTimesError() {

    }

    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    private void getCity(Context context) {
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

                        getPrayerTimesByCity(context, city.getCity(), city.getCountry(), Integer.valueOf(repear), city_name);
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
                        TimingsAppDatabase.getInstance(context).AddPrayerTimesEveryMonth(GetPrayerTimesEveryMonth.this, azan, city_name);
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
        Geocoder geocoder = new Geocoder(context, locale);
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

}