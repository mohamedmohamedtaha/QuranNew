package com.MohamedTaha.Imagine.New.notification.prayerTimes.mediaPlayer;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

public class AudioFocusChange implements AudioManager.OnAudioFocusChangeListener {
    private AudioManager audioManager;
    private Context context;
    private MediaPlayerListener mediaPlayerListener;

    public AudioFocusChange(MediaPlayerListener mediaPlayerListener, Context context) {
        this.context = context;
        this.mediaPlayerListener = mediaPlayerListener;
        if (requestAduioFocus() == false) {
            mediaPlayerListener.stopService();
            Log.d("TAG", " mediaPlayerListener : stopService");

        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        //Invoked when the audio focus fo the system is updated.
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                //resume playback
                mediaPlayerListener.playMedia();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                mediaPlayerListener.slowVolume();
            case AudioManager.AUDIOFOCUS_LOSS:
                //mediaPlayerListener.stopMedia();
                break;
        }
    }

    private boolean requestAduioFocus() {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //Focus gained
            return true;
        }
        //Could not gain focus
        return false;
    }

    public boolean removeAudioFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == audioManager.abandonAudioFocus(this);
    }

    public void onDestroy() {
        removeAudioFocus();
    }

}
