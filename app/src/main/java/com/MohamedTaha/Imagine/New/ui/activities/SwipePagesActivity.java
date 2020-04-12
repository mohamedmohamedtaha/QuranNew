package com.MohamedTaha.Imagine.New.ui.activities;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.New.Adapter.AdapterForAzkarSwipe;
import com.MohamedTaha.Imagine.New.Adapter.AdapterForSwipe;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.helper.SharedPerefrenceHelper;
import com.MohamedTaha.Imagine.New.helper.ShowDialog;
import com.MohamedTaha.Imagine.New.helper.Utilities;
import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;
import com.booking.rtlviewpager.RtlViewPager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.New.helper.Images.SAVE_POSITION;
import static com.MohamedTaha.Imagine.New.notification.quran.AlarmReceiver.NOTIFICATION_ID;
import static com.MohamedTaha.Imagine.New.notification.quran.AlarmReceiver.SAVE_Position_Notification;
import static com.MohamedTaha.Imagine.New.notification.quran.AlarmReceiver.TIME_SEND;
import static com.MohamedTaha.Imagine.New.ui.fragments.AzkarFragment.SAVE_AZKAR;
import static com.MohamedTaha.Imagine.New.ui.fragments.AzkarFragment.SAVE_POTION_AZKAR;
import static com.MohamedTaha.Imagine.New.ui.fragments.GridViewFragment.SAVE_IMAGES;
import static com.MohamedTaha.Imagine.New.ui.fragments.GridViewFragment.SAVE_STATE;
import static com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment.SAVE_ALL_IMAGES;
import static com.MohamedTaha.Imagine.New.ui.fragments.SplashFragment.SAVE_PAGE;

public class SwipePagesActivity extends AppCompatActivity {
    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<Integer> imagesNotification = new ArrayList<>();
    ArrayList<Integer> imagesFirst = new ArrayList<>();

    public static final String IS_TRUE = "is_true";
    public static final String IS_TRUE_AZKAR = "is_true_azkar";
    @BindView(R.id.SwipePagesActivity_PB)
    ProgressBar SwipePagesActivityPB;
    @BindView(R.id.SwipePagesActivity_VP)
    RtlViewPager SwipePagesActivityVP;
    List<ModelAzkar> modelAzkarList = new ArrayList<>();
    private int save_position;
    private int save_position_azkar;
    private int position = 1;
    private int position_azkar = 0;
    Bundle bundle;
    int notificationId =-1;
    String language_name ;


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
        //for close Notification
        notificationId = getIntent().getIntExtra(NOTIFICATION_ID, -1);
        int timeSend  = getIntent().getIntExtra(TIME_SEND,-1);
        if (notificationId >= 0) {
            getArgemnetsNotification();
            createImageNotification();
            AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, imagesNotification);
            SwipePagesActivityVP.setAdapter(adapterForSwipe);
            SwipePagesActivityVP.setCurrentItem(notificationId);
            Log.d("TAG" , " " + notificationId);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.cancel(timeSend);

        } else if (bundle.getBoolean(SAVE_STATE)) {
            getArgemnets();
            createImage();
            AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, images, new AdapterForSwipe.showDetail() {
                @Override
                public void showDetails(int positon) {
                    ShowDialog.showDialog(SwipePagesActivity.this, save_position, getString(R.string.save_position));
                }
            });
            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    save_position = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            SwipePagesActivityVP.setAdapter(adapterForSwipe);
                if (SharedPerefrenceHelper.getBoolean(this, IS_TRUE, false)) {
                    ShowDialog.showDialog(this, SwipePagesActivityVP, position);
                } else {
                    SwipePagesActivityVP.setCurrentItem(position);
                }
        } else if (bundle.getBoolean(SAVE_PAGE)){
            getImagesFirst();
            createImagesFirst();
            AdapterForSwipe adapterForSwipe = new AdapterForSwipe(this, imagesFirst, new AdapterForSwipe.showDetail() {
                @Override
                public void showDetails(int positon) {
                    ShowDialog.showDialog(SwipePagesActivity.this, save_position, getString(R.string.save_position));
                }
            });
            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    save_position = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            SwipePagesActivityVP.setAdapter(adapterForSwipe);
            int position = SharedPerefrenceHelper.getInt(getApplicationContext(), SAVE_IMAGES, 0);
            SwipePagesActivityVP.setCurrentItem(position);
        }

            else {
            getArgemnetsForAzkar();
            AdapterForAzkarSwipe adapterForAzkarSwipe = new AdapterForAzkarSwipe(SwipePagesActivity.this, modelAzkarList, new AdapterForAzkarSwipe.showDetail() {
                @Override
                public void showDetails(ModelAzkar modelAzkar) {
                    ShowDialog.showDialogAzkar(SwipePagesActivity.this, save_position_azkar, getString(R.string.save_position_alzekr),modelAzkar);
                }
            });
            SwipePagesActivityVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    save_position_azkar = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            SwipePagesActivityVP.setAdapter(adapterForAzkarSwipe);

            if (SharedPerefrenceHelper.getBooleanForAzkar(this, IS_TRUE_AZKAR, false)) {
                ShowDialog.showDialogForAzkar(this, SwipePagesActivityVP, position_azkar);
            } else {
                SwipePagesActivityVP.setCurrentItem(position_azkar);
            }
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
            Log.d("TAG" , " " + imagesNotification);
        }
    }
    private void createImageNotification() {
        if (imagesNotification != null) {
            for (int i = 0; i < imagesNotification.size(); i++) {
                bundle.putInt(SAVE_Position_Notification, imagesNotification.get(i));
                    }
                          //  bundle.putInt(SAVE_Position_Notification, imagesNotification);
        Log.d("TAG" , " " + imagesNotification);


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
            modelAzkarList = new Gson().fromJson(st, listType);
            position_azkar = bundle.getInt(SAVE_POTION_AZKAR);
        }
        SwipePagesActivityPB.setVisibility(View.GONE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        SharedPerefrenceHelper.putInt(this, SAVE_IMAGES, save_position);
        SharedPerefrenceHelper.putInt(this, SAVE_AZKAR, save_position_azkar);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        save_position = savedInstanceState.getInt(SAVE_IMAGES);
        save_position_azkar = savedInstanceState.getInt(SAVE_AZKAR);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        imagesNotification.clear();
  //      images.clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (bundle.getBoolean(SAVE_PAGE)){
//            Intent intent = new Intent(SwipePagesActivity.this, SwipePagesActivity.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.item_anim_no_thing, R.anim.item_anim_slide_from_bottom);
//        }else {
            overridePendingTransition(R.anim.item_anim_no_thing, R.anim.item_anim_slide_from_bottom);

      //  }
    }
}