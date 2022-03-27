package com.mohamedtaha.imagine.hilt.module

import android.content.Context
import androidx.room.Room
import com.mohamedtaha.imagine.room.TimingsAppDatabase
import com.mohamedtaha.imagine.room.TimingsDao
import com.mohamedtaha.imagine.room.TimingsImpl
import com.mohamedtaha.imagine.room.TimingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun databaseNameProvide():String = "timings_db"

    @Provides
    fun timingDaoProvide(timingsAppDatabase: TimingsAppDatabase) : TimingsDao = timingsAppDatabase.timingsDao()

    @Singleton
    @Provides
    fun timingAppDatabaseProvide(@ApplicationContext context: Context,databaseName:String):TimingsAppDatabase{
        return Room.databaseBuilder( context,
            TimingsAppDatabase::class.java,
            databaseName
        ).build()
    }

    @Singleton
    @Provides
    fun databaseProvide(timingsDao: TimingsDao):TimingsRepository = TimingsImpl(timingsDao)
}