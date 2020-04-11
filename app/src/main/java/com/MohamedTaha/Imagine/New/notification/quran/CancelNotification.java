package com.MohamedTaha.Imagine.New.notification.quran;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.MohamedTaha.Imagine.New.service.ServiceForNotificationImage;

import static com.MohamedTaha.Imagine.New.notification.quran.AlarmReceiver.TIME_SEND;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelNotification extends BroadcastReceiver {
    private int time_send;

    @Override
    public void onReceive(Context context, Intent intent) {
        time_send = intent.getIntExtra(TIME_SEND, -1);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(time_send);
        context.stopService(new Intent(context, ServiceForNotificationImage.class));
    }
}
