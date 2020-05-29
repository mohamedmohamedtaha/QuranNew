package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.LIST_TIME__NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.TEXT_NAME_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.ACTION_STOP_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.SEND_TIME_FOR_SEINDING;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelNotificationPrayerTime extends BroadcastReceiver {
    private int send_time;
    private List<ModelMessageNotification> listForSavePrayerTimes ;
    private String name_prayer_time;


    @Override
    public void onReceive(Context context, Intent intent) {
        Alarm alarm = new Alarm(context);
        if (intent.getAction().equals(ACTION_STOP_NOTIFICATION)){
            Type listType = new TypeToken<List<ModelMessageNotification>>() {
            }.getType();
            String st = intent.getStringExtra(LIST_TIME__NOTIFICATION);
            listForSavePrayerTimes = new Gson().fromJson(st, listType);
            send_time = intent.getIntExtra(SEND_TIME_FOR_SEINDING, -1);
         //   name_prayer_time = new Gson().fromJson(intent.getStringExtra(TEXT_NAME_NOTIFICATION), String.class);

           name_prayer_time = intent.getStringExtra(TEXT_NAME_NOTIFICATION);
           NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        alarm.cancelAlarm(listForSavePrayerTimes,name_prayer_time);
            Log.d("TAG", " CancelNotificationPrayerTime : " + listForSavePrayerTimes.size() +" : " + name_prayer_time +" : "+ send_time);
            manager.cancel(send_time);
            context.stopService(new Intent(context, ServiceForPlayPrayerTimesNotification.class));
    }}
}
