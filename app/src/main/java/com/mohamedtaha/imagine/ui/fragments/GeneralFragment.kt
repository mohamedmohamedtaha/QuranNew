package com.mohamedtaha.imagine.ui.fragments

import androidx.preference.PreferenceFragmentCompat
import android.os.Bundle
import com.mohamedtaha.imagine.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneralFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.general_preferences, rootKey)
    }
}