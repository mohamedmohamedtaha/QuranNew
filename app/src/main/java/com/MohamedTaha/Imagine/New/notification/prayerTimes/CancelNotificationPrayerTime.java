package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.MohamedTaha.Imagine.New.helper.Utilities;
import com.MohamedTaha.Imagine.New.service.MediaPlayerService;
import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.AlarmUtils.LIST_TIME__NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.AlarmUtils.TEXT_NAME_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.AlarmUtils.TIME_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.ACTION_STOP_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.NOTIFICATION_ID_SERVICE;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.SEND_TIME_FOR_SEINDING;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelNotificationPrayerTime extends BroadcastReceiver {
    private List<ModelMessageNotification> listForSavePrayerTimes;
    private int request_code;
    private boolean isServiceRunning;


    @Override
    public void onReceive(Context context, Intent intent) {
        isServiceRunning = Utilities.isServiceRunning(ServiceForPlayPrayerTimesNotification.class.getName(), context);
     //   Alarm alarm = new Alarm(context);
        if (intent.getAction().equals(ACTION_STOP_NOTIFICATION)) {
            Type listType = new TypeToken<List<ModelMessageNotification>>() {
            }.getType();
            String st = intent.getStringExtra(LIST_TIME__NOTIFICATION);
            listForSavePrayerTimes = new Gson().fromJson(st, listType);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
         //   alarm.cancelAlarm(listForSavePrayerTimes, name_prayer_time);
            manager.cancel(NOTIFICATION_ID_SERVICE);
            if (isServiceRunning){
                context.stopService(new Intent(context, ServiceForPlayPrayerTimesNotification.class));
            }
        }
    }
}
