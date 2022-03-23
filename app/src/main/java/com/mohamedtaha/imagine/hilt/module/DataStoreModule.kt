package com.mohamedtaha.imagine.hilt.module

import android.content.Context
import com.mohamedtaha.imagine.datastore.DataStoreRepository
import com.mohamedtaha.imagine.datastore.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {
    @Provides
    @Singleton
    fun dataStoreRepositoryImplProvider(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepositoryImpl(context)

}