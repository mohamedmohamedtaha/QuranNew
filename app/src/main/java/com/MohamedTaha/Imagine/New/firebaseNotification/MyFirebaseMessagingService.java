package com.MohamedTaha.Imagine.New.firebaseNotification;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.MohamedTaha.Imagine.New.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String CHANNEL_ID_FIREBASE  = "channel_id_notification_firebase";
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        handleNotificationFirebase(remoteMessage);
    }
    private void handleNotificationFirebase(RemoteMessage remoteMessage){
        Bitmap bitmap_icon = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID_FIREBASE)
                .setLargeIcon(bitmap_icon)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.drawable.ic_notification)
                .setAutoCancel(true);
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }
}















