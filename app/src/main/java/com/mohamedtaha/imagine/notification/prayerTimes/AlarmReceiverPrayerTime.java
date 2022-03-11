package com.mohamedtaha.imagine.notification.prayerTimes;

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

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.ui.activities.NavigationDrawaberActivity;
import com.mohamedtaha.imagine.helper.util.ConvertTimes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by MANASATT on 04/09/17.
 */
public class AlarmReceiverPrayerTime extends BroadcastReceiver {
    private static final String CHANNEL_ID = "com.MohamedTaha.Imagine.Quran.notification.prayer.times";
    public static final String TEXT_NOTIFICATIONALRARM = "com.MohamedTaha.Imagine.Quran.notification.text.notification.alarm";
    private static final String NOTIFICATION_ID_FOR_PRAYER_TIMES = "notification_id_for_prayer_times";
    public static int num;
    private int notification_id_for_prayer_times;
    ArrayList<ModelMessageNotification> minutes;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "onReceive");
        minutes = new ArrayList<>();
        minutes.clear();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            Type listType = new TypeToken<List<ModelMessageNotification>>() {
            }.getType();
            String st = bundle.getString(TEXT_NOTIFICATIONALRARM);
            minutes = new Gson().fromJson(st, listType);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            for (int i = 0; i < minutes.size(); i++) {
                if (ConvertTimes.convertFromMilliSecondsToTime(calendar.getTimeInMillis())
                        .equals(ConvertTimes.convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()))) {
                    Log.d("TAG", ConvertTimes.convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()) + ":" +
                            minutes.get(i).getText_notification());
                    intent = new Intent(context, NavigationDrawaberActivity.class);
                    intent.putExtra(NOTIFICATION_ID_FOR_PRAYER_TIMES, notification_id_for_prayer_times);
                    //intent.putExtra(SEND_TIME_FOR_SEINDING, num);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    createNotification(context, context.getString(R.string.app_name), minutes.get(i).getText_notification());
                } else {
//                    Log.d("TAG : error: ", convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()) + ":" +
//                            convertFromMilliSecondsToTime(calendar.getTimeInMillis()));
                }
            }
        }
    }

    public static NotificationCompat.Builder createNotification(Context context, CharSequence ticker, CharSequence desribe) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context);
        }
        Intent cancelNotification = new Intent(context, CancelNotificationPrayerTime.class);
   //     cancelNotification.putExtra(SEND_TIME_FOR_SEINDING, num);
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
        builder.addAction(R.drawable.ic_close, context.getString(R.string.close), exitPending);
        builder.setDefaults(Notification.DEFAULT_ALL);//Require VIBREATE permission
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(desribe));
        builder.setAutoCancel(false);
        notificationManager.notify(num, builder.build());
        return builder;
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