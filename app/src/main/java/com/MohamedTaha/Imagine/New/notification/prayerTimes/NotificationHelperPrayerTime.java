package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.azan.Timings;
import com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryDay;
import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesCustomNotification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class NotificationHelperPrayerTime {
    private List<ModelMessageNotification> listForSavePrayerTimes;

    public void sendNotificationForPrayerTime(Context context, Timings prayer_times) {
        Log.d("TAG", "NotificationHelperPrayerTime");
        listForSavePrayerTimes = new ArrayList<>();

        setTimePrayerWithText(Integer.valueOf(prayer_times.getFajr().substring(0, 2)),
                Integer.valueOf(prayer_times.getFajr().substring(3, 5)), context.getString(R.string.fagr_string));
        setTimePrayerWithText(Integer.valueOf(prayer_times.getSunrise().substring(0, 2)),
                Integer.valueOf(prayer_times.getSunrise().substring(3, 5)), context.getString(R.string.sunrise_string));
        setTimePrayerWithText(Integer.valueOf(prayer_times.getDhuhr().substring(0, 2)),
                Integer.valueOf(prayer_times.getDhuhr().substring(3, 5)), context.getString(R.string.duhr_string));
        setTimePrayerWithText(Integer.valueOf(prayer_times.getAsr().substring(0, 2)),
                Integer.valueOf(prayer_times.getAsr().substring(3, 5)), context.getString(R.string.asr_string));
        setTimePrayerWithText(Integer.valueOf(prayer_times.getMaghrib().substring(0, 2)),
                Integer.valueOf(prayer_times.getMaghrib().substring(3, 5)), context.getString(R.string.magrib_string));
        setTimePrayerWithText(Integer.valueOf(prayer_times.getIsha().substring(0, 2)),
                Integer.valueOf(prayer_times.getIsha().substring(3, 5)), context.getString(R.string.isha_string));
        Alarm alarm = new Alarm(context);

      //  alarm.customCancelAlarm(listForSavePrayerTimes);

//        setTimePrayerWithText(Integer.valueOf("19"),
//                Integer.valueOf("26"), context.getString(R.string.fagr_string));
//        setTimePrayerWithText(Integer.valueOf("19"),
//                Integer.valueOf("27"), context.getString(R.string.sunrise_string));
//        setTimePrayerWithText(Integer.valueOf("19"),
//                Integer.valueOf("29"), context.getString(R.string.duhr_string));
//        setTimePrayerWithText(Integer.valueOf("19"),
//                Integer.valueOf("32"), context.getString(R.string.asr_string));
//        setTimePrayerWithText(Integer.valueOf("19"),
//                Integer.valueOf("51"), context.getString(R.string.magrib_string));
//        setTimePrayerWithText(Integer.valueOf("20"),
//                Integer.valueOf("33"), context.getString(R.string.isha_string));

        alarm.setAlarm(ServiceForPlayPrayerTimesCustomNotification.class, listForSavePrayerTimes);

       // alarm.cancelAlarm(listForSavePrayerTimes,null);

//        alarm.customAlarm(Integer.valueOf("21"),Integer.valueOf("02"), context.getString(R.string.fagr_string));
//        alarm.customAlarm(Integer.valueOf("21"),Integer.valueOf("03"), context.getString(R.string.sunrise_string));
//        alarm.customAlarm(Integer.valueOf("21"),Integer.valueOf("04"), context.getString(R.string.duhr_string));
//        alarm.customAlarm(Integer.valueOf("21"),Integer.valueOf("05"), context.getString(R.string.asr_string));
//        alarm.customAlarm(Integer.valueOf("21"),Integer.valueOf("06"), context.getString(R.string.magrib_string));
//        alarm.customAlarm(Integer.valueOf("21"),Integer.valueOf("07"), context.getString(R.string.isha_string));
    }

    private void setTimePrayerWithText(int value_hour, int value_secound, String name_prayer) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, value_hour);
        setTime.set(Calendar.MINUTE, value_secound);
        setTime.set(Calendar.SECOND, 01);
        if (calendar.getTimeInMillis() < (setTime.getTimeInMillis())) {
            listForSavePrayerTimes.add(new ModelMessageNotification(setTime.getTimeInMillis(), name_prayer));
        }
    }
    public  void getPrayerTimesEveryday(Context context) {
        int ALARM_TYPE_ELAPSED = 10;
        AlarmManager alarmManager;
        PendingIntent alarmPendingIntent;
        Intent intent = new Intent(context, GetPrayerTimesEveryDay.class);
        Log.d("TAG", "getPrayerTimesEveryday ");
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, 2);
        //setTime.set(Calendar.MINUTE,30);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                setTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);
    }

    //Enable boot receiver to persist alarms set for notification across device reboots
    public void enableBootRecieiver(Context context) {
        ComponentName receiver = new ComponentName(context, AlarmBootRecevierPrayerTimeEveryDay.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}