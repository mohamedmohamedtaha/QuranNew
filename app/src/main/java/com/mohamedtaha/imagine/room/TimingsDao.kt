package com.mohamedtaha.imagine.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mohamedtaha.imagine.mvp.model.azan.Timings
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.concurrent.Flow

@Dao
interface TimingsDao {
    @get:Query("SELECT * FROM prayer_time")
    val allTimings: LiveData<List<Timings>>

    @get:Query("SELECT * FROM prayer_time")
    val allTimingsRxjave: kotlinx.coroutines.flow.Flow<List<Timings>>

    @Query("SELECT id_seq FROM prayer_time WHERE date_today = :date_today")
    fun getTimingsByDataToday(date_today: String): kotlinx.coroutines.flow.Flow<Int>

    @Query("SELECT id_seq FROM prayer_time WHERE date_today = :date_today")
    fun checkIsDateTodayFind(date_today: String?): kotlinx.coroutines.flow.Flow<Int>

    @Query("SELECT * FROM prayer_time WHERE date_today = :date_today")
    fun getPrayerTimesForCurrentDate(date_today: String): kotlinx.coroutines.flow.Flow<Timings>

    @get:Query("SELECT city FROM prayer_time LIMIT 1")
    val cityName: kotlinx.coroutines.flow.Flow<String>

    //    @Query("SELECT date_today FROM prayer_time WHERE date_today = :date_today")
    //    Flowable<String> getTimingsByDataToday(String date_today);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTimings(vararg timings: Timings)

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //    Single<List<Timings>> insertTimings(Timings timings);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTimings(timings: Timings)

    @Query("DELETE FROM  prayer_time")
    fun deleteAllTimings() //    @Query("DELETE FROM  prayer_time")
    //   Single<List<Timings>> deleteAllTimings();
}