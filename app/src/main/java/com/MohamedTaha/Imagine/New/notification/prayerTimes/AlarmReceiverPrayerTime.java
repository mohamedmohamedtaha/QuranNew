package com.MohamedTaha.Imagine.New.notification.prayerTimes;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertFromMilliSecondsToTime;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.TEXT_NOTIFICATION;

/**
 * Created by MANASATT on 04/09/17.
 */

public class AlarmReceiverPrayerTime extends BroadcastReceiver {
    private static final String CHANNEL_ID = "com.MohamedTaha.Imagine.Quran.notification";
    public static final String SEND_TIME_FOR_SEINDING = "time_send";
    private static final String NOTIFICATION_ID_FOR_PRAYER_TIMES = "notification_id_for_prayer_times";
    public static int num;
    private int notification_id_for_prayer_times;
    ArrayList<ModelMessageNotification> minutes;

    @Override
    public void onReceive(Context context, Intent intent) {
        minutes = new ArrayList<>();
        minutes.clear();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            Type listType = new TypeToken<List<ModelMessageNotification>>() {
            }.getType();
            String st = bundle.getString(TEXT_NOTIFICATION);
            minutes = new Gson().fromJson(st, listType);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            for (int i = 0; i < minutes.size(); i++) {
                if (convertFromMilliSecondsToTime(calendar.getTimeInMillis())
                        .equals(convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()))) {
                    Log.d("TT", convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()) + ":" +
                            minutes.get(i).getText_notification());
                    /**This flag is generally used by activies that want to present a launcher style
                     behavior:they give the user  *a list of separete things* that can be done
                     ,which otherwise run completely independently of the acitivity launching them*/
                    intent = new Intent(context, NavigationDrawaberActivity.class);
                    intent.putExtra(NOTIFICATION_ID_FOR_PRAYER_TIMES, notification_id_for_prayer_times);
                    intent.putExtra(SEND_TIME_FOR_SEINDING, num);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    PendingIntent openIntent = PendingIntent.getActivity(context, num, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    //Build notification
                    createNotification(context, openIntent, context.getString(R.string.app_name), minutes.get(i).getText_notification());
                } else {
                }
            }
        }
    }

    public static NotificationCompat.Builder createNotification(Context context, PendingIntent openIntent, CharSequence ticker, CharSequence desribe) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context);
        }
        Intent cancelNotification = new Intent(context, CancelNotificationPrayerTime.class);
        cancelNotification.putExtra(SEND_TIME_FOR_SEINDING, num);

        PendingIntent exitPending = PendingIntent.getBroadcast(context, num, cancelNotification, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap_icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);
        //Create a new Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setLargeIcon(bitmap_icon);
        builder.setTicker(ticker);
        builder.setContentText(desribe);
        //For sound
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);

        builder.setWhen(System.currentTimeMillis());  // the time stamp
        builder.setChannelId(CHANNEL_ID);
        // Make the transport controls visible on the lock screen
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        //Set the notification color
        builder.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.colorPrimaryDark));

        //will make it a Heads Up  Display Style
        builder.addAction(R.drawable.ic_close, context.getString(R.string.notNow), exitPending);
        builder.addAction(R.drawable.ic_reply, context.getString(R.string.readNow), openIntent);
        builder.setContentIntent(openIntent);
        builder.setDefaults(Notification.DEFAULT_ALL);//Require VIBREATE permission
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(desribe));
        builder.setAutoCancel(true);
        notificationManager.notify(num, builder.build());
        Log.d("TT", "Moahmed");

        return builder;
    }

    public static void setMultipleAlarms(Context context) {
        List<Integer> minutes = new ArrayList<>();
        minutes.add(30);
        minutes.add(31);
        minutes.add(32);
        PendingIntent pendingIntent = null;
        AlarmManager[] alarmManager = new AlarmManager[minutes.size()];
        Intent intent[] = new Intent[alarmManager.length];
        for (int i = 0; i < alarmManager.length; i++) {
            intent[i] = new Intent(context, AlarmReceiverPrayerTime.class);
            /*
        Here is very important,when we set one alarm, pending intent id becomes zero
        but if we want set multiple alarms pending intent id has to be unique so i counter
        is enough to be unique for PendingIntent
      */
            pendingIntent = PendingIntent.getBroadcast(context, i, intent[i], 0);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MINUTE, minutes.get(i));
//            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
//                    SystemClock.elapsedRealtime() + setAlarm(repear),
//                    setAlarm(repear), alarmPendingIntent);
            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager[i].set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            createNotification(context, pendingIntent, context.getString(R.string.app_name), "Test for Notification");
            Log.d("TT", "i is : " + i);

        }

    }

    public static void cancelMultipleAlarms(Context context) {
        int size = 3;
        AlarmManager alarmManager[] = new AlarmManager[size];
        Intent intent[] = new Intent[size];
        for (int i = 0; i < size; i++) {
            alarmManager[i] = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            intent[i] = new Intent(context, AlarmReceiverPrayerTime.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, intent[i], 0);
            alarmManager[i].cancel(pendingIntent);
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public static void createChannel(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //The id of the channel
        //for create Channel for notification for Android O
        //The user visible name of the channel
        CharSequence name = "Quran Notification";
        //The user visible description of the channel
        String description = "Quran Notification controls";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID, name, importance);
        //Configure the channel's intial preference
        notificationChannel2.setLightColor(Color.GREEN);
        //Configure the notification channel.
        notificationChannel2.setDescription(description);
        notificationChannel2.setShowBadge(false);
        notificationChannel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(notificationChannel2);
    }


}