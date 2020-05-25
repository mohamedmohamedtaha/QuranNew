package com.MohamedTaha.Imagine.New.room;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.MohamedTaha.Imagine.New.DatabaseCallbackEveryMonth;
import com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Data;
import com.MohamedTaha.Imagine.New.mvp.model.AzanSource.DataTest;
import com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Date;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Azan;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

@Database(entities = {Timings.class},version = 2,exportSchema = false)
public abstract class TimingsAppDatabase extends RoomDatabase {
    private static final String TAG = TimingsAppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "timingsdb";
    private static TimingsAppDatabase mInstance;

    public abstract TimingsDao timingsDao();
    public static TimingsAppDatabase getInstance(Context context){
        if (mInstance == null){
           // synchronized (LOCK){
            synchronized (TimingsAppDatabase.class){
                if (mInstance == null){
                Log.d(TAG, "getInstance: Creating a new database instance");
                mInstance = Room.databaseBuilder(context.getApplicationContext(),
                        TimingsAppDatabase.class,
                        TimingsAppDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration().build();
            }}
            Log.d(TAG,"getInstance :  Getting the database instance, no need to recreated it.");
        }
        return mInstance;
    }
    /**
     * Populate Database Section
     */
    private static RoomDatabase.Callback mRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
    public void AddPrayerTimesForMonth(DatabaseCallback databaseCallback, Azan azan, String city_name){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                for (int i = 0; i < azan.getData().size(); i++) {
                    Timings timingsOne = new Timings();
                    timingsOne.setFajr(azan.getData().get(i).getTimings().getFajr());
                    timingsOne.setSunrise(azan.getData().get(i).getTimings().getSunrise());
                    timingsOne.setDhuhr(azan.getData().get(i).getTimings().getDhuhr());
                    timingsOne.setAsr(azan.getData().get(i).getTimings().getAsr());
                    timingsOne.setMaghrib(azan.getData().get(i).getTimings().getMaghrib());
                    timingsOne.setIsha(azan.getData().get(i).getTimings().getIsha());
                    timingsOne.setDate_today(azan.getData().get(i).getDate().getGregorian().getDate());
                    timingsOne.setId_seq(i+1);
                    timingsOne.setCity(city_name);
                    timingsDao().insertTimings(timingsOne);
                    Log.d("TAG","i :"+ i);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                       databaseCallback.onPrayerTimesAdded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onPrayerTimesError();
                    }
                });
    }
    public void AddPrayerTimesEveryMonth(DatabaseCallbackEveryMonth databaseCallbackEveryMonth, Azan azan, String city_name){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                for (int i = 0; i < azan.getData().size(); i++) {
                    Timings timingsOne = new Timings();
                    timingsOne.setFajr(azan.getData().get(i).getTimings().getFajr());
                    timingsOne.setSunrise(azan.getData().get(i).getTimings().getSunrise());
                    timingsOne.setDhuhr(azan.getData().get(i).getTimings().getDhuhr());
                    timingsOne.setAsr(azan.getData().get(i).getTimings().getAsr());
                    timingsOne.setMaghrib(azan.getData().get(i).getTimings().getMaghrib());
                    timingsOne.setIsha(azan.getData().get(i).getTimings().getIsha());
                    timingsOne.setDate_today(azan.getData().get(i).getDate().getGregorian().getDate());
                    timingsOne.setId_seq(i+1);
                    timingsOne.setCity(city_name);
                    timingsDao().insertTimings(timingsOne);
                    Log.d("TAG","i :"+ i);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallbackEveryMonth.onPrayerTimesError();
                    }
                });
    }

//    public void AddPrayerTimesForYear(DatabaseCallback databaseCallback, com.MohamedTaha.Imagine.New.mvp.model.AzanSource.Azan azan, String city_name){
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//               Data data = azan.getData();
//                DataTest[] dataTests = data.getDataTests();
//                Data data1 = data.get1();
//                for (int i = 1; i < 12; i++) {
//                    for (int y= 0;y< 30;y++) {
//                        Timings timingsOne = new Timings();
//                        timingsOne.setFajr(dataTests[y].getTimings().getFajr());
//                        timingsOne.setSunrise(dataTests[y].getTimings().getSunrise());
//                        timingsOne.setDhuhr(dataTests[y].getTimings().getDhuhr());
//                        timingsOne.setAsr(dataTests[y].getTimings().getAsr());
//                        timingsOne.setMaghrib(dataTests[y].getTimings().getMaghrib());
//                        timingsOne.setIsha(dataTests[y].getTimings().getIsha());
//                        timingsOne.setDate_today(dataTests[y].getDate().getGregorian().getDate());
//                        timingsOne.setId_seq(i + 1);
//                        timingsOne.setCity(city_name);
//                        timingsDao().insertTimings(timingsOne);
//                        Log.d("TAG", "y :" + y);
//                        Log.d("TAG", "date :" +timingsOne.getDate_today());
//
//                    }
//                    Log.d("TAG", "i :" + i);
//
//               }
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        databaseCallback.onPrayerTimesAdded();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        databaseCallback.onPrayerTimesError();
//                    }
//                });
//    }

    public void DeletePrayerTimes(DatabaseCallback databaseCallback){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                timingsDao().deleteAllTimings();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        databaseCallback.onPrayerTimesDeleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onPrayerTimesError();
                    }
                });

    }
    public void DeletePrayerTimes(DatabaseCallbackEveryMonth databaseCallback){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                timingsDao().deleteAllTimings();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        databaseCallback.onPrayerTimesDeleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onPrayerTimesError();
                    }
                });

    }

    public void DeletePrayerTimesForGetDataWithLocation(DatabaseCallback databaseCallback){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                timingsDao().deleteAllTimings();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        databaseCallback.getDataFromLocationAfterDeleteData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        databaseCallback.onPrayerTimesError();
                    }
                });

    }

    //For migration after edit or add on the table
    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
}
