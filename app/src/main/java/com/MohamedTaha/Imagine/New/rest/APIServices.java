package com.MohamedTaha.Imagine.New.rest;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.getCity.GetCity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("calendar")
    Call<Azan> getPrayerTimes(@Query("latitude") double latitude,
                              @Query("longitude") double longitude,
                              @Query("annual") boolean annual, @Query("method") float  method);

    @GET("calendarByCity")
    Call<Azan> getPrayerTimesByCity(@Query("city") String city,
                                    @Query("country") String country,
                                    @Query("method") float  method);

    @GET("json")
    Call<GetCity> getCity();
}
