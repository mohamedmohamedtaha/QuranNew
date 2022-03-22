package com.mohamedtaha.imagine.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val repository: DataStoreRepository) :
    ViewModel() {
    val youTubeChannel: Flow<String> get() = repository.getYouTubeChannel()

    fun saveYouTubeChannel(link: String) {
        viewModelScope.launch { repository.saveYouTubeChannel(link) }
    }

    val getReadingQuran get() = repository.getReadingQuran()
    fun saveReadingQuran(numberPage: Int) {
        viewModelScope.launch { repository.saveReadingQuran(numberPage) }
    }

    fun removeReadingQuran() {
        viewModelScope.launch { repository.removeReadingQuran() }
    }

    fun saveRemembrances(numberPage: Int) {
        viewModelScope.launch {
            repository.saveRemembrances(numberPage)
        }
    }

    val  getRemembrances get() = repository.getRemembrances()
    fun removeRemembrances() {
        viewModelScope.launch { repository.removeRemembrances() }
    }

}