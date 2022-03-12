package com.mohamedtaha.imagine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.preference.PreferenceManager;

import com.mohamedtaha.imagine.helper.SharedPerefrenceHelper;

import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity;

public class GetMethodPreferences {
    public static final String COMPARE_METHOD = "compare_method";
    private Context context;
    private SharedPreferences sharedPreferences;
    private String repear;
    private String compare_methods = null;
    private boolean isValueForPrayerTimesChanged = false;
    private NetworkConnected networkConnected;

    public String getRepear() {
        return repear;
    }

    public void setRepear(String repear) {
        this.repear = repear;
    }

    public String getCompare_methods() {
        return compare_methods;
    }

    public void setCompare_methods(String compare_methods) {
        this.compare_methods = compare_methods;
    }

    public GetMethodPreferences(Context context) {
        this.context = context;
        initialization();

    }

    private String getMethodPreferences(String country_name) {
        if (country_name.equals(context.getString(R.string.saudi_arabia))) {
            customForPreferences(context.getString(R.string.settings_method_umm_al_qura_university_makkah_value),context.getString(R.string.settings_method_umm_al_qura_university_makkah_value));
        } else if (country_name.equals(context.getString(R.string.qatar))) {
            customForPreferences(context.getString(R.string.settings_method_qatar_value),context.getString(R.string.settings_method_qatar_value));
        } else if (country_name.equals(context.getString(R.string.kuwait))) {
            customForPreferences(context.getString(R.string.settings_method_kuwait_value),context.getString(R.string.settings_method_kuwait_value));
        } else if (country_name.equals(context.getString(R.string.turkey))) {
            customForPreferences(context.getString(R.string.settings_method_diyanet_işleri_başkanlığı_turkey_value),context.getString(R.string.settings_method_diyanet_işleri_başkanlığı_turkey_value));
        } else if (country_name.equals(context.getString(R.string.russia))) {
            customForPreferences(context.getString(R.string.settings_method_spiritual_administration_of_muslims_of_russia_value),context.getString(R.string.settings_method_spiritual_administration_of_muslims_of_russia_value));
        } else {
            customForPreferences(context.getString(R.string.settings_method_key),context.getString(R.string.settings_method_default));
        }
        return repear;
    }
    private void initialization(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        repear = sharedPreferences.getString(context.getString(R.string.settings_method_key),
                context.getString(R.string.settings_method_default));
        compare_methods = SharedPerefrenceHelper.getStringCompareMethod(context, COMPARE_METHOD, "5");
        if (SharedPerefrenceHelper.getBooleanForWayUsing(context, NavigationDrawaberActivity.IS_FIRST_TIME_WAY_USING, false)) {
            Log.d("TAG", "Repear is " + repear);
            if (!compare_methods.equals(repear)) {
                showDialogBoxForCompareMethod();
            }
        }
}
    private void showDialogBoxForCompareMethod() {
        if (context != null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(R.string.change_method);
            alertDialog.setCancelable(false);
            alertDialog.setMessage(R.string.is_want_new_data);
            alertDialog.setPositiveButton(R.string.textYes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isValueForPrayerTimesChanged = true;
                    networkConnected.checkNetworkConnected();
                }
            });
            alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialogCreator = alertDialog.create();
            dialogCreator.show();
            Button neagtive_button = dialogCreator.getButton(DialogInterface.BUTTON_NEGATIVE);
            Button positive_button = dialogCreator.getButton(DialogInterface.BUTTON_POSITIVE);
            LinearLayout.LayoutParams params_for_space_between_buttons = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params_for_space_between_buttons.setMargins(0, 0, 30, 0);
            neagtive_button.setLayoutParams(params_for_space_between_buttons);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                positive_button.setBackgroundColor(context.getColor(R.color.colorAccent));
                neagtive_button.setBackgroundColor(context.getColor(R.color.colorAccent));
            } else {
                positive_button.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
                neagtive_button.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
            }
        } else {
            Log.i("TAG", "Activity is null....");
        }
    }

    private String customForPreferences(String method_key, String method_default) {
        SharedPerefrenceHelper.putStringCompareMethod(context, COMPARE_METHOD, repear);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        repear = sharedPreferences.getString(method_key, method_default);
        return repear;
    }

}
