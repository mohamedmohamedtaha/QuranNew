package com.mohamedtaha.imagine.room;

import android.app.Application;
import android.content.Context;


import androidx.lifecycle.LiveData;

import com.mohamedtaha.imagine.mvp.model.azan.Timings;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;


public class TimingsRepository {
    private static TimingsRepository timingsRepository;
    private static TimingsDao timingsDao;
    private LiveData<List<Timings>> listLiveData;
    private Flowable<List<Timings>> listLiveDataRxjava;
    private Single<Integer> row;

    TimingsRepository(Application application) {
        TimingsAppDatabase database = TimingsAppDatabase.getInstance(application);
        timingsDao = database.timingsDao();
        listLiveData = timingsDao.getAllTimings();
        listLiveDataRxjava = timingsDao.getAllTimingsRxjave();

    }
    TimingsRepository(Context application) {
        TimingsAppDatabase database = TimingsAppDatabase.getInstance(application);
        timingsDao = database.timingsDao();
        listLiveData = timingsDao.getAllTimings();
        listLiveDataRxjava = timingsDao.getAllTimingsRxjave();

    }
    public static TimingsRepository getInstance(Context application){
        if (timingsRepository == null){
            synchronized (TimingsRepository.class){
                if (timingsRepository == null){
                    timingsRepository = new TimingsRepository(application);
                }
            }
        }return timingsRepository;
    }
    public static TimingsRepository getInstance(Application application){
        if (timingsRepository == null){
            synchronized (TimingsRepository.class){
                if (timingsRepository == null){
                    timingsRepository = new TimingsRepository(application);
                }
            }
        }return timingsRepository;
    }
    public Flowable<List<Timings>>getAllTimingsRxjava(){
        return listLiveDataRxjava;
    }
    public   Flowable<Integer>getTimingsByDataToday(String date_today){
        return timingsDao.getTimingsByDataToday(date_today);
    }
    public Single<Integer> checkIsDateTodayFind(String date_today){
        return timingsDao.checkIsDateTodayFind(date_today);
//        AppExecutors.getInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                row = timingsDao.checkIsDateTodayFind(date_today);;
//            }
//        });
//        return row;

    }
    public   Flowable<String>getCityName(){
        return timingsDao.getCityName();
    }
    public   Flowable<Timings>getPrayerTimesForCurrentDate(String date_today){
        return timingsDao.getPrayerTimesForCurrentDate(date_today);
    }

    public LiveData<List<Timings>>getAllTimings(){
        return listLiveData;
    }
    public void insert(final Timings timings){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                timingsDao.insertTimings(timings);
            }
        });
    }
    public void update(final Timings timings){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                timingsDao.updateTimings(timings);
            }
        });
    }
    public void deleteAll(){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                timingsDao.deleteAllTimings();
            }
        });
    }
}
