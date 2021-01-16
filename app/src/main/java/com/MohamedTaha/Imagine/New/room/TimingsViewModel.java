package com.MohamedTaha.Imagine.New.room;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;


public class TimingsViewModel extends AndroidViewModel {
    public static final String SAVE_INSTANCE_DATE_TODAY = "save_instance_date_today";
    private TimingsRepository timingsRepository;
    private LiveData<List<Timings>> getAllTimings;
    private Flowable<List<Timings>> getAllTimingsRxjava;
    public static int store_date_today = 0;
    public boolean isNewlyCreated = true;

    public TimingsViewModel(@NonNull Application application) {
        super(application);
        timingsRepository = TimingsRepository.getInstance(application);
        getAllTimings = timingsRepository.getAllTimings();
        getAllTimingsRxjava = timingsRepository.getAllTimingsRxjava();
    }

    public Flowable<List<Timings>> getAllTimingsRxjava() {
        return getAllTimingsRxjava;
    }

    public Flowable<Integer> getTimingsByDataToday(String date_today) {
        return timingsRepository.getTimingsByDataToday(date_today);
    }

    public Single<Integer> checkIsDateTodayFind(String date_today) {
        return timingsRepository.checkIsDateTodayFind(date_today);
    }

    public Flowable<String> getCityName() {
        return timingsRepository.getCityName();
    }

    public LiveData<List<Timings>> getAllTimings() {
        return getAllTimings;
    }

    public void insert(Timings timings) {
        timingsRepository.insert(timings);
    }

    public void update(Timings timings) {
        timingsRepository.update(timings);
    }

    public void deleteAll(Timings timings) {
        timingsRepository.deleteAll();
    }

    public void saveState(Bundle outState) {

        outState.putInt(SAVE_INSTANCE_DATE_TODAY, store_date_today);
    }

    public void restoreState(Bundle outState) {
        store_date_today = outState.getInt(SAVE_INSTANCE_DATE_TODAY);
    }
}
