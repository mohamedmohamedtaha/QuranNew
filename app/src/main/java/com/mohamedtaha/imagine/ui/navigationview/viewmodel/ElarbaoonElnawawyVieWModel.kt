package com.mohamedtaha.imagine.ui.navigationview.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ElarbaoonElnawawyVieWModel @Inject constructor(): ViewModel() {
    private val _elarbaoonElnawawy = MutableLiveData<List<ElarbaoonElnawawyModel>>()
    val elarbaoonElnawawy get() = _elarbaoonElnawawy
    fun getElarbaoonElnawawy(context: Context) {
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
        for (i in numberElhadeth.indices) {
            val elnawawyModel = ElarbaoonElnawawyModel()
            elnawawyModel.numberElhadeth = numberElhadeth[i]
            elnawawyModel.nameElhadeth = nameElhadeth[i]
            elnawawyModel.textElhadeth = textElhadeth[i]
            elnawawyModel.descriptionElhadeth = descriptionElhadeth[i]
            elnawawyModel.translateElhadeth = transelateElhadeth[i]
            elnawawyModel.position = i
            elnawawyModelList.add(elnawawyModel);
        }
        _elarbaoonElnawawy.value = elnawawyModelList
    }
}