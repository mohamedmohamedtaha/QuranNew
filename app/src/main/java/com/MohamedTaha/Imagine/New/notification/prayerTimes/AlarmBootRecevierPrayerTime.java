package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MohamedTaha.Imagine.New.notification.quran.NotificationHelper;

public class AlarmBootRecevierPrayerTime extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            //only enabling one type of notification
            NotificationHelperPrayerTime.sendNotificationForPrayerTime(context);
        }
    }
}