package com.mohamedtaha.imagine.ui.home.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AzkarViewModel @Inject constructor(): ViewModel(){

    private val _azkar = MutableLiveData<List<ModelAzkar>>()
    val azkar get() = _azkar
    fun getAllAzkar(context: Context) {
        val azkar = ArrayList<ModelAzkar>()
        val arrayAzkar = context.resources.getStringArray(R.array.azkar);
        val array_describe_azkar = context.resources.getStringArray(R.array.describe_azkar);
        for (i in arrayAzkar.indices) {
            val modelAzkar = ModelAzkar()
            modelAzkar.name_azkar = arrayAzkar[i]
            modelAzkar.describe_azkar = array_describe_azkar[i]
            modelAzkar.position = i
            azkar.add(modelAzkar)
        }
        _azkar.value = azkar
    }
    fun getAllAzkarBySearch(context: Context,search:String) {
        val azkar = ArrayList<ModelAzkar>()
        val arrayAzkar = context.resources.getStringArray(R.array.azkar);
        val array_describe_azkar = context.resources.getStringArray(R.array.describe_azkar);
        for (i in arrayAzkar.indices) {
            if (arrayAzkar[i].contains(search)){
            val modelAzkar = ModelAzkar()
            modelAzkar.name_azkar = arrayAzkar[i]
            modelAzkar.describe_azkar = array_describe_azkar[i]
            modelAzkar.position = i
            azkar.add(modelAzkar)}
        }
        _azkar.value = azkar
    }

}