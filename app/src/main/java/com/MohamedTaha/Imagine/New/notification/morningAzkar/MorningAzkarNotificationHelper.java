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
import com.MohamedTaha.Imagine.New.receiver.GetPrayerTimesEveryDay;
import com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;
import static com.MohamedTaha.Imagine.New.notification.morningAzkar.MorningAzkarAlarmReceiver.NOTIFICATION_ID_AZKAR;

public class MorningAzkarNotificationHelper {
    private static AlarmManager alarmManager;
    private static PendingIntent alarmPendingIntent;
    private Context context;
    Calendar setTimeMow = Calendar.getInstance();
    private List<ModelMessageNotification> listForSavePrayerTimes;

    public MorningAzkarNotificationHelper(Context context) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
    }

    public void showNotificationAzkar() {
        listForSavePrayerTimes = new ArrayList<>();
        AlarmUtils alarm = new AlarmUtils();
        alarm.cancelAllAlarmForBroadcastAzkar(context);
        addAzkar(5, 33, 27 ,context.getString(R.string.morning_azkar));
        addAzkar(16, 34, 28,context.getString(R.string.night_azkar));

//        addAzkar(23, 47, 27 ,context.getString(R.string.morning_azkar));
//        addAzkar(23, 48, 28,context.getString(R.string.night_azkar));

        alarm.setAlarmForBroadcast(context, listForSavePrayerTimes);


//        Intent intent = new Intent(context, MorningAzkarAlarmReceiver.class);
//        intent.putExtra(NOTIFICATION_ID_MORNING_AZKAR, 27);
//        alarmPendingIntent = PendingIntent.getBroadcast(context, 99, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        Calendar setTimeMorning = Calendar.getInstance();
//        setTimeMorning.setTimeInMillis(System.currentTimeMillis());
//        setTimeMorning.set(Calendar.HOUR_OF_DAY, 5);
//        setTimeMorning.set(Calendar.MINUTE, 33);
////        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
////                SystemClock.elapsedRealtime() + setTime.getTimeInMillis(),
////                AlarmManager.INTERVAL_HALF_DAY, alarmPendingIntent);
//
//        if (setTimeMow.getTimeInMillis() < setTimeMorning.getTimeInMillis()) {
//            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setTimeMorning.getTimeInMillis(),
//                    AlarmManager.INTERVAL_DAY, alarmPendingIntent);
//        }
    }

    private void addAzkar(int value_hour, int value_secound,int number_azkar, String name_azkar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, value_hour);
        setTime.set(Calendar.MINUTE, value_secound);
        setTime.set(Calendar.SECOND, 1);
        if (calendar.getTimeInMillis() < (setTime.getTimeInMillis())) {
            listForSavePrayerTimes.add(new ModelMessageNotification(setTime.getTimeInMillis(), name_azkar,number_azkar));
        }
    }
    public void getAzkarTimesEveryday(Context context) {
        int ALARM_TYPE_ELAPSED = 10;
        AlarmManager alarmManager;
        PendingIntent alarmPendingIntent;
        Intent intent = new Intent(context, GetAzkarTimesEveryDay.class);
        Log.d("TAG", "getAzkarTimesEveryday ");
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar setTime = Calendar.getInstance();
        setTime.setTimeInMillis(System.currentTimeMillis());
        setTime.set(Calendar.HOUR_OF_DAY, 2);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, setTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmPendingIntent);
        }
    }

    public void nightAzkar() {
        Intent intent = new Intent(context, MorningAzkarAlarmReceiver.class);
        intent.putExtra(NOTIFICATION_ID_AZKAR, 28);
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