package com.MohamedTaha.Imagine.New.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.CancelNotificationPrayerTime;
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
    public static final String ACTION_STOP_NOTIFICATION = "com.MohamedTaha.Imagine.Quran.notification.ACTION_STOP";
    private static final String CHANNEL_ID = "com.MohamedTaha.Imagine.Quran.notification.prayer.times";
    private static final String MEDIA_SESIION_TAG = "com.MohamedTaha.Imagine.Quran.notification.AudioPlayer";

    public static int num;
    public static final int NOTIFICATION_ID_SERVICE = 11;
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
    private String name_elmoazen = null;

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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //getString Retrieve a String value from the Preference
        name_elmoazen = sharedPreferences.getString(getString(R.string.settings_azan_key),
                getString(R.string.settings_azan_default));
        builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        audioFocusChange = new AudioFocusChange(this, this);
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
        if (bundle != null) {
//            intent.getExtras().getString(TEXT_NAME_NOTIFICATION);
//            intent.getExtras().getLong(TIME_NOTIFICATION);
//            String st =intent.getExtras().getString(LIST_TIME__NOTIFICATION);

            listForSavePrayerTimes = new ArrayList<>();
            prayer_time = bundle.getLong(TIME_NOTIFICATION);
            name_prayer_time = bundle.getString(TEXT_NAME_NOTIFICATION);
            Type listType = new TypeToken<List<ModelMessageNotification>>() {
            }.getType();
            String st = bundle.getString(LIST_TIME__NOTIFICATION);
            listForSavePrayerTimes = new Gson().fromJson(st, listType);
            Log.d("TAG", "prayer_time is : " + prayer_time + "name_prayer_time is : " + name_prayer_time);
            //initMediaPlayer(name_prayer_time);
            createNotification(name_prayer_time);
            if (!name_prayer_time.equals(getString(R.string.sunrise_string))) {
                getNameShekh();
                if (name_prayer_time != null) {
                    Log.d("TAG", "mediaPlayer is : ");
                    mediaPlayer.start();
                    isPlaying = true;
                }
            } else {
            //    mediaSessionManager = null ;
                Log.d("TAG", "mediaSessionManager = null is : " + mediaSessionManager );

            }
        } else {
            Log.d("TAG", "NOTIFICATION_ID_SERVICE two ");
            startForeground(NOTIFICATION_ID_SERVICE, builder.build());
            stopSelf();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void setAzanSound(int elfagr_Azan, int other_prayer_times) {
        if (mediaPlayer == null) {
            setMediaPlayerForSound(elfagr_Azan, other_prayer_times);
            Log.d("TAG", "mediaPlayer == null");
        } else if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            isPlaying = false;
            mediaPlayer = null;
            setMediaPlayerForSound(elfagr_Azan, other_prayer_times);
            Log.d("TAG", "isPlaying");
        } else {
            isPlaying = false;
            mediaPlayer = null;
            setMediaPlayerForSound(elfagr_Azan, other_prayer_times);
            Log.d("TAG", " not isPlaying");

        }
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }

    private void setMediaPlayerForSound(int elfagr_Azan, int other_prayer_times) {
        if (name_prayer_time.equals(getString(R.string.fagr_string))) {
            mediaPlayer = MediaPlayer.create(this, elfagr_Azan);
            Log.d("TAG", "azan_haram_fajr");
        }
//        else if (name_prayer_time.equals(getString(R.string.sunrise_string))) {
//            releaseMediaPlayer();
//            audioFocusChange.onDestroy();
//        //    return;
//        }
        else {
            mediaPlayer = MediaPlayer.create(this, other_prayer_times);
            Log.d("TAG", "azan_haram");
        }
    }

    private void getNameShekh() {
        if (name_elmoazen.equals(getString(R.string.settings_azan_eldosary_value))) {
            setAzanSound(R.raw.yaser_eldosary_elfagr, R.raw.yaser_eldosary);
            Log.d("TAG", "yaser_eldosary");

        } else if (name_elmoazen.equals(getString(R.string.settings_azan_elharam_elmaky_value))) {
            setAzanSound(R.raw.azan_haram_fajr, R.raw.azan_haram);
            Log.d("TAG", "azan_haram");

        } else if (name_elmoazen.equals(getString(R.string.settings_azan_elharam_elmadany_value))) {
            setAzanSound(R.raw.elmadena_elfagr, R.raw.elmadena);
            Log.d("TAG", "elmadena");

        } else if (name_elmoazen.equals(getString(R.string.settings_azan_abdelbaset_value))) {
            setAzanSound(R.raw.egypt_masr, R.raw.abdelbaset);
            Log.d("TAG", "abdelbaset");

        } else if (name_elmoazen.equals(getString(R.string.settings_azan_elmenshawy_value))) {
            setAzanSound(R.raw.egypt_masr, R.raw.elmenshawy);
            Log.d("TAG", "elmenshawy");

        } else {
            setAzanSound(R.raw.mshary_rashed_elfagr, R.raw.mshary_rashed);
            Log.d("TAG", "mshary_rashed_elfagr");

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        audioFocusChange.onDestroy();
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
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
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
        //cancelNotification.putExtra(TEXT_NAME_NOTIFICATION,new Gson().toJson(prayer_time));
        cancelNotification.putExtra(TEXT_NAME_NOTIFICATION, name_prayer_time);
        cancelNotification.putExtra(TIME_NOTIFICATION, prayer_time);

        cancelNotification.putExtra(LIST_TIME__NOTIFICATION, new Gson().toJson(listForSavePrayerTimes));
        PendingIntent exitPending = PendingIntent.getBroadcast(this, num, cancelNotification, PendingIntent.FLAG_UPDATE_CURRENT);
//        RemoteViews remoteViewsCollapes = new RemoteViews(getPackageName(),R.layout.custom_notification_collapsed);
//        remoteViewsCollapes.setTextViewText(R.id.TV_Name_Prayer_Times,desribe);
//        RemoteViews remoteViewsExpanded = new RemoteViews(getPackageName(),R.layout.custom_notification_expanded);
//        remoteViewsExpanded.setTextViewText(R.id.TV_Name_Prayer_Times,desribe);
//        remoteViewsCollapes.setOnClickPendingIntent(R.id.BT_Close_Notification,playbackAction(0));
//        remoteViewsExpanded.setOnClickPendingIntent(R.id.BT_Close_Notification,playbackAction(0));
        Bitmap bitmap_icon = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        builder.setWhen(System.currentTimeMillis())  // the time stamp
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(bitmap_icon)
                //  .setTicker(getString(R.string.app_name))
                .setContentText(desribe)
                // .setCustomContentView(remoteViewsCollapes)
                //.setCustomBigContentView(remoteViewsExpanded)
                .setAutoCancel(false)
                .setOngoing(true)// Cant cancel your notification (except NotificationManger.cancel();
                .setChannelId(CHANNEL_ID)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setColor(ContextCompat.getColor(this, R.color.colorOrange))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(R.drawable.ic_close, "close", exitPending)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0)
                        .setShowCancelButton(true));
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID_SERVICE, builder.build());
        } else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID_SERVICE, builder.build());
        }
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
        Log.d("TAG", "playMedia");


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