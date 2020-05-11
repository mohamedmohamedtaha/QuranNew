package com.MohamedTaha.Imagine.New.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.ModelMessageNotification;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertFromMilliSecondsToTime;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.AlarmReceiverPrayerTime.createNotification;
import static com.MohamedTaha.Imagine.New.notification.prayerTimes.NotificationHelperPrayerTime.TEXT_NOTIFICATION;

public class ServiceForPlayPrayerTimesNotification extends Service implements MediaPlayer.OnCompletionListener
        , MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    public static final String SEND_TIME_FOR_SEINDING = "time_send";
    private static final String NOTIFICATION_ID_FOR_PRAYER_TIMES = "notification_id_for_prayer_times";
    public static int num;
    private int notification_id_for_prayer_times;
    ArrayList<ModelMessageNotification> minutes;
    //For play song
    private MediaPlayer mediaPlayer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initMediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand");
        // releaseMediaPlayer();

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Log.d("TAG", "!isPlaying");
        } else {
        //    releaseMediaPlayer();
         //   initMediaPlayer();
          //  mediaPlayer.start();
            Log.d("TAG", "isPlaying");
        }
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
                    intent = new Intent(this, NavigationDrawaberActivity.class);
                    intent.putExtra(NOTIFICATION_ID_FOR_PRAYER_TIMES, notification_id_for_prayer_times);
                    intent.putExtra(SEND_TIME_FOR_SEINDING, num);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // PendingIntent openIntent = PendingIntent.getActivity(this, num, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    //Build notification
                    createNotification(this, getString(R.string.app_name), minutes.get(i).getText_notification());

                } else {
                    Log.d("TAG : error: ", convertFromMilliSecondsToTime(minutes.get(i).getTime_payer()) + ":" +
                            convertFromMilliSecondsToTime(calendar.getTimeInMillis()));
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
        }
    }

    private void initMediaPlayer() {
        if (mediaPlayer == null)
        mediaPlayer = MediaPlayer.create(this, R.raw.azan_haram);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d("TAG", "onPrepared");

    }
}
