package com.MohamedTaha.Imagine.New.receiver.changeTime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MohamedTaha.Imagine.New.notification.quran.NotificationHelper;

public class ReadAyatAlarmChangeTimeRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.TIME_SET")) {
            NotificationHelper notificationHelper = new NotificationHelper(context);
            notificationHelper.sendNotificationEveryHalfDay();
        }
    }
}
