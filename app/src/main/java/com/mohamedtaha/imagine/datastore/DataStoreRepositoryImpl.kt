package com.mohamedtaha.imagine.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.preference.Preference
import com.mohamedtaha.imagine.datastore.Session.PREFERENCES_NAME
import com.mohamedtaha.imagine.datastore.Session.SAVE_READING_QURAN
import com.mohamedtaha.imagine.datastore.Session.YOUTUBE_CHANNEL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepositoryImpl @Inject constructor(private val context: Context) :
    DataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
    override suspend fun saveYouTubeChannel(link: String) {
        val preferencesKeyYoutubeChannel = stringPreferencesKey(YOUTUBE_CHANNEL)
        context.dataStore.edit { preferences ->
            preferences[preferencesKeyYoutubeChannel] = link
        }
    }

    override fun getYouTubeChannel() =
        context.dataStore.data.map {
            val preferencesKeyYouTubeChannel = stringPreferencesKey(YOUTUBE_CHANNEL)
            it[preferencesKeyYouTubeChannel] ?: ""
        }

    override suspend fun saveReadingQuran(numberPage:Int) {
        val preferencesKey  = intPreferencesKey(SAVE_READING_QURAN)
        context.dataStore.edit { preferences->
            preferences[preferencesKey] =  numberPage
        }
    }

    override fun getReadingQuran() = context.dataStore.data.map {
        val preferencesKey = intPreferencesKey(SAVE_READING_QURAN)
        it[preferencesKey] ?: -1
    }

    override suspend fun removeReadingQuran() = context.dataStore.edit {
        val preferencesKey = stringPreferencesKey(SAVE_READING_QURAN)
        it.remove(preferencesKey)
    }


}





















