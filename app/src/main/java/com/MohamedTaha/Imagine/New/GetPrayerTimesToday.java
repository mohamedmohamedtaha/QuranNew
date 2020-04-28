package com.MohamedTaha.Imagine.New;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.MohamedTaha.Imagine.New.room.TimingsViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.helper.util.ConvertTimes.convertDate;

public class GetPrayerTimesToday extends AppCompatActivity{
    private TimingsViewModel timingsViewModel;
    private Context context;

    public GetPrayerTimesToday(Context context) {
        this.context = context;
    }

    public void getData(){
        timingsViewModel = new ViewModelProvider(this).get(TimingsViewModel.class);
        timingsViewModel.getTimingsByDataToday(convertDate()).
                subscribeOn(Schedulers.trampoline())
                // Add RXAndroid2 for support with Room because still RXjava3 don't support Room
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(date_today -> {
                    Toast.makeText(context, "date today is " + date_today, Toast.LENGTH_SHORT).show();
                }, e -> {
                    Toast.makeText(context, "e : " + e.getMessage(), Toast.LENGTH_SHORT).show();

                });
    }


}
