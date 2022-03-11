package com.mohamedtaha.imagine.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.mvp.model.ModelAzkar;
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity;
import com.mohamedtaha.imagine.ui.fragments.SwarFragment;

import static com.mohamedtaha.imagine.ui.fragments.AzkarFragment.SAVE_AZKAR;

public class ShowDialog {
    public static void showDialogForRetrieveReadingSora(Activity activity, ViewPager SwipePagesActivityVP, int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_show_dialog);
        TextView textView =  dialog.findViewById(R.id.show_text);
        textView.setText(activity.getResources().getString(R.string.do_want_Save));
        Button yesButton =  dialog.findViewById(R.id.BT_Yes);
        Button noButton =  dialog.findViewById(R.id.BT_No);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = SharedPerefrenceHelper.getInt(activity, SwarFragment.SAVE_IMAGES, 0);
                SwipePagesActivityVP.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPerefrenceHelper.removeData(activity);
                SwipePagesActivityVP.setCurrentItem(position);
                dialog.dismiss();

            }
        });
        dialog.show();
    }

    public static void showDialog(Activity activity, int save_position, String text) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_details);
        TextView tv_save_position =  dialog.findViewById(R.id.TV_Save_Position);
        RelativeLayout relativeLayout =  dialog.findViewById(R.id.Relative);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        tv_save_position.setText(text);
        tv_save_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPerefrenceHelper.removeData(activity);
                SharedPerefrenceHelper.putBoolean(activity, SwipePagesActivity.IS_TRUE, true);
                SharedPerefrenceHelper.putInt(activity, SwarFragment.SAVE_IMAGES, save_position);
                HelperClass.customToast(activity, activity.getResources().getString(R.string.save));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showDialogAzkar(Activity activity, int save_position, String text, ModelAzkar modelAzkar) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_details);
        TextView tv_save_position = dialog.findViewById(R.id.TV_Save_Position);
        TextView tv_save_zakr = dialog.findViewById(R.id.TV_Save_zakr);
        tv_save_zakr.setVisibility(View.VISIBLE);
        tv_save_position.setText(text);
        tv_save_zakr.setText(activity.getString(R.string.save_zkr));

        RelativeLayout relativeLayout =  dialog.findViewById(R.id.Relative);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        tv_save_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPerefrenceHelper.putBooleanForAzkar(activity, SwipePagesActivity.IS_TRUE_AZKAR, true);
                SharedPerefrenceHelper.putIntForAzkar(activity, SAVE_AZKAR, save_position);
                HelperClass.customToast(activity, activity.getResources().getString(R.string.save));
                dialog.dismiss();
            }
        });
        tv_save_zakr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB){
                    ClipboardManager clipboardManager = (ClipboardManager)activity.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboardManager.setText(modelAzkar.getName_azkar() + "\n" + modelAzkar.getDescribe_azkar());
//                }else {
//                    ClipboardManager clipboardManager = (ClipboardManager)activity.getSystemService(Context.CLIPBOARD_SERVICE);
//                    ClipData clipData = ClipData.newPlainText("name ","taha");
//                    clipboardManager.setPrimaryClip(clipData);
//                       }
                HelperClass.customToast(activity, activity.getResources().getString(R.string.save_zkr_done));
                dialog.dismiss();


            }
        });
       dialog.show();
    }

    public static void showDialogForRetrieveAzkar(Activity activity, ViewPager SwipePagesActivityVP, int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_show_dialog);
        TextView textView = dialog.findViewById(R.id.show_text);
        textView.setText(activity.getResources().getString(R.string.do_want_Save_azkar));
        Button yesButton =  dialog.findViewById(R.id.BT_Yes);
        Button noButton =  dialog.findViewById(R.id.BT_No);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = SharedPerefrenceHelper.getIntForAzkar(activity, SAVE_AZKAR, 0);
                SwipePagesActivityVP.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPerefrenceHelper.removeDataForAzkar(activity);
                SwipePagesActivityVP.setCurrentItem(position);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showDialogForCkeckDownload(Activity activity, String name_sora) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_show_dialog);
        TextView textView =  dialog.findViewById(R.id.show_text);
        textView.setText(name_sora + activity.getResources().getString(R.string.do_want_Save_sound));
        Button yesButton =  dialog.findViewById(R.id.BT_Yes);
        Button noButton =  dialog.findViewById(R.id.BT_No);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}