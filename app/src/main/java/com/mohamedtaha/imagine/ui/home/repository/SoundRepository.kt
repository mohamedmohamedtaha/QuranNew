package com.mohamedtaha.imagine.ui.home.repository

import com.mohamedtaha.imagine.mvp.model.ImageModel

interface SoundRepository {
    suspend fun getDataForReader(position: Int): ArrayList<ImageModel>
}