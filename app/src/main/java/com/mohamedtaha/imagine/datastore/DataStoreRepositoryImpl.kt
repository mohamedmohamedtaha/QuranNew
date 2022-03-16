package com.mohamedtaha.imagine.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mohamedtaha.imagine.datastore.Session.PREFERENCES_NAME
import com.mohamedtaha.imagine.datastore.Session.YOUTUBE_CHANNEL
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


}





















