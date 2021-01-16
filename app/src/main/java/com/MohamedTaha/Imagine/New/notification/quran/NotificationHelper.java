package com.MohamedTaha.Imagine.New.notification.quran;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import androidx.preference.PreferenceManager;
import android.util.Log;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.receiver.bootDevice.ReadAyatAlarmBootRecevier;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;

import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by MANASATT on 04/09/17.
 */

public class NotificationHelper {
    public static int ALARM_TYPE_ELAPSED = 101;
    private static AlarmManager alarmManager;
    private static PendingIntent alarmPendingIntent;
    private Context context ;
    private CompositeDisposable disposable;
    String repearAsync = null;
    SharedPreferences sharedPreferences = null;
    String repear = null;
    public NotificationHelper(Context context) {
        this.context = context;
    }

    public  void sendNotificationEveryHalfDay() {
     //   getSharedPreferencesThreadSeperate();
         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //getString Retrieve a String value from the Preference
        String repear = sharedPreferences.getString(context.getString(R.string.settings_Notification_key),
                context.getString(R.string.settings_Notification_default));
        //Setting intent to class where notification will be handled
       Intent intent = new Intent(context, AlarmReceiver.class);
      //  Intent intent = new Intent(context, ServiceForNotificationImage.class);
        Log.d("TAG", "ServiceForNotificationImage ");
        //Setting pending intent to respond to broadcast sent by AlarmManager every day at 8am
        alarmPendingIntent = PendingIntent.getBroadcast(context, ALARM_TYPE_ELAPSED, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
             alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + setAlarm(repear),
                setAlarm(repear), alarmPendingIntent);
//        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
//                AlarmManager.RTC_WAKEUP,
//                AlarmManager.RTC_WAKEUP, alarmPendingIntent);
    }
    private void getSharedPreferencesThreadSeperate(){
        disposable = new CompositeDisposable();
        Observable<String> modelAzkarObservable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    //getString Retrieve a String value from the Preference
                    repear = sharedPreferences.getString(context.getString(R.string.settings_Notification_key),
                            context.getString(R.string.settings_Notification_default));
                    Log.d("TAG", "doInBackground - Thread call" + Thread.currentThread().getId() + "Name : "
                            + Thread.currentThread().getName() );
                } catch (Exception e) {
                }
                return repear;
            }
        }).subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread());
        disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull String repearAsync) {
                if (repearAsync != null) {
                    repear = repearAsync;
                    Log.d("TAG", "onNext Prefrences  ");
                    Log.d("TAG", "doInBackground - Thread " + Thread.currentThread().getId() + "Name : "
                            + Thread.currentThread().getName() );
                }
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                if (repearAsync != null) {
                    Log.d("TAG", "onError Prefrences ");

                }
            }

            @Override
            public void onComplete() {
                if (repearAsync != null) {
                    Log.d("TAG", "onComplete Prefrences ");
                    Log.d("TAG", "doInBackground - Thread " + Thread.currentThread().getId() + "Name : "
                            + Thread.currentThread().getName() );
                }
            }
        }));
    }

    private static Long setAlarm(String key) {
        Long alarm = 0L ;
        switch (key) {
            case "AlarmManager.INTERVAL_FIFTEEN_MINUTES":
                alarm =  AlarmManager.INTERVAL_FIFTEEN_MINUTES;
            break;
            case "AlarmManager.INTERVAL_HALF_HOUR":
                alarm =  AlarmManager.INTERVAL_HALF_HOUR;
            break;
            case "AlarmManager.INTERVAL_HOUR":
                alarm =  AlarmManager.INTERVAL_HOUR;
            break;
            case "AlarmManager.INTERVAL_HALF_DAY":
                alarm =  AlarmManager.INTERVAL_HALF_DAY;
            break;
            case "AlarmManager.INTERVAL_DAY":
                alarm =  AlarmManager.INTERVAL_DAY;
            break;
            default:
        }
        return alarm;
    }

    //Enable boot receiver to persist alarms set for notification across device reboots
    public void enableBootRecieiver() {
        ComponentName receiver = new ComponentName(context, ReadAyatAlarmBootRecevier.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}