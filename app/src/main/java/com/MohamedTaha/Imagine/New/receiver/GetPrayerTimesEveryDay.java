package com.MohamedTaha.Imagine.New.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;
import com.MohamedTaha.Imagine.New.room.TimingsRepository;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.enableBootRecieiver;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.sendNotificationForPrayerTime;

public class GetPrayerTimesEveryDay extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TimingsRepository timingsRepository = TimingsRepository.getInstance(context);
          // For get prayer times
        timingsRepository.getPrayerTimesForCurrentDate(convertDate()).
                subscribeOn(Schedulers.trampoline())
                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prayer_times -> {
                    if (prayer_times.getDate_today().equals(convertDate())){
                        sendNotificationForPrayerTime(context, prayer_times);
                        enableBootRecieiver(context);
                    }
                }, e -> {
                    Log.d("TAG",e.getMessage() );
                    Toast.makeText(context, "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
