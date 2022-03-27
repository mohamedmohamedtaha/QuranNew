package com.mohamedtaha.imagine.room

import com.mohamedtaha.imagine.mvp.model.azan.Timings
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TimingsImpl @Inject constructor(val timingsDao: TimingsDao) : TimingsRepository {
    override fun getTimingsByDataToday(date_today: String): Flow<Int> {
        return timingsDao.getTimingsByDataToday(date_today)
    }

    override fun checkIsDateTodayFind(date_today: String): Flow<Int> {
        return timingsDao.checkIsDateTodayFind(date_today)
    }

    override fun getPrayerTimesForCurrentDate(date_today: String): Flow<Timings> {
        return timingsDao.getPrayerTimesForCurrentDate(date_today)
    }

    override fun getCityName(): Flow<String> {
        return timingsDao.cityName
    }

    override fun insert(timings: Timings) {
        AppExecutors.instance!!.diskIO().execute { timingsDao.insertTimings(timings)
        }
}

    override fun update(timings: Timings) {
        AppExecutors.instance!!.diskIO().execute { timingsDao.updateTimings(timings) }

    }

    override fun delete() {
        AppExecutors.instance!!.diskIO().execute { timingsDao.deleteAllTimings()
    }
}}