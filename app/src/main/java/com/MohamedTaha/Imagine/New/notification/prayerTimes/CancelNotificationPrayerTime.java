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
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.ACTION_STOP_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.SEND_TIME_FOR_SEINDING;

/**
 * Created by MANASATT on 04/09/17.
 */

public class CancelNotificationPrayerTime extends BroadcastReceiver {
    private int send_time;
    private List<ModelMessageNotification> listForSavePrayerTimes ;


    @Override
    public void onReceive(Context context, Intent intent) {
        Alarm alarm = new Alarm(context);
        send_time = intent.getIntExtra(SEND_TIME_FOR_SEINDING, -1);
        if (intent.getAction().equals(ACTION_STOP_NOTIFICATION)){
            Type listType = new TypeToken<List<ModelMessageNotification>>() {
            }.getType();
            String st = intent.getStringExtra(LIST_TIME__NOTIFICATION);
            listForSavePrayerTimes = new Gson().fromJson(st, listType);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        alarm.cancelAlarm(listForSavePrayerTimes);
        manager.cancel(send_time);
            Log.d("TAG", " CancelNotificationPrayerTime : " + listForSavePrayerTimes.size());

            context.stopService(new Intent(context, ServiceForPlayPrayerTimesNotification.class));
    }}
}
