package com.mohamedtaha.imagine.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.mohamedtaha.imagine.notification.morningAzkar.MorningAzkarAlarmReceiver;
import com.mohamedtaha.imagine.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AlarmUtils {
    public static final String TIME_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.time.notification";
    public static final String LIST_TIME__NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.list.time.notification";
    public static final String TEXT_NAME_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.text.name.notification";
    private static final String TAG_ALARM = "com.MohamedTaha.Imagine.Quran.notification.alarms";
    private static final String TAG_ALARM_FOR_BROADCAST = "com.MohamedTaha.Imagine.New.notification.prayerTimes.broadcast";

    private Context context;

    public AlarmUtils(Context context) {
        this.context = context;
    }



    public void addAlarm(Intent intent, int notificationId, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
        saveAlarmId( notificationId);
    }

    public void setAlarm(List<ModelMessageNotification> listForSavePrayerTimes) {
        PendingIntent pendingIntent = null;
        AlarmManager[] alarmManager = new AlarmManager[listForSavePrayerTimes.size()];
        Intent[] intent = new Intent[alarmManager.length];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        for (int i = 0; i < alarmManager.length; i++) {
            intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
            intent[i].putExtra(TIME_NOTIFICATION, listForSavePrayerTimes.get(i).getTime_payer());
            intent[i].putExtra(TEXT_NAME_NOTIFICATION, listForSavePrayerTimes.get(i).getText_notification());
            intent[i].putExtra(LIST_TIME__NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));
            Log.d("TAG", " TEXT_NOTIFICATION : " + listForSavePrayerTimes.get(i).getTime_payer()
                    + " : " + " TEXT_NOTIFICATION : " + listForSavePrayerTimes.get(i).getText_notification());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                pendingIntent = PendingIntent.getForegroundService(context, i, intent[i], 0);
            } else {
                pendingIntent = PendingIntent.getService(context, i, intent[i], 0);
            }
      //      pendingIntent = PendingIntent.getBroadcast(context, i, intent[i], 0);

            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager[i].setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager[i].setExact(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            } else {
                alarmManager[i].set(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            }
            saveAlarmId(i);
        }
    }

    public void setAlarmForBroadcast( List<ModelMessageNotification> listForSavePrayerTimes) {
        PendingIntent pendingIntent = null;
        AlarmManager[] alarmManager = new AlarmManager[listForSavePrayerTimes.size()];
        Intent[] intent = new Intent[alarmManager.length];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        for (int i = 0; i < alarmManager.length; i++) {
            intent[i] = new Intent(context, MorningAzkarAlarmReceiver.class);
            intent[i].putExtra(MorningAzkarAlarmReceiver.NOTIFICATION_ID_NUMBER_AZKAR, listForSavePrayerTimes.get(i).getNumber_azkar());
            intent[i].putExtra(MorningAzkarAlarmReceiver.NOTIFICATION_ID_TEXT_AZKAR, listForSavePrayerTimes.get(i).getText_notification());
            intent[i].putExtra(MorningAzkarAlarmReceiver.NOTIFICATION_ID_TIME_AZKAR, listForSavePrayerTimes.get(i).getTime_payer());
            intent[i].putExtra(MorningAzkarAlarmReceiver.NOTIFICATION_ID_LIST_AZKAR, new Gson().toJson(listForSavePrayerTimes));
            Log.d("TAG", " TEXT_NOTIFICATION : " + listForSavePrayerTimes.get(i).getTime_payer()
                    + " : " + " TEXT_NOTIFICATION : " + listForSavePrayerTimes.get(i).getText_notification());
            pendingIntent = PendingIntent.getBroadcast(context, i, intent[i], 0);
            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager[i].setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager[i].setExact(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            } else {
                alarmManager[i].set(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            }
            saveAlarmIdForBroadcast(i);
        }
    }

    public void cancelAlarm( Intent intent, int notificationId) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
        removeAlarmId(notificationId);
    }

    public void cancelAllAlarm() {
        if (6 > 0) {
            PendingIntent pendingIntent;
            AlarmManager[] alarmManager = new AlarmManager[6];
            Intent[] intent = new Intent[alarmManager.length];
            for (int i = 0; i < 6; i++) {
                alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    pendingIntent = PendingIntent.getForegroundService(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                } else {
                    pendingIntent = PendingIntent.getService(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                }
                if (pendingIntent != null && alarmManager != null) {
                    alarmManager[i].cancel(pendingIntent);
                    pendingIntent.cancel();
                    removeAlarmId(i);
                }
                Log.d("TAG", " cancelAlarm :" + i);
            }
        }
    }

    public void cancelAllAlarmForBroadcastAzkar() {
        if (2 > 0) {
            PendingIntent pendingIntent;
            AlarmManager[] alarmManager = new AlarmManager[2];
            Intent[] intent = new Intent[alarmManager.length];
            for (int i = 0; i < 2; i++) {
                alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent[i] = new Intent(context, MorningAzkarAlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                if (pendingIntent != null && alarmManager != null) {
                    alarmManager[i].cancel(pendingIntent);
                    pendingIntent.cancel();
                    removeAlarmIdForBroadcastAzkar(i);
                }
                Log.d("TAG", " cancelAlarm :" + i);
            }
        }
    }
    public void cancelCustomAlarmForBroadcastAzkar(List<ModelMessageNotification> listForSavePrayerTimes,String name_azkar) {
        if (listForSavePrayerTimes.size() > 0) {
            PendingIntent pendingIntent;
            AlarmManager[] alarmManager = new AlarmManager[listForSavePrayerTimes.size()];
            Intent[] intent = new Intent[alarmManager.length];
            for (int i = 0; i < listForSavePrayerTimes.size(); i++) {
                if (listForSavePrayerTimes.get(i).getText_notification().equals(name_azkar)){
                    alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
                pendingIntent = PendingIntent.getBroadcast(context, i, intent[i], PendingIntent.FLAG_NO_CREATE);
                if (pendingIntent != null && alarmManager != null) {
                    alarmManager[i].cancel(pendingIntent);
                    pendingIntent.cancel();
                    removeAlarmIdForBroadcastAzkar(i);
                }
                Log.d("TAG", " cancelAlarm :" + i);
            }}
        }
    }

    public void cancelAllAlarms( Intent intent) {
        for (int idAlarm : getAlarmIds()) {
            cancelAlarm(intent, idAlarm);
        }
    }

    public boolean hasAlarm(Intent intent, int notificationId) {
        return PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_NO_CREATE) != null;
    }

    private  void saveAlarmId( int id) {
        List<Integer> idsAlarms = getAlarmIds();
        if (idsAlarms.contains(id)) {
            return;
        }

        idsAlarms.add(id);

        saveIdsInPreferences(idsAlarms);
    }

    private  void saveAlarmIdForBroadcast( int id) {
        List<Integer> idsAlarms = getAlarmIdsForBroadcast();
        if (idsAlarms.contains(id)) {
            return;
        }

        idsAlarms.add(id);

        saveIdsInPreferencesForBroadcast(idsAlarms);
    }

    private void removeAlarmId( int id) {
        List<Integer> idsAlarms = getAlarmIds();
        for (int i = 0; i < idsAlarms.size(); i++) {
            if (idsAlarms.get(i) == id)
                idsAlarms.remove(i);
        }

        saveIdsInPreferences(idsAlarms);
    }

    private  void removeAlarmIdForBroadcastAzkar(int id) {
        List<Integer> idsAlarms = getAlarmIdsForBroadcast();
        for (int i = 0; i < idsAlarms.size(); i++) {
            if (idsAlarms.get(i) == id)
                idsAlarms.remove(i);
        }

        saveIdsInPreferencesForBroadcast(idsAlarms);
    }

    private List<Integer> getAlarmIds() {
        List<Integer> ids = new ArrayList<>();
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            JSONArray jsonArray2 = new JSONArray(prefs.getString(context.getPackageName() + TAG_ALARM, "[]"));
            for (int i = 0; i < jsonArray2.length(); i++) {
                ids.add(jsonArray2.getInt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }


    private  void saveIdsInPreferences(List<Integer> lstIds) {
        JSONArray jsonArray = new JSONArray();
        for (Integer idAlarm : lstIds) {
            jsonArray.put(idAlarm);
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(context.getPackageName() + TAG_ALARM, jsonArray.toString());

        editor.apply();
    }

    private  List<Integer> getAlarmIdsForBroadcast() {
        List<Integer> ids = new ArrayList<>();
        try {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            JSONArray jsonArray2 = new JSONArray(prefs.getString(context.getPackageName() + TAG_ALARM_FOR_BROADCAST, "[]"));
            for (int i = 0; i < jsonArray2.length(); i++) {
                ids.add(jsonArray2.getInt(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    private void saveIdsInPreferencesForBroadcast( List<Integer> lstIds) {
        JSONArray jsonArray = new JSONArray();
        for (Integer idAlarm : lstIds) {
            jsonArray.put(idAlarm);
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(context.getPackageName() + TAG_ALARM_FOR_BROADCAST, jsonArray.toString());
        editor.apply();
    }
}