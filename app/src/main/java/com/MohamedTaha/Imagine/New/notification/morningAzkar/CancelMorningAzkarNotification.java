package com.MohamedTaha.Imagine.New.notification.morningAzkar;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.MohamedTaha.Imagine.New.notification.morningAzkar.MorningAzkarAlarmReceiver.TIME_SEND_MORNING_AZKAR;
import static com.MohamedTaha.Imagine.New.notification.quran.AlarmReceiver.TIME_SEND;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelMorningAzkarNotification extends BroadcastReceiver {
    private int time_send;

    @Override
    public void onReceive(Context context, Intent intent) {
        time_send = intent.getIntExtra(TIME_SEND_MORNING_AZKAR, -1);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(time_send);
    }
}
