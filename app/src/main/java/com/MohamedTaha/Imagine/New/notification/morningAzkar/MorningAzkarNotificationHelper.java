package com.MohamedTaha.Imagine.New.notification.morningAzkar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.AlarmUtils;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.ModelMessageNotification;
import com.MohamedTaha.Imagine.New.receiver.GetAzkarTimesEveryDay;
import com.MohamedTaha.Imagine.New.receiver.bootDevice.MorningAzkarAlarmBootRecevier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class MorningAzkarNotificationHelper {
    private static AlarmManager alarmManager;
    private Context context;
    Calendar setTimeMow = Calendar.getInstance();
    private List<ModelMessageNotification> listForSavePrayerTimes;

    public MorningAzkarNotificationHelper(Context context) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
    }

    public void showNotificationAzkar() {
        listForSavePrayerTimes = new ArrayList<>();
        AlarmUtils alarm = new AlarmUtils(context);
        alarm.cancelAllAlarmForBroadcastAzkar();
        addAzkar(5, 01, 27 ,context.getString(R.string.morning_azkar));
        addAzkar(16, 34, 28,context.getString(R.string.night_azkar));

//        addAzkar(13, 33, 27, context.getString(R.string.morning_azkar));
//        addAzkar(13, 40, 28, context.getString(R.string.night_azkar));

        alarm.setAlarmForBroadcast(listForSavePrayerTimes);
    }

    private void addAzkar(int value_hour, int value_secound, int number_azkar, String name_azkar) {
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, value_hour);
        setTime.set(Calendar.MINUTE, value_secound);
        setTime.set(Calendar.SECOND, 1);
        if (System.currentTimeMillis() < setTime.getTimeInMillis()) {
            listForSavePrayerTimes.add(new ModelMessageNotification(setTime.getTimeInMillis(), name_azkar, number_azkar));
        }
    }

    public void getAzkarTimesEveryday() {
        int ALARM_TYPE_ELAPSED = 20;
        AlarmManager alarmManager;
        PendingIntent alarmPendingIntent;
        Intent intent = new Intent(context, GetAzkarTimesEveryDay.class);
        Log.d("TAG", "getAzkarTimesEveryday ");
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, 1);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);
        }
    }

    public void enableBootRecieiver() {
        ComponentName receiver = new ComponentName(context, MorningAzkarAlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }
}