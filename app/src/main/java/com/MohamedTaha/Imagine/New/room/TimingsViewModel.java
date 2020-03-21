package com.MohamedTaha.Imagine.New.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

import java.util.List;

public class TimingsViewModel extends AndroidViewModel {
    private TimingsRepository timingsRepository;
    private LiveData<List<Timings>> getAllTimings;
    public TimingsViewModel(@NonNull Application application) {
        super(application);
        timingsRepository = TimingsRepository.getInstance(application);
        getAllTimings = timingsRepository.getAllTimings();
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
