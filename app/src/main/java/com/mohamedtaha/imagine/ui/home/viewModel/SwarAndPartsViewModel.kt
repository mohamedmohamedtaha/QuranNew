package com.mohamedtaha.imagine.ui.home.viewModel

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.helper.images
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.mvp.model.ImageModel
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import com.mohamedtaha.imagine.mvp.model.ModelSora
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SwarAndPartsViewModel @Inject constructor() : ViewModel() {
    private val _allSawar = MutableLiveData<List<ModelSora>>()
    val modelSora get() = _allSawar
    private val _allParts = MutableLiveData<List<ModelSora>>()
    val allParts: LiveData<List<ModelSora>> = _allParts
    private val _sound = MutableLiveData<List<ImageModel>>()
    val sound get() = _sound
    private val _azkar = MutableLiveData<List<ModelAzkar>>()
    val azkar get() = _azkar
    private val _allImages = MutableLiveData<List<Int>>()
    val allImages get() = _allImages
    private val _elarbaoonElnawawy = MutableLiveData<List<ElarbaoonElnawawyModel>>()
    val elarbaoonElnawawy get() = _elarbaoonElnawawy

    fun getAllSwar(context: Context) {
        val allSawar = ArrayList<ModelSora>()
        val nameAllSwar = context.resources.getStringArray(R.array.name_allSwar);
        val nzolAlsora = context.resources.getStringArray(R.array.nzolElswar);
        for (name in nameAllSwar.indices) {
            val modelSora = ModelSora()
            modelSora.nameSora = nameAllSwar[name]
            modelSora.nzolElsora = nzolAlsora[name]
            modelSora.position = name
            allSawar.add(modelSora)
        }
        _allSawar.value = allSawar
    }

    fun getAllParts(context: Context) {
        val allParts = ArrayList<ModelSora>()
        val nameParts = context.resources.getStringArray(R.array.allParts);
        for (name in nameParts.indices) {
            val nameSoraLocal = ModelSora()
            nameSoraLocal.namePart = nameParts[name]
            nameSoraLocal.position = name
            allParts.add(nameSoraLocal)
        }
        _allParts.value = allParts

    }

    fun getData(context: Context) {
        val images = ArrayList<ImageModel>()
        images.add(
            ImageModel(
                context.getString(R.string.dokaly_mohamed),
                R.drawable.aldokaly_mohamed,
                context.getString(R.string.qalon_an_nafea),
                0
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.elzen_mohamed),
                R.drawable.elzen_mohamed,
                context.getString(R.string.hafs_an_asem),
                1
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.al_ouoon_alkushi),
                R.drawable.al_ouoon_alkushi,
                context.getString(R.string.warsh_an_nafea),
                2
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.alfateh_mohamed),
                R.drawable.alfateh_mohamed,
                context.getString(R.string.aldawry_ebn_omar),
                3
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.shatri),
                R.drawable.shatri,
                context.getString(R.string.hafs_an_asem),
                4
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ahmed_elagamy),
                R.drawable.elagamy,
                context.getString(R.string.hafs_an_asem),
                5
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ahmed_eltrabolsy),
                R.drawable.ahmed_khdr_eltrabolsy,
                context.getString(R.string.qalon_an_nafea),
                6
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ahmed_eltrabolsy),
                R.drawable.ahmed_khdr_eltrabolsy,
                context.getString(R.string.hafs_an_asem),
                7
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ahmed_elhawashy),
                R.drawable.ahmed_elhawashy,
                context.getString(R.string.hafs_an_asem),
                8
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ahmed_saber),
                R.mipmap.logo,
                context.getString(R.string.hafs_an_asem),
                9
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ahmed_amer),
                R.drawable.ahmed_amer,
                context.getString(R.string.hafs_an_asem),
                10
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ahmed_naeane),
                R.drawable.ahmed_neanea,
                context.getString(R.string.hafs_an_asem),
                11
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.akram_al_aalakmi),
                R.drawable.akram_al_aalakmi,
                context.getString(R.string.hafs_an_asem),
                12
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ibrahim_al_akhdar),
                R.drawable.ebrahem_khoder,
                context.getString(R.string.hafs_an_asem),
                13
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ibrahim_al_dosary),
                R.drawable.ebrahem_eldosary,
                context.getString(R.string.hafs_an_asem),
                14
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ibrahim_al_dosary),
                R.drawable.ebrahem_eldosary,
                context.getString(R.string.warsh_an_nafea),
                15
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.bnder_belela),
                R.drawable.belela,
                context.getString(R.string.hafs_an_asem),
                16
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.tawfik_sayegh),
                R.drawable.tawfik_sayegh,
                context.getString(R.string.hafs_an_asem),
                17
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.jamal_shaker),
                R.drawable.jamal_shaker,
                context.getString(R.string.hafs_an_asem),
                18
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.jaman_elesemy),
                R.drawable.jaman_elesemy,
                context.getString(R.string.hafs_an_asem),
                19
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.hatem_fared),
                R.drawable.hatem_fared,
                context.getString(R.string.hafs_an_asem),
                20
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.khaled_elmehana),
                R.drawable.khaled_elmehana,
                context.getString(R.string.hafs_an_asem),
                21
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.khaled_abdelkafy),
                R.drawable.khaled_abdelkafy,
                context.getString(R.string.hafs_an_asem),
                22
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.jleel),
                R.drawable.jleel,
                context.getString(R.string.hafs_an_asem),
                23
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.qht),
                R.drawable.qht,
                context.getString(R.string.hafs_an_asem),
                24
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.tnjy),
                R.drawable.tnjy,
                context.getString(R.string.hafs_an_asem),
                25
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ramy_eldabs),
                R.mipmap.logo,
                context.getString(R.string.hafs_an_asem),
                26
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.zaki_daghistani),
                R.drawable.zaki_daghistani,
                context.getString(R.string.hafs_an_asem),
                27
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.sahl_yassen),
                R.drawable.sahl_yassen,
                context.getString(R.string.hafs_an_asem),
                28
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.s_gmd),
                R.drawable.s_gmd,
                context.getString(R.string.hafs_an_asem),
                29
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.saad),
                R.mipmap.logo,
                context.getString(R.string.hafs_an_asem),
                30
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.soud_elsherem),
                R.drawable.sherem,
                context.getString(R.string.hafs_an_asem),
                31
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.sayed),
                R.drawable.syed_rmdan,
                context.getString(R.string.hafs_an_asem),
                32
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.taher),
                R.drawable.taher,
                context.getString(R.string.hafs_an_asem),
                33
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.hkm),
                R.drawable.hkm,
                context.getString(R.string.hafs_an_asem),
                34
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.salah_hashim_m),
                R.drawable.salah_hashim_m,
                context.getString(R.string.hafs_an_asem),
                35
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.slah_elbadry),
                R.drawable.bder,
                context.getString(R.string.hafs_an_asem),
                36
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.bu_khtr),
                R.drawable.bu_khtr,
                context.getString(R.string.hafs_an_asem),
                37
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.tareq),
                R.drawable.tarek_abdelany,
                context.getString(R.string.qalon_an_nafea),
                38
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ryan),
                R.drawable.ryan,
                context.getString(R.string.hafs_an_asem),
                39
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.adel_elkalbany),
                R.drawable.elklbany,
                context.getString(R.string.hafs_an_asem),
                40
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelbaset),
                R.drawable.abdeelbaset,
                context.getString(R.string.hafs_an_asem),
                41
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelbaset),
                R.drawable.abdeelbaset,
                context.getString(R.string.warsh_an_nafea),
                42
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelbaset),
                R.drawable.abdeelbaset,
                context.getString(R.string.ekmoshaf_elmogawad),
                43
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdellah_elgeheny),
                R.drawable.elgeheny,
                context.getString(R.string.hafs_an_asem),
                44
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelrhman_elqasem),
                R.drawable.elqasem,
                context.getString(R.string.hafs_an_asem),
                45
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelrshed_sofy),
                R.drawable.soufi_hfs,
                context.getString(R.string.alsosy_ebn_omar),
                46
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelrshed_sofy),
                R.drawable.soufi_hfs,
                context.getString(R.string.khalaf_ben_hamza),
                47
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelazez_elzahrany),
                R.drawable.abdelazez_elzahrany,
                context.getString(R.string.hafs_an_asem),
                48
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelbara_elthebety),
                R.drawable.abdelbara_elthebety,
                context.getString(R.string.hafs_an_asem),
                49
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelrhman_elosa),
                R.drawable.abdelrhman_elosa,
                context.getString(R.string.hafs_an_asem),
                50
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.bari),
                R.drawable.abdelbary,
                context.getString(R.string.hafs_an_asem),
                51
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdelrhman_elsodes),
                R.drawable.elsodes,
                context.getString(R.string.hafs_an_asem),
                52
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.a_majed),
                R.drawable.a_majed,
                context.getString(R.string.hafs_an_asem),
                53
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.soufi_hfs),
                R.drawable.soufi_hfs,
                context.getString(R.string.hafs_an_asem),
                54
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.a_ahmed),
                R.drawable.a_ahmed,
                context.getString(R.string.hafs_an_asem),
                55
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.khalf),
                R.drawable.khalf,
                context.getString(R.string.hafs_an_asem),
                56
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.abdullahk),
                R.drawable.abdullahk,
                context.getString(R.string.hafs_an_asem),
                57
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.bsfr),
                R.drawable.bsfr,
                context.getString(R.string.hafs_an_asem),
                58
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.kyat),
                R.drawable.kyat,
                context.getString(R.string.hafs_an_asem),
                59
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.gulan),
                R.drawable.gulan,
                context.getString(R.string.hafs_an_asem),
                60
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mohsin_harthi),
                R.drawable.mohsin_harthi,
                context.getString(R.string.hafs_an_asem),
                61
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.obk),
                R.drawable.obk,
                context.getString(R.string.hafs_an_asem),
                62
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.kanakeri),
                R.drawable.kanakeri,
                context.getString(R.string.hafs_an_asem),
                63
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.wdod),
                R.drawable.wdod,
                context.getString(R.string.hafs_an_asem),
                64
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.a_jbr),
                R.drawable.a_jbr,
                context.getString(R.string.hafs_an_asem),
                65
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ali_elhozefy),
                R.drawable.ali_elhozefy,
                context.getString(R.string.qalon_an_nafea),
                66
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ali_elhozefy),
                R.drawable.ali_elhozefy,
                context.getString(R.string.hafs_an_asem),
                67
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ali_swiesy),
                R.drawable.ali_swiesy,
                context.getString(R.string.hafs_an_asem),
                68
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.hafz),
                R.drawable.hafz,
                context.getString(R.string.hafs_an_asem),
                69
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.omar_kzabri),
                R.drawable.omar_kzabri,
                context.getString(R.string.warsh_an_nafea),
                70
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.frs_a),
                R.drawable.frs_a,
                context.getString(R.string.hafs_an_asem),
                71
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.maher_elmeaqly),
                R.drawable.almaqely,
                context.getString(R.string.hafs_an_asem),
                72
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.shaksh),
                R.drawable.shaksh,
                context.getString(R.string.hafs_an_asem),
                73
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ayyub),
                R.drawable.ayyub,
                context.getString(R.string.hafs_an_asem),
                74
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.m_krm),
                R.drawable.m_krm,
                context.getString(R.string.hafs_an_asem),
                75
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.m_krm),
                R.drawable.m_krm,
                context.getString(R.string.warsh_an_nafea_by_abe_bakr),
                76
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.earawi),
                R.drawable.earawi,
                context.getString(R.string.warsh_an_nafea_by_abe_bakr),
                77
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.shah),
                R.drawable.shah,
                context.getString(R.string.hafs_an_asem),
                78
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.rashad),
                R.drawable.rashad,
                context.getString(R.string.hafs_an_asem),
                79
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.lhdan),
                R.drawable.lhdan,
                context.getString(R.string.hafs_an_asem),
                80
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.khan),
                R.mipmap.logo,
                context.getString(R.string.hafs_an_asem),
                81
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mohamed_elmenshawyM),
                R.drawable.elmenshawy,
                context.getString(R.string.ekmoshaf_elmogawad),
                82
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mohamed_elmenshawyH),
                R.drawable.elmenshawy,
                context.getString(R.string.hafs_an_asem),
                83
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mohamed_eltblawy),
                R.drawable.eltblawy,
                context.getString(R.string.hafs_an_asem),
                84
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mohamed_abd_elhakem),
                R.drawable.mohamed_abd_elhakem,
                context.getString(R.string.elbezy_an_ebnkaser),
                85
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mohamed_abd_elhakem),
                R.drawable.mohamed_abd_elhakem,
                context.getString(R.string.aldawry_an_elkasai),
                86
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mohamed_gebrer),
                R.drawable.mohamedgbrer,
                context.getString(R.string.hafs_an_asem),
                87
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mshary_elafasy),
                R.drawable.msharyelafasy,
                context.getString(R.string.hafs_an_asem),
                88
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mrifai),
                R.drawable.mrifai,
                context.getString(R.string.hafs_an_asem),
                89
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.sheimy),
                R.drawable.sheimy,
                context.getString(R.string.aldawry_an_elkasai),
                90
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mahmoud_elhosaryM),
                R.drawable.elhosary,
                context.getString(R.string.ekmoshaf_elmogawad),
                91
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mahmoud_elhosaryM),
                R.drawable.elhosary,
                context.getString(R.string.warsh_an_nafea),
                92
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mahmoud_elhosaryH),
                R.drawable.elhosary,
                context.getString(R.string.hafs_an_asem),
                93
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mahmoud_elbana),
                R.drawable.elbana,
                context.getString(R.string.hafs_an_asem),
                94
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mahmoud_elbana),
                R.drawable.elbana,
                context.getString(R.string.ekmoshaf_elmogawad),
                95
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.mustafa),
                R.drawable.mustafa,
                context.getString(R.string.hafs_an_asem),
                96
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.lahoni),
                R.drawable.lahoni,
                context.getString(R.string.hafs_an_asem),
                97
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.ra3ad),
                R.drawable.ra3ad,
                context.getString(R.string.hafs_an_asem),
                98
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.harthi),
                R.mipmap.logo,
                context.getString(R.string.hafs_an_asem),
                99
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.moftah_elslty),
                R.drawable.moftah_elslty,
                context.getString(R.string.aldawry_an_elkasai),
                100
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.moftah_elslty),
                R.drawable.moftah_elslty,
                context.getString(R.string.abnzakor),
                101
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.moftah_elslty),
                R.drawable.moftah_elslty,
                context.getString(R.string.hafs_an_asem),
                102
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.moftah_elslty),
                R.drawable.moftah_elslty,
                context.getString(R.string.aldawry_ebn_omar),
                103
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.bilal),
                R.drawable.bilal,
                context.getString(R.string.hafs_an_asem),
                104
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.naser_elqatamy),
                R.drawable.naserelqatamy,
                context.getString(R.string.hafs_an_asem),
                105
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.nasser_almajed),
                R.drawable.nasser_almajed,
                context.getString(R.string.hafs_an_asem),
                106
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.nabel_elrefaay),
                R.drawable.nabel_elrefaay,
                context.getString(R.string.hafs_an_asem),
                107
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.namh),
                R.drawable.namh,
                context.getString(R.string.hafs_an_asem),
                108
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.wadea_elyamany),
                R.drawable.wadea_elyamany,
                context.getString(R.string.hafs_an_asem),
                109
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.yaser_eldosary),
                R.drawable.eldosary,
                context.getString(R.string.hafs_an_asem),
                110
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.hani),
                R.drawable.hani,
                context.getString(R.string.hafs_an_asem),
                111
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.waled_elnaehy),
                R.mipmap.logo,
                context.getString(R.string.alon_ben_nafea),
                112
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.yaser_elmazroay),
                R.drawable.yaser_elmazroay,
                context.getString(R.string.yaqob_elhadrmy),
                113
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.yaser_elqarashy),
                R.drawable.elqarashy,
                context.getString(R.string.hafs_an_asem),
                114
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.salamah),
                R.drawable.salamah,
                context.getString(R.string.hafs_an_asem),
                115
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.yasen_elgazaery),
                R.drawable.yasen_elgazaery,
                context.getString(R.string.warsh_an_nafea),
                116
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.yahya),
                R.drawable.yahya,
                context.getString(R.string.hafs_an_asem),
                117
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.noah),
                R.drawable.noah,
                context.getString(R.string.hafs_an_asem),
                118
            )
        )
        images.add(
            ImageModel(
                context.getString(R.string.yosem_elshereay),
                R.drawable.yosem_elshereay,
                context.getString(R.string.hafs_an_asem),
                119
            )
        )
        _sound.value = images
    }

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

    fun getPositionForNameSwars(position: Int, bundle: Bundle) {
        when (position) {
            0, 1 -> bundle.putInt(images.SAVE_POSITION, 0)
            2 -> bundle.putInt(images.SAVE_POSITION, 48)
            3 -> bundle.putInt(images.SAVE_POSITION, 75)
            4 -> bundle.putInt(images.SAVE_POSITION, 104)
            5 -> bundle.putInt(images.SAVE_POSITION, 126)
            6 -> bundle.putInt(images.SAVE_POSITION, 149)
            7 -> bundle.putInt(images.SAVE_POSITION, 175)
            8 -> bundle.putInt(images.SAVE_POSITION, 185)
            9 -> bundle.putInt(images.SAVE_POSITION, 206)
            10 -> bundle.putInt(images.SAVE_POSITION, 219)
            11 -> bundle.putInt(images.SAVE_POSITION, 233)
            12 -> bundle.putInt(images.SAVE_POSITION, 247)
            13 -> bundle.putInt(images.SAVE_POSITION, 253)
            14 -> bundle.putInt(images.SAVE_POSITION, 260)
            15 -> bundle.putInt(images.SAVE_POSITION, 265)
            16 -> bundle.putInt(images.SAVE_POSITION, 280)
            17 -> bundle.putInt(images.SAVE_POSITION, 291)
            18 -> bundle.putInt(images.SAVE_POSITION, 303)
            19 -> bundle.putInt(images.SAVE_POSITION, 310)
            20 -> bundle.putInt(images.SAVE_POSITION, 320)
            21 -> bundle.putInt(images.SAVE_POSITION, 330)
            22 -> bundle.putInt(images.SAVE_POSITION, 340)
            23 -> bundle.putInt(images.SAVE_POSITION, 348)
            24 -> bundle.putInt(images.SAVE_POSITION, 357)
            25 -> bundle.putInt(images.SAVE_POSITION, 365)
            26 -> bundle.putInt(images.SAVE_POSITION, 375)
            27 -> bundle.putInt(images.SAVE_POSITION, 383)
            28 -> bundle.putInt(images.SAVE_POSITION, 394)
            29 -> bundle.putInt(images.SAVE_POSITION, 402)
            30 -> bundle.putInt(images.SAVE_POSITION, 409)
            31 -> bundle.putInt(images.SAVE_POSITION, 413)
            32 -> bundle.putInt(images.SAVE_POSITION, 416)
            33 -> bundle.putInt(images.SAVE_POSITION, 426)
            34 -> bundle.putInt(images.SAVE_POSITION, 432)
            35 -> bundle.putInt(images.SAVE_POSITION, 438)
            36 -> bundle.putInt(images.SAVE_POSITION, 444)
            37 -> bundle.putInt(images.SAVE_POSITION, 451)
            38 -> bundle.putInt(images.SAVE_POSITION, 456)
            39 -> bundle.putInt(images.SAVE_POSITION, 465)
            40 -> bundle.putInt(images.SAVE_POSITION, 475)
            41 -> bundle.putInt(images.SAVE_POSITION, 481)
            42 -> bundle.putInt(images.SAVE_POSITION, 487)
            43 -> bundle.putInt(images.SAVE_POSITION, 494)
            44 -> bundle.putInt(images.SAVE_POSITION, 497)
            45 -> bundle.putInt(images.SAVE_POSITION, 500)
            46 -> bundle.putInt(images.SAVE_POSITION, 505)
            47 -> bundle.putInt(images.SAVE_POSITION, 509)
            48 -> bundle.putInt(images.SAVE_POSITION, 513)
            49 -> bundle.putInt(images.SAVE_POSITION, 516)
            50 -> bundle.putInt(images.SAVE_POSITION, 518)
            51 -> bundle.putInt(images.SAVE_POSITION, 521)
            52 -> bundle.putInt(images.SAVE_POSITION, 524)
            53 -> bundle.putInt(images.SAVE_POSITION, 526)
            54 -> bundle.putInt(images.SAVE_POSITION, 529)
            55 -> bundle.putInt(images.SAVE_POSITION, 532)
            56 -> bundle.putInt(images.SAVE_POSITION, 535)
            57 -> bundle.putInt(images.SAVE_POSITION, 540)
            58 -> bundle.putInt(images.SAVE_POSITION, 543)
            59 -> bundle.putInt(images.SAVE_POSITION, 547)
            60 -> bundle.putInt(images.SAVE_POSITION, 549)
            61 -> bundle.putInt(images.SAVE_POSITION, 551)
            62 -> bundle.putInt(images.SAVE_POSITION, 552)
            63 -> bundle.putInt(images.SAVE_POSITION, 554)
            64 -> bundle.putInt(images.SAVE_POSITION, 556)
            65 -> bundle.putInt(images.SAVE_POSITION, 558)
            66 -> bundle.putInt(images.SAVE_POSITION, 560)
            67 -> bundle.putInt(images.SAVE_POSITION, 562)
            68 -> bundle.putInt(images.SAVE_POSITION, 564)
            69 -> bundle.putInt(images.SAVE_POSITION, 566)
            70 -> bundle.putInt(images.SAVE_POSITION, 568)
            71 -> bundle.putInt(images.SAVE_POSITION, 570)
            72 -> bundle.putInt(images.SAVE_POSITION, 572)
            73 -> bundle.putInt(images.SAVE_POSITION, 573)
            74 -> bundle.putInt(images.SAVE_POSITION, 575)
            75 -> bundle.putInt(images.SAVE_POSITION, 576)
            76 -> bundle.putInt(images.SAVE_POSITION, 578)
            77 -> bundle.putInt(images.SAVE_POSITION, 580)
            78 -> bundle.putInt(images.SAVE_POSITION, 581)
            79 -> bundle.putInt(images.SAVE_POSITION, 583)
            80 -> bundle.putInt(images.SAVE_POSITION, 584)
            81, 82 -> bundle.putInt(images.SAVE_POSITION, 585)
            83 -> bundle.putInt(images.SAVE_POSITION, 587)
            84 -> bundle.putInt(images.SAVE_POSITION, 588)
            85, 86 -> bundle.putInt(images.SAVE_POSITION, 589)
            87 -> bundle.putInt(images.SAVE_POSITION, 590)
            88 -> bundle.putInt(images.SAVE_POSITION, 591)
            89 -> bundle.putInt(images.SAVE_POSITION, 592)
            90, 91 -> bundle.putInt(images.SAVE_POSITION, 593)
            92, 93 -> bundle.putInt(images.SAVE_POSITION, 594)
            94, 95 -> bundle.putInt(images.SAVE_POSITION, 595)
            96, 97 -> bundle.putInt(images.SAVE_POSITION, 596)
            98, 99 -> bundle.putInt(images.SAVE_POSITION, 597)
            100, 101 -> bundle.putInt(images.SAVE_POSITION, 598)
            102, 103, 104 -> bundle.putInt(images.SAVE_POSITION, 599)
            105, 106, 107 -> bundle.putInt(images.SAVE_POSITION, 600)
            108, 109, 110 -> bundle.putInt(images.SAVE_POSITION, 601)
            111, 112, 113 -> bundle.putInt(images.SAVE_POSITION, 602)
            else -> {}
        }
    }

    fun addImagesList() {
        val images = ArrayList<Int>()
        images.add(R.drawable.page2)
        images.add(R.drawable.page3)
        images.add(R.drawable.page4)
        images.add(R.drawable.page5)
        images.add(R.drawable.page6)
        images.add(R.drawable.page7)
        images.add(R.drawable.page8)
        images.add(R.drawable.page9)
        images.add(R.drawable.page10)
        images.add(R.drawable.page11)
        images.add(R.drawable.page12)
        images.add(R.drawable.page13)
        images.add(R.drawable.page14)
        images.add(R.drawable.page15)
        images.add(R.drawable.page16)
        images.add(R.drawable.page17)
        images.add(R.drawable.page18)
        images.add(R.drawable.page19)
        images.add(R.drawable.page20)
        images.add(R.drawable.page21)
        images.add(R.drawable.page22)
        images.add(R.drawable.page23)
        images.add(R.drawable.page24)
        images.add(R.drawable.page25)
        images.add(R.drawable.page26)
        images.add(R.drawable.page27)
        images.add(R.drawable.page28)
        images.add(R.drawable.page29)
        images.add(R.drawable.page30)
        images.add(R.drawable.page31)
        images.add(R.drawable.page32)
        images.add(R.drawable.page33)
        images.add(R.drawable.page34)
        images.add(R.drawable.page35)
        images.add(R.drawable.page36)
        images.add(R.drawable.page37)
        images.add(R.drawable.page38)
        images.add(R.drawable.page39)
        images.add(R.drawable.page40)
        images.add(R.drawable.page41)
        images.add(R.drawable.page42)
        images.add(R.drawable.page43)
        images.add(R.drawable.page44)
        images.add(R.drawable.page45)
        images.add(R.drawable.page46)
        images.add(R.drawable.page47)
        images.add(R.drawable.page48)
        images.add(R.drawable.page49)
        images.add(R.drawable.page50)
        images.add(R.drawable.page51)
        images.add(R.drawable.page52)
        images.add(R.drawable.page53)
        images.add(R.drawable.page54)
        images.add(R.drawable.page55)
        images.add(R.drawable.page56)
        images.add(R.drawable.page57)
        images.add(R.drawable.page58)
        images.add(R.drawable.page59)
        images.add(R.drawable.page60)
        images.add(R.drawable.page61)
        images.add(R.drawable.page62)
        images.add(R.drawable.page63)
        images.add(R.drawable.page64)
        images.add(R.drawable.page65)
        images.add(R.drawable.page66)
        images.add(R.drawable.page67)
        images.add(R.drawable.page68)
        images.add(R.drawable.page69)
        images.add(R.drawable.page70)
        images.add(R.drawable.page71)
        images.add(R.drawable.page72)
        images.add(R.drawable.page73)
        images.add(R.drawable.page74)
        images.add(R.drawable.page75)
        images.add(R.drawable.page76)
        images.add(R.drawable.page77)
        images.add(R.drawable.page78)
        images.add(R.drawable.page79)
        images.add(R.drawable.page80)
        images.add(R.drawable.page81)
        images.add(R.drawable.page82)
        images.add(R.drawable.page83)
        images.add(R.drawable.page84)
        images.add(R.drawable.page85)
        images.add(R.drawable.page86)
        images.add(R.drawable.page87)
        images.add(R.drawable.page88)
        images.add(R.drawable.page89)
        images.add(R.drawable.page90)
        images.add(R.drawable.page91)
        images.add(R.drawable.page92)
        images.add(R.drawable.page93)
        images.add(R.drawable.page94)
        images.add(R.drawable.page95)
        images.add(R.drawable.page96)
        images.add(R.drawable.page97)
        images.add(R.drawable.page98)
        images.add(R.drawable.page99)
        images.add(R.drawable.page100)
        images.add(R.drawable.page101)
        images.add(R.drawable.page102)
        images.add(R.drawable.page103)
        images.add(R.drawable.page104)
        images.add(R.drawable.page105)
        images.add(R.drawable.page106)
        images.add(R.drawable.page107)
        images.add(R.drawable.page108)
        images.add(R.drawable.page109)
        images.add(R.drawable.page110)
        images.add(R.drawable.page111)
        images.add(R.drawable.page112)
        images.add(R.drawable.page113)
        images.add(R.drawable.page114)
        images.add(R.drawable.page115)
        images.add(R.drawable.page116)
        images.add(R.drawable.page117)
        images.add(R.drawable.page118)
        images.add(R.drawable.page119)
        images.add(R.drawable.page120)
        images.add(R.drawable.page121)
        images.add(R.drawable.page122)
        images.add(R.drawable.page123)
        images.add(R.drawable.page124)
        images.add(R.drawable.page125)
        images.add(R.drawable.page126)
        images.add(R.drawable.page127)
        images.add(R.drawable.page128)
        images.add(R.drawable.page129)
        images.add(R.drawable.page130)
        images.add(R.drawable.page131)
        images.add(R.drawable.page132)
        images.add(R.drawable.page133)
        images.add(R.drawable.page134)
        images.add(R.drawable.page135)
        images.add(R.drawable.page136)
        images.add(R.drawable.page137)
        images.add(R.drawable.page138)
        images.add(R.drawable.page139)
        images.add(R.drawable.page140)
        images.add(R.drawable.page141)
        images.add(R.drawable.page142)
        images.add(R.drawable.page143)
        images.add(R.drawable.page144)
        images.add(R.drawable.page145)
        images.add(R.drawable.page146)
        images.add(R.drawable.page147)
        images.add(R.drawable.page148)
        images.add(R.drawable.page149)
        images.add(R.drawable.page150)
        images.add(R.drawable.page151)
        images.add(R.drawable.page152)
        images.add(R.drawable.page153)
        images.add(R.drawable.page154)
        images.add(R.drawable.page155)
        images.add(R.drawable.page156)
        images.add(R.drawable.page157)
        images.add(R.drawable.page158)
        images.add(R.drawable.page159)
        images.add(R.drawable.page160)
        images.add(R.drawable.page161)
        images.add(R.drawable.page162)
        images.add(R.drawable.page163)
        images.add(R.drawable.page164)
        images.add(R.drawable.page165)
        images.add(R.drawable.page166)
        images.add(R.drawable.page167)
        images.add(R.drawable.page168)
        images.add(R.drawable.page169)
        images.add(R.drawable.page170)
        images.add(R.drawable.page171)
        images.add(R.drawable.page172)
        images.add(R.drawable.page173)
        images.add(R.drawable.page174)
        images.add(R.drawable.page175)
        images.add(R.drawable.page176)
        images.add(R.drawable.page177)
        images.add(R.drawable.page178)
        images.add(R.drawable.page179)
        images.add(R.drawable.page180)
        images.add(R.drawable.page181)
        images.add(R.drawable.page182)
        images.add(R.drawable.page183)
        images.add(R.drawable.page184)
        images.add(R.drawable.page185)
        images.add(R.drawable.page186)
        images.add(R.drawable.page187)
        images.add(R.drawable.page188)
        images.add(R.drawable.page189)
        images.add(R.drawable.page190)
        images.add(R.drawable.page191)
        images.add(R.drawable.page192)
        images.add(R.drawable.page193)
        images.add(R.drawable.page194)
        images.add(R.drawable.page195)
        images.add(R.drawable.page196)
        images.add(R.drawable.page197)
        images.add(R.drawable.page198)
        images.add(R.drawable.page199)
        images.add(R.drawable.page200)
        images.add(R.drawable.page201)
        images.add(R.drawable.page202)
        images.add(R.drawable.page203)
        images.add(R.drawable.page204)
        images.add(R.drawable.page205)
        images.add(R.drawable.page206)
        images.add(R.drawable.page207)
        images.add(R.drawable.page208)
        images.add(R.drawable.page209)
        images.add(R.drawable.page210)
        images.add(R.drawable.page211)
        images.add(R.drawable.page212)
        images.add(R.drawable.page213)
        images.add(R.drawable.page214)
        images.add(R.drawable.page215)
        images.add(R.drawable.page216)
        images.add(R.drawable.page217)
        images.add(R.drawable.page218)
        images.add(R.drawable.page219)
        images.add(R.drawable.page220)
        images.add(R.drawable.page221)
        images.add(R.drawable.page222)
        images.add(R.drawable.page223)
        images.add(R.drawable.page224)
        images.add(R.drawable.page225)
        images.add(R.drawable.page226)
        images.add(R.drawable.page227)
        images.add(R.drawable.page228)
        images.add(R.drawable.page229)
        images.add(R.drawable.page230)
        images.add(R.drawable.page231)
        images.add(R.drawable.page232)
        images.add(R.drawable.page233)
        images.add(R.drawable.page234)
        images.add(R.drawable.page235)
        images.add(R.drawable.page236)
        images.add(R.drawable.page237)
        images.add(R.drawable.page238)
        images.add(R.drawable.page239)
        images.add(R.drawable.page240)
        images.add(R.drawable.page241)
        images.add(R.drawable.page242)
        images.add(R.drawable.page243)
        images.add(R.drawable.page244)
        images.add(R.drawable.page245)
        images.add(R.drawable.page246)
        images.add(R.drawable.page247)
        images.add(R.drawable.page248)
        images.add(R.drawable.page249)
        images.add(R.drawable.page250)
        images.add(R.drawable.page251)
        images.add(R.drawable.page252)
        images.add(R.drawable.page253)
        images.add(R.drawable.page254)
        images.add(R.drawable.page255)
        images.add(R.drawable.page256)
        images.add(R.drawable.page257)
        images.add(R.drawable.page258)
        images.add(R.drawable.page259)
        images.add(R.drawable.page260)
        images.add(R.drawable.page261)
        images.add(R.drawable.page262)
        images.add(R.drawable.page263)
        images.add(R.drawable.page264)
        images.add(R.drawable.page265)
        images.add(R.drawable.page266)
        images.add(R.drawable.page267)
        images.add(R.drawable.page268)
        images.add(R.drawable.page269)
        images.add(R.drawable.page270)
        images.add(R.drawable.page271)
        images.add(R.drawable.page272)
        images.add(R.drawable.page273)
        images.add(R.drawable.page274)
        images.add(R.drawable.page275)
        images.add(R.drawable.page276)
        images.add(R.drawable.page277)
        images.add(R.drawable.page278)
        images.add(R.drawable.page279)
        images.add(R.drawable.page280)
        images.add(R.drawable.page281)
        images.add(R.drawable.page282)
        images.add(R.drawable.page283)
        images.add(R.drawable.page284)
        images.add(R.drawable.page285)
        images.add(R.drawable.page286)
        images.add(R.drawable.page287)
        images.add(R.drawable.page288)
        images.add(R.drawable.page289)
        images.add(R.drawable.page290)
        images.add(R.drawable.page291)
        images.add(R.drawable.page292)
        images.add(R.drawable.page293)
        images.add(R.drawable.page294)
        images.add(R.drawable.page295)
        images.add(R.drawable.page296)
        images.add(R.drawable.page297)
        images.add(R.drawable.page298)
        images.add(R.drawable.page299)
        images.add(R.drawable.page300)
        images.add(R.drawable.page301)
        images.add(R.drawable.page302)
        images.add(R.drawable.page303)
        images.add(R.drawable.page304)
        images.add(R.drawable.page305)
        images.add(R.drawable.page306)
        images.add(R.drawable.page307)
        images.add(R.drawable.page308)
        images.add(R.drawable.page309)
        images.add(R.drawable.page310)
        images.add(R.drawable.page311)
        images.add(R.drawable.page312)
        images.add(R.drawable.page313)
        images.add(R.drawable.page314)
        images.add(R.drawable.page315)
        images.add(R.drawable.page316)
        images.add(R.drawable.page317)
        images.add(R.drawable.page318)
        images.add(R.drawable.page319)
        images.add(R.drawable.page320)
        images.add(R.drawable.page321)
        images.add(R.drawable.page322)
        images.add(R.drawable.page323)
        images.add(R.drawable.page324)
        images.add(R.drawable.page325)
        images.add(R.drawable.page326)
        images.add(R.drawable.page327)
        images.add(R.drawable.page328)
        images.add(R.drawable.page329)
        images.add(R.drawable.page330)
        images.add(R.drawable.page331)
        images.add(R.drawable.page332)
        images.add(R.drawable.page333)
        images.add(R.drawable.page334)
        images.add(R.drawable.page335)
        images.add(R.drawable.page336)
        images.add(R.drawable.page337)
        images.add(R.drawable.page338)
        images.add(R.drawable.page339)
        images.add(R.drawable.page340)
        images.add(R.drawable.page341)
        images.add(R.drawable.page342)
        images.add(R.drawable.page343)
        images.add(R.drawable.page344)
        images.add(R.drawable.page345)
        images.add(R.drawable.page346)
        images.add(R.drawable.page347)
        images.add(R.drawable.page348)
        images.add(R.drawable.page349)
        images.add(R.drawable.page350)
        images.add(R.drawable.page351)
        images.add(R.drawable.page352)
        images.add(R.drawable.page353)
        images.add(R.drawable.page354)
        images.add(R.drawable.page355)
        images.add(R.drawable.page356)
        images.add(R.drawable.page357)
        images.add(R.drawable.page358)
        images.add(R.drawable.page359)
        images.add(R.drawable.page360)
        images.add(R.drawable.page361)
        images.add(R.drawable.page362)
        images.add(R.drawable.page363)
        images.add(R.drawable.page364)
        images.add(R.drawable.page365)
        images.add(R.drawable.page366)
        images.add(R.drawable.page367)
        images.add(R.drawable.page368)
        images.add(R.drawable.page369)
        images.add(R.drawable.page370)
        images.add(R.drawable.page371)
        images.add(R.drawable.page372)
        images.add(R.drawable.page373)
        images.add(R.drawable.page374)
        images.add(R.drawable.page375)
        images.add(R.drawable.page376)
        images.add(R.drawable.page377)
        images.add(R.drawable.page378)
        images.add(R.drawable.page379)
        images.add(R.drawable.page380)
        images.add(R.drawable.page381)
        images.add(R.drawable.page382)
        images.add(R.drawable.page383)
        images.add(R.drawable.page384)
        images.add(R.drawable.page385)
        images.add(R.drawable.page386)
        images.add(R.drawable.page387)
        images.add(R.drawable.page388)
        images.add(R.drawable.page389)
        images.add(R.drawable.page390)
        images.add(R.drawable.page391)
        images.add(R.drawable.page392)
        images.add(R.drawable.page393)
        images.add(R.drawable.page394)
        images.add(R.drawable.page395)
        images.add(R.drawable.page396)
        images.add(R.drawable.page397)
        images.add(R.drawable.page398)
        images.add(R.drawable.page399)
        images.add(R.drawable.page400)
        images.add(R.drawable.page401)
        images.add(R.drawable.page402)
        images.add(R.drawable.page403)
        images.add(R.drawable.page404)
        images.add(R.drawable.page405)
        images.add(R.drawable.page406)
        images.add(R.drawable.page407)
        images.add(R.drawable.page408)
        images.add(R.drawable.page409)
        images.add(R.drawable.page410)
        images.add(R.drawable.page411)
        images.add(R.drawable.page412)
        images.add(R.drawable.page413)
        images.add(R.drawable.page414)
        images.add(R.drawable.page415)
        images.add(R.drawable.page416)
        images.add(R.drawable.page417)
        images.add(R.drawable.page418)
        images.add(R.drawable.page419)
        images.add(R.drawable.page420)
        images.add(R.drawable.page421)
        images.add(R.drawable.page422)
        images.add(R.drawable.page423)
        images.add(R.drawable.page424)
        images.add(R.drawable.page425)
        images.add(R.drawable.page426)
        images.add(R.drawable.page427)
        images.add(R.drawable.page428)
        images.add(R.drawable.page429)
        images.add(R.drawable.page430)
        images.add(R.drawable.page431)
        images.add(R.drawable.page432)
        images.add(R.drawable.page433)
        images.add(R.drawable.page434)
        images.add(R.drawable.page435)
        images.add(R.drawable.page436)
        images.add(R.drawable.page437)
        images.add(R.drawable.page438)
        images.add(R.drawable.page439)
        images.add(R.drawable.page440)
        images.add(R.drawable.page441)
        images.add(R.drawable.page442)
        images.add(R.drawable.page443)
        images.add(R.drawable.page444)
        images.add(R.drawable.page445)
        images.add(R.drawable.page446)
        images.add(R.drawable.page447)
        images.add(R.drawable.page448)
        images.add(R.drawable.page449)
        images.add(R.drawable.page450)
        images.add(R.drawable.page451)
        images.add(R.drawable.page452)
        images.add(R.drawable.page453)
        images.add(R.drawable.page454)
        images.add(R.drawable.page455)
        images.add(R.drawable.page456)
        images.add(R.drawable.page457)
        images.add(R.drawable.page458)
        images.add(R.drawable.page459)
        images.add(R.drawable.page460)
        images.add(R.drawable.page461)
        images.add(R.drawable.page462)
        images.add(R.drawable.page463)
        images.add(R.drawable.page464)
        images.add(R.drawable.page465)
        images.add(R.drawable.page466)
        images.add(R.drawable.page467)
        images.add(R.drawable.page468)
        images.add(R.drawable.page469)
        images.add(R.drawable.page470)
        images.add(R.drawable.page471)
        images.add(R.drawable.page472)
        images.add(R.drawable.page473)
        images.add(R.drawable.page474)
        images.add(R.drawable.page475)
        images.add(R.drawable.page476)
        images.add(R.drawable.page477)
        images.add(R.drawable.page478)
        images.add(R.drawable.page479)
        images.add(R.drawable.page480)
        images.add(R.drawable.page481)
        images.add(R.drawable.page482)
        images.add(R.drawable.page483)
        images.add(R.drawable.page484)
        images.add(R.drawable.page485)
        images.add(R.drawable.page486)
        images.add(R.drawable.page487)
        images.add(R.drawable.page488)
        images.add(R.drawable.page489)
        images.add(R.drawable.page490)
        images.add(R.drawable.page491)
        images.add(R.drawable.page492)
        images.add(R.drawable.page493)
        images.add(R.drawable.page494)
        images.add(R.drawable.page495)
        images.add(R.drawable.page496)
        images.add(R.drawable.page497)
        images.add(R.drawable.page498)
        images.add(R.drawable.page499)
        images.add(R.drawable.page500)
        images.add(R.drawable.page501)
        images.add(R.drawable.page502)
        images.add(R.drawable.page503)
        images.add(R.drawable.page504)
        images.add(R.drawable.page505)
        images.add(R.drawable.page506)
        images.add(R.drawable.page507)
        images.add(R.drawable.page508)
        images.add(R.drawable.page509)
        images.add(R.drawable.page510)
        images.add(R.drawable.page511)
        images.add(R.drawable.page512)
        images.add(R.drawable.page513)
        images.add(R.drawable.page514)
        images.add(R.drawable.page515)
        images.add(R.drawable.page516)
        images.add(R.drawable.page517)
        images.add(R.drawable.page518)
        images.add(R.drawable.page519)
        images.add(R.drawable.page520)
        images.add(R.drawable.page521)
        images.add(R.drawable.page522)
        images.add(R.drawable.page523)
        images.add(R.drawable.page524)
        images.add(R.drawable.page525)
        images.add(R.drawable.page526)
        images.add(R.drawable.page527)
        images.add(R.drawable.page528)
        images.add(R.drawable.page529)
        images.add(R.drawable.page530)
        images.add(R.drawable.page531)
        images.add(R.drawable.page532)
        images.add(R.drawable.page533)
        images.add(R.drawable.page534)
        images.add(R.drawable.page535)
        images.add(R.drawable.page536)
        images.add(R.drawable.page537)
        images.add(R.drawable.page538)
        images.add(R.drawable.page539)
        images.add(R.drawable.page540)
        images.add(R.drawable.page541)
        images.add(R.drawable.page542)
        images.add(R.drawable.page543)
        images.add(R.drawable.page544)
        images.add(R.drawable.page545)
        images.add(R.drawable.page546)
        images.add(R.drawable.page547)
        images.add(R.drawable.page548)
        images.add(R.drawable.page549)
        images.add(R.drawable.page550)
        images.add(R.drawable.page551)
        images.add(R.drawable.page552)
        images.add(R.drawable.page553)
        images.add(R.drawable.page554)
        images.add(R.drawable.page555)
        images.add(R.drawable.page556)
        images.add(R.drawable.page557)
        images.add(R.drawable.page558)
        images.add(R.drawable.page559)
        images.add(R.drawable.page560)
        images.add(R.drawable.page561)
        images.add(R.drawable.page562)
        images.add(R.drawable.page563)
        images.add(R.drawable.page564)
        images.add(R.drawable.page565)
        images.add(R.drawable.page566)
        images.add(R.drawable.page567)
        images.add(R.drawable.page568)
        images.add(R.drawable.page569)
        images.add(R.drawable.page570)
        images.add(R.drawable.page571)
        images.add(R.drawable.page572)
        images.add(R.drawable.page573)
        images.add(R.drawable.page574)
        images.add(R.drawable.page575)
        images.add(R.drawable.page576)
        images.add(R.drawable.page577)
        images.add(R.drawable.page578)
        images.add(R.drawable.page579)
        images.add(R.drawable.page580)
        images.add(R.drawable.page581)
        images.add(R.drawable.page582)
        images.add(R.drawable.page583)
        images.add(R.drawable.page584)
        images.add(R.drawable.page585)
        images.add(R.drawable.page586)
        images.add(R.drawable.page587)
        images.add(R.drawable.page588)
        images.add(R.drawable.page589)
        images.add(R.drawable.page590)
        images.add(R.drawable.page591)
        images.add(R.drawable.page592)
        images.add(R.drawable.page593)
        images.add(R.drawable.page594)
        images.add(R.drawable.page595)
        images.add(R.drawable.page596)
        images.add(R.drawable.page597)
        images.add(R.drawable.page598)
        images.add(R.drawable.page599)
        images.add(R.drawable.page600)
        images.add(R.drawable.page601)
        images.add(R.drawable.page602)
        images.add(R.drawable.page603)
        images.add(R.drawable.page604)
        _allImages.value = images
    }

    fun getPositionForNameParts(position: Int, bundle: Bundle) {
        when (position) {
            0 -> bundle.putInt(images.SAVE_POSITION, 0)
            1 -> bundle.putInt(images.SAVE_POSITION, 20)
            2 -> bundle.putInt(images.SAVE_POSITION, 40)
            3 -> bundle.putInt(images.SAVE_POSITION, 60)
            4 -> bundle.putInt(images.SAVE_POSITION, 80)
            5 -> bundle.putInt(images.SAVE_POSITION, 100)
            6 -> bundle.putInt(images.SAVE_POSITION, 120)
            7 -> bundle.putInt(images.SAVE_POSITION, 140)
            8 -> bundle.putInt(images.SAVE_POSITION, 160)
            9 -> bundle.putInt(images.SAVE_POSITION, 180)
            10 -> bundle.putInt(images.SAVE_POSITION, 200)
            11 -> bundle.putInt(images.SAVE_POSITION, 220)
            12 -> bundle.putInt(images.SAVE_POSITION, 240)
            13 -> bundle.putInt(images.SAVE_POSITION, 260)
            14 -> bundle.putInt(images.SAVE_POSITION, 280)
            15 -> bundle.putInt(images.SAVE_POSITION, 300)
            16 -> bundle.putInt(images.SAVE_POSITION, 320)
            17 -> bundle.putInt(images.SAVE_POSITION, 340)
            18 -> bundle.putInt(images.SAVE_POSITION, 360)
            19 -> bundle.putInt(images.SAVE_POSITION, 380)
            20 -> bundle.putInt(images.SAVE_POSITION, 400)
            21 -> bundle.putInt(images.SAVE_POSITION, 420)
            22 -> bundle.putInt(images.SAVE_POSITION, 440)
            23 -> bundle.putInt(images.SAVE_POSITION, 460)
            24 -> bundle.putInt(images.SAVE_POSITION, 480)
            25 -> bundle.putInt(images.SAVE_POSITION, 500)
            26 -> bundle.putInt(images.SAVE_POSITION, 520)
            27 -> bundle.putInt(images.SAVE_POSITION, 540)
            28 -> bundle.putInt(images.SAVE_POSITION, 560)
            29 -> bundle.putInt(images.SAVE_POSITION, 580)
        }
    }

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
            elnawawyModel.number_elhadeth = number_elhadeth[i]
            elnawawyModel.name_elhadeth = name_elhadeth[i]
            elnawawyModel.text_elhadeth = text_elhadeth[i]
            elnawawyModel.description_elhadeth = description_elhadeth[i]
            elnawawyModel.transelate_elhadeth = transelate_elhadeth[i]
            elnawawyModel.position = i
            elnawawyModelList.add(elnawawyModel);
        }
        _elarbaoonElnawawy.value = elnawawyModelList
    }

}
