package com.mohamedtaha.imagine.notification.morningAzkar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.mvp.model.ModelAzkar;
import com.mohamedtaha.imagine.notification.prayerTimes.ModelMessageNotification;
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MANASATT on 04/09/17.
 */

public class MorningAzkarAlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID_MORNING_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.CHANNEL_ID_MORNING_AZKAR";
    public static final String NOTIFICATION_ID_NUMBER_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.NOTIFICATION_ID_MORNING_AZKAR";
    public static final String NOTIFICATION_ID_TEXT_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.NOTIFICATION_ID_TEXT_AZKAR";
    public static final String NOTIFICATION_ID_TIME_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.NOTIFICATION_ID_TIME_AZKAR";
    public static final String NOTIFICATION_ID_LIST_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.NOTIFICATION_ID_LIST_AZKAR";
    public static final String TIME_SEND_MORNING_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.TIME_SEND_MORNING_AZKAR";
    public static final String SAVE_POSITION_MORNING_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.SAVE_POSITION_MORNING_AZKAR";
    private static int num;
    private List<ModelAzkar> modelAzkar;
    private int number_azkar;
    private String text_azkar ;
    private ArrayList<ModelMessageNotification> list_azkar;

    @Override
    public void onReceive(Context context, Intent intent) {
        list_azkar= new ArrayList<>();
        num = (int) System.currentTimeMillis();
        number_azkar = intent.getIntExtra(NOTIFICATION_ID_NUMBER_AZKAR,-1 );
        text_azkar = intent.getStringExtra(NOTIFICATION_ID_LIST_AZKAR);
        Type listType = new TypeToken<List<ModelMessageNotification>>() {
        }.getType();
        String st = intent.getStringExtra(NOTIFICATION_ID_LIST_AZKAR);
        list_azkar = new Gson().fromJson(st, listType);
      //  list_azkar = new Gson().fromJson(intent.getStringExtra(NOTIFICATION_ID_LIST_AZKAR), ModelMessageNotification.class);
        Intent intentToRepeat = new Intent(context, SwipePagesActivity.class);
        intentToRepeat.putExtra(NOTIFICATION_ID_NUMBER_AZKAR, number_azkar);
        intentToRepeat.putExtra(TIME_SEND_MORNING_AZKAR, num);
        intentToRepeat.putExtra(NOTIFICATION_ID_LIST_AZKAR, new Gson().toJson(list_azkar));
        intentToRepeat.putExtra(SAVE_POSITION_MORNING_AZKAR,new Gson().toJson(getAzkar(context, number_azkar)));
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent openIntent = PendingIntent.getActivity(context, num, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
        createNotification(context, openIntent, context.getString(R.string.app_name), modelAzkar.get(0).getName_azkar());
    }
    private List<ModelAzkar> getAzkar(Context context,int number_zakr){
        modelAzkar = new ArrayList<>();
        ModelAzkar modelAzkarItem = new ModelAzkar();
        if (number_zakr == 27){
            modelAzkarItem.setName_azkar(context.getString(R.string.morning_azkar));
            modelAzkarItem.setDescribe_azkar(context.getString(R.string.morning_azkar_describe));
        }else if (number_zakr == 28){
            modelAzkarItem.setName_azkar(context.getString(R.string.night_azkar));
            modelAzkarItem.setDescribe_azkar(context.getString(R.string.night_azkar_describe));
        }
        modelAzkar.add(modelAzkarItem);
        return modelAzkar;
    }

    public static NotificationCompat.Builder createNotification(Context context, PendingIntent openIntent, CharSequence ticker, CharSequence desribe) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context);
        }
        Intent cancelNotification = new Intent(context, CancelMorningAzkarNotification.class);
        cancelNotification.putExtra(TIME_SEND_MORNING_AZKAR, num);
        PendingIntent exitPending = PendingIntent.getBroadcast(context, num, cancelNotification, PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bitmap_icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);
        //Create a new Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID_MORNING_AZKAR);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setLargeIcon(bitmap_icon);
        builder.setTicker(ticker);
        builder.setContentText(desribe);
        //For sound
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);
        builder.setWhen(System.currentTimeMillis());  // the time stamp
        builder.setChannelId(CHANNEL_ID_MORNING_AZKAR);
        // Make the transport controls visible on the lock screen
        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        //Set the notification color
        builder.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.colorPrimaryDark));

        //will make it a Heads Up  Display Style
        builder.addAction(R.drawable.ic_close, context.getString(R.string.notNow), exitPending);
        builder.addAction(R.drawable.ic_reply, context.getString(R.string.readNow), openIntent);
        builder.setContentIntent(openIntent);
        builder.setDefaults(Notification.DEFAULT_ALL);//Require VIBREATE permission
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(desribe));
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
//        builder.setFullScreenIntent(openIntent,true);
        notificationManager.notify(num, builder.build());
        return builder;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public static void createChannel(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //The id of the channel
        //for create Channel for notification for Android O
        //The user visible name of the channel
        CharSequence name = "Quran Notification";
        //The user visible description of the channel
        String description = "Quran Notification controls";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID_MORNING_AZKAR, name, importance);
        //Configure the channel's intial preference
        notificationChannel2.setLightColor(Color.GREEN);
        //Configure the notification channel.
        notificationChannel2.setDescription(description);
        notificationChannel2.setShowBadge(false);
        notificationChannel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(notificationChannel2);
    }
}