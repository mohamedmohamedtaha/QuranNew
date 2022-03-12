package com.mohamedtaha.imagine.firebaseNotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String CHANNEL_ID_FIREBASE  = "com.MohamedTaha.Imagine.Quran.notification.firebase";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
       // super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0){
            handleNotificationFirebase(remoteMessage);
        }
    }
    private void handleNotificationFirebase(RemoteMessage remoteMessage){
        Intent intent = new Intent(this, NavigationDrawaberActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
        Bitmap bitmap_icon = BitmapFactory.decodeResource(getResources(), R.mipmap.logo);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID_FIREBASE)
                .setLargeIcon(bitmap_icon)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap_icon).bigLargeIcon(null))
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setStyle(new NotificationCompat.BigTextStyle())
                .setSound(soundUri)
                .setSmallIcon(R.drawable.ic_notification)
                .setAutoCancel(true);
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public void createChannel() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //The id of the channel
        //for create Channel for notification for Android O
        //The user visible name of the channel
        CharSequence name = "firebase Notification";
        //The user visible description of the channel
        String description = "firebase Notification controls";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID_FIREBASE, name, importance);
        //Configure the channel's intial preference
        notificationChannel2.setLightColor(Color.GREEN);
        //Configure the notification channel.
        notificationChannel2.setDescription(description);
        notificationChannel2.setShowBadge(false);
        notificationChannel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(notificationChannel2);
    }

    @Override
    public void onNewToken(@NonNull String s) {
        Log.d("TAG", "Refreshed token: " + s);

        super.onNewToken(s);
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()){
                    Log.d("TAG","getInstanceId failed ", task.getException());
                    return;
                }
                //Get new Instance ID token
                String token = task.getResult().getToken();
                Log.d("TAG", "Token is : " + token);
            }
        });
    }
}















