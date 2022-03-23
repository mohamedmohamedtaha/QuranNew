package com.mohamedtaha.imagine.ui.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedtaha.imagine.mvp.model.ImageModel
import com.mohamedtaha.imagine.ui.home.repository.SoundRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SoundViewModel @Inject constructor(private val soundRepository: SoundRepository) :
    ViewModel() {
    private val _getAllNameSore = MutableLiveData<ArrayList<ImageModel>>()
    val getAllNameSore get() = _getAllNameSore

    fun getAllNameSora(position: Int) {
        viewModelScope.launch {
            try {
                _getAllNameSore.value = soundRepository.getDataForReader(position)

            } catch (e: Exception) {

            }
        }
    }
}