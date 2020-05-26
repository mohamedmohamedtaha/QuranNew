package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;

import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.SEND_TIME_FOR_SEINDING;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelNotificationPrayerTime extends BroadcastReceiver {
    private int send_time;

    @Override
    public void onReceive(Context context, Intent intent) {
        send_time = intent.getIntExtra(SEND_TIME_FOR_SEINDING, -1);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(send_time);
        context.stopService(new Intent(context, ServiceForPlayPrayerTimesNotification.class));
    }
}
