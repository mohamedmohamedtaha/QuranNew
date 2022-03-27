package com.mohamedtaha.imagine.room

import com.mohamedtaha.imagine.mvp.model.azan.Timings
import kotlinx.coroutines.flow.Flow

interface TimingsRepository {
//    var allTimings: LiveData<List<Timings>>
//        private set
//    var allTimingsRxjava: Flowable<List<Timings>>
//        private set
//    private val row: Single<Int>? = null

    //    internal constructor(application: Application?) {
//        val database = TimingsAppDatabase.getInstance(application!!)
//        timingsDao = database!!.timingsDao()
//        allTimings = timingsDao.allTimings
//        allTimingsRxjava = timingsDao.allTimingsRxjave
//    }
//
//    internal constructor(application: Context?) {
//        val database = TimingsAppDatabase.getInstance(application!!)
//        timingsDao = database!!.timingsDao()
//        allTimings = timingsDao.allTimings
//        allTimingsRxjava = timingsDao.allTimingsRxjave
//    }
    fun getTimingsByDataToday(date_today: String): Flow<Int>
    fun checkIsDateTodayFind(date_today: String): Flow<Int>
    fun getPrayerTimesForCurrentDate(date_today: String): Flow<Timings>
    fun getCityName(): Flow<String>
    fun insert(timings: Timings)
    fun update(timings: Timings)
    fun delete()
//    fun getTimingsByDataToday(date_today: String): Flowable<Int> {
//        return timingsDao.getTimingsByDataToday(date_today)
//    }

//    fun checkIsDateTodayFind(date_today: String): Single<Int> {
//        return timingsDao.checkIsDateTodayFind(date_today)
//        //        AppExecutors.getInstance().diskIO().execute(new Runnable() {
////            @Override
////            public void run() {
////                row = timingsDao.checkIsDateTodayFind(date_today);;
////            }
////        });
////        return row;
//    }

//    val cityName: Flowable<String>
//        get() = timingsDao.cityName

//    fun getPrayerTimesForCurrentDate(date_today: String): Flowable<Timings> {
//        return timingsDao.getPrayerTimesForCurrentDate(date_today)
//    }

//    fun insert(timings: Timings) {
//        instance!!.diskIO().execute { timingsDao.insertTimings(timings) }
//    }

//    fun update(timings: Timings) {
//        instance!!.diskIO().execute { timingsDao.updateTimings(timings) }
//    }
//
//    fun deleteAll() {
//        instance!!.diskIO().execute { timingsDao.deleteAllTimings() }
//    }

//    companion object {
//        private var timingsRepository: TimingsRepository? = null
//        private lateinit var timingsDao: TimingsDao
//        fun getInstance(application: Context?): TimingsRepository? {
//            if (timingsRepository == null) {
//                synchronized(TimingsRepository::class.java) {
//                    if (timingsRepository == null) {
//                        timingsRepository = TimingsRepository(application)
//                    }
//                }
//            }
//            return timingsRepository
//        }
//
//        fun getInstance(application: Application?): TimingsRepository? {
//            if (timingsRepository == null) {
//                synchronized(TimingsRepository::class.java) {
//                    if (timingsRepository == null) {
//                        timingsRepository = TimingsRepository(application)
//                    }
//                }
//            }
//            return timingsRepository
//        }
//    }
}