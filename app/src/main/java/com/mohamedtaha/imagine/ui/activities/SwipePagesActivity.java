package com.mohamedtaha.imagine.ui.activities;

import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mohamedtaha.imagine.Adapter.AdapterForSwipe;
import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.helper.HelperClass;
import com.mohamedtaha.imagine.helper.SharedPerefrenceHelper;
import com.mohamedtaha.imagine.helper.ShowDialog;
import com.mohamedtaha.imagine.mvp.model.ModelAzkar;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mohamedtaha.imagine.helper.Images.SAVE_POSITION;
import static com.mohamedtaha.imagine.notification.morningAzkar.MorningAzkarAlarmReceiver.NOTIFICATION_ID_NUMBER_AZKAR;
import static com.mohamedtaha.imagine.notification.morningAzkar.MorningAzkarAlarmReceiver.SAVE_POSITION_MORNING_AZKAR;
import static com.mohamedtaha.imagine.notification.morningAzkar.MorningAzkarAlarmReceiver.TIME_SEND_MORNING_AZKAR;
import static com.mohamedtaha.imagine.notification.quran.AlarmReceiver.NOTIFICATION_ID;
import static com.mohamedtaha.imagine.notification.quran.AlarmReceiver.SAVE_Position_Notification;
import static com.mohamedtaha.imagine.notification.quran.AlarmReceiver.TIME_SEND;
import static com.mohamedtaha.imagine.ui.activities.NavigationDrawaberActivity.SAVE_ALL_IMAGES;
import static com.mohamedtaha.imagine.ui.activities.NavigationDrawaberActivity.SAVE_PAGE;
import static com.mohamedtaha.imagine.ui.fragments.AzkarFragment.SAVE_AZKAR;
import static com.mohamedtaha.imagine.ui.fragments.AzkarFragment.SAVE_POTION_AZKAR;
import static com.mohamedtaha.imagine.ui.fragments.SwarFragment.SAVE_IMAGES;
import static com.mohamedtaha.imagine.ui.fragments.SwarFragment.SAVE_STATE;
public class SwipePagesActivity extends AppCompatActivity {
    ArrayList<Integer> images, imagesNotification, imagesFirst;
    public static final String IS_TRUE = "is_true";
    public static final String IS_TRUE_AZKAR = "is_true_azkar";
    @BindView(R.id.SwipePagesActivity_PB)
    ProgressBar SwipePagesActivityPB;
//    @BindView(R.id.SwipePagesActivity_VP)
//    RtlViewPager SwipePagesActivityVP;
//    @Inject
//    List<ModelAzkar> modelAzkarList;
//    @Inject
//    SharedPreferences sharedPreferences;
    private int save_position;
    private int save_position_azkar;
    private int position = 1;
    private int position_azkar = 0;
    private Bundle bundle;
    int notificationId = -1;
    int notification_id_morning_azkar = -1;
    private String language_name;
    private boolean screenOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_pages);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        //to Check before change Language
        language_name = Locale.getDefault().getLanguage();
        if (!language_name.equals("ar")) {
            HelperClass.change_language("ar", this);
        }
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        SwipePagesActivityComponent swipePagesActivityComponent = DaggerSwipePagesActivityComponent.builder()
//                .sharedPreferencesModule(new SharedPreferencesModule(this)).build();
//        swipePagesActivityComponent.inject(this);
     //   screenOn = sharedPreferences.getBoolean(getString(R.string.switch_key), true);
        getScreenOn(screenOn);
        //for close Notification
        notificationId = getIntent().getIntExtra(NOTIFICATION_ID, -1);
        notification_id_morning_azkar = getIntent().getIntExtra(NOTIFICATION_ID_NUMBER_AZKAR, -1);
        int timeSend = getIntent().getIntExtra(TIME_SEND, -1);
        int time_send_morning_azkar = getIntent().getIntExtra(TIME_SEND_MORNING_AZKAR, -1);
        if (notificationId >= 0) {
            getArgemnetsNotification();
            createImageNotification();
            AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, imagesNotification);
//            SwipePagesActivityVP.setAdapter(adapterForSwipe);
//            SwipePagesActivityVP.setCurrentItem(notificationId);
            Log.d("TAG", " " + notificationId);
            if (notificationManager != null) {
                notificationManager.cancel(timeSend);
            }
        } else if (notification_id_morning_azkar >= 0) {
            getArgemnetsForNotificationAzkar();
         //   AdapterForAzkarSwipe adapterForAzkarSwipe = new AdapterForAzkarSwipe(SwipePagesActivity.this, modelAzkarList);
            if (notification_id_morning_azkar == 27) {
//                SwipePagesActivityVP.setAdapter(adapterForAzkarSwipe);
//                SwipePagesActivityVP.setCurrentItem(notification_id_morning_azkar);

            } else {
//                SwipePagesActivityVP.setAdapter(adapterForAzkarSwipe);
//                SwipePagesActivityVP.setCurrentItem(notification_id_morning_azkar);
            }
            if (notificationManager != null) {
                notificationManager.cancel(time_send_morning_azkar);
            }
        } else if (bundle.getBoolean(SAVE_STATE)) {
            getArgemnets();
            createImage();
            AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, images, postiopn ->
                    ShowDialog.showDialog(SwipePagesActivity.this, save_position, getString(R.string.save_position)));
//
//            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    save_position = position;
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                }
//            });
//            SwipePagesActivityVP.setAdapter(adapterForSwipe);
//            if (SharedPerefrenceHelper.getBoolean(this, IS_TRUE, false)) {
//                ShowDialog.showDialogForRetrieveReadingSora(this, SwipePagesActivityVP, position);
//            } else {
//                SwipePagesActivityVP.setCurrentItem(position);
//            }
        } else if (bundle.getBoolean(SAVE_PAGE)) {
            getImagesFirst();
            createImagesFirst();
            AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, imagesFirst, positon ->
                    ShowDialog.showDialog(SwipePagesActivity.this, save_position, getString(R.string.save_position)));
//            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    save_position = position;
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                }
//            });
//            SwipePagesActivityVP.setAdapter(adapterForSwipe);
//            int position = SharedPerefrenceHelper.getInt(getApplicationContext(), SAVE_IMAGES, 0);
//            SwipePagesActivityVP.setCurrentItem(position);
        } else {
            getArgemnetsForAzkar();
          //  AdapterForAzkarSwipe adapterForAzkarSwipe = new AdapterForAzkarSwipe(SwipePagesActivity.this, modelAzkarList, modelAzkar ->
          //          ShowDialog.showDialogAzkar(SwipePagesActivity.this, save_position_azkar, getString(R.string.save_position_alzekr), modelAzkar));
//            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    save_position_azkar = position;
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//                }
//            });
//            SwipePagesActivityVP.setAdapter(adapterForAzkarSwipe);
//            if (SharedPerefrenceHelper.getBooleanForAzkar(this, IS_TRUE_AZKAR, false)) {
//                ShowDialog.showDialogForRetrieveAzkar(this, SwipePagesActivityVP, position_azkar);
//            } else {
//                SwipePagesActivityVP.setCurrentItem(position_azkar);
//            }
        }
    }

    private void getScreenOn(boolean isScreenOn) {
        if (isScreenOn) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    private void getArgemnets() {
        if (bundle != null) {
            images = bundle.getIntegerArrayList(SAVE_IMAGES);
            position = bundle.getInt(SAVE_POSITION);
        }
    }

    private void createImage() {
        for (int i = 0; i < images.size(); i++) {
            bundle.putInt(SAVE_IMAGES, images.get(i));
            bundle.putInt(SAVE_POSITION, position);
        }
        SwipePagesActivityPB.setVisibility(View.GONE);
    }

    private void getArgemnetsNotification() {
        if (bundle != null) {
            imagesNotification = bundle.getIntegerArrayList(SAVE_Position_Notification);
            Log.d("TAG", " " + imagesNotification);
        }
    }

    private void createImageNotification() {
        if (imagesNotification != null) {
            for (int i = 0; i < imagesNotification.size(); i++) {
                bundle.putInt(SAVE_Position_Notification, imagesNotification.get(i));
            }
            Log.d("TAG", " " + imagesNotification);
            SwipePagesActivityPB.setVisibility(View.GONE);
        }
    }

    private void getAzkarNotification() {
        if (bundle != null) {
            imagesNotification = bundle.getIntegerArrayList(SAVE_Position_Notification);
            Log.d("TAG", " " + imagesNotification);
        }
    }

    private void createAzkarNotification() {
        if (imagesNotification != null) {
            for (int i = 0; i < imagesNotification.size(); i++) {
                bundle.putInt(SAVE_Position_Notification, imagesNotification.get(i));
            }
            Log.d("TAG", " " + imagesNotification);
            SwipePagesActivityPB.setVisibility(View.GONE);
        }
    }

    private void getImagesFirst() {
        if (bundle != null) {
            imagesFirst = bundle.getIntegerArrayList(SAVE_ALL_IMAGES);
        }
    }

    private void createImagesFirst() {
        if (imagesFirst != null) {
            for (int i = 0; i < imagesFirst.size(); i++) {
                bundle.putInt(SAVE_ALL_IMAGES, imagesFirst.get(i));
            }
            SwipePagesActivityPB.setVisibility(View.GONE);
        }
    }

    private void getArgemnetsForAzkar() {
        if (bundle != null) {
            Type listType = new TypeToken<List<ModelAzkar>>() {
            }.getType();
            String st = bundle.getString(SAVE_AZKAR);
          //  modelAzkarList = new Gson().fromJson(st, listType);
            position_azkar = bundle.getInt(SAVE_POTION_AZKAR);
        }
        SwipePagesActivityPB.setVisibility(View.GONE);
    }

    private void getArgemnetsForNotificationAzkar() {
        if (bundle != null) {
            Type listType = new TypeToken<List<ModelAzkar>>() {
            }.getType();
            String st = bundle.getString(SAVE_POSITION_MORNING_AZKAR);
            //modelAzkarList = new Gson().fromJson(st, listType);
            position_azkar = bundle.getInt(NOTIFICATION_ID_NUMBER_AZKAR);
        }
        SwipePagesActivityPB.setVisibility(View.GONE);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        SharedPerefrenceHelper.putInt(this, SAVE_IMAGES, save_position);
        SharedPerefrenceHelper.putInt(this, SAVE_AZKAR, save_position_azkar);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        save_position = savedInstanceState.getInt(SAVE_IMAGES);
        save_position_azkar = savedInstanceState.getInt(SAVE_AZKAR);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.item_anim_no_thing, R.anim.item_anim_slide_from_bottom);
    }

}