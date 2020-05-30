package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
          //  playbackAction(context,0);
            manager.cancel(send_time);
//            Intent playbackAction = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
//            playbackAction.setAction(ACTION_STOP_NOTIFICATION);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                PendingIntent.getForegroundService(context, 0, playbackAction, 0);
//            }else {
//                PendingIntent.getService(context, 0, playbackAction, 0);
//            }
             context.stopService(new Intent(context, ServiceForPlayPrayerTimesNotification.class));

            }}

    private PendingIntent playbackAction(Context context,int actionNumber) {
        Intent playbackAction;
        playbackAction = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
        switch (actionNumber) {
            case 0:
                playbackAction.setAction(ACTION_STOP_NOTIFICATION);
//                bundle.putLong(TIME_NOTIFICATION, prayer_time);
//                bundle.putString(TEXT_NAME_NOTIFICATION, name_prayer_time);
                //      playbackAction.putExtras(bundle);
                //  stopService(new Intent(getApplicationContext(), ServiceForPlayPrayerTimesNotification.class));


                return PendingIntent.getService(context, actionNumber, playbackAction, 0);
            default:
                break;
        }
        return null;
    }

}
