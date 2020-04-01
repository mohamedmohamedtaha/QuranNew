package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.MohamedTaha.Imagine.New.notification.quran.AlarmBootRecevier;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertFromMilliSecondsToTime;

/**
 * Created by MANASATT on 04/09/17.
 */

public class NotificationHelperPrayerTime {
    public static int ALARM_TYPE_ELAPSED = 101;
    private static AlarmManager alarmManager;
    private static PendingIntent alarmPendingIntent;
    public static final String TEXT_NOTIFICATION = "text_notification";

    public static void sendNotificationForPrayerTime(Context context) {
        //Setting intent to class where notification will be handled

//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        ArrayList<PendingIntent> pendingIntents = new ArrayList<PendingIntent>();
//        List<ModelMessageNotification> minutes = new ArrayList<>();
//        //Set the alarm to start independed prayer timer
//        Calendar calendar = Calendar.getInstance();
//        ArrayList<String> text_for_prayer_times = new ArrayList<>();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 17);
//        calendar.set(Calendar.MINUTE, 46);
//        text_for_prayer_times.add("حان الأن موعد أذان الفجر");
//        minutes.add(new ModelMessageNotification(calendar.getTimeInMillis(), text_for_prayer_times));
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTimeInMillis(System.currentTimeMillis());
//        calendar1.set(Calendar.HOUR_OF_DAY, 17);
//        calendar1.set(Calendar.MINUTE, 47);
//        text_for_prayer_times.add("حان الأن موعد أذان الظهر");
//        minutes.add(new ModelMessageNotification(calendar1.getTimeInMillis(), text_for_prayer_times));
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTimeInMillis(System.currentTimeMillis());
//        calendar2.set(Calendar.HOUR_OF_DAY, 17);
//        calendar2.set(Calendar.MINUTE, 48);
//        text_for_prayer_times.add("حان الأن موعد أذان العصر");
//        minutes.add(new ModelMessageNotification(calendar2.getTimeInMillis(), text_for_prayer_times));
//        for (int i = 0; i < 3; i++) {
//            Intent intent = new Intent(context, AlarmReceiverPrayerTime.class);
//            intent.putExtra(TEXT_NOTIFICATION,"TAHA");
////            Bundle bundle = new Bundle();
////            bundle.putParcelable(TEXT_NOTIFICATION, Parcels.wrap("TAHA"));
////            intent.putExtras(bundle);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, intent, 0);
////                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime() + 60000 *i ,
////                        pendingIntent);
//            alarmManager.set(AlarmManager.RTC_WAKEUP, minutes.get(i).getTime_payer(),
//                    pendingIntent);
//
//            pendingIntents.add(pendingIntent);
//            Log.d("TT", "i is : " + i);
//        }
//        Log.d("TT", "finish");

        List<ModelMessageNotification> minutes = new ArrayList<>();
        PendingIntent pendingIntent = null;
        //Set the alarm to start independed prayer timer
        Calendar calendar = Calendar.getInstance();
      //  ArrayList<String> text_for_prayer_times = new ArrayList<>();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,21);
        calendar.set(Calendar.MINUTE,42);
//     //   text_for_prayer_times.add("حان الأن موعد أذان الفجر");
        minutes.add(new ModelMessageNotification(calendar.getTimeInMillis(),"حان الأن موعد أذان الفجر"));
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTimeInMillis(System.currentTimeMillis());
//        calendar1.set(Calendar.HOUR_OF_DAY,19);
//        calendar1.set(Calendar.MINUTE,50);
//    //    text_for_prayer_times.add("حان الأن موعد أذان الظهر");
//        minutes.add(new ModelMessageNotification(calendar1.getTimeInMillis(),"حان الأن موعد أذان الظهر"));
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTimeInMillis(System.currentTimeMillis());
//        calendar2.set(Calendar.HOUR_OF_DAY,19);
//        calendar2.set(Calendar.MINUTE,51);
//     //   text_for_prayer_times.add("حان الأن موعد أذان العصر");
//        minutes.add(new ModelMessageNotification(calendar2.getTimeInMillis(),"حان الأن موعد أذان العصر"));
        AlarmManager[] alarmManager = new AlarmManager[minutes.size()];
        Intent intent[] = new Intent[alarmManager.length];
        for (int i = 0; i < alarmManager.length; i++) {
            intent[i] = new Intent(context, AlarmReceiverPrayerTime.class);
          //  intent[i].putExtra(TEXT_NOTIFICATION,"حان الأن موعد أذان العصر");
              Bundle bundle = new Bundle();
//            bundle.putParcelable(TEXT_NOTIFICATION, Parcels.wrap("حان الأن موعد أذان العصر"));
//            intent[i].putParcelableArrayListExtra(bundle);
              bundle.putString(TEXT_NOTIFICATION,new Gson().toJson(minutes));
             intent[i].putExtras(bundle);
          //  intent[i].putParcelableArrayListExtra(TEXT_NOTIFICATION,minutes.get(i).getText_notification());
//            intent[i].putExtra(TEXT_NOTIFICATION,minutes.get(i).getText_notification());
            /*
        Here is very important,when we set one alarm, pending intent id becomes zero
        but if we want set multiple alarms pending intent id has to be unique so i counter
        is enough to be unique for PendingIntent
      */
            pendingIntent = PendingIntent.getBroadcast(context, i, intent[i], 0);
            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager[i].set(AlarmManager.RTC_WAKEUP, minutes.get(i).getTime_payer(), pendingIntent);
            Log.d("TT" , "i is : " + convertFromMilliSecondsToTime(calendar.getTimeInMillis()) +
                    "tt:"+convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()));

        }


//
//        Intent intent = new Intent(context, AlarmReceiver.class);
//
//        //Setting pending intent to respond to broadcast sent by AlarmManager every day at 8am
//        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        getting instance of AlarmManager service
//        Inexact alarm everyday since device is booted up. This is a better choise and
//        scales well when device time preference/locale is changed
//        we are setting alarm to fire notification after 15 minutes , and every 15 minutes there on
//
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime() + setAlarm(repear),
//                setAlarm(repear), alarmPendingIntent);
//
//
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
//                AlarmManager.RTC_WAKEUP,
//                AlarmManager.RTC_WAKEUP, alarmPendingIntent);
    }

    //Enable boot receiver to persist alarms set for notification across device reboots
    public static void enableBootRecieiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}