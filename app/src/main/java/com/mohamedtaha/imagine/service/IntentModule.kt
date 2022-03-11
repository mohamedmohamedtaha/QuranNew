package com.mohamedtaha.imagine.service

import android.content.Intent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class IntentModule {
    //@ScopeActivity
    @Provides
    fun intentProvide(): Intent = Intent()
}