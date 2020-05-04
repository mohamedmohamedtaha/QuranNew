package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;
import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationHelperPrayerTime {
    public static final String TEXT_NOTIFICATION = "text_notification";
    private static List<ModelMessageNotification> listForSavePrayerTimes;

    public static void sendNotificationForPrayerTime(Context context, Timings prayer_times) {
        listForSavePrayerTimes = new ArrayList<>();
        listForSavePrayerTimes.clear();
        PendingIntent pendingIntent = null;
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        Log.d("TAG", "Fire Alarm " + prayer_times.getDate_today());

        setTimePrayerWithText(listForSavePrayerTimes, setTime, Integer.valueOf(prayer_times.getFajr().substring(0, 2)),
                Integer.valueOf(prayer_times.getFajr().substring(3, 5)), "حان الأن موعد أذان الفجر");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, Integer.valueOf(prayer_times.getSunrise().substring(0, 2)),
                Integer.valueOf(prayer_times.getSunrise().substring(3, 5)), "حان الأن موعد أذان الشروق");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, Integer.valueOf(prayer_times.getDhuhr().substring(0, 2)),
                Integer.valueOf(prayer_times.getDhuhr().substring(3, 5)), "حان الأن موعد أذان الظهر");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, Integer.valueOf(prayer_times.getAsr().substring(0, 2)),
                Integer.valueOf(prayer_times.getAsr().substring(3, 5)), "حان الأن موعد أذان العصر");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, Integer.valueOf(prayer_times.getMaghrib().substring(0, 2)),
                Integer.valueOf(prayer_times.getMaghrib().substring(3, 5)), "حان الأن موعد أذان المغرب");
        setTimePrayerWithText(listForSavePrayerTimes, setTime, Integer.valueOf(prayer_times.getIsha().substring(0, 2)),
                Integer.valueOf(prayer_times.getIsha().substring(3, 5)), "حان الأن موعد أذان العشاء");
        Alarm alarm = new Alarm(context);
        alarm.setAlarm(pendingIntent, setTime, ServiceForPlayPrayerTimesNotification.class, listForSavePrayerTimes);
        Log.d("TAG", "Fire Alarm " + listForSavePrayerTimes.get(0).getTime_payer() + "/n"+
                listForSavePrayerTimes.get(1).getTime_payer() + "/n"
               + listForSavePrayerTimes.get(2).getTime_payer() + "/n"
                +listForSavePrayerTimes.get(3).getTime_payer() + "/n"
                +listForSavePrayerTimes.get(4).getTime_payer() + "/n"
                +listForSavePrayerTimes.get(5).getTime_payer() + "/n"
        );
    }

    private static void setTimePrayerWithText(List<ModelMessageNotification> modelMessageNotificationList,
                                              Calendar time_prayer, int value_hour, int value_secound, String name_prayer) {
        time_prayer.set(Calendar.HOUR_OF_DAY, value_hour);
        time_prayer.set(Calendar.MINUTE, value_secound);
        modelMessageNotificationList.add(new ModelMessageNotification(time_prayer.getTimeInMillis(), name_prayer));
    }

    //Enable boot receiver to persist alarms set for notification across device reboots
    public static void enableBootRecieiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootRecevierPrayerTime.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}