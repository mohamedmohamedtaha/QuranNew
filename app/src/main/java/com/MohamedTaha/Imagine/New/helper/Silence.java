package com.MohamedTaha.Imagine.New.helper;

import android.content.Context;
import android.media.AudioManager;

public class Silence {
    private Context context;

    public Silence(Context context) {
        this.context = context;
    }
    public boolean checkIsDeviceSilence(){
        boolean isSilence =false ;
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        switch (audioManager.getRingerMode()){
            case AudioManager.RINGER_MODE_SILENT:
            case AudioManager.RINGER_MODE_VIBRATE:
                isSilence = false;
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                isSilence = true;
                break;
        }
        return isSilence;
    }
}
