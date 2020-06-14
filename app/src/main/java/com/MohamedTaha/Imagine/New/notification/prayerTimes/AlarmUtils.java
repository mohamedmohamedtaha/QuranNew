package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.LIST_TIME__NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.TEXT_NAME_NOTIFICATION;

public class AlarmUtils {
    private static final String sTagAlarms = ":alarms";

    public static void addAlarm(Context context, Intent intent, int notificationId, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }

        saveAlarmId(context, notificationId);
    }
    public void setAlarm(Context context,Class name_class, List<ModelMessageNotification> listForSavePrayerTimes) {

        PendingIntent  pendingIntent = null;
        AlarmManager[] alarmManager = new AlarmManager[listForSavePrayerTimes.size()];

      Intent[]  intent = new Intent[alarmManager.length];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        for (int i = 0; i < alarmManager.length; i++) {
            intent[i] = new Intent(context, name_class);
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

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager[i].setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager[i].setExact(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            } else {
                alarmManager[i].set(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            }
            //    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //      alarmManager[i].setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            // } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //    alarmManager[i].setInexactRepeating(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(),AlarmManager.INTERVAL_DAY, pendingIntent);
            // } else {
            //   alarmManager[i].set(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            // }
            saveAlarmId(context, i);
        }
    }

    public static void cancelAlarm(Context context, Intent intent, int notificationId) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
        removeAlarmId(context, notificationId);
    }
    public void cancelAllAlarm(Context context) {
        if (6>0){
            PendingIntent pendingIntent;
            AlarmManager[]   alarmManager = new AlarmManager[6];
            Intent[] intent = new Intent[6];
            for (int i = 0; i < 6; i++) {
                    alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        pendingIntent = PendingIntent.getForegroundService(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                    } else {
                        pendingIntent = PendingIntent.getService(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                    }
                    if (pendingIntent != null && alarmManager != null){
                        alarmManager[i].cancel(pendingIntent);
                        pendingIntent.cancel();
                        removeAlarmId(context, i);
                    }
                    Log.d("TAG", " cancelAlarm :" + i);
            }}
    }

    public static void cancelAllAlarms(Context context, Intent intent) {
        for (int idAlarm : getAlarmIds(context)) {
            cancelAlarm(context, intent, idAlarm);
        }
    }

    public static boolean hasAlarm(Context context, Intent intent, int notificationId) {
        return PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_NO_CREATE) != null;
    }

    private static void saveAlarmId(Context context, int id) {
        List<Integer> idsAlarms = getAlarmIds(context);
        if (idsAlarms.contains(id)) {
            return;
        }

        idsAlarms.add(id);

        saveIdsInPreferences(context, idsAlarms);
    }

    private static void removeAlarmId(Context context, int id) {
        List<Integer> idsAlarms = getAlarmIds(context);
        for (int i = 0; i < idsAlarms.size(); i++) {
            if (idsAlarms.get(i) == id)
                idsAlarms.remove(i);
        }

        saveIdsInPreferences(context, idsAlarms);
    }

    private static List<Integer> getAlarmIds(Context context) {
        List<Integer> ids = new ArrayList<>();
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            JSONArray jsonArray2 = new JSONArray(prefs.getString(context.getPackageName() + sTagAlarms, "[]"));

            for (int i = 0; i < jsonArray2.length(); i++) {
                ids.add(jsonArray2.getInt(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ids;
    }

    private static void saveIdsInPreferences(Context context, List<Integer> lstIds) {
        JSONArray jsonArray = new JSONArray();
        for (Integer idAlarm : lstIds) {
            jsonArray.put(idAlarm);
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(context.getPackageName() + sTagAlarms, jsonArray.toString());

        editor.apply();
    }
}