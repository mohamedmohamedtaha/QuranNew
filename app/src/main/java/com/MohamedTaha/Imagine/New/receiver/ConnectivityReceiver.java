package com.MohamedTaha.Imagine.New.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.helper.checkConnection.NetworkConnection;

public class ConnectivityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isConnected = NetworkConnection.networkConnectivity(context);
        if (isConnected) {
            //For check from Status Network
        } else {
            Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }
}
