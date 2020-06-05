package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBootRecevierPrayerTimeEveryDay extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
          //  Alarm alarm = new Alarm(context);
        //    alarm.cancelAlarmBoot();
            NotificationHelperPrayerTime notificationHelperPrayerTime = new NotificationHelperPrayerTime();
            notificationHelperPrayerTime.getPrayerTimesEveryday(context);
        }
    }
}