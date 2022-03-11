package com.mohamedtaha.imagine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mohamedtaha.imagine.notification.morningAzkar.MorningAzkarNotificationHelper;

public class GetAzkarTimesEveryDay extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MorningAzkarNotificationHelper morningAzkarNotificationHelper = new MorningAzkarNotificationHelper(context);
        morningAzkarNotificationHelper.showNotificationAzkar();

    }
}
