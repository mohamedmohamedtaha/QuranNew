package com.MohamedTaha.Imagine.New.helper;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPerefrenceHelper {
    private static final String SHARED_PREFRENCES_NAME = "com.MohamedTaha.Imagine.New.helper.save_path_images";
    private static final String SHARED_PREFRENCES_NAME_AZKAR = "com.MohamedTaha.Imagine.New.helper.save_path_azkar";
    private static final String SHARED_PREFRENCES_WAY_USING = "com.MohamedTaha.Imagine.New.helper.way_using";
    private static final String SHARED_PREFRENCES_COMPARE_METHOD = "com.MohamedTaha.Imagine.New.helper.compare_method";
    private static final String SHARED_PREFRENCES_AZAN = "com.MohamedTaha.Imagine.New.helper.shared_preference_azan";

    //@Inject
    public SharedPerefrenceHelper() {
    }

    private static SharedPreferences getSharedPrefrencesForWayUsing(Context context) {
        return context.getSharedPreferences(SHARED_PREFRENCES_WAY_USING, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSharedPrefrences(Context context) {
        return context.getSharedPreferences(SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSharedPrefrencesForAzkar(Context context) {
        return context.getSharedPreferences(SHARED_PREFRENCES_NAME_AZKAR, Context.MODE_PRIVATE);
    }

    public static void putBooleanForAzkar(Context context, String key, boolean value) {
        getSharedPrefrencesForAzkar(context).edit().putBoolean(key, value).apply();
    }

    public static void putIntForAzkar(Context context, String key, int value) {
        getSharedPrefrencesForAzkar(context).edit().putInt(key, value).apply();
    }

    public static int getIntForAzkar(Context context, String key, int defaultValue) {
        return getSharedPrefrencesForAzkar(context).getInt(key, defaultValue);
    }

    public static boolean getBooleanForAzkar(Context context, String key, boolean defaultValue) {
        return getSharedPrefrencesForAzkar(context).getBoolean(key, defaultValue);
    }

    public static boolean getBooleanForWayUsing(Context context, String key, boolean defaultValue) {
        return getSharedPrefrencesForWayUsing(context).getBoolean(key, defaultValue);
    }

    public static void putBooleanForWayUsing(Context context, String key, boolean value) {
        getSharedPrefrencesForWayUsing(context).edit().putBoolean(key, value).apply();
    }

    public static void removeDataForWayUsing(Context context) {
        getSharedPrefrencesForWayUsing(context).edit().clear().apply();
    }

    public static void removeDataForAzkar(Context context) {
        getSharedPrefrencesForAzkar(context).edit().clear().apply();
    }

    public static void putInt(Context context, String key, int value) {
        getSharedPrefrences(context).edit().putInt(key, value).apply();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPrefrences(context).edit().putBoolean(key, value).apply();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        return getSharedPrefrences(context).getInt(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPrefrences(context).getBoolean(key, defaultValue);
    }

    public static void removeData(Context context) {
        getSharedPrefrences(context).edit().clear().apply();
    }

    private static SharedPreferences getSharedPrefrencesCompareMethod(Context context) {
        return context.getSharedPreferences(SHARED_PREFRENCES_COMPARE_METHOD, Context.MODE_PRIVATE);
    }

    public static void putStringCompareMethod(Context context, String key, String value) {
        getSharedPrefrencesCompareMethod(context).edit().putString(key, value).apply();
    }

    public static String getStringCompareMethod(Context context, String key, String defaultValue) {
        return getSharedPrefrencesCompareMethod(context).getString(key, defaultValue);
    }

    private static SharedPreferences getSharedPreferencesListPreferenceAzan(Context context) {
        return context.getSharedPreferences(SHARED_PREFRENCES_AZAN, Context.MODE_PRIVATE);
    }

    public static void putStringAzan(Context context, String key, String value) {
        getSharedPreferencesListPreferenceAzan(context).edit().putString(key, value).apply();
    }

}

