package com.MohamedTaha.Imagine.New.notification.quran;

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

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity;

import java.util.ArrayList;
import java.util.Random;

import static com.MohamedTaha.Imagine.New.helper.Images.IMAGES;
import static com.MohamedTaha.Imagine.New.helper.Images.addImagesList;

/**
 * Created by MANASATT on 04/09/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    public static int notificationId;
    private static final String CHANNEL_ID = "com.MohamedTaha.Imagine.Quran.notification";
    public static final String NOTIFICATION_ID = "notificationOpen";
    public static final String TIME_SEND = "time_send";
    public static final String SAVE_Position_Notification = "save_position_notification";

    public static int num;

    // List<Integer> images = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        String[] toastMessages = context.getResources().getStringArray(R.array.notificationAlarm);
        int randomIndex = new Random().nextInt(toastMessages.length - 1);
        num = (int) System.currentTimeMillis();
        notificationId = setNotificationForShow(randomIndex);
        addImagesList();
        //Get notification Manager to manage/send notification
        //Intent to invoke app when click
        // on notification
        //In the sample, we want to start/launch this sample app when user clicks on notification
        Intent intentToRepeat = new Intent(context, SwipePagesActivity.class);
        intentToRepeat.putExtra(NOTIFICATION_ID, notificationId);
        intentToRepeat.putExtra(TIME_SEND, num);
        intentToRepeat.putIntegerArrayListExtra(SAVE_Position_Notification, (ArrayList<Integer>) IMAGES);
        //set flag to restart /relaunch the app
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Pending intent to handle launch of Activity in intent above
//        PendingIntent openIntent = PendingIntent.getActivity(context, NotificationHelper.ALARM_TYPE_RTC,
//                intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent openIntent = PendingIntent.getActivity(context, num, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
        //Build notification
        createNotification(context, openIntent, context.getString(R.string.app_name), toastMessages[randomIndex]);
    }

    public static int setNotificationForShow(int randomIndex) {
        int number;
        switch (randomIndex) {
            case 0:
            case 1:
                number = 0;
                break;
            case 2:
            case 3:
                number = 1;
                break;
            case 4:
                number = 45;
                break;
            case 5:
                number = 52;
                break;
            case 6:
            case 16:
                number = 53;
                break;
            case 7:
            case 8:
                number = 48;
                break;
            case 9:
            case 10:
            case 21:
                number = 5;
                break;
            case 11:
            case 72:
                number = 9;
                break;
            case 12:
            case 13:
                number = 10;
                break;
            case 14:
                number = 49;
                break;
            case 15:
                number = 50;
                break;
            case 17:
                number = 54;
                break;
            case 18:
            case 19:
                number = 55;
                break;
            case 20:
                number = 3;
                break;
            case 22:
            case 23:
                number = 2;
                break;
            case 24:
                number = 3;
                break;
            case 25:
            case 26:
                number = 75;
                break;
            case 27:
            case 28:
            case 29:
                number = 76;
                break;
            case 30:
                number = 77;
                break;
            case 31:
            case 32:
                number = 78;
                break;
            case 33:
                number = 81;
                break;
            case 34:
            case 35:
                number = 83;
                break;
            case 36:
                number = 84;
                break;
            case 37:
                number = 85;
                break;
            case 38:
                number = 86;
                break;
            case 39:
                number = 106;
                break;
            case 40:
            case 41:
                number = 108;
                break;
            case 42:
                number = 109;
                break;
            case 43:
                number = 110;
                break;
            case 44:
                number = 111;
                break;
            case 45:
                number = 112;
                break;
            case 46:
            case 47:
            case 48:
            case 49:
                number = 602;
                break;
            case 50:
                number = 599;
                break;
            case 51:
                number = 596;
                break;
            case 52:
            case 53:
                number = 597;
                break;
            case 54:
                number = 596;
                break;
            case 55:
            case 56:
            case 57:
                number = 595;
                break;
            case 58:
                number = 593;
                break;
            case 59:
                number = 592;
                break;
            case 60:
                number = 585;
                break;
            case 61:
                number = 586;
                break;
            case 62:
            case 63:
                number = 591;
                break;
            case 64:
            case 65:
                number = 590;
                break;
            case 66:
                number = 589;
                break;
            case 67:
                number = 590;
                break;
            case 68:
            case 69:
            case 70:
                number = 588;
                break;
            case 71:
                number = 585;
                break;
            case 73:
            case 74:
                number = 258;
                break;
            case 75:
                number = 262;
                break;
            case 76:
                number = 271;
                break;
            case 77:
                number = 273;
                break;
            case 78:
                number = 274;
                break;
            case 79:
                number = 275;
                break;
            case 80:
            case 81:
                number = 276;
                break;
            case 82:
                number = 280;
                break;
            case 83:
                number = 281;
                break;
            case 84:
                number = 282;
                break;
            case 85:
                number = 283;
                break;
            case 86:
                number = 284;
                break;
            case 87:
                number =347;
                break;
            case 88:
                number=343;
                break;
            case  89:
             number=292;
                break;
            case 90:
                number=295;
                break;
            case 91:
                number=297;
                break;
            case 92:
                number= 298;
                break;
            case 93:
                number = 302;
                break;
            case 94:
                number=305;
                break;
            case 95:
                number =308;
                break;
            case 96:
                number =309;
                break;
            case 97:
                number=310;
                break;
            case 98:
                number=317;
                break;
            case 99:
                number=320;
                break;
            case 100:
                number=322;
                break;
            default:
                number = 330;
        }
        return number;
    }

    public static NotificationCompat.Builder createNotification(Context context, PendingIntent openIntent, CharSequence ticker, CharSequence desribe) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //you only need to create  the cnannel on API 26+ devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context);
        }
        Intent cancelNotification = new Intent(context, CancelNotification.class);
        //    cancelNotification.putExtra(NOTIFICATION_ID, notificationId);
        cancelNotification.putExtra(TIME_SEND, num);

        PendingIntent exitPending = PendingIntent.getBroadcast(context, num, cancelNotification, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap_icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);
        //Create a new Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_notification);
        builder.setLargeIcon(bitmap_icon);
        builder.setTicker(ticker);
        builder.setContentText(desribe);
        //For sound
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarmSound);

        builder.setWhen(System.currentTimeMillis());  // the time stamp
        builder.setChannelId(CHANNEL_ID);
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
        NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID, name, importance);
        //Configure the channel's intial preference
        notificationChannel2.setLightColor(Color.GREEN);
        //Configure the notification channel.
        notificationChannel2.setDescription(description);
        notificationChannel2.setShowBadge(false);
        notificationChannel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(notificationChannel2);
    }
}