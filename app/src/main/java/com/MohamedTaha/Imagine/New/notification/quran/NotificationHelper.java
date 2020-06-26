package com.MohamedTaha.Imagine.New.notification.quran;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import androidx.preference.PreferenceManager;
import android.util.Log;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.receiver.bootDevice.ReadAyatAlarmBootRecevier;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by MANASATT on 04/09/17.
 */

public class NotificationHelper {
    public static int ALARM_TYPE_ELAPSED = 101;
    private static AlarmManager alarmManager;
    private static PendingIntent alarmPendingIntent;
    private Context context ;

    public NotificationHelper(Context context) {
        this.context = context;
    }

    public  void sendNotificationEveryHalfDay() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //getString Retrieve a String value from the Preference
        String repear = sharedPreferences.getString(context.getString(R.string.settings_Notification_key),
                context.getString(R.string.settings_Notification_default));
        //Setting intent to class where notification will be handled
       Intent intent = new Intent(context, AlarmReceiver.class);
      //  Intent intent = new Intent(context, ServiceForNotificationImage.class);
        Log.d("TAG", "ServiceForNotificationImage ");
        //Setting pending intent to respond to broadcast sent by AlarmManager every day at 8am
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
             alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + setAlarm(repear),
                setAlarm(repear), alarmPendingIntent);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
//                AlarmManager.RTC_WAKEUP,
//                AlarmManager.RTC_WAKEUP, alarmPendingIntent);
    }

    private static Long setAlarm(String key) {
        Long alarm = 0L ;
        switch (key) {
            case "AlarmManager.INTERVAL_FIFTEEN_MINUTES":
                alarm =  AlarmManager.INTERVAL_FIFTEEN_MINUTES;
            break;
            case "AlarmManager.INTERVAL_HALF_HOUR":
                alarm =  AlarmManager.INTERVAL_HALF_HOUR;
            break;
            case "AlarmManager.INTERVAL_HOUR":
                alarm =  AlarmManager.INTERVAL_HOUR;
            break;
            case "AlarmManager.INTERVAL_HALF_DAY":
                alarm =  AlarmManager.INTERVAL_HALF_DAY;
            break;
            case "AlarmManager.INTERVAL_DAY":
                alarm =  AlarmManager.INTERVAL_DAY;
            break;
            default:
        }
        return alarm;
    }

    //Enable boot receiver to persist alarms set for notification across device reboots
    public void enableBootRecieiver() {
        ComponentName receiver = new ComponentName(context, ReadAyatAlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}