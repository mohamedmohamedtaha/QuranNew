package com.mohamedtaha.imagine.hilt.module

import com.mohamedtaha.imagine.configfirebase.RemoteConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {
    @Provides
    @Singleton
    fun remoteConfigProvide() = RemoteConfig()
}