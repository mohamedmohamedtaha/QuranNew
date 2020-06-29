package com.MohamedTaha.Imagine.New.service;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;

import com.MohamedTaha.Imagine.New.notification.prayerTimes.mediaPlayer.AudioFocusChange;
import com.MohamedTaha.Imagine.New.notification.prayerTimes.mediaPlayer.MediaPlayerListener;

import java.util.logging.Logger;

public class SettingsButtonsChange  extends ContentObserver {
    private int previousVolume;
    private AudioManager audio ;
    private Context context;
    private MediaPlayerListener mediaPlayerListener;

    public SettingsButtonsChange(Context c, Handler handler,MediaPlayerListener mediaPlayerListener) {
        super(handler);
        context=c;
        this.mediaPlayerListener =mediaPlayerListener;
        audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        previousVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        int delta=previousVolume-currentVolume;

        if(delta>0)
        {
            Log.d("TAG","volume decreased.");
            previousVolume=currentVolume;
            mediaPlayerListener.muteSound();
        }
        else if(delta<0)
        {
            Log.d("TAG"," volume increased.");
            previousVolume=currentVolume;
            mediaPlayerListener.muteSound();

        }
    }
}