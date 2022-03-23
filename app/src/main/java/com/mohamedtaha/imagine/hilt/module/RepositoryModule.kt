package com.mohamedtaha.imagine.hilt.module

import com.mohamedtaha.imagine.ui.home.repository.SoundImp
import com.mohamedtaha.imagine.ui.home.repository.SoundRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun soundImpProvider(soundImp: SoundImp): SoundRepository = soundImp
}