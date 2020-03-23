package com.MohamedTaha.Imagine.New.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

import java.util.List;

import io.reactivex.Flowable;
@Dao
public interface TimingsDao {
    @Query("SELECT * FROM prayer_time")
    LiveData<List<Timings>>getAllTimings();

    @Query("SELECT * FROM prayer_time")
    Flowable<List<Timings>> getAllTimingsRxjave();


    @Query("SELECT id_prayer_time FROM prayer_time WHERE date_today = :date_today")
    Flowable<Integer> getTimingsByDataToday(String date_today);

//    @Query("SELECT date_today FROM prayer_time WHERE date_today = :date_today")
//    Flowable<String> getTimingsByDataToday(String date_today);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTimings(Timings timings);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTimings(Timings timings);

    @Query("DELETE FROM  prayer_time")
    void deleteAllTimings();

}