package com.mohamedtaha.imagine;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mohamedtaha.imagine.mvp.model.ModelAzkar;
import com.mohamedtaha.imagine.notification.morningAzkar.CancelMorningAzkarNotification;
import com.mohamedtaha.imagine.ui.home.activity.SwipePagesActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FullScreenActivity extends AppCompatActivity {
    private static final String CHANNEL_ID_MORNING_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.CHANNEL_ID_MORNING_AZKAR";
    public static final String NOTIFICATION_ID_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.NOTIFICATION_ID_MORNING_AZKAR";
    public static final String NOTIFICATION_ID_TEXT_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.NOTIFICATION_ID_TEXT_AZKAR";

    public static final String TIME_SEND_MORNING_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.TIME_SEND_MORNING_AZKAR";
    public static final String SAVE_POSITION_MORNING_AZKAR = "com.MohamedTaha.Imagine.Quran.notification.SAVE_POSITION_MORNING_AZKAR";
    private static int num;
    private List<ModelAzkar> modelAzkar;
    private int position_azkar;
    private String text_azkar ;
    private Intent intent;
    private Button cancel_notification ;
    private TextView tv_show_notification_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
//        View decorView = getWindow().getDecorView();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        cancel_notification = findViewById(R.id.cancel_notification);
        tv_show_notification_text = findViewById(R.id.tv_show_notification_text);
        cancel_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelNotification = new Intent(FullScreenActivity.this, CancelMorningAzkarNotification.class);
                cancelNotification.putExtra(TIME_SEND_MORNING_AZKAR, num);
                PendingIntent exitPending = PendingIntent.getBroadcast(FullScreenActivity.this, num, cancelNotification, PendingIntent.FLAG_UPDATE_CURRENT);
            finish();
            }
        });
        intent = getIntent();
        num = (int) System.currentTimeMillis();
        position_azkar = intent.getIntExtra(NOTIFICATION_ID_AZKAR ,-1 );
        text_azkar = intent.getStringExtra(NOTIFICATION_ID_TEXT_AZKAR);
        Intent intentToRepeat = new Intent(FullScreenActivity.this, SwipePagesActivity.class);
        intentToRepeat.putExtra(NOTIFICATION_ID_AZKAR, position_azkar);
        intentToRepeat.putExtra(TIME_SEND_MORNING_AZKAR, num);
        intentToRepeat.putExtra(SAVE_POSITION_MORNING_AZKAR,new Gson().toJson(getAzkar(position_azkar)));
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      //  PendingIntent openIntent = PendingIntent.getActivity(FullScreenActivity.this, num, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
        tv_show_notification_text.setText(modelAzkar.get(0).getName_azkar());
       // createNotification(context, openIntent, getString(R.string.app_name), modelAzkar.get(0).getName_azkar());

        // Important: have to do the following in order to show without unlocking
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }
    private List<ModelAzkar> getAzkar(int number_zakr){
        modelAzkar = new ArrayList<>();
        ModelAzkar modelAzkarItem = new ModelAzkar();
        if (number_zakr == 27){
            modelAzkarItem.setName_azkar(getString(R.string.morning_azkar));
            modelAzkarItem.setDescribe_azkar(getString(R.string.morning_azkar_describe));
        }else if (number_zakr == 28){
            modelAzkarItem.setName_azkar(getString(R.string.night_azkar));
            modelAzkarItem.setDescribe_azkar(getString(R.string.night_azkar_describe));
        }
        modelAzkar.add(modelAzkarItem);
        return modelAzkar;
    }

}