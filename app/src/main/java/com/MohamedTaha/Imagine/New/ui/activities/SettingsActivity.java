package com.MohamedTaha.Imagine.New.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.MohamedTaha.Imagine.New.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_activity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
