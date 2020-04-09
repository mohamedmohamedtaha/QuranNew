package com.MohamedTaha.Imagine.New.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.MohamedTaha.Imagine.New.R;

import java.util.Locale;

public class HelperClass {

    public static void customToast(Activity activity, String ToastTitle) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) activity.findViewById(R.id.toast_layout_root));
        TextView text = layout.findViewById(R.id.text);
        text.setText(ToastTitle);
        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 150);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    //to change Language
    public static void change_language(String localeCode,Context context) {
        Resources resources = context.getResources();
        //Change Locale settings in the App.
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale(localeCode.toLowerCase()));
    }
    // This method for handle Activity
    public static void startActivity(Context context, Class<?> toActivity) {
        Intent startActivity = new Intent(context, toActivity);
        startActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startActivity);
    }

    public static void replece(Fragment fragment, FragmentManager fragmentManager, int id) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        //transaction.commit();
        // for change from commit() because don't happen Error
        //   java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        transaction.commitAllowingStateLoss();

    }
    //This method for Exit App
    public static void closeApp(Context context) {
        Intent exitAppIntent = new Intent(Intent.ACTION_MAIN);
        exitAppIntent.addCategory(Intent.CATEGORY_HOME);
        exitAppIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(exitAppIntent);
    }

    public void custom_toolbar(Context context, Toolbar toolbar) {
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
//        ((AppCompatActivity)context).setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity)context.setDisplayShowHomeEnabled(true);
//        //for delete label for Activity
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


}
