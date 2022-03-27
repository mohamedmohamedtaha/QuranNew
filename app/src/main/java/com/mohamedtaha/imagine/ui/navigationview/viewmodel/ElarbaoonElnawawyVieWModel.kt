package com.mohamedtaha.imagine.ui.navigationview.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElarbaoonElnawawyVieWModel @Inject constructor() : ViewModel() {
    private val _elarbaoonElnawawy = MutableLiveData<List<ElarbaoonElnawawyModel>>()
    val elarbaoonElnawawy get() = _elarbaoonElnawawy
    fun getElarbaoonElnawawy(context: Context) {
        viewModelScope.launch {
            try {
        val elnawawyModelList = ArrayList<ElarbaoonElnawawyModel>()
        val numberElhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_number_elhadeth)
        val nameElhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_name_elhadeth)
        val textElhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_text_elhadeth)
        val descriptionElhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_decription_elhadeth)
        val transelateElhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_translate_elhadeth)
        for (i in nameElhadeth.indices) {
            val elnawawyModel = ElarbaoonElnawawyModel()
            elnawawyModel.nameElhadeth = nameElhadeth[i]
            elnawawyModel.numberElhadeth = numberElhadeth[i]
            elnawawyModel.textElhadeth = textElhadeth[i]
            elnawawyModel.descriptionElhadeth = descriptionElhadeth[i]
            elnawawyModel.translateElhadeth = transelateElhadeth[i]
            elnawawyModel.position = i
            elnawawyModelList.add(elnawawyModel);
        }
        _elarbaoonElnawawy.value = elnawawyModelList
        }catch (e:java.lang.Exception){

        }}
    }

    fun getElarbaoonElnawawyBySearch(context: Context, search: String) {
        viewModelScope.launch {
            try {
                val elnawawyModelList = ArrayList<ElarbaoonElnawawyModel>()
                val numberElhadeth =
                    context.resources.getStringArray(R.array.elarbaon_elnawawaya_number_elhadeth)
                val nameElhadeth =
                    context.resources.getStringArray(R.array.elarbaon_elnawawaya_name_elhadeth)
                val textElhadeth =
                    context.resources.getStringArray(R.array.elarbaon_elnawawaya_text_elhadeth)
                val descriptionElhadeth =
                    context.resources.getStringArray(R.array.elarbaon_elnawawaya_decription_elhadeth)
                val transelateElhadeth =
                    context.resources.getStringArray(R.array.elarbaon_elnawawaya_translate_elhadeth)
                for (i in nameElhadeth.indices) {
                    if (nameElhadeth[i].contains(search)) {
                        val elnawawyModel = ElarbaoonElnawawyModel()
                        elnawawyModel.nameElhadeth = nameElhadeth[i]
                        elnawawyModel.numberElhadeth = numberElhadeth[i]
                        elnawawyModel.textElhadeth = textElhadeth[i]
                        elnawawyModel.descriptionElhadeth = descriptionElhadeth[i]
                        elnawawyModel.translateElhadeth = transelateElhadeth[i]
                        elnawawyModel.position = i
                        elnawawyModelList.add(elnawawyModel)
                    }
                }
                _elarbaoonElnawawy.value = elnawawyModelList
            } catch (e: Exception) {

            }
        }
    }
}