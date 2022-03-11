package com.mohamedtaha.imagine.notification.prayerTimes;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mohamedtaha.imagine.helper.Utilities;
import com.mohamedtaha.imagine.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelNotificationPrayerTime extends BroadcastReceiver {
    private List<ModelMessageNotification> listForSavePrayerTimes;
    private int request_code;


    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isServiceRunning = Utilities.isServiceRunning(ServiceForPlayPrayerTimesNotification.class.getName(), context);
     //   Alarm alarm = new Alarm(context);
        if (intent.getAction().equals(ServiceForPlayPrayerTimesNotification.ACTION_STOP_NOTIFICATION)) {
            Type listType = new TypeToken<List<ModelMessageNotification>>() {
            }.getType();
            String st = intent.getStringExtra(AlarmUtils.LIST_TIME__NOTIFICATION);
            listForSavePrayerTimes = new Gson().fromJson(st, listType);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
         //   alarm.cancelAlarm(listForSavePrayerTimes, name_prayer_time);
            manager.cancel(ServiceForPlayPrayerTimesNotification.NOTIFICATION_ID_SERVICE);
            if (isServiceRunning){
                context.stopService(new Intent(context, ServiceForPlayPrayerTimesNotification.class));
            }
        }
    }
}
