package com.MohamedTaha.Imagine.New.receiver.bootDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MohamedTaha.Imagine.New.notification.quran.NotificationHelper;

/**
 * Created by MANASATT on 04/09/17.
 */

public class ReadAyatAlarmBootRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            NotificationHelper notificationHelper = new NotificationHelper(context);
            notificationHelper.sendNotificationEveryHalfDay();
        }
    }
}