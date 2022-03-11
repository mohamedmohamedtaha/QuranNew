package com.mohamedtaha.imagine.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mohamedtaha.imagine.mvp.model.azan.Timings;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;


@Dao
public interface TimingsDao {
    @Query("SELECT * FROM prayer_time")
    LiveData<List<Timings>> getAllTimings();

    @Query("SELECT * FROM prayer_time")
    Flowable<List<Timings>> getAllTimingsRxjave();


    @Query("SELECT id_seq FROM prayer_time WHERE date_today = :date_today")
    Flowable<Integer> getTimingsByDataToday(String date_today);

    @Query("SELECT id_seq FROM prayer_time WHERE date_today = :date_today")
    Single<Integer> checkIsDateTodayFind(String date_today);

    @Query("SELECT * FROM prayer_time WHERE date_today = :date_today")
    Flowable<Timings> getPrayerTimesForCurrentDate(String date_today);

    @Query("SELECT city FROM prayer_time LIMIT 1")
    Flowable<String> getCityName();

//    @Query("SELECT date_today FROM prayer_time WHERE date_today = :date_today")
//    Flowable<String> getTimingsByDataToday(String date_today);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTimings(Timings... timings);

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    Single<List<Timings>> insertTimings(Timings timings);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTimings(Timings timings);

    @Query("DELETE FROM  prayer_time")
    void deleteAllTimings();

//    @Query("DELETE FROM  prayer_time")
//   Single<List<Timings>> deleteAllTimings();
}
