package com.mohamedtaha.imagine.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveYouTubeChannel(link:String)
    fun getYouTubeChannel():Flow<String>
}