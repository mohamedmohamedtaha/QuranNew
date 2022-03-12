package com.mohamedtaha.imagine.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import static com.mohamedtaha.imagine.helper.checkConnection.NoInternetConnection.isInternet;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.helper.checkConnection.NoInternetConnection;

public class NoInternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (NoInternetConnection.isInternet()) {
        } else {
            Toast.makeText(context, context.getString(R.string.no_internet_network), Toast.LENGTH_SHORT).show();
        }
    }
}
