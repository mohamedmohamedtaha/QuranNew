package com.MohamedTaha.Imagine.New.rest;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Datum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("calendar")
    Call<Azan> getPrayerTimes(@Query("latitude") double latitude, @Query("longitude") double longitude, @Query("annual") boolean annual);


}
