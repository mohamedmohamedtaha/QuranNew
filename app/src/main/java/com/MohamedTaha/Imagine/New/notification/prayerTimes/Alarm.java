package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.TEXT_NOTIFICATION;

public class Alarm {
    private Context context;

    public Alarm(Context context) {
        this.context = context;
    }

    public void setAlarm(PendingIntent pendingIntent, Calendar setTime, Class name_class,List<ModelMessageNotification> listForSavePrayerTimes) {
        AlarmManager[] alarmManager = new AlarmManager[listForSavePrayerTimes.size()];
        Intent intent[] = new Intent[alarmManager.length];
        for (int i = 0; i < alarmManager.length; i++) {
            intent[i] = new Intent(context, name_class);
            Bundle bundle = new Bundle();
            bundle.putString(TEXT_NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));
            intent[i].putExtras(bundle);
            /* Here is very important,when we set one alarm, pending intent id becomes zero
            but if we want set multiple alarms pending intent id has to be unique so i counter
            is enough to be unique for PendingIntent    */
            pendingIntent = PendingIntent.getService(context, i, intent[i], 0);
            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager[i].setExact(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
        }
    }

    public void cancelAlarm(Class name_class,List<ModelMessageNotification> listForSavePrayerTimes) {
        AlarmManager alarmManager[] = new AlarmManager[listForSavePrayerTimes.size()];
        Intent intent[] = new Intent[listForSavePrayerTimes.size()];
        for (int i = 0; i < listForSavePrayerTimes.size(); i++) {
            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            intent[i] = new Intent(context, name_class);
            PendingIntent pendingIntent = PendingIntent.getService(context, i, intent[i], 0);
            alarmManager[i].cancel(pendingIntent);
        }
    }
}
