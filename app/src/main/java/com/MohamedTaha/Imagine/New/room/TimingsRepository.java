package com.MohamedTaha.Imagine.New.room;

import android.app.Application;


import androidx.lifecycle.LiveData;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

import java.util.List;

public class TimingsRepository {
    private static TimingsRepository timingsRepository;
    private static TimingsDao timingsDao;
    private LiveData<List<Timings>> listLiveData;

    TimingsRepository(Application application) {
        TimingsAppDatabase database = TimingsAppDatabase.getInstance(application);
        timingsDao = database.timingsDao();
        listLiveData = timingsDao.getAllTimings();
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
