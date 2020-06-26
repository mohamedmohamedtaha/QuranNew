package com.MohamedTaha.Imagine.New.receiver.changeTime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime;

public class PrayerTimeEveryDayAlarmChangeTimeRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.TIME_SET")) {
            NotificationHelperPrayerTime notificationHelperPrayerTime = new NotificationHelperPrayerTime(context);
            notificationHelperPrayerTime.getPrayerTimesEveryday();
        }
    }
}
