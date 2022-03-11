package com.mohamedtaha.imagine.ui.home.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.mvp.model.ModelSora
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SwarViewModel @Inject constructor() : ViewModel() {
    private val _modelSora = MutableLiveData<List<ModelSora>>()
    val modelSora get() = _modelSora

    fun getAllSwar(context:Context){
        val allSora = ArrayList<ModelSora>()
        val nameAllSwar = context.resources.getStringArray(R.array.name_allSwar);
       val  nzolAlsora = context.resources.getStringArray(R.array.nzolElswar);
        for (name in nameAllSwar.indices) {
            val modelSora = ModelSora()
            modelSora.nameSora = nameAllSwar[name]
            modelSora.nzolElsora = nzolAlsora[name]
            modelSora.position = name
            allSora.add(modelSora)
        }
        _modelSora.value = allSora
    }
}