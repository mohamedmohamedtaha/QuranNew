package com.MohamedTaha.Imagine.New.ui.fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.MohamedTaha.Imagine.New.R;

public class GeneralFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.general_preferences, rootKey);
    }
}
