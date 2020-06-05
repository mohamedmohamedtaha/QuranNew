package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesCustomNotification;
import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesCustomNotification.NOTIFICATION_ID_SERVICE;

public class Alarm {
    private Context context;
    public static final String TIME_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.time.notification";
    public static final String LIST_TIME__NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.list.time.notification";
    public static final String REQUEST_CODE_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.request_code.notification";
    public static final String TEXT_NAME_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.text.name.notification";
    private static int requestCode;
    private PendingIntent pendingIntent = null;
    private AlarmManager[] alarmManager = null;
    private Intent[] intent = null ;
    private NotificationManager[] notificationManagers;

    public Alarm(Context context) {
        this.context = context;
    }

    public void setAlarm(Class name_class, List<ModelMessageNotification> listForSavePrayerTimes) {

         pendingIntent = null;
          alarmManager = new AlarmManager[listForSavePrayerTimes.size()];

         intent = new Intent[alarmManager.length];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        for (int i = 0; i < alarmManager.length; i++) {
            intent[i] = new Intent(context, name_class);
            intent[i].putExtra(TIME_NOTIFICATION, listForSavePrayerTimes.get(i).getTime_payer());
            intent[i].putExtra(TEXT_NAME_NOTIFICATION, listForSavePrayerTimes.get(i).getText_notification());
            intent[i].putExtra(LIST_TIME__NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));
         //   intent[i].setAction(" ");
//            Bundle bundle = new Bundle();
//            bundle.putLong(TIME_NOTIFICATION, listForSavePrayerTimes.get(i).getTime_payer());
//            bundle.putString(TEXT_NAME_NOTIFICATION, listForSavePrayerTimes.get(i).getText_notification());
//            bundle.putString(LIST_TIME__NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));
            Log.d("TAG", " TEXT_NOTIFICATION : " + listForSavePrayerTimes.get(i).getTime_payer()
                    + " : " + " TEXT_NOTIFICATION : " + listForSavePrayerTimes.get(i).getText_notification());
           // intent[i].putExtras(bundle);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                pendingIntent = PendingIntent.getForegroundService(context, i, intent[i], 0);
            } else {
                pendingIntent = PendingIntent.getService(context, i, intent[i], 0);
            }
            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        //    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          //      alarmManager[i].setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
           // } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager[i].setExact(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
           // } else {
             //   alarmManager[i].set(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
           // }
        }
    }

    public void customAlarm(int hour, int minute, String name_prayer_time) {
        requestCode++;
        Calendar calendar_now = Calendar.getInstance();
        calendar_now.setTimeInMillis(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, hour);
        setTime.set(Calendar.MINUTE, minute);
        setTime.set(Calendar.SECOND, 01);

        PendingIntent pendingIntent = null;
        AlarmManager alarmManager;
        Intent intent;
        intent = new Intent(context, ServiceForPlayPrayerTimesCustomNotification.class);
        intent.setAction(" ");
        Bundle bundle = new Bundle();
        bundle.putLong(TIME_NOTIFICATION, setTime.getTimeInMillis());
        bundle.putString(TEXT_NAME_NOTIFICATION, name_prayer_time);
        bundle.putInt(REQUEST_CODE_NOTIFICATION, requestCode);

        Log.d("TAG", " setTime.getTimeInMillis : " + setTime.getTimeInMillis() + " : " + name_prayer_time);
        intent.putExtras(bundle);
        if (calendar_now.getTimeInMillis() < (setTime.getTimeInMillis())) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pendingIntent = PendingIntent.getForegroundService(context, requestCode, intent, 0);
        } else {
            pendingIntent = PendingIntent.getService(context, requestCode, intent, 0);
        }
        Log.d("TAG", " requestCode : " + requestCode);
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, setTime.getTimeInMillis(), pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, setTime.getTimeInMillis(), pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, setTime.getTimeInMillis(), pendingIntent);
            }
        }
    }

    public void cancelAlarmBoot() {
            for (int i = 0; i < 5; i++) {
                notificationManagers[i] =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        pendingIntent = PendingIntent.getForegroundService(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                    } else {
                        pendingIntent = PendingIntent.getService(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                    }
                  //  if (pendingIntent != null && alarmManager != null){
                        alarmManager[i].cancel(pendingIntent);
                        notificationManagers[i].cancel(NOTIFICATION_ID_SERVICE);
                        // Disable BootReceiver so that alarm won't start again if device is rebooted
                        ComponentName receiver = new ComponentName(context, AlarmBootRecevierPrayerTimeEveryDay.class);
                        PackageManager pm = context.getPackageManager();
                        pm.setComponentEnabledSetting(receiver,
                                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                PackageManager.DONT_KILL_APP);

                   // }
                    Log.d("TAG", " cancelAlarm :" + i);

            }}
    public void cancelAlarm(List<ModelMessageNotification> listForSavePrayerTimes, String name_prayer_time) {
        if (listForSavePrayerTimes.size() >0){
             alarmManager = new AlarmManager[listForSavePrayerTimes.size()];
             intent = new Intent[listForSavePrayerTimes.size()];
            for (int i = 0; i < listForSavePrayerTimes.size(); i++) {
                if (listForSavePrayerTimes.get(i).getText_notification().equals(name_prayer_time)){
                    alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        pendingIntent = PendingIntent.getForegroundService(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                    } else {
                        pendingIntent = PendingIntent.getService(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                    }
                    if (pendingIntent != null && alarmManager != null){
                        alarmManager[i].cancel(pendingIntent);
                    }
                    Log.d("TAG", " cancelAlarm :" + i);
                }
            }}
    }

    public void customCancelAlarm(List<ModelMessageNotification> listForSavePrayerTimes) {
        if (alarmManager != null) {
            for (int i = 0; i < listForSavePrayerTimes.size(); i++) {
                //  if (listForSavePrayerTimes.get(i).getText_notification().equals(name_prayer_time)){
                alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    pendingIntent = PendingIntent.getForegroundService(context, i, intent[i], 0);
                } else {
                    pendingIntent = PendingIntent.getService(context, i, intent[i], 0);
                }
                alarmManager[i].cancel(pendingIntent);
                Log.d("TAG", " customCancelAlarm :");
                // }
            }
        }
    }
}