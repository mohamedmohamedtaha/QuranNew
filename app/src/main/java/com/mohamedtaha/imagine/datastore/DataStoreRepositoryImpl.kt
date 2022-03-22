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
import com.mohamedtaha.imagine.datastore.Session.SAVE_REMEMBRANCES
import com.mohamedtaha.imagine.datastore.Session.YOUTUBE_CHANNEL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepositoryImpl @Inject constructor(private val context: Context) :
    DataStoreRepository {
    private val preferencesKeyYoutubeChannel = stringPreferencesKey(YOUTUBE_CHANNEL)
    private val preferencesKeySaveReading  = intPreferencesKey(SAVE_READING_QURAN)
    private val preferencesKeySaveRemembrances = intPreferencesKey(SAVE_REMEMBRANCES)
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
    override suspend fun saveYouTubeChannel(link: String) {
        context.dataStore.edit { preferences ->
            preferences[preferencesKeyYoutubeChannel] = link
        }
    }

    override fun getYouTubeChannel() =
        context.dataStore.data.map {
            it[preferencesKeyYoutubeChannel] ?: ""
        }

    override suspend fun saveReadingQuran(numberPage:Int) {
        context.dataStore.edit { preferences->
            preferences[preferencesKeySaveReading] =  numberPage
        }
    }

    override fun getReadingQuran() = context.dataStore.data.map {
        it[preferencesKeySaveReading] ?: -1
    }

    override suspend fun removeReadingQuran() = context.dataStore.edit {
        it.remove(preferencesKeySaveReading)
    }

    override suspend fun saveRemembrances(numberPage: Int) {
        context.dataStore.edit { preferences->
            preferences[preferencesKeySaveRemembrances] = numberPage
        }
    }

    override fun getRemembrances() = context.dataStore.data.map {
        it[preferencesKeySaveRemembrances]?: -1
    }

    override suspend fun removeRemembrances() {
        context.dataStore.edit {
            it.remove(preferencesKeySaveRemembrances)
        }
    }


}





















