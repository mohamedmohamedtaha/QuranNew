package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesCustomNotification;
import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.LIST_TIME__NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.REQUEST_CODE_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.TEXT_NAME_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.TIME_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesCustomNotification.NOTIFICATION_ID_SERVICE;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.ACTION_STOP_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.SEND_TIME_FOR_SEINDING;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelNotificationPrayerTime extends BroadcastReceiver {
    private int send_time;
    private List<ModelMessageNotification> listForSavePrayerTimes ;
    private String name_prayer_time;
    private String prayer_time;
    private int request_code ;


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
            prayer_time = intent.getStringExtra(TIME_NOTIFICATION);
            request_code = intent.getIntExtra(REQUEST_CODE_NOTIFICATION,-1);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
           alarm.cancelAlarm(listForSavePrayerTimes,name_prayer_time);
           // alarm.customCancelAlarm(request_code);

            Log.d("TAG", " CancelNotificationPrayerTime : " + name_prayer_time +" send_time : "+ send_time + "   " +"NOTIFICATION_ID_SERVICE : " + NOTIFICATION_ID_SERVICE + " prayer_time : " + prayer_time);
            manager.cancel(NOTIFICATION_ID_SERVICE);
            context.stopService(new Intent(context, ServiceForPlayPrayerTimesCustomNotification.class));
        }}
   }
