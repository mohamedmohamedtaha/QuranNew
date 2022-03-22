package com.mohamedtaha.imagine.hilt.module

import android.content.Intent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IntentModule {
    @Provides
    fun intentProvide():Intent = Intent()
}