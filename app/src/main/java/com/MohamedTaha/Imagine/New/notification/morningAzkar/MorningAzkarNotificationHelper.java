package com.MohamedTaha.Imagine.New.notification.morningAzkar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static com.MohamedTaha.Imagine.New.notification.morningAzkar.MorningAzkarAlarmReceiver.NOTIFICATION_ID_MORNING_AZKAR;

public class MorningAzkarNotificationHelper {
    private static AlarmManager alarmManager;
    private static PendingIntent alarmPendingIntent;
    private Context context;
    Calendar setTimeMow = Calendar.getInstance();

    public MorningAzkarNotificationHelper(Context context) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
    }

    public void morningAzkar() {
        Intent intent = new Intent(context, MorningAzkarAlarmReceiver.class);
        intent.putExtra(NOTIFICATION_ID_MORNING_AZKAR, 27);
        alarmPendingIntent = PendingIntent.getBroadcast(context, 99, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTimeMorning = Calendar.getInstance();
        setTimeMorning.setTimeInMillis(System.currentTimeMillis());
        setTimeMorning.set(Calendar.HOUR_OF_DAY, 5);
        setTimeMorning.set(Calendar.MINUTE, 33);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime() + setTime.getTimeInMillis(),
//                AlarmManager.INTERVAL_HALF_DAY, alarmPendingIntent);
        if (setTimeMow.getTimeInMillis() < setTimeMorning.getTimeInMillis()) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setTimeMorning.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, alarmPendingIntent);
        }
    }

    public void nightAzkar() {
        Intent intent = new Intent(context, MorningAzkarAlarmReceiver.class);
        intent.putExtra(NOTIFICATION_ID_MORNING_AZKAR, 28);
        alarmPendingIntent = PendingIntent.getBroadcast(context, 88, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTimeNight = Calendar.getInstance();
        setTimeNight.setTimeInMillis(System.currentTimeMillis());
        setTimeNight.set(Calendar.HOUR_OF_DAY, 16);
        setTimeNight.set(Calendar.MINUTE, 34);
        if (setTimeMow.getTimeInMillis() < setTimeNight.getTimeInMillis()) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setTimeNight.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, alarmPendingIntent);
        }
    }
    
    public void enableBootRecieiver(Context context) {
        ComponentName receiver = new ComponentName(context, MorningAzkarAlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}