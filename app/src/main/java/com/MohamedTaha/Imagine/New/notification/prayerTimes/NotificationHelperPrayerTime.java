package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;
import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationHelperPrayerTime {
    public static final String TEXT_NOTIFICATION = "text_notification";
    private static List<ModelMessageNotification> listForSavePrayerTimes;

    public static void sendNotificationForPrayerTime(Context context, Timings prayer_times) {
        Log.d("TAG", "NotificationHelperPrayerTime");
        listForSavePrayerTimes = new ArrayList<>();
        listForSavePrayerTimes.clear();
        PendingIntent pendingIntent = null;
//        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf(prayer_times.getFajr().substring(0, 2)),
//                Integer.valueOf(prayer_times.getFajr().substring(3, 5)), context.getString(R.string.fagr_string));
//        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf(prayer_times.getSunrise().substring(0, 2)),
//                Integer.valueOf(prayer_times.getSunrise().substring(3, 5)), context.getString(R.string.sunrise_string));
//        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf(prayer_times.getDhuhr().substring(0, 2)),
//                Integer.valueOf(prayer_times.getDhuhr().substring(3, 5)), context.getString(R.string.duhr_string));
//        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf(prayer_times.getAsr().substring(0, 2)),
//                Integer.valueOf(prayer_times.getAsr().substring(3, 5)), context.getString(R.string.asr_string));
//        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf(prayer_times.getMaghrib().substring(0, 2)),
//                Integer.valueOf(prayer_times.getMaghrib().substring(3, 5)), context.getString(R.string.magrib_string));
//        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf(prayer_times.getIsha().substring(0, 2)),
//                Integer.valueOf(prayer_times.getIsha().substring(3, 5)), context.getString(R.string.isha_string));

        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf("17"),
                Integer.valueOf("54"), "حان الأن موعد أذان الفجر");
        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf("17"),
                Integer.valueOf("55"), "حان الأن موعد أذان الشروق");
        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf("17"),
                Integer.valueOf("57"), "حان الأن موعد أذان الظهر");
        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf("17"),
                Integer.valueOf("58"), "حان الأن موعد أذان العصر");
        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf("17"),
                Integer.valueOf("59"), "حان الأن موعد أذان المغرب");
        setTimePrayerWithText(listForSavePrayerTimes, Integer.valueOf("18"),
                Integer.valueOf("00"), "حان الأن موعد أذان العشاء");
        Alarm alarm = new Alarm(context);
        alarm.setAlarm(pendingIntent, ServiceForPlayPrayerTimesNotification.class, listForSavePrayerTimes);
        Log.d("TAG", "Fire Alarm " +
                listForSavePrayerTimes.get(0).getTime_payer() + " : " + listForSavePrayerTimes.get(0).getText_notification() + "/n" +
                listForSavePrayerTimes.get(1).getTime_payer() + " : " + listForSavePrayerTimes.get(1).getText_notification() + "/n"
                + listForSavePrayerTimes.get(2).getTime_payer() + " : " + listForSavePrayerTimes.get(2).getText_notification() + "/n"
                + listForSavePrayerTimes.get(3).getTime_payer() + " : " + listForSavePrayerTimes.get(3).getText_notification() + "/n"
                + listForSavePrayerTimes.get(4).getTime_payer() + " : " + listForSavePrayerTimes.get(4).getText_notification() + "/n"
                + listForSavePrayerTimes.get(5).getTime_payer() + " : " + listForSavePrayerTimes.get(5).getText_notification() + "/n"
        );
    }

    private static void setTimePrayerWithText(List<ModelMessageNotification> modelMessageNotificationList, int value_hour, int value_secound, String name_prayer) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, value_hour);
        setTime.set(Calendar.MINUTE, value_secound);
        setTime.set(Calendar.SECOND, 00);
        if (calendar.getTimeInMillis() < (setTime.getTimeInMillis())) {
            modelMessageNotificationList.add(new ModelMessageNotification(setTime.getTimeInMillis(), name_prayer));
        }
    }

    //Enable boot receiver to persist alarms set for notification across device reboots
    public static void enableBootRecieiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootRecevierPrayerTimeEveryDay.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}