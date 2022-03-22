package com.mohamedtaha.imagine.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveYouTubeChannel(link:String)
    fun getYouTubeChannel():Flow<String>

    suspend fun saveReadingQuran(numberPage:Int)
    fun getReadingQuran():Flow<Int>
    suspend fun removeReadingQuran():Preferences

    suspend fun saveRemembrances(numberPage: Int)
    fun getRemembrances():Flow<Int>
    suspend fun removeRemembrances()
}