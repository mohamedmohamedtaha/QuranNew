package com.MohamedTaha.Imagine.New.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.getPrayerTimesEveryMonth;

public class AlarmBootRecevierPrayerTimeEveryMonth extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            getPrayerTimesEveryMonth(context);
        }
    }
}
