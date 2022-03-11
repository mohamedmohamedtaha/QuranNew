package com.mohamedtaha.imagine.receiver.bootDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mohamedtaha.imagine.notification.morningAzkar.MorningAzkarNotificationHelper;

/**
 * Created by MANASATT on 04/09/17.
 */

public class MorningAzkarAlarmBootRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            MorningAzkarNotificationHelper morningAzkarNotificationHelper = new MorningAzkarNotificationHelper(context);
            morningAzkarNotificationHelper.getAzkarTimesEveryday();
        }
    }
}