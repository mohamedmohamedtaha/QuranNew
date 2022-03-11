package com.mohamedtaha.imagine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.helper.checkConnection.NetworkConnection;

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
