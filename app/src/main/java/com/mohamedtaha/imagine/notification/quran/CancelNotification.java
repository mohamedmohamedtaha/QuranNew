package com.mohamedtaha.imagine.notification.quran;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelNotification extends BroadcastReceiver {
    private int time_send;

    @Override
    public void onReceive(Context context, Intent intent) {
        time_send = intent.getIntExtra(AlarmReceiver.TIME_SEND, -1);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(time_send);
    }
}
