package com.mohamedtaha.imagine.notification.morningAzkar;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelMorningAzkarNotification extends BroadcastReceiver {
    private int time_send;

    @Override
    public void onReceive(Context context, Intent intent) {
        time_send = intent.getIntExtra(MorningAzkarAlarmReceiver.TIME_SEND_MORNING_AZKAR, -1);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(time_send);
    }
}
