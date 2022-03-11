package com.mohamedtaha.imagine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mohamedtaha.imagine.notification.prayerTimes.NotificationHelperPrayerTime;
import com.mohamedtaha.imagine.room.TimingsRepository;
import com.mohamedtaha.imagine.helper.util.ConvertTimes;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GetPrayerTimesEveryDay extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelperPrayerTime notificationHelperPrayerTime = new NotificationHelperPrayerTime(context);
        TimingsRepository timingsRepository = TimingsRepository.getInstance(context);
        timingsRepository.getPrayerTimesForCurrentDate(ConvertTimes.convertDate()).
                subscribeOn(Schedulers.trampoline())
                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(prayer_times -> {
                    if (prayer_times.getDate_today().equals(ConvertTimes.convertDate())) {
                        Log.d("TAG", "prayer_times.getDate_today().equals(convertDate()) : " + prayer_times.getDate_today().equals(ConvertTimes.convertDate()));
                        notificationHelperPrayerTime.sendNotificationForPrayerTime( prayer_times);
                    }
                }, e -> {
                    Log.d("TAG", "GetPrayerTimesEveryDay e : " + e.getMessage());
                });
    }
}