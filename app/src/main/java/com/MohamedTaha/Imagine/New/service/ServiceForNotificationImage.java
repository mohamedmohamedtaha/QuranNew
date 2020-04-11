package com.MohamedTaha.Imagine.New.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.MohamedTaha.Imagine.New.notification.quran.AlarmReceiver.createNotification;

public class ServiceForNotificationImage extends Service {
    public static final String NOTIFICATION_ID = "notificationOpen";
    public static final String TIME_SEND = "time_send";
    public static final String SAVE_Position_Notification = "save_position_notification";
    public int num;
    private int notificationId;
    private List<Integer> NotificationWithImage;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String[] toastMessages = getResources().getStringArray(R.array.notificationAlarm);
            int randomIndex = new Random().nextInt(toastMessages.length - 1);
            num = (int) System.currentTimeMillis();
            notificationId = setNotificationForShow(randomIndex);
            Intent intentToRepeat = new Intent(this, SwipePagesActivity.class);
            intentToRepeat.putExtra(NOTIFICATION_ID, notificationId);
            intentToRepeat.putExtra(TIME_SEND, num);
            intentToRepeat.putIntegerArrayListExtra(SAVE_Position_Notification, (ArrayList<Integer>) NotificationWithImage);
            intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent openIntent = PendingIntent.getActivity(this, num, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
            createNotification(this, openIntent, getString(R.string.app_name), toastMessages[randomIndex]);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public int setNotificationForShow(int randomIndex) {
        NotificationWithImage = new ArrayList<>();
        int number;
        switch (randomIndex) {
            case 0:
            case 1:
                number = 0;
                NotificationWithImage.add(R.drawable.page2);
                break;
            case 2:
            case 3:
                number = 1;
                NotificationWithImage.add(R.drawable.page3);

                break;
            case 4:
                number = 45;
                NotificationWithImage.add(R.drawable.page47);

                break;
            case 5:
                number = 52;
                NotificationWithImage.add(R.drawable.page54);
                break;
            case 6:
            case 16:
                number = 53;
                NotificationWithImage.add(R.drawable.page5);
                break;
            case 7:
            case 8:
                number = 48;
                NotificationWithImage.add(R.drawable.page50);
                break;
            case 9:
            case 10:
            case 21:
                number = 5;
                NotificationWithImage.add(R.drawable.page7);

                break;
            case 11:
            case 72:
                number = 9;
                NotificationWithImage.add(R.drawable.page11);

                break;
            case 12:
            case 13:
                number = 10;
                NotificationWithImage.add(R.drawable.page12);

                break;
            case 14:
                number = 49;
                NotificationWithImage.add(R.drawable.page51);
                break;
            case 15:
                number = 50;
                NotificationWithImage.add(R.drawable.page52);
                break;
            case 17:
                number = 54;
                NotificationWithImage.add(R.drawable.page56);
                break;
            case 18:
            case 19:
                number = 55;
                NotificationWithImage.add(R.drawable.page57);

                break;
            case 20:
            case 24:
                number = 3;
                NotificationWithImage.add(R.drawable.page5);

                break;
            case 22:
            case 23:
                number = 2;
                NotificationWithImage.add(R.drawable.page4);

                break;
            case 25:
            case 26:
                number = 75;
                NotificationWithImage.add(R.drawable.page77);

                break;
            case 27:
            case 28:
            case 29:
                number = 76;
                NotificationWithImage.add(R.drawable.page78);

                break;
            case 30:
                number = 77;
                NotificationWithImage.add(R.drawable.page79);
                break;
            case 31:
            case 32:
                number = 78;
                NotificationWithImage.add(R.drawable.page80);

                break;
            case 33:
                number = 81;
                NotificationWithImage.add(R.drawable.page83);

                break;
            case 34:
            case 35:
                number = 83;
                NotificationWithImage.add(R.drawable.page85);
                break;
            case 36:
                number = 84;
                NotificationWithImage.add(R.drawable.page86);
                break;
            case 37:
                number = 85;
                NotificationWithImage.add(R.drawable.page87);

                break;
            case 38:
                number = 86;
                NotificationWithImage.add(R.drawable.page88);

                break;
            case 39:
                number = 106;
                NotificationWithImage.add(R.drawable.page108);

                break;
            case 40:
            case 41:
                number = 108;
                NotificationWithImage.add(R.drawable.page110);

                break;
            case 42:
                number = 109;
                NotificationWithImage.add(R.drawable.page111);

                break;
            case 43:
                number = 110;
                NotificationWithImage.add(R.drawable.page112);

                break;
            case 44:
                number = 111;
                NotificationWithImage.add(R.drawable.page113);

                break;
            case 45:
                number = 112;
                NotificationWithImage.add(R.drawable.page114);
                break;
            case 46:
            case 47:
            case 48:
            case 49:
                number = 602;
                NotificationWithImage.add(R.drawable.page604);
                break;
            case 50:
                number = 599;
                NotificationWithImage.add(R.drawable.page601);

                break;
            case 51:
            case 54:
                number = 596;
                NotificationWithImage.add(R.drawable.page598);
                break;
            case 52:
            case 53:
                number = 597;
                NotificationWithImage.add(R.drawable.page599);
                break;
            case 55:
            case 56:
            case 57:
                number = 595;
                NotificationWithImage.add(R.drawable.page597);

                break;
            case 58:
                number = 593;
                NotificationWithImage.add(R.drawable.page595);

                break;
            case 59:
                number = 592;
                NotificationWithImage.add(R.drawable.page594);

                break;
            case 60:
                number = 585;
                NotificationWithImage.add(R.drawable.page587);
                break;
            case 61:
                number = 586;
                NotificationWithImage.add(R.drawable.page588);
                break;
            case 62:
            case 63:
                number = 591;
                NotificationWithImage.add(R.drawable.page593);

                break;
            case 64:
            case 65:
            case 67:
                number = 590;
                NotificationWithImage.add(R.drawable.page592);
                break;
            case 66:
                number = 589;
                NotificationWithImage.add(R.drawable.page591);
                break;
            case 68:
            case 69:
            case 70:
                number = 588;
                NotificationWithImage.add(R.drawable.page600);

                break;
            case 71:
                number = 585;
                NotificationWithImage.add(R.drawable.page587);

                break;
            case 73:
            case 74:
                number = 258;
                NotificationWithImage.add(R.drawable.page260);

                break;
            case 75:
                number = 262;
                NotificationWithImage.add(R.drawable.page264);

                break;
            case 76:
                number = 271;
                NotificationWithImage.add(R.drawable.page273);

                break;
            case 77:
                number = 273;
                NotificationWithImage.add(R.drawable.page275);

                break;
            case 78:
                number = 274;
                NotificationWithImage.add(R.drawable.page276);

                break;
            case 79:
                number = 275;
                NotificationWithImage.add(R.drawable.page277);

                break;
            case 80:
            case 81:
                number = 276;
                NotificationWithImage.add(R.drawable.page278);

                break;
            case 82:
                number = 280;
                NotificationWithImage.add(R.drawable.page282);

                break;
            case 83:
                number = 281;
                NotificationWithImage.add(R.drawable.page283);

                break;
            case 84:
                number = 282;
                NotificationWithImage.add(R.drawable.page284);

                break;
            case 85:
                number = 283;
                NotificationWithImage.add(R.drawable.page285);

                break;
            case 86:
                number = 284;
                NotificationWithImage.add(R.drawable.page286);

                break;
            case 87:
                number = 347;
                NotificationWithImage.add(R.drawable.page349);

                break;
            case 88:
                number = 343;
                NotificationWithImage.add(R.drawable.page345);

                break;
            case 89:
                number = 292;
                NotificationWithImage.add(R.drawable.page294);

                break;
            case 90:
                number = 295;
                NotificationWithImage.add(R.drawable.page297);

                break;
            case 91:
                number = 297;
                NotificationWithImage.add(R.drawable.page299);
                break;
            case 92:
                number = 298;
                NotificationWithImage.add(R.drawable.page300);

                break;
            case 93:
                number = 302;
                NotificationWithImage.add(R.drawable.page304);

                break;
            case 94:
                number = 305;
                NotificationWithImage.add(R.drawable.page307);

                break;
            case 95:
                number = 308;
                NotificationWithImage.add(R.drawable.page310);

                break;
            case 96:
                number = 309;
                NotificationWithImage.add(R.drawable.page311);

                break;
            case 97:
                number = 310;
                NotificationWithImage.add(R.drawable.page312);

                break;
            case 98:
                number = 317;
                NotificationWithImage.add(R.drawable.page319);

                break;
            case 99:
                number = 320;
                NotificationWithImage.add(R.drawable.page322);

                break;
            case 100:
                number = 322;
                NotificationWithImage.add(R.drawable.page324);

                break;
            default:
                number = 330;
                NotificationWithImage.add(R.drawable.page332);

        }
        return number;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "Cancel service onDestroy");
    }
}