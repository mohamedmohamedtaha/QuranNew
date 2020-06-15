package com.MohamedTaha.Imagine.New.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.MohamedTaha.Imagine.New.helper.Utilities;
import com.MohamedTaha.Imagine.New.service.MediaPlayerService;

import static com.MohamedTaha.Imagine.New.service.MediaPlayerService.ACTION_STOP;
import static com.MohamedTaha.Imagine.New.service.MediaPlayerService.transportControls;
import static com.MohamedTaha.Imagine.New.service.ServiceForPlayPrayerTimesNotification.NOTIFICATION_ID_SERVICE;
import static com.MohamedTaha.Imagine.New.ui.activities.ListSoundReader.FragmentListSoundLLControlMedia;

public class CancelNotificationMediaPlayer extends BroadcastReceiver {
    private boolean isServiceRunning;

    @Override
    public void onReceive(Context context, Intent intent) {
        isServiceRunning = Utilities.isServiceRunning(MediaPlayerService.class.getName(), context);
        if (intent.getAction().equals(ACTION_STOP)) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(NOTIFICATION_ID_SERVICE);
            if (isServiceRunning){
                context.stopService(new Intent(context, MediaPlayerService.class));
            }
            transportControls.stop();
            FragmentListSoundLLControlMedia.setVisibility(View.GONE);
    }
}
}
