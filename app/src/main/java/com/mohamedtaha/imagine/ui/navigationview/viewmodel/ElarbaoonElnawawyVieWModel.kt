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
    private val _descriptionElhadeth = MutableLiveData<String>()
    val descriptionElhadeth get() = _descriptionElhadeth

    private val _translateElhadeth = MutableLiveData<String>()
    val translateElhadeth get() = _translateElhadeth
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
    fun getDescriptionElhadeth(context: Context,position_elhadeth:Int){

       viewModelScope.launch {

           try {
               val text_elhadeth = context.resources.getStringArray(R.array.elarbaon_elnawawaya_decription_elhadeth)
               for (i in text_elhadeth.indices) {
                   if (position_elhadeth == i) {
                       _descriptionElhadeth.value = text_elhadeth[i]
                       break
                   }
               }

           }catch (e:Exception){

           }
       }
    }
    fun gettranslate(context: Context,position_elhadeth:Int){
        viewModelScope.launch {
            try {
                val text_elhadeth =
                    context.resources.getStringArray(R.array.elarbaon_elnawawaya_translate_elhadeth)
                for (i in text_elhadeth.indices) {
                    Log.d("TAG", text_elhadeth[i])
                    if (position_elhadeth == i) {
                        _translateElhadeth.value = text_elhadeth[i]
                        break
                    }
                }
            }catch (e:Exception){

            }
        }
    }
}