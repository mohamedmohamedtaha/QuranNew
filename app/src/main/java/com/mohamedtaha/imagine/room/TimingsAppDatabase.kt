package com.mohamedtaha.imagine.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mohamedtaha.imagine.DatabaseCallbackEveryMonth
import com.mohamedtaha.imagine.mvp.model.azan.Azan
import com.mohamedtaha.imagine.mvp.model.azan.Timings
import com.mohamedtaha.imagine.room.TimingsAppDatabase
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

@Database(entities = [Timings::class], version = 5, exportSchema = false)
abstract class TimingsAppDatabase : RoomDatabase() {
    abstract fun timingsDao(): TimingsDao
    fun AddPrayerTimesForMonth(databaseCallback: DatabaseCallback, azan: Azan, city_name: String?) {
        Completable.fromAction {
            for (i in azan.data.indices) {
                val timingsOne = Timings()
                timingsOne.fajr = azan.data[i].timings.fajr
                timingsOne.sunrise = azan.data[i].timings.sunrise
                timingsOne.dhuhr = azan.data[i].timings.dhuhr
                timingsOne.asr = azan.data[i].timings.asr
                timingsOne.maghrib = azan.data[i].timings.maghrib
                timingsOne.isha = azan.data[i].timings.isha
                timingsOne.date_today = azan.data[i].date.gregorian.date
                timingsOne.day_today_hegry = azan.data[i].date.hijri.date
                timingsOne.id_seq = i + 1
                timingsOne.city = city_name
                timingsDao().insertTimings(timingsOne)
                Log.d("TAG", "i :$i")
            }
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {
                    databaseCallback.onPrayerTimesAdded()
                    Log.d("TAG", "onComplete")
                }

                override fun onError(e: Throwable) {
                    databaseCallback.onPrayerTimesError(e)
                    Log.d("TAG", "onError : " + e.message)
                }
            })
    }

    fun AddPrayerTimesEveryMonth(
        databaseCallbackEveryMonth: DatabaseCallbackEveryMonth,
        azan: Azan,
        city_name: String?
    ) {
        Completable.fromAction {
            for (i in azan.data.indices) {
                val timingsOne = Timings()
                timingsOne.fajr = azan.data[i].timings.fajr
                timingsOne.sunrise = azan.data[i].timings.sunrise
                timingsOne.dhuhr = azan.data[i].timings.dhuhr
                timingsOne.asr = azan.data[i].timings.asr
                timingsOne.maghrib = azan.data[i].timings.maghrib
                timingsOne.isha = azan.data[i].timings.isha
                timingsOne.date_today = azan.data[i].date.gregorian.date
                timingsOne.day_today_hegry = azan.data[i].date.hijri.date
                timingsOne.id_seq = i + 1
                timingsOne.city = city_name
                timingsDao().insertTimings(timingsOne)
                Log.d("TAG", "i :$i")
            }
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}
                override fun onError(e: Throwable) {
                    databaseCallbackEveryMonth.onPrayerTimesError()
                }
            })
    }

    fun DeletePrayerTimes(databaseCallback: DatabaseCallback) {
        Completable.fromAction { timingsDao().deleteAllTimings() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {
                    databaseCallback.onPrayerTimesDeleted()
                }

                override fun onError(e: Throwable) {
                    databaseCallback.onPrayerTimesError(e)
                }
            })
    }

    fun DeletePrayerTimes(databaseCallback: DatabaseCallbackEveryMonth) {
        Completable.fromAction { timingsDao().deleteAllTimings() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {
                    databaseCallback.onPrayerTimesDeleted()
                }

                override fun onError(e: Throwable) {
                    databaseCallback.onPrayerTimesError()
                }
            })
    }

    fun DeletePrayerTimesForGetDataWithLocation(databaseCallback: DatabaseCallback) {
        Completable.fromAction { timingsDao().deleteAllTimings() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {
                    databaseCallback.dataFromLocationAfterDeleteData
                }

                override fun onError(e: Throwable) {
                    databaseCallback.onPrayerTimesError(e)
                }
            })
    }

    companion object {
        private val TAG = TimingsAppDatabase::class.java.simpleName
        private val LOCK = Any()
        private const val DATABASE_NAME = "timingsdb"
        private var mInstance: TimingsAppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): TimingsAppDatabase? {
            if (mInstance == null) {
                // synchronized (LOCK){
                synchronized(TimingsAppDatabase::class.java) {
                    if (mInstance == null) {
                        Log.d(TAG, "getInstance: Creating a new database instance")
                        mInstance = Room.databaseBuilder(
                            context.applicationContext,
                            TimingsAppDatabase::class.java,
                            DATABASE_NAME
                        ) // .addCallback(mRoomDatabaseCallback)
                            .fallbackToDestructiveMigration().build()
                    }
                }
                Log.d(TAG, "getInstance :  Getting the database instance, no need to recreated it.")
            }
            return mInstance
        }

        /**
         * Populate Database Section
         */
        private val mRoomDatabaseCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }

        //For migration after edit or add on the table
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {}
        }
    }
}