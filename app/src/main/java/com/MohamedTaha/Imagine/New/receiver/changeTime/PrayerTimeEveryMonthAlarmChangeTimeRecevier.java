package com.MohamedTaha.Imagine.New.receiver.changeTime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime;

import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.ScheduleGetDataEveryMonth;
import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.getPrayerTimesEveryMonth;

public class PrayerTimeEveryMonthAlarmChangeTimeRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.TIME_SET")) {
        //    getPrayerTimesEveryMonth(context);
            ScheduleGetDataEveryMonth(context);

        }
    }
}
