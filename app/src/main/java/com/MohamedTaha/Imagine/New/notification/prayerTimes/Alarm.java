package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertFromMilliSecondsToTime;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.TEXT_NOTIFICATION;
public class Alarm {
    private Context context;
    public static final String TIME_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.time.notification";
    public static final String LIST_TIME__NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.list.time.notification";

    public static final String TEXT_NAME_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.text.name.notification";


    public Alarm(Context context) {
        this.context = context;
    }

    public void setAlarm( Class name_class, List<ModelMessageNotification> listForSavePrayerTimes) {
        PendingIntent pendingIntent = null;
        AlarmManager[] alarmManager = new AlarmManager[listForSavePrayerTimes.size()];
        Intent intent[] = new Intent[alarmManager.length];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        for (int i = 0; i < alarmManager.length; i++) {
            intent[i] = new Intent(context, name_class);
            Bundle bundle = new Bundle();
            bundle.putLong(TIME_NOTIFICATION, listForSavePrayerTimes.get(i).getTime_payer());
            bundle.putString(TEXT_NAME_NOTIFICATION, listForSavePrayerTimes.get(i).getText_notification());
            bundle.putString(LIST_TIME__NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));

            Log.d("TAG", " TEXT_NOTIFICATION : " + listForSavePrayerTimes.get(i).getTime_payer() );
            Log.d("TAG", " TEXT_NOTIFICATION : " + listForSavePrayerTimes.get(i).getText_notification() );

            intent[i].putExtras(bundle);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                pendingIntent = PendingIntent.getForegroundService(context, i, intent[i], 0);
            } else {
                pendingIntent = PendingIntent.getService(context, i, intent[i], 0);
            }
            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
          //  if (convertFromMilliSecondsToTime(calendar.getTimeInMillis())
            //        .equals(convertFromMilliSecondsToTime(listForSavePrayerTimes.get(i).getTime_payer()))) {
            //    alarmManager[i].setExact(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
       //     }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager[i].setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            }
            else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager[i].setExact(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            } else {
                alarmManager[i].set(AlarmManager.RTC_WAKEUP, listForSavePrayerTimes.get(i).getTime_payer(), pendingIntent);
            }
        }
    }

    public void cancelAlarm( List<ModelMessageNotification> listForSavePrayerTimes,String name_prayer_time) {
        AlarmManager alarmManager[] = new AlarmManager[listForSavePrayerTimes.size()];
        Intent intent[] = new Intent[listForSavePrayerTimes.size()];
        for (int i = 0; i < listForSavePrayerTimes.size(); i++) {
          //  if (listForSavePrayerTimes.get(i).getText_notification().equals(name_prayer_time)){
                alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            intent[i] = new Intent(context, ServiceForPlayPrayerTimesNotification.class);
            PendingIntent pendingIntent = PendingIntent.getService(context, i, intent[i], 0);
                alarmManager[i].cancel(pendingIntent);
                Log.d("TAG", " cancelAlarm " );
          //  }

        }
    }
}
