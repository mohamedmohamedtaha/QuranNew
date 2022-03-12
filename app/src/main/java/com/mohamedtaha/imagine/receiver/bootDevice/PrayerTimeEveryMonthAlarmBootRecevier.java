package com.mohamedtaha.imagine.receiver.bootDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity.ScheduleGetDataEveryMonth;

public class PrayerTimeEveryMonthAlarmBootRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
          //  getPrayerTimesEveryMonth(context);
            ScheduleGetDataEveryMonth(context);
        }
    }
}
