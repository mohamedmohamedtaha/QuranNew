package com.MohamedTaha.Imagine.New.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

import java.util.List;

import io.reactivex.Flowable;


public class TimingsViewModel extends AndroidViewModel {
    private TimingsRepository timingsRepository;
    private LiveData<List<Timings>> getAllTimings;
    private Flowable<List<Timings>> getAllTimingsRxjava;

    public TimingsViewModel(@NonNull Application application) {
        super(application);
        timingsRepository = TimingsRepository.getInstance(application);
           getAllTimings = timingsRepository.getAllTimings();
        getAllTimingsRxjava = timingsRepository.getAllTimingsRxjava();

    }
    public Flowable<List<Timings>> getAllTimingsRxjava(){
        return getAllTimingsRxjava;
    }
    public   Flowable<Integer>getTimingsByDataToday(String date_today){
        return timingsRepository.getTimingsByDataToday(date_today);
    }

    public LiveData<List<Timings>> getAllTimings(){
        return getAllTimings;
    }
    public void insert(Timings timings){
        timingsRepository.insert(timings);
    }
    public void update(Timings timings){
        timingsRepository.update(timings);
    }
    public void deleteAll(Timings timings){
        timingsRepository.deleteAll();
    }
}
