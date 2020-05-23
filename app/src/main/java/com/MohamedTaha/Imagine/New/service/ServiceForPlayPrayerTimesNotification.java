package com.MohamedTaha.Imagine.New.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.CancelNotificationPrayerTime;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.ModelMessageNotification;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertFromMilliSecondsToTime;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.TEXT_NOTIFICATION;

public class ServiceForPlayPrayerTimesNotification extends Service implements MediaPlayer.OnCompletionListener
        , MediaPlayer.OnErrorListener {
    public static final String SEND_TIME_FOR_SEINDING = "time_send";
    private static final String CHANNEL_ID = "com.MohamedTaha.Imagine.Quran.notification.prayer.times";
    private static final String NOTIFICATION_ID_FOR_PRAYER_TIMES = "notification_id_for_prayer_times";
    public static int num;
    private static final int NOTIFICATION_ID_SERVICE = 11;

    private int notification_id_for_prayer_times;
    ArrayList<ModelMessageNotification> minutes;
    private MediaPlayer mediaPlayer = null;
    private NotificationCompat.Builder builder;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d("TAG", "onTaskRemoved");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand");
        builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);

        //for start service when don't find the internet and don't throw Exception.
        //you only need to create  the cnannel on API 26+ devices
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//       //     startForeground(NOTIFICATION_ID_SERVICE, builder.build());
//            createChannel();
//        }

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
                if (convertFromMilliSecondsToTime(calendar.getTimeInMillis()).equals(convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()))) {
                    Log.d("TT", convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()) + ":" +
                            minutes.get(i).getText_notification());
                    initMediaPlayer(i);
                    intent = new Intent(this, NavigationDrawaberActivity.class);
                    intent.putExtra(NOTIFICATION_ID_FOR_PRAYER_TIMES, notification_id_for_prayer_times);
                    intent.putExtra(SEND_TIME_FOR_SEINDING, num);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    createNotification(this, getString(R.string.app_name), minutes.get(i).getText_notification());
                    Log.d("TAG : Right : ", convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()) + ":" +
                            convertFromMilliSecondsToTime(calendar.getTimeInMillis()));
                    if (!mediaPlayer.isPlaying() && !minutes.get(i).getText_notification().equals(getString(R.string.sunrise_string))) {
                        mediaPlayer.start();
                    }
                } else {
                    Log.d("TAG : error: ", convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()) + ":" +
                            convertFromMilliSecondsToTime(calendar.getTimeInMillis()));
                    startForeground(NOTIFICATION_ID_SERVICE, builder.build());
//                    if (!mediaPlayer.isPlaying() && !minutes.get(i).getText_notification().equals(getString(R.string.sunrise_string))){
//                        stopSelf();
//
//                    }
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        Log.d("TAG", "onDestroy");
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        releaseMediaPlayer();
        Log.d("TAG", "onCompletion");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        releaseMediaPlayer();
        Log.d("TAG", "onError");
        return false;
    }


    private void releaseMediaPlayer() {
        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void initMediaPlayer(int postion) {
        if (mediaPlayer == null) {
            if (!minutes.get(postion).getText_notification().equals(getString(R.string.fagr_string))){
                mediaPlayer = MediaPlayer.create(this, R.raw.azan_haram);
                Log.d("TAG", "azan_haram  " + minutes.get(postion).getText_notification());
            }else{
                mediaPlayer = MediaPlayer.create(this, R.raw.azan_haram_fajr);
                Log.d("TAG", "azan_haram_fajr   " + minutes.get(postion).getText_notification());

            }
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
        }
    }

    private void createNotification(Context context, CharSequence ticker, CharSequence desribe) {
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
        Intent cancelNotification = new Intent(context, CancelNotificationPrayerTime.class);
        cancelNotification.putExtra(SEND_TIME_FOR_SEINDING, num);
        PendingIntent exitPending = PendingIntent.getBroadcast(context, num, cancelNotification, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bitmap_icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);
        //Create a new Notification
        //builder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID);
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
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        // notificationManager.notify(num, builder.build());
        //Display the notification and place the service in the foreground
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID_SERVICE, builder.build());
        } else {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID_SERVICE, builder.build());
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public void createChannel() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //The id of the channel
        //for create Channel for notification for Android O
        //The user visible name of the channel
        CharSequence name = "Prayer Times Notification";
        //The user visible description of the channel
        String description = "Prayer Times Notification controls";
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
