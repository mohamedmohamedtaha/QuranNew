package com.MohamedTaha.Imagine.New.ui.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.MohamedTaha.Imagine.New.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener  {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Indicate here the XML resource you created above that holds the preferences
        setPreferencesFromResource(R.xml.preference, rootKey);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Preference section_notification = findPreference(getString(R.string.settings_Notification_key));
        Preference section_method = findPreference(getString(R.string.settings_method_key));
        Preference section_elazan = findPreference(getString(R.string.settings_azan_default));
        Preference section_switch = findPreference(getString(R.string.switch_key));
        bindPreferenceSummaryToValue(section_notification);
        bindPreferenceSummaryToValue(section_method);
        bindPreferenceSummaryToValue(section_elazan);
        bindPreferenceSummaryToBoolean(section_switch);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        // The code in this method takes care of updating the displayed preference summary after it has been changed
        String stringValue = newValue.toString();
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            //Set the summary to reflect the new value
            if (prefIndex >= 0) {
                CharSequence[] labels = listPreference.getEntries();
                preference.setSummary(labels[prefIndex]);
            }
        }else if (preference instanceof SwitchPreference) {
            preference.setSummary(stringValue);
        }else {
            preference.setSummary(stringValue);
        }
        return true;
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        String prefString = preferences.getString(preference.getKey(), "");
        onPreferenceChange(preference, prefString);
    }
    private void bindPreferenceSummaryToBoolean(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        boolean prefString = preferences.getBoolean(preference.getKey(), true);
        onPreferenceChange(preference, prefString);
    }
}