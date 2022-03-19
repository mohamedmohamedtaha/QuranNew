package com.mohamedtaha.imagine.ui.home.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.youtube.player.internal.e
import com.google.android.youtube.player.internal.i
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElarbaoonElnawawyVieWModel @Inject constructor(): ViewModel() {
    private val _elarbaoonElnawawy = MutableLiveData<List<ElarbaoonElnawawyModel>>()
    val elarbaoonElnawawy get() = _elarbaoonElnawawy


    fun getElarbaoonElnawawy(context: Context) {
        val elnawawyModelList = ArrayList<ElarbaoonElnawawyModel>()
        val number_elhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_number_elhadeth)
        val name_elhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_name_elhadeth)
        val text_elhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_text_elhadeth)
        val description_elhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_decription_elhadeth)
        val transelate_elhadeth =
            context.resources.getStringArray(R.array.elarbaon_elnawawaya_translate_elhadeth)
        for (i in number_elhadeth.indices) {
            val elnawawyModel = ElarbaoonElnawawyModel()
            elnawawyModel.numberElhadeth = number_elhadeth[i]
            elnawawyModel.nameElhadeth = name_elhadeth[i]
            elnawawyModel.textElhadeth = text_elhadeth[i]
            elnawawyModel.descriptionElhadeth = description_elhadeth[i]
            elnawawyModel.translateElhadeth = transelate_elhadeth[i]
            elnawawyModel.position = i
            elnawawyModelList.add(elnawawyModel);
        }
        _elarbaoonElnawawy.value = elnawawyModelList
    }
}