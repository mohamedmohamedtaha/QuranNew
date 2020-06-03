package com.MohamedTaha.Imagine.New.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.media.session.MediaButtonReceiver;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.CancelNotificationPrayerTime;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.CancelNotificationWithSwipe;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.ModelMessageNotification;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.mediaPlayer.AudioFocusChange;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.mediaPlayer.MediaPlayerListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.LIST_TIME__NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.TEXT_NAME_NOTIFICATION;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.Alarm.TIME_NOTIFICATION;

public class ServiceForPlayPrayerTimesNotification extends Service implements MediaPlayer.OnCompletionListener
        , MediaPlayer.OnErrorListener, MediaPlayerListener {
    public static final String SEND_TIME_FOR_SEINDING = "time_send";
    public static final String SEND_TIME_FOR_SWIPE_NOTIFICATION = "time_send_for_swipe_notification";
    public static final String ACTION_STOP_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.ACTION_STOP";
    private static final String CHANNEL_ID = "com.MohamedTaha.Imagine.Quran.notification.prayer.times";
    private static final String MEDIA_SESIION_TAG = "com.MohamedTaha.Imagine.Quran.notification.AudioPlayer";

    public static int num;
    private static final int NOTIFICATION_ID_SERVICE = 11;
    private MediaPlayer mediaPlayer = null;
    private NotificationCompat.Builder builder;
    private AudioFocusChange audioFocusChange;
    private boolean isPlaying = false;
    private MediaControllerCompat.TransportControls transportControlsCompat;
    private MediaSessionCompat mediaSession;
    private MediaSessionManager mediaSessionManager;
    private Bundle bundle;
    private Long prayer_time = 0L;
    private String name_prayer_time = null;
    private List<ModelMessageNotification> listForSavePrayerTimes = null;
    private CancelNotificationPrayerTime cancelNotificationPrayerTime;

    private void initMediaSession() throws RemoteException {
        if (mediaSessionManager != null) return; //mediaSessionManager exists
        mediaSessionManager = (MediaSessionManager) getSystemService(Context.MEDIA_SESSION_SERVICE);
        //Create a new MediaSession
        mediaSession = new MediaSessionCompat(getApplicationContext(), MEDIA_SESIION_TAG);
        //Get MediaSessions transport controls
        transportControlsCompat = mediaSession.getController().getTransportControls();
        //set MediaSession - > ready to receive media commands
        mediaSession.setActive(true);
        //indicate that the MediaSession handles transport control commands
        //through its MediaSessionCompat.Callback.
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand");
        registerCloseService();
        builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        audioFocusChange = new AudioFocusChange(this, this);
        //for start service when don't find the internet and don't throw Exception.
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelForNotifiction();
        }
        if (mediaSessionManager == null) {
            Log.d("TAG", "mediaSessionManager is " + mediaSessionManager);

            try {
                initMediaSession();
                Log.d("TAG", "initMediaSession is ");

            } catch (RemoteException e) {
                e.printStackTrace();
                stopSelf();
                Log.d("TAG", "stopSelf " + e.getMessage());

            }
        }
        num = (int) System.currentTimeMillis();
        bundle = intent.getExtras();
        handleIncomingActions(intent);

        if (bundle != null) {
            listForSavePrayerTimes = new ArrayList<>();
            prayer_time = bundle.getLong(TIME_NOTIFICATION);
            name_prayer_time = bundle.getString(TEXT_NAME_NOTIFICATION);
            Type listType = new TypeToken<List<ModelMessageNotification>>() {
            }.getType();
            String st = bundle.getString(LIST_TIME__NOTIFICATION);
            listForSavePrayerTimes = new Gson().fromJson(st, listType);
            Log.d("TAG", "prayer_time is : " + prayer_time + "name_prayer_time is : " + name_prayer_time);
            initMediaPlayer(name_prayer_time);
            createNotification(name_prayer_time);
            if (!mediaPlayer.isPlaying()) {
                if (name_prayer_time != null && !name_prayer_time.equals(getString(R.string.sunrise_string))) {
                    Log.d("TAG", "mediaPlayer is : ");
                    mediaPlayer.start();
                    isPlaying = true;
                }
            } else {
                Log.d("TAG", "NOTIFICATION_ID_SERVICE One ");
//                startForeground(NOTIFICATION_ID_SERVICE, builder.build());
                //     stopForeground(true);

                //     stopSelf();
            }
        } else {
            //stopForeground(true);

            Log.d("TAG", "NOTIFICATION_ID_SERVICE two ");
              startForeground(NOTIFICATION_ID_SERVICE, builder.build());
             stopSelf();
        }

        //return START_NOT_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        audioFocusChange.onDestroy();
        if (cancelNotificationPrayerTime != null) {
            unregisterReceiver(cancelNotificationPrayerTime);
        }
        Log.d("TAG", "onDestroy");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        releaseMediaPlayer();
        audioFocusChange.onDestroy();
        Log.d("TAG", "onCompletion");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        releaseMediaPlayer();
        audioFocusChange.onDestroy();
        Log.d("TAG", "onError");
        return false;
    }

    private void releaseMediaPlayer() {
        Log.d("TAG", "releaseMediaPlayer");

        if (mediaPlayer == null) return;
        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
        }
    }

    private void initMediaPlayer(String name_prayer_time) {
        //    releaseMediaPlayer();
        Log.d("TAG", "initMediaPlayer");
        if (mediaPlayer == null) {
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);
            if (name_prayer_time.equals(getString(R.string.fagr_string))) {
                mediaPlayer = MediaPlayer.create(this, R.raw.azan_haram_fajr);
                Log.d("TAG", "azan_haram_fajr");
            } else if (name_prayer_time.equals(getString(R.string.sunrise_string))) {
               // mediaPlayer.stop();
          //      mediaSessionManager = null;
            } else {
                mediaPlayer = MediaPlayer.create(this, R.raw.azan_haram);
                Log.d("TAG", "azan_haram");

            }
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);

        } else {
            Log.d("TAG", "initMediaPlayer not null");
            mediaPlayer.reset();

        }
    }

    private void createNotification(CharSequence desribe) {
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannelForNotifiction();
        }
        Intent cancelNotification = new Intent(this, CancelNotificationPrayerTime.class);
        cancelNotification.setAction(ACTION_STOP_NOTIFICATION);
        cancelNotification.putExtra(SEND_TIME_FOR_SEINDING, num);
        //  cancelNotification.putExtra(TEXT_NAME_NOTIFICATION,new Gson().toJson(prayer_time));
        Intent broadcastIntent = new Intent(ACTION_STOP_NOTIFICATION);
        sendBroadcast(broadcastIntent);

        cancelNotification.putExtra(TEXT_NAME_NOTIFICATION, name_prayer_time);
        cancelNotification.putExtra(LIST_TIME__NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));
        PendingIntent exitPending = PendingIntent.getBroadcast(this, num, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //  PendingIntent exitPending = PendingIntent.getBroadcast(context, num, cancelNotification, PendingIntent.FLAG_CANCEL_CURRENT);
        Bitmap bitmap_icon = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);

        builder.setWhen(System.currentTimeMillis())  // the time stamp
                //Set notification content information
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(bitmap_icon)
                // .setContentTitle(getString(R.string.app_name))
                //  .setTicker(getString(R.string.app_name))
                .setContentText(desribe)
                .setAutoCancel(false)
                .setOngoing(true)// Cant cancel your notification (except NotificationManger.cancel();
                //Enable launching the player by clicking the notification
                //Stop the service when the notification is swiped away
                // .setDeleteIntent(exitPending)
                .setChannelId(CHANNEL_ID)
                // Make the transport controls visible on the lock screen
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                //Set the notification color
                .setColor(ContextCompat.getColor(this, R.color.colorOrange))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.ic_close, "close", playbackAction(0))
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                                //      .setShowActionsInCompactView(0)
                                .setShowCancelButton(true)
                        .setCancelButtonIntent(
                                MediaButtonReceiver.buildMediaButtonPendingIntent(
                                        this, PlaybackStateCompat.ACTION_STOP)))
                .setDeleteIntent(MediaButtonReceiver.buildMediaButtonPendingIntent(this,
                        PlaybackStateCompat.ACTION_STOP)
                );
        // builder.addAction(R.drawable.ic_close, context.getString(R.string.close), exitPending);


//        builder.setSmallIcon(R.drawable.ic_notification);
//        builder.setLargeIcon(bitmap_icon);
//        builder.setContentTitle(context.getString(R.string.app_name));
//        builder.setTicker(ticker);
//        builder.setContentText(desribe);
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(alarmSound);
//        builder.setWhen(System.currentTimeMillis());  // the time stamp
//        builder.setChannelId(CHANNEL_ID);
//        // Make the transport controls visible on the lock screen
//        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
//        builder.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.colorPrimaryDark));
//        builder.addAction(R.drawable.ic_close, context.getString(R.string.close), exitPending);
//        builder.setDefaults(Notification.DEFAULT_ALL);//Require VIBREATE permission
//        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(desribe));
//       builder.setAutoCancel(false);
        //  builder.setDeleteIntent(exitPending);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID_SERVICE, builder.build());
        } else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID_SERVICE, builder.build());
        }
    }

    private void registerCloseService() {
        IntentFilter filter = new IntentFilter(ACTION_STOP_NOTIFICATION);
        registerReceiver(cancelNotificationPrayerTime, filter);
    }


    private PendingIntent playbackAction(int actionNumber) {
        Intent playbackAction;
        playbackAction = new Intent(this, ServiceForPlayPrayerTimesNotification.class);
        playbackAction.setAction(ACTION_STOP_NOTIFICATION);
        playbackAction.putExtra(SEND_TIME_FOR_SEINDING, num);
        playbackAction.putExtra(TIME_NOTIFICATION, prayer_time);
        Log.d("TAG", "prayer_time is " + prayer_time);
        playbackAction.putExtra(TEXT_NAME_NOTIFICATION, name_prayer_time);
        playbackAction.putExtra(LIST_TIME__NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));
        switch (actionNumber) {
            case 0:
                Log.d("TAG", "actionNumber is " + actionNumber);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    return PendingIntent.getForegroundService(this, actionNumber, playbackAction, 0);
                } else {
                    return PendingIntent.getService(this, actionNumber, playbackAction, 0);
                }
            default:
                break;
        }
        return null;
    }

    private void handleIncomingActions(Intent playbackAction) {
        int send_time;
        if (playbackAction == null || playbackAction.getAction() == null) return;
        String actionString = playbackAction.getAction();
        if (actionString.equalsIgnoreCase(ACTION_STOP_NOTIFICATION)) {
            transportControlsCompat.stop();
//            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            alarm.cancelAlarm(listForSavePrayerTimes, name_prayer_time);
//            send_time = playbackAction.getIntExtra(SEND_TIME_FOR_SEINDING, -1);
//            Log.d("TAG", " CancelNotificationPrayerTime : " + listForSavePrayerTimes.size() + " : " + name_prayer_time);
//            manager.cancel(send_time);

        }
    }

    private PendingIntent getDeleteIntent() {
        Intent cancelNotificationWithSwipe = new Intent(this, CancelNotificationWithSwipe.class);
        cancelNotificationWithSwipe.setAction(SEND_TIME_FOR_SWIPE_NOTIFICATION);
        PendingIntent exitPending = PendingIntent.getBroadcast(this, 0, cancelNotificationWithSwipe, PendingIntent.FLAG_CANCEL_CURRENT);
        return exitPending;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public void createChannelForNotifiction() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence name = "Prayer Times Notification";
        String description = "Prayer Times Notification controls";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID, name, importance);
        notificationChannel2.setLightColor(Color.GREEN);
        notificationChannel2.setDescription(description);
        notificationChannel2.setShowBadge(false);
        notificationChannel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(notificationChannel2);
    }

    @Override
    public void playMedia() {

//        if (mediaPlayer == null) return;
//        if (mediaPlayer != null && isPlaying) {
//            if (!mediaPlayer.isPlaying()) {
//                mediaPlayer.start();
//            }
//            mediaPlayer.setVolume(1.0f, 1.0f);
//        }

    }

    @Override
    public void stopMedia() {
        Log.d("TAG", "stopMedia");
        releaseMediaPlayer();
        audioFocusChange.onDestroy();
    }

    @Override
    public void slowVolume() {
        if (mediaPlayer.isPlaying() && isPlaying) mediaPlayer.setVolume(0.1f, 0.1f);
    }

    @Override
    public void stopService() {
        Log.d("TAG", "stopService");
        stopSelf();
    }
}