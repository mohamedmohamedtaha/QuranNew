package com.mohamedtaha.imagine.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.mohamedtaha.imagine.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.notification_preferences, rootKey)
    }
}