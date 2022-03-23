package com.mohamedtaha.imagine.hilt.module

import android.os.Bundle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.util.*

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun timerProvide():Timer = Timer()

    @Provides
    fun bundleProvide():Bundle = Bundle()

}