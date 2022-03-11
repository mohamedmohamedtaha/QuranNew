package com.mohamedtaha.imagine.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.mohamedtaha.imagine.helper.Utilities;
import com.mohamedtaha.imagine.service.MediaPlayerService;
import com.mohamedtaha.imagine.service.ServiceForPlayPrayerTimesNotification;
import com.mohamedtaha.imagine.ui.activities.ListSoundReader;

public class CancelNotificationMediaPlayer extends BroadcastReceiver {
    private boolean isServiceRunning;

    @Override
    public void onReceive(Context context, Intent intent) {
        isServiceRunning = Utilities.isServiceRunning(MediaPlayerService.class.getName(), context);
        if (intent.getAction().equals(MediaPlayerService.ACTION_STOP)) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel(ServiceForPlayPrayerTimesNotification.NOTIFICATION_ID_SERVICE);
            if (isServiceRunning){
                context.stopService(new Intent(context, MediaPlayerService.class));
            }
            MediaPlayerService.transportControls.stop();
            ListSoundReader.FragmentListSoundLLControlMedia.setVisibility(View.GONE);
    }
}
}
