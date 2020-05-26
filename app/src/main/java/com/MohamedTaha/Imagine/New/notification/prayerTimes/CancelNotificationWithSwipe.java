package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.SEND_TIME_FOR_SWIPE_NOTIFICATION;

public class CancelNotificationWithSwipe extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(SEND_TIME_FOR_SWIPE_NOTIFICATION)){
            Log.d("TAG", " TT" + action.equals(SEND_TIME_FOR_SWIPE_NOTIFICATION));
        }
    }
}
