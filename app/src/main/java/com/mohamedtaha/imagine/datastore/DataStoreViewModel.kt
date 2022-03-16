package com.mohamedtaha.imagine.datastore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val repository: DataStoreRepository):ViewModel(){
    val youTubeChannel :Flow<String> get() = repository.getYouTubeChannel()

    fun saveYouTubeChannel(link:String){
        viewModelScope.launch { repository.saveYouTubeChannel(link) }
    }

}