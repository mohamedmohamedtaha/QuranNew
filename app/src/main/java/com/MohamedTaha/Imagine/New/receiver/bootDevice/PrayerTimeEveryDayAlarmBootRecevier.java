package com.MohamedTaha.Imagine.New.receiver.bootDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime;

public class PrayerTimeEveryDayAlarmBootRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            NotificationHelperPrayerTime notificationHelperPrayerTime = new NotificationHelperPrayerTime(context);
            notificationHelperPrayerTime.getPrayerTimesEveryday();
        }
    }
}