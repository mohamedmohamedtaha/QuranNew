package com.MohamedTaha.Imagine.New.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.MohamedTaha.Imagine.New.notification.morningAzkar.MorningAzkarNotificationHelper;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime;
import com.MohamedTaha.Imagine.New.room.TimingsRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;

public class GetPrayerTimesEveryDay extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelperPrayerTime notificationHelperPrayerTime = new NotificationHelperPrayerTime();
        TimingsRepository timingsRepository = TimingsRepository.getInstance(context);
        timingsRepository.getPrayerTimesForCurrentDate(convertDate()).
                subscribeOn(Schedulers.trampoline())
                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prayer_times -> {
                    if (prayer_times.getDate_today().equals(convertDate())) {
                        notificationHelperPrayerTime.sendNotificationForPrayerTime(context, prayer_times);
                    }
                }, e -> {
                    Log.d("TAG", "GetPrayerTimesEveryDay e : " + e.getMessage());
                });
    }
}