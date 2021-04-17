package com.MohamedTaha.Imagine.New.receiver.bootDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.ScheduleGetDataEveryMonth;
import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.getPrayerTimesEveryMonth;

public class PrayerTimeEveryMonthAlarmBootRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
          //  getPrayerTimesEveryMonth(context);
            ScheduleGetDataEveryMonth(context);
        }
    }
}
