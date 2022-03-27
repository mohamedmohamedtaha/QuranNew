package com.mohamedtaha.imagine.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mohamedtaha.imagine.room.TimingsRepository
import androidx.lifecycle.LiveData
import com.mohamedtaha.imagine.mvp.model.azan.Timings
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.mohamedtaha.imagine.room.TimingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TimingsViewModel @Inject constructor(val timingsRepository: TimingsRepository) : ViewModel() {
//    val allTimings: LiveData<List<Timings>>
//    val allTimingsRxjava: Flowable<List<Timings>>
    var isNewlyCreated = true
    fun getTimingsByDataToday(date_today: String): Flow<Int> {
        return timingsRepository.getTimingsByDataToday(date_today)
    }

    fun checkIsDateTodayFind(date_today: String): Flow<Int> {
        return timingsRepository.checkIsDateTodayFind(date_today)
    }

    val cityName: Flow<String>
        get() = timingsRepository.getCityName()

    fun insert(timings: Timings) {
        timingsRepository.insert(timings)
    }

    fun update(timings: Timings) {
        timingsRepository.update(timings)
    }

    fun deleteAll(timings: Timings) {
        timingsRepository.delete()
    }

    fun saveState(outState: Bundle) {
        outState.putInt(SAVE_INSTANCE_DATE_TODAY, store_date_today)
    }

    fun restoreState(outState: Bundle) {
        store_date_today = outState.getInt(SAVE_INSTANCE_DATE_TODAY)
    }

    companion object {
        const val SAVE_INSTANCE_DATE_TODAY = "save_instance_date_today"
        var store_date_today = 0
    }

//    init {
//        timingsRepository = TimingsRepository.getInstance(application)
//        allTimings = timingsRepository.allTimings
//        allTimingsRxjava = timingsRepository.allTimingsRxjava
//    }
}