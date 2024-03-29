package com.mohamedtaha.imagine.rest;

import com.mohamedtaha.imagine.mvp.model.azan.Azan;
import com.mohamedtaha.imagine.mvp.model.getCity.GetCity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("calendar")
    Call<Azan> getPrayerTimes(@Query("latitude") double latitude,
                              @Query("longitude") double longitude,
                              @Query("annual") boolean annual, @Query("method") int method);

    @GET("calendarByCity")
    Call<Azan> getPrayerTimesByCity(@Query("city") String city,
                                    @Query("country") String country, @Query("annual") boolean annual,
                                    @Query("method") int method);

    @GET("json")
    Call<GetCity> getCity();
}
