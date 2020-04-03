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
import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertFromMilliSecondsToTime;

/**
 * Created by MANASATT on 04/09/17.
 */

public class NotificationHelperPrayerTime {
    public static final String TEXT_NOTIFICATION = "text_notification";

    public static void sendNotificationForPrayerTime(Context context) {
        //Setting intent to class where notification will be handled
        List<ModelMessageNotification> listForSavePrayerTimes = new ArrayList<>();
        listForSavePrayerTimes.clear();
        PendingIntent pendingIntent = null;
        //Set the alarm to start independed prayer timer
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTimePrayerWithText(listForSavePrayerTimes, setTime, 22, 15, "حان الأن موعد أذان الفجر");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, 22, 20, "حان الأن موعد أذان الشروق");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, 22, 25, "حان الأن موعد أذان الظهر");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, 22, 30, "حان الأن موعد أذان العصر");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, 22, 35, "حان الأن موعد أذان المغرب");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, 22, 40, "حان الأن موعد أذان العشاء");
        AlarmManager[] alarmManager = new AlarmManager[listForSavePrayerTimes.size()];
        Intent intent[] = new Intent[alarmManager.length];
        for (int i = 0; i < alarmManager.length; i++) {

         //1   intent[i] = new Intent(context, AlarmReceiverPrayerTime.class);
            intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
            Bundle bundle = new Bundle();
            bundle.putString(TEXT_NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));
            intent[i].putExtras(bundle);
            /* Here is very important,when we set one alarm, pending intent id becomes zero
            but if we want set multiple alarms pending intent id has to be unique so i counter
            is enough to be unique for PendingIntent    */

          //2  pendingIntent = PendingIntent.getBroadcast(context, i, intent[i], 0);
            pendingIntent = PendingIntent.getService(context, i, intent[i], 0);


          //  context.startService(new Intent(context, ServiceForPlayPrayerTimesNotification.class));
        //    context.stopService(new Intent(context, ServiceForPlayPrayerTimesNotification.class));

            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager[i].setExact(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            Log.d("TT", "i is : " + convertFromMilliSecondsToTime(setTime.getTimeInMillis()) +
                    "tt:" + convertFromMilliSecondsToTime(listForSavePrayerTimes.get(i).getTime_payer()));
        }
    }

    private static void setTimePrayerWithText(List<ModelMessageNotification> modelMessageNotificationList,
                                              Calendar time_prayer, int value_hour, int value_secound, String name_prayer) {
        time_prayer.set(Calendar.HOUR_OF_DAY, value_hour);
        time_prayer.set(Calendar.MINUTE, value_secound);
        modelMessageNotificationList.add(new ModelMessageNotification(time_prayer.getTimeInMillis(), name_prayer));
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