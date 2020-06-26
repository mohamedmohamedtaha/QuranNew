package com.MohamedTaha.Imagine.New.receiver.changeTime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;

import com.MohamedTaha.Imagine.New.notification.morningAzkar.MorningAzkarNotificationHelper;

public class MorningAzkarAlarmChangeTimeRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.TIME_SET")) {
            MorningAzkarNotificationHelper morningAzkarNotificationHelper = new MorningAzkarNotificationHelper(context);
            morningAzkarNotificationHelper.getAzkarTimesEveryday();
        }
    }
}
