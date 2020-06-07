package com.MohamedTaha.Imagine.New;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.MohamedTaha.Imagine.New.helper.checkConnection.NetworkConnection;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.getCity.GetCity;
import com.MohamedTaha.Imagine.New.rest.APIServices;
import com.MohamedTaha.Imagine.New.room.DatabaseCallback;
import com.MohamedTaha.Imagine.New.room.TimingsAppDatabase;
import com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.MohamedTaha.Imagine.New.helper.checkConnection.NoInternetConnection.isInternet;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClient.getRetrofit;
import static com.MohamedTaha.Imagine.New.rest.RetrofitClientCity.getRetrofitForCity;
import static com.MohamedTaha.Imagine.New.service.MediaPlayerService.BROADCAST_NOT_CONNECTION;
import static com.MohamedTaha.Imagine.New.service.MediaPlayerService.BROADCAST_NOT_INTERNET;
import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.store_city_name;
import static com.MohamedTaha.Imagine.New.ui.fragments.AzanFragment.MY_PERMISSIONS_WRITE_STORAGE;

public class AddDataInDatabase extends Activity implements  DatabaseCallback {
    private Context context;
    private Activity activity;
    private APIServices apiServicesForCity;
    private APIServices apiServices;
    private InteractiveAndDisactiveUser interactiveAndDisactiveUser;
    private String city_name = null;
    private SharedPreferences sharedPreferences;
    private String repear;

    public AddDataInDatabase(Context context,Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    private void isNetworkConnected() {
        interactiveAndDisactiveUser.disInteractiveUSerWithUI();
        NoInternetConnection noInternetConnection = new NoInternetConnection();
        noInternetConnection.execute("http://clients3.google.com/generate_204");
        boolean isConnected = NetworkConnection.networkConnectivity(context);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isConnected) {
                    Intent broadcastIntent = new Intent(BROADCAST_NOT_CONNECTION);
                    context.sendBroadcast(broadcastIntent);
                    Log.d("TAG", "the internet is not connected");
                    interactiveAndDisactiveUser.interactiveUSerWithUI();
                } else {
                    if (!isInternet()) {
                        //send BroadcastReceiver to the Service -> Not Internet
                        Intent broadcastIntent = new Intent(BROADCAST_NOT_INTERNET);
                        context.sendBroadcast(broadcastIntent);
                        interactiveAndDisactiveUser.interactiveUSerWithUI();
                    } else {
//                        if (isRefresh) {
//                            checkBeforeGetDataFromInternetTest();
//                        } else {
//                            Log.d("TAG", "TimingsAppDatabase DeletePrayerTimes second");
//
//                            new Thread(new Runnable() {
//                                public void run() {
//                                    // a potentially time consuming task
//
//                                    TimingsAppDatabase.getInstance(context).DeletePrayerTimes(AddDataInDatabase.t);
//                                    Log.d("TAG", "City name is from store_city_name" + store_city_name);
//                                }
//                            }).start();
//
//
//                        }
                    }
                }
            }
        }, 1000);
    }

    @Override
    public void onPrayerTimesAdded() {

    }

    @Override
    public void onPrayerTimesDeleted() {
        checkBeforeGetData();

    }

    @Override
    public void getDataFromLocationAfterDeleteData() {

    }

    @Override
    public void onPrayerTimesError() {

    }
    private void checkBeforeGetData() {
        if (isStoragePermissionGranted()) {
            getCity();
        }
    }
    private boolean isStoragePermissionGranted() {
        Log.i("TAG", " isStoragePermissionGranted");
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", " Granted fisrt");
                    interactiveAndDisactiveUser.disInteractiveUSerWithUI();
                    return true;
                } else {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_STORAGE);
                    Log.i("TAG", " not Granted first ");
                    interactiveAndDisactiveUser.interactiveUSerWithUI();
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
    private void getCity() {
        interactiveAndDisactiveUser.disInteractiveUSerWithUI();
        Log.d("TAG", "getPrayerTimesByCity");
        apiServicesForCity = getRetrofitForCity().create(APIServices.class);
        Call<GetCity> getCityCall = apiServicesForCity.getCity();
        getCityCall.enqueue(new Callback<GetCity>() {
            @Override
            public void onResponse(Call<GetCity> call, Response<GetCity> response) {
                GetCity city = response.body();
                try {
                    if (city.getStatus().equals("success")) {
                        Log.d("TAG", city.getCity() + " : " + city.getCountry());
                        city_name = getCityNameTest(city.getLat(), city.getLon());

                        Log.d("TAG", "City name in arabic is : " + city_name);
//                        if (!isValueForPrayerTimesChanged) {
//                            getMethodPreferences(city.getCountry());
//                        }
                        if (city_name != null) {
                            getPrayerTimesByCity(city.getCity(), city.getCountry(), Integer.valueOf(repear), city_name);
                        } else {
                            getPrayerTimesByCity(city.getCity(), city.getCountry(), Integer.valueOf(repear), city.getCity());

                        }
                    } else {
                        interactiveAndDisactiveUser.interactiveUSerWithUI();
                        Log.d("TAG", "checkBeforeGetDataFromInternetTest ");
//                        if (checkGPS()) {
//                            if (bundle == null) {
//                                turnGPS();
//                            }
  //                      }
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<GetCity> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", " onFailure " + t.getMessage());
                //checkBeforeGetDataFromInternetTest();
            }
        });
    }
    private String getCityNameTest(double latitude, double longitude) {
        String cityName = null;
        //For change language to English
        Locale locale = new Locale("ar");
        Geocoder geocoder = new Geocoder(context, locale);
        Log.d("TAG", " geocoder : " + geocoder);

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Log.d("TAG", " addresses : " + addresses);

            if (addresses != null && addresses.size() > 0) {
                for (Address adr : addresses) {
                    Log.d("TAG", " adr.getSubAdminArea() : " + adr.getSubAdminArea() + " : " + adr.getAdminArea());

                    if (adr.getLocality() != null && adr.getLocality().length() > 0) {
                        cityName = adr.getLocality();
                        Log.d("TAG", " cityName getCityNameTest : " + cityName);
                        break;
                    } else {
                        cityName = adr.getSubAdminArea();
                    }
                }
            }
            return cityName;
        } catch (IOException e) {
            Log.d("TAG", " E : " + e.getMessage());
        }
        return null;
    }

    private void getPrayerTimesByCity(String city, String country, int method, String city_name) {
        apiServices = getRetrofit().create(APIServices.class);
        Call<Azan> azanCall = apiServices.getPrayerTimesByCity(city, country, false, method);
        azanCall.enqueue(new Callback<Azan>() {
            @Override
            public void onResponse(Call<Azan> call, Response<Azan> response) {
                Azan azan = response.body();
                try {
                    if (azan.getStatus().equals("OK")) {
                        TimingsAppDatabase.getInstance(context).AddPrayerTimesForMonth(AddDataInDatabase.this, azan, city_name);
                  //      fragmentAzanBinding.TVShowError.setVisibility(View.GONE);
                    } else {
                    //    fragmentAzanBinding.TVShowError.setVisibility(View.VISIBLE);
                      //  fragmentAzanBinding.TVShowError.setText(context.getString(R.string.cant));
                        interactiveAndDisactiveUser.interactiveUSerWithUI();
                    }
                } catch (Exception e) {
                    Log.i("TAG", " Error " + e.getMessage());
                        interactiveAndDisactiveUser.interactiveUSerWithUI();
                    }
            }

            @Override
            public void onFailure(Call<Azan> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", " onFailure " + t.getMessage());
                    interactiveAndDisactiveUser.interactiveUSerWithUI();
            }
        });
    }
    private void changeValueInListPreference() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.settings_method_key), repear);
        editor.commit();
    }
}


