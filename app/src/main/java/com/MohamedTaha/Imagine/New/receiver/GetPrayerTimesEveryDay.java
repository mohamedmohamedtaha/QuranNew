package com.MohamedTaha.Imagine.New.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.MohamedTaha.Imagine.New.room.TimingsViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;

public class GetPrayerTimesEveryDay extends BroadcastReceiver {
    private TimingsViewModel timingsViewModel;

    @Override
    public void onReceive(Context context, Intent intent) {

        timingsViewModel = new ViewModelProvider(context).get(TimingsViewModel.class);
        // For get Date today
        timingsViewModel.getTimingsByDataToday(convertDate()).
                subscribeOn(Schedulers.trampoline())
                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(date_today -> {
                      Toast.makeText(context, "date today is " + date_today, Toast.LENGTH_SHORT).show();
                }, e -> {
                    Toast.makeText(context, "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });
    }
}
