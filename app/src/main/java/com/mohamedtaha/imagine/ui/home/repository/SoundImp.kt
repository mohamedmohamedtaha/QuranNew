package com.mohamedtaha.imagine.ui.home.repository

import android.app.Application
import android.widget.Toast
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.mvp.model.ImageModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SoundImp @Inject constructor(private val application: Application) : SoundRepository {
    private val nameSora = ArrayList<ImageModel>()
    override suspend fun getDataForReader(position: Int): ArrayList<ImageModel> {
        //Fetch the data in String.xml file
        val soar = application.resources.getStringArray(R.array.name_allSwar)
        when (position) {
            0 -> {
                val arrayListEldokalyMohamed =
                    application.resources.getStringArray(R.array.linkesEldokalyMohamed)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListEldokalyMohamed[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.dokaly_mohamed)
                    model.urlImage = R.drawable.aldokaly_mohamed
                    nameSora.add(model)
                    ++i
                }
            }
            1 -> {
                val arrayListElzenMohamed =
                    application.resources.getStringArray(R.array.linkesElzenMohamed)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElzenMohamed[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.elzen_mohamed)
                    model.urlImage = R.drawable.elzen_mohamed
                    nameSora.add(model)
                    ++i
                }
            }
            2 -> {
                val arrayListEloyonElkorashy =
                    application.resources.getStringArray(R.array.linkesEloyonElkorashy)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListEloyonElkorashy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.al_ouoon_alkushi)
                    model.urlImage = R.drawable.al_ouoon_alkushi
                    nameSora.add(model)
                    ++i
                }
            }
            3 -> {
                val arrayListElfalehMohamed =
                    application.resources.getStringArray(R.array.linkesElfalemMohamed)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElfalehMohamed[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.alfateh_mohamed)
                    model.urlImage = R.drawable.alfateh_mohamed
                    nameSora.add(model)
                    ++i
                }
            }
            4 -> {
                val arrayListElshatry = application.resources.getStringArray(R.array.linkesElshatry)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElshatry[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.shatri)
                    model.urlImage = R.drawable.shatri
                    nameSora.add(model)
                    ++i
                }
            }
            5 -> {
                val arrayListElagamy = application.resources.getStringArray(R.array.linkesElagamy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElagamy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ahmed_elagamy)
                    model.urlImage = R.drawable.elagamy
                    nameSora.add(model)
                    ++i
                }
            }
            6 -> {
                val arrayListAhmedKhedrEltrabolsy =
                    application.resources.getStringArray(R.array.linkesAhmedKhedrEltrabolsy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAhmedKhedrEltrabolsy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ahmed_eltrabolsy)
                    model.urlImage = R.drawable.ahmed_khdr_eltrabolsy
                    nameSora.add(model)
                    ++i
                }
            }
            7 -> {
                val arrayListAhmedEltrabolsy =
                    application.resources.getStringArray(R.array.linkesAhmedEltrabolsy)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAhmedEltrabolsy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ahmed_eltrabolsy)
                    model.urlImage = R.drawable.ahmed_khdr_eltrabolsy
                    nameSora.add(model)
                    ++i
                }
            }
            8 -> {
                val arrayListAhmedElhawashy =
                    application.resources.getStringArray(R.array.linkesAhmedElhawashy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAhmedElhawashy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ahmed_elhawashy)
                    model.urlImage = R.drawable.ahmed_elhawashy
                    nameSora.add(model)
                    ++i
                }
            }
            9 -> {
                val arrayListAhmedSaber =
                    application.resources.getStringArray(R.array.linkesAhmedSaber)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAhmedSaber[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ahmed_saber)
                    model.urlImage = R.mipmap.logo
                    nameSora.add(model)
                    ++i
                }
            }
            10 -> {
                val arrayListAhmedAmer =
                    application.resources.getStringArray(R.array.linkesAhmedAmer)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAhmedAmer[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ahmed_amer)
                    model.urlImage = R.drawable.ahmed_amer
                    nameSora.add(model)
                    ++i
                }
            }
            11 -> {
                val arrayListAhmedNeanea =
                    application.resources.getStringArray(R.array.linkesAhmedNeanea)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAhmedNeanea[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ahmed_naeane)
                    model.urlImage = R.drawable.ahmed_neanea
                    nameSora.add(model)
                    ++i
                }
            }
            12 -> {
                val arrayListAkramAlAalakmi =
                    application.resources.getStringArray(R.array.linkesAkramAlAalakmi)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAkramAlAalakmi[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.akram_al_aalakmi)
                    model.urlImage = R.drawable.akram_al_aalakmi
                    nameSora.add(model)
                    ++i
                }
            }
            13 -> {
                val arrayListEbrahemElakhdar =
                    application.resources.getStringArray(R.array.linkesEbrahemElakhdar)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListEbrahemElakhdar[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ibrahim_al_akhdar)
                    model.urlImage = R.drawable.ebrahem_khoder
                    nameSora.add(model)
                    ++i
                }
            }
            14 -> {
                val arrayListEbraghemEldosary =
                    application.resources.getStringArray(R.array.linkesEbrahemEldosary)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListEbraghemEldosary[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ibrahim_al_dosary)
                    model.urlImage = R.drawable.ebrahem_eldosary
                    nameSora.add(model)
                    ++i
                }
            }
            15 -> {
                val arrayListEbraghemEldosaryWarsh =
                    application.resources.getStringArray(R.array.linkesEbrahemEldosaryWarsh)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListEbraghemEldosaryWarsh[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ibrahim_al_dosary)
                    model.urlImage = R.drawable.ebrahem_eldosary
                    nameSora.add(model)
                    ++i
                }
            }
            16 -> {
                val arrayListBnderBelela =
                    application.resources.getStringArray(R.array.linkesBnderBelela)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListBnderBelela[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.bnder_belela)
                    model.urlImage = R.drawable.belela
                    nameSora.add(model)
                    ++i
                }
            }
            17 -> {
                val arraylistTwfeaElsayegh =
                    application.resources.getStringArray(R.array.linkesTwfeaElsayegh)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arraylistTwfeaElsayegh[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.tawfik_sayegh)
                    model.urlImage = R.drawable.tawfik_sayegh
                    nameSora.add(model)
                    ++i
                }
            }
            18 -> {
                val arrayListJamalShaker =
                    application.resources.getStringArray(R.array.linkesJamalShaker)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListJamalShaker[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.jamal_shaker)
                    model.urlImage = R.drawable.jamal_shaker
                    nameSora.add(model)
                    ++i
                }
            }
            19 -> {
                val arrayListJamanElesemy =
                    application.resources.getStringArray(R.array.linkesJamanElesemy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListJamanElesemy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.jaman_elesemy)
                    model.urlImage = R.drawable.jaman_elesemy
                    nameSora.add(model)
                    ++i
                }
            }
            20 -> {
                val arrayListHatemFared =
                    application.resources.getStringArray(R.array.linkesHatemFared)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListHatemFared[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.hatem_fared)
                    model.urlImage = R.drawable.hatem_fared
                    nameSora.add(model)
                    ++i
                }
            }
            21 -> {
                val arraylistKhalidElmohana =
                    application.resources.getStringArray(R.array.linkesKhalidElmohana)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arraylistKhalidElmohana[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.khaled_elmehana)
                    model.urlImage = R.drawable.khaled_elmehana
                    nameSora.add(model)
                    ++i
                }
            }
            22 -> {
                val arraylistKhalidAbdElkafy =
                    application.resources.getStringArray(R.array.linkesKhalidAbdElkafy)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arraylistKhalidAbdElkafy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.khaled_abdelkafy)
                    model.urlImage = R.drawable.khaled_abdelkafy
                    nameSora.add(model)
                    ++i
                }
            }
            23 -> {
                val arrayListKhaledElgalel =
                    application.resources.getStringArray(R.array.linkesKhaledElgalel)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListKhaledElgalel[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.jleel)
                    model.urlImage = R.drawable.jleel
                    nameSora.add(model)
                    ++i
                }
            }
            24 -> {
                val arrayListKhaledElqahtamny =
                    application.resources.getStringArray(R.array.linkesKhaledElqahtamny)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListKhaledElqahtamny[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.qht)
                    model.urlImage = R.drawable.qht
                    nameSora.add(model)
                    ++i
                }
            }
            25 -> {
                val arrayListKhalefaEltenegy =
                    application.resources.getStringArray(R.array.linkesKhalefaEltenegy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListKhalefaEltenegy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.tnjy)
                    model.urlImage = R.drawable.tnjy
                    nameSora.add(model)
                    ++i
                }
            }
            26 -> {
                val arrayListRamyEldeaas =
                    application.resources.getStringArray(R.array.linkesRamyEldeaas)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListRamyEldeaas[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ramy_eldabs)
                    model.urlImage = R.mipmap.logo
                    nameSora.add(model)
                    ++i
                }
            }
            27 -> {
                val arrayListZaky = application.resources.getStringArray(R.array.linkesZaky)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListZaky[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.zaki_daghistani)
                    model.urlImage = R.drawable.zaki_daghistani
                    nameSora.add(model)
                    ++i
                }
            }
            28 -> {
                val arrayListSahlYassen =
                    application.resources.getStringArray(R.array.linkesSahlYassen)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSahlYassen[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.sahl_yassen)
                    model.urlImage = R.drawable.sahl_yassen
                    nameSora.add(model)
                    ++i
                }
            }
            29 -> {
                val arrayListSadElghamdy =
                    application.resources.getStringArray(R.array.linkesSadElghamdy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSadElghamdy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.s_gmd)
                    model.urlImage = R.drawable.s_gmd
                    nameSora.add(model)
                    ++i
                }
            }
            30 -> {
                val arrayListSadElmeqren =
                    application.resources.getStringArray(R.array.linkeSadElmeqren)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSadElmeqren[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.saad)
                    model.urlImage = R.mipmap.logo
                    nameSora.add(model)
                    ++i
                }
            }
            31 -> {
                val arrayListSoaadElsherem =
                    application.resources.getStringArray(R.array.linkesSoaadElsherem)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSoaadElsherem[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.soud_elsherem)
                    model.urlImage = R.drawable.sherem
                    nameSora.add(model)
                    ++i
                }
            }
            32 -> {
                val arrayListSyedRamadan =
                    application.resources.getStringArray(R.array.linkesSyedRamadan)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSyedRamadan[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.sayed)
                    model.urlImage = R.drawable.syed_rmdan
                    nameSora.add(model)
                    ++i
                }
            }
            33 -> {
                val arrayListShahrzadTaher =
                    application.resources.getStringArray(R.array.linkesShahrzadTaher)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListShahrzadTaher[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.taher)
                    model.urlImage = R.drawable.taher
                    nameSora.add(model)
                    ++i
                }
            }
            34 -> {
                val arrayListHkm = application.resources.getStringArray(R.array.linkesHKM)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListHkm[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.hkm)
                    model.urlImage = R.drawable.hkm
                    nameSora.add(model)
                    ++i
                }
            }
            35 -> {
                val arrayListSalahHashim =
                    application.resources.getStringArray(R.array.linkesSalahHashim)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSalahHashim[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.salah_hashim_m)
                    model.urlImage = R.drawable.salah_hashim_m
                    nameSora.add(model)
                    ++i
                }
            }
            36 -> {
                val arrayListBder = application.resources.getStringArray(R.array.linkesBder)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListBder[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.slah_elbadry)
                    model.urlImage = R.drawable.bder
                    nameSora.add(model)
                    ++i
                }
            }
            37 -> {
                val arrayListBuKhtr = application.resources.getStringArray(R.array.linkesBuKhtr)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListBuKhtr[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.bu_khtr)
                    model.urlImage = R.drawable.bu_khtr
                    nameSora.add(model)
                    ++i
                }
            }
            38 -> {
                val arrayListTareq = application.resources.getStringArray(R.array.linkesTareq)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListTareq[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.tareq)
                    model.urlImage = R.drawable.tarek_abdelany
                    nameSora.add(model)
                    ++i
                }
            }
            39 -> {
                val arrayListRyan = application.resources.getStringArray(R.array.linkesRyan)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListRyan[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ryan)
                    model.urlImage = R.drawable.ryan
                    nameSora.add(model)
                    ++i
                }
            }
            40 -> {
                val arrayListElklbanyHafs =
                    application.resources.getStringArray(R.array.linkesElklbany)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElklbanyHafs[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.adel_elkalbany)
                    model.urlImage = R.drawable.elklbany
                    nameSora.add(model)
                    ++i
                }
            }
            41 -> {
                val arrayListAbdelbasetHafs =
                    application.resources.getStringArray(R.array.linkesAbdelbasetHAfs)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdelbasetHafs[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelbaset)
                    model.urlImage = R.drawable.abdeelbaset
                    nameSora.add(model)
                    ++i
                }
            }
            42 -> {
                val arrayListAbdelbasetWarsh =
                    application.resources.getStringArray(R.array.linkesAbdelbasetWarsh)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdelbasetWarsh[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelbaset)
                    model.urlImage = R.drawable.abdeelbaset
                    nameSora.add(model)
                    ++i
                }
            }
            43 -> {
                val arrayListAbdelbasetMgwad =
                    application.resources.getStringArray(R.array.linkesAbdelbasetMgwad)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdelbasetMgwad[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelbaset)
                    model.urlImage = R.drawable.abdeelbaset
                    nameSora.add(model)
                    ++i
                }
            }
            44 -> {
                val arrayListAbdlaElgeheny =
                    application.resources.getStringArray(R.array.linkesAbdlaElgeheny)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdlaElgeheny[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdellah_elgeheny)
                    model.urlImage = R.drawable.elgeheny
                    nameSora.add(model)
                    ++i
                }
            }
            45 -> {
                var i = 0
                val arrayLinkElqasem = application.resources.getStringArray(R.array.linkesElqasem)

                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayLinkElqasem[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelrhman_elqasem)
                    model.urlImage = R.drawable.elqasem
                    nameSora.add(model)
                    ++i
                }
            }
            46 -> {
                val arrayListSoufi = application.resources.getStringArray(R.array.linkesSoufi)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSoufi[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelrshed_sofy)
                    model.urlImage = R.drawable.soufi_hfs
                    nameSora.add(model)
                    ++i
                }
            }
            47 -> {
                val arrayListSoufiKlf = application.resources.getStringArray(R.array.linkesSoufiKlf)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSoufiKlf[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelrshed_sofy)
                    model.urlImage = R.drawable.soufi_hfs
                    nameSora.add(model)
                    ++i
                }
            }
            48 -> {
                val arrayListbdelazezZahrani =
                    application.resources.getStringArray(R.array.linkesAbdelazezZahrani)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListbdelazezZahrani[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelazez_elzahrany)
                    model.urlImage = R.drawable.abdelazez_elzahrany
                    nameSora.add(model)
                    ++i
                }
            }
            49 -> {
                val arrayListAbdelbariElthebety =
                    application.resources.getStringArray(R.array.linkesAbdelbariElthebety)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdelbariElthebety[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelbara_elthebety)
                    model.urlImage = R.drawable.abdelbara_elthebety
                    nameSora.add(model)
                    ++i
                }
            }
            50 -> {
                val arraylistAbdelrhmanEloasi =
                    application.resources.getStringArray(R.array.linkesAbdelrhmanEloasi)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arraylistAbdelrhmanEloasi[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelrhman_elosa)
                    model.urlImage = R.drawable.abdelrhman_elosa
                    nameSora.add(model)
                    ++i
                }
            }
            51 -> {
                val arrayListBary = application.resources.getStringArray(R.array.linkesBary)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListBary[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.bari)
                    model.urlImage = R.drawable.abdelbary
                    nameSora.add(model)
                    ++i
                }
            }
            52 -> {
                val arrayListElsodes = application.resources.getStringArray(R.array.linkesElsodes)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElsodes[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdelrhman_elsodes)
                    model.urlImage = R.drawable.elsodes
                    nameSora.add(model)
                    ++i
                }
            }
            53 -> {
                val arrayListAbdelRhmanMaged =
                    application.resources.getStringArray(R.array.linkesAbdelrhmanMaged)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdelRhmanMaged[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.a_majed)
                    model.urlImage = R.drawable.a_majed
                    nameSora.add(model)
                    ++i
                }
            }
            54 -> {
                val arrayListSoufiHfs = application.resources.getStringArray(R.array.linkesSoufiHfs)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSoufiHfs[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.soufi_hfs)
                    model.urlImage = R.drawable.soufi_hfs
                    nameSora.add(model)
                    ++i
                }
            }
            55 -> {
                val arrayListAbdelazezAhmed =
                    application.resources.getStringArray(R.array.linkesAbdelazezAhmed)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdelazezAhmed[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.a_ahmed)
                    model.urlImage = R.drawable.a_ahmed
                    nameSora.add(model)
                    ++i
                }
            }
            56 -> {
                val arrayListkhalf = application.resources.getStringArray(R.array.linkeKkhalf)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListkhalf[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.khalf)
                    model.urlImage = R.drawable.khalf
                    nameSora.add(model)
                    ++i
                }
            }
            57 -> {
                val arrayListAbdalah = application.resources.getStringArray(R.array.linkesAbdalah)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdalah[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.abdullahk)
                    model.urlImage = R.drawable.abdullahk
                    nameSora.add(model)
                    ++i
                }
            }
            58 -> {
                val arrayListBsfr = application.resources.getStringArray(R.array.linkesBsfr)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListBsfr[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.bsfr)
                    model.urlImage = R.drawable.bsfr
                    nameSora.add(model)
                    ++i
                }
            }
            59 -> {
                val arrayListKyat = application.resources.getStringArray(R.array.linkesKyat)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListKyat[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.kyat)
                    model.urlImage = R.drawable.kyat
                    nameSora.add(model)
                    ++i
                }
            }
            60 -> {
                val arrayListGulan = application.resources.getStringArray(R.array.linkesGulan)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListGulan[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.gulan)
                    model.urlImage = R.drawable.gulan
                    nameSora.add(model)
                    ++i
                }
            }
            61 -> {
                val arrayListMohsinHarthi =
                    application.resources.getStringArray(R.array.linkesmohsin_harthi)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMohsinHarthi[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mohsin_harthi)
                    model.urlImage = R.drawable.mohsin_harthi
                    nameSora.add(model)
                    ++i
                }
            }
            62 -> {
                val arrayListObk = application.resources.getStringArray(R.array.linkesObk)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListObk[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.obk)
                    model.urlImage = R.drawable.obk
                    nameSora.add(model)
                    ++i
                }
            }
            63 -> {
                val arrayListKanakeri = application.resources.getStringArray(R.array.linkesKanakeri)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListKanakeri[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.kanakeri)
                    model.urlImage = R.drawable.kanakeri
                    nameSora.add(model)
                    ++i
                }
            }
            64 -> {
                val arrayListWdod = application.resources.getStringArray(R.array.linkesWdod)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListWdod[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.wdod)
                    model.urlImage = R.drawable.wdod
                    nameSora.add(model)
                    ++i
                }
            }
            65 -> {
                val arrayListAjbr = application.resources.getStringArray(R.array.linkesAjbr)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAjbr[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.a_jbr)
                    model.urlImage = R.drawable.a_jbr
                    nameSora.add(model)
                    ++i
                }
            }
            66 -> {
                val arrayListAliElhozefy =
                    application.resources.getStringArray(R.array.linkesAlyElhozefy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAliElhozefy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ali_elhozefy)
                    model.urlImage = R.drawable.ali_elhozefy
                    nameSora.add(model)
                    ++i
                }
            }
            67 -> {
                var i = 0
                val arrayLinkAliElhozefyHafs =
                    application.resources.getStringArray(R.array.linkesAliAlyElhozefyHafs)
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayLinkAliElhozefyHafs[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ali_elhozefy)
                    model.urlImage = R.drawable.ali_elhozefy
                    nameSora.add(model)
                    ++i
                }
            }
            68 -> {
                var i = 0
                val arrayLinkAlyElswesy =
                    application.resources.getStringArray(R.array.linkesAlyElswesy)

                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayLinkAlyElswesy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ali_swiesy)
                    model.urlImage = R.drawable.ali_swiesy
                    nameSora.add(model)
                    ++i
                }
            }
            69 -> {
                val arrayListHafz = application.resources.getStringArray(R.array.linkesHafz)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListHafz[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.hafz)
                    model.urlImage = R.drawable.hafz
                    nameSora.add(model)
                    ++i
                }
            }
            70 -> {
                val arrayListOmarElqzabry =
                    application.resources.getStringArray(R.array.linkesOmarElqzabry)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListOmarElqzabry[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.omar_kzabri)
                    model.urlImage = R.drawable.omar_kzabri
                    nameSora.add(model)
                    ++i
                }
            }
            71 -> {
                val arraylistfrsA = application.resources.getStringArray(R.array.linkesFrs_a)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arraylistfrsA[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.frs_a)
                    model.urlImage = R.drawable.frs_a
                    nameSora.add(model)
                    ++i
                }
            }
            72 -> {
                val arrayListMaherElmeaqly =
                    application.resources.getStringArray(R.array.linkesMaherElmaqly)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMaherElmeaqly[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.maher_elmeaqly)
                    model.urlImage = R.drawable.almaqely
                    nameSora.add(model)
                    ++i
                }
            }
            73 -> {
                val arrayListShakshfs = application.resources.getStringArray(R.array.linkesShakshFs)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListShakshfs[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.shaksh)
                    model.urlImage = R.drawable.shaksh
                    nameSora.add(model)
                    ++i
                }
            }
            74 -> {
                val arrayListAyyub = application.resources.getStringArray(R.array.linkesAyyub)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAyyub[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ayyub)
                    model.urlImage = R.drawable.ayyub
                    nameSora.add(model)
                    ++i
                }
            }
            75 -> {
                val arrayListMKrm = application.resources.getStringArray(R.array.linkesM_krm)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMKrm[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.m_krm)
                    model.urlImage = R.drawable.m_krm
                    nameSora.add(model)
                    ++i
                }
            }
            76 -> {
                val arrayListMkreemWarsh =
                    application.resources.getStringArray(R.array.linkesMkreem_warsh)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMkreemWarsh[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.m_krm)
                    model.urlImage = R.drawable.m_krm
                    nameSora.add(model)
                    ++i
                }
            }
            77 -> {
                val arrayListEarawi = application.resources.getStringArray(R.array.linkesEarawi)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListEarawi[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.earawi)
                    model.urlImage = R.drawable.earawi
                    nameSora.add(model)
                    ++i
                }
            }
            78 -> {
                val arrayListShah = application.resources.getStringArray(R.array.linkesShah)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListShah[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.shah)
                    model.urlImage = R.drawable.shah
                    nameSora.add(model)
                    ++i
                }
            }
            79 -> {
                val arrayListRashad = application.resources.getStringArray(R.array.linkesRashad)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListRashad[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.rashad)
                    model.urlImage = R.drawable.rashad
                    nameSora.add(model)
                    ++i
                }
            }
            80 -> {
                val arrayListLhdan = application.resources.getStringArray(R.array.linkesLhdan)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListLhdan[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.lhdan)
                    model.urlImage = R.drawable.lhdan
                    nameSora.add(model)
                    ++i
                }
            }
            81 -> {
                val arrayListKhan = application.resources.getStringArray(R.array.linkesKhan)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListKhan[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.khan)
                    model.urlImage = R.mipmap.logo
                    nameSora.add(model)
                    ++i
                }
            }
            82 -> {
                val arrayListElmenshawyMgwad =
                    application.resources.getStringArray(R.array.linkesElmenshawyMgwad)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElmenshawyMgwad[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mohamed_elmenshawyM)
                    model.urlImage = R.drawable.elmenshawy
                    nameSora.add(model)
                    ++i
                }
            }
            83 -> {
                var i = 0
                val arrayLinkElmenshawyHafs =
                    application.resources.getStringArray(R.array.linkesElmenshawyHafs)

                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayLinkElmenshawyHafs[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mohamed_elmenshawyH)
                    model.urlImage = R.drawable.elmenshawy
                    nameSora.add(model)
                    ++i
                }
            }
            84 -> {
                val arrayListAltablawy =
                    application.resources.getStringArray(R.array.linkesEltblawy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAltablawy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mohamed_eltblawy)
                    model.urlImage = R.drawable.eltblawy
                    nameSora.add(model)
                    ++i
                }
            }
            85 -> {
                val arrayListAbdelhakemAlabdlah =
                    application.resources.getStringArray(R.array.linkesAbdelhakemAlabdlah)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdelhakemAlabdlah[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mohamed_abd_elhakem)
                    model.urlImage = R.drawable.mohamed_abd_elhakem
                    nameSora.add(model)
                    ++i
                }
            }
            86 -> {
                val arrayListAbdullahDoris =
                    application.resources.getStringArray(R.array.linkesAbdullahDoris)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListAbdullahDoris[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mohamed_abd_elhakem)
                    model.urlImage = R.drawable.mohamed_abd_elhakem
                    nameSora.add(model)
                    ++i
                }
            }
            87 -> {
                val arrayListMohamedGbrer =
                    application.resources.getStringArray(R.array.linkesMohamedGbrer)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMohamedGbrer[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mohamed_gebrer)
                    model.urlImage = R.drawable.mohamedgbrer
                    nameSora.add(model)
                    ++i
                }
            }
            88 -> {
                val arrayListMsharyElafasy =
                    application.resources.getStringArray(R.array.linkesMsharyElafasy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMsharyElafasy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mshary_elafasy)
                    model.urlImage = R.drawable.msharyelafasy
                    nameSora.add(model)
                    ++i
                }
            }
            89 -> {
                val arrayListMrifai = application.resources.getStringArray(R.array.linkesMrifai)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMrifai[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mrifai)
                    model.urlImage = R.drawable.mrifai
                    nameSora.add(model)
                    ++i
                }
            }
            90 -> {
                val arrayListSheimy = application.resources.getStringArray(R.array.linkesSheimy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSheimy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.sheimy)
                    model.urlImage = R.drawable.sheimy
                    nameSora.add(model)
                    ++i
                }
            }
            91 -> {
                var i = 0
                val arrayLinkElhosaryMgwad =
                    application.resources.getStringArray(R.array.linkesElhosaryMgwad)

                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayLinkElhosaryMgwad[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mahmoud_elhosaryM)
                    model.urlImage = R.drawable.elhosary
                    nameSora.add(model)
                    ++i
                }
            }
            92 -> {
                val arrayListElhosaryWarsh =
                    application.resources.getStringArray(R.array.linkesElhosaryWarsh)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElhosaryWarsh[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mahmoud_elhosaryM)
                    model.urlImage = R.drawable.elhosary
                    nameSora.add(model)
                    ++i
                }
            }
            93 -> {
                val arrayListElhosaryHafs =
                    application.resources.getStringArray(R.array.linkesElhosaryHAfs)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElhosaryHafs[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mahmoud_elhosaryH)
                    model.urlImage = R.drawable.elhosary
                    nameSora.add(model)
                    ++i
                }
            }
            94 -> {
                val arrayListElbana = application.resources.getStringArray(R.array.linkesElbana)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElbana[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mahmoud_elbana)
                    model.urlImage = R.drawable.elbana
                    nameSora.add(model)
                    ++i
                }
            }
            95 -> {
                val arrayListBnaMjwds =
                    application.resources.getStringArray(R.array.linkesBna_mjwds)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListBnaMjwds[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mahmoud_elbana)
                    model.urlImage = R.drawable.elbana
                    nameSora.add(model)
                    ++i
                }
            }
            96 -> {
                val arrayListMustafa = application.resources.getStringArray(R.array.linkesMustafa)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMustafa[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.mustafa)
                    model.urlImage = R.drawable.mustafa
                    nameSora.add(model)
                    ++i
                }
            }
            97 -> {
                val arrayListLahoni = application.resources.getStringArray(R.array.linkesLahoni)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListLahoni[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.lahoni)
                    model.urlImage = R.drawable.lahoni
                    nameSora.add(model)
                    ++i
                }
            }
            98 -> {
                val arrayListRa3ad = application.resources.getStringArray(R.array.linkesRa3ad)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListRa3ad[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.ra3ad)
                    model.urlImage = R.drawable.ra3ad
                    nameSora.add(model)
                    ++i
                }
            }
            99 -> {
                val arrayListHarthi = application.resources.getStringArray(R.array.linkesHarthi)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListHarthi[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.harthi)
                    model.urlImage = R.mipmap.logo
                    nameSora.add(model)
                    ++i
                }
            }
            100 -> {
                val arrayListMoftahElsalatnyEldawary =
                    application.resources.getStringArray(R.array.linkesMoftahElsalatnyEldawary)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMoftahElsalatnyEldawary[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.moftah_elslty)
                    model.urlImage = R.drawable.moftah_elslty
                    nameSora.add(model)
                    ++i
                }
            }
            101 -> {
                val arrayListMoftahElsalatnyEnbZakor =
                    application.resources.getStringArray(R.array.linkesMoftahElsalatnyEnbZakor)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMoftahElsalatnyEnbZakor[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.moftah_elslty)
                    model.urlImage = R.drawable.moftah_elslty
                    nameSora.add(model)
                    ++i
                }
            }
            102 -> {
                val arrayListMoftahHafs =
                    application.resources.getStringArray(R.array.linkesMoftahHafs)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMoftahHafs[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.moftah_elslty)
                    model.urlImage = R.drawable.moftah_elslty
                    nameSora.add(model)
                    ++i
                }
            }
            103 -> {
                val arrayListMoftahElsltny =
                    application.resources.getStringArray(R.array.linkesMoftahElsltny)
                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListMoftahElsltny[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.moftah_elslty)
                    model.urlImage = R.drawable.moftah_elslty
                    nameSora.add(model)
                    ++i
                }
            }
            104 -> {
                val arrayListBilal = application.resources.getStringArray(R.array.linkesBilal)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListBilal[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.bilal)
                    model.urlImage = R.drawable.bilal
                    nameSora.add(model)
                    ++i
                }
            }
            105 -> {
                val arrayListNaserElqatamy =
                    application.resources.getStringArray(R.array.linkesNaserElqatamy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListNaserElqatamy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.naser_elqatamy)
                    model.urlImage = R.drawable.naserelqatamy
                    nameSora.add(model)
                    ++i
                }
            }
            106 -> {
                val arraylistNasserAlmajed =
                    application.resources.getStringArray(R.array.linkesNasser_almajed)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arraylistNasserAlmajed[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.nasser_almajed)
                    model.urlImage = R.drawable.nasser_almajed
                    nameSora.add(model)
                    ++i
                }
            }
            107 -> {
                val arrayListNabelElrefay =
                    application.resources.getStringArray(R.array.linkesNabelElrefay)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListNabelElrefay[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.nabel_elrefaay)
                    model.urlImage = R.drawable.nabel_elrefaay
                    nameSora.add(model)
                    ++i
                }
            }
            108 -> {
                val arrayListNamh = application.resources.getStringArray(R.array.linkesNamh)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListNamh[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.namh)
                    model.urlImage = R.drawable.namh
                    nameSora.add(model)
                    ++i
                }
            }
            109 -> {
                val arraylinkesWadeaElyamany =
                    application.resources.getStringArray(R.array.linkesWadeaElyamany)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arraylinkesWadeaElyamany[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.wadea_elyamany)
                    model.urlImage = R.drawable.wadea_elyamany
                    nameSora.add(model)
                    ++i
                }
            }
            110 -> {
                var i = 0
                val arrayLinkYasserEldosary =
                    application.resources.getStringArray(R.array.linkesyasser)
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayLinkYasserEldosary[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.yaser_eldosary)
                    model.urlImage = R.drawable.eldosary
                    nameSora.add(model)
                    ++i
                }
            }
            111 -> {
                val arrayListHani = application.resources.getStringArray(R.array.linkesHani)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListHani[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.hani)
                    model.urlImage = R.drawable.hani
                    nameSora.add(model)
                    ++i
                }
            }
            112 -> {
                val arrayListWaledElnaahy =
                    application.resources.getStringArray(R.array.linkesWaledElnaahy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListWaledElnaahy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.waled_elnaehy)
                    model.urlImage = R.mipmap.logo
                    nameSora.add(model)
                    ++i
                }
            }
            113 -> {
                val arrayListYaserElmazroay =
                    application.resources.getStringArray(R.array.linkesYaserElmazroay)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListYaserElmazroay[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.yaser_elmazroay)
                    model.urlImage = R.drawable.yaser_elmazroay
                    nameSora.add(model)
                    ++i
                }
            }
            114 -> {
                val arrayListElqarashy =
                    application.resources.getStringArray(R.array.linkesElqarashy)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListElqarashy[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.yaser_elqarashy)
                    model.urlImage = R.drawable.elqarashy
                    nameSora.add(model)
                    ++i
                }
            }
            115 -> {
                val arrayListSalamah = application.resources.getStringArray(R.array.linkesSalamah)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListSalamah[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.salamah)
                    model.urlImage = R.drawable.salamah
                    nameSora.add(model)
                    ++i
                }
            }
            116 -> {
                val arrayListYasenElgazaery =
                    application.resources.getStringArray(R.array.linkesYasenElgazaery)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListYasenElgazaery[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.yasen_elgazaery)
                    model.urlImage = R.drawable.yasen_elgazaery
                    nameSora.add(model)
                    ++i
                }
            }
            117 -> {
                val arrayListYahya = application.resources.getStringArray(R.array.linkesYahya)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListYahya[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.yahya)
                    model.urlImage = R.drawable.yahya
                    nameSora.add(model)
                    ++i
                }
            }
            118 -> {
                val arrayListNoah = application.resources.getStringArray(R.array.linkesNoah)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListNoah[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.noah)
                    model.urlImage = R.drawable.noah
                    nameSora.add(model)
                    ++i
                }
            }
            119 -> {
                val arrayListYosefElshereay =
                    application.resources.getStringArray(R.array.linkesYousef)

                var i = 0
                while (i < soar.size) {
                    val model = ImageModel()
                    model.nameSora = soar[i]
                    model.soraLink = arrayListYosefElshereay[i]
                    model.position = i
                    model.nameShekh = application.getString(R.string.yosem_elshereay)
                    model.urlImage = R.drawable.yosem_elshereay
                    nameSora.add(model)
                    ++i
                }
            }
            else -> Toast.makeText(
                application.applicationContext,
                "لم يتم ادخال قائمة بعد",
                Toast.LENGTH_SHORT
            ).show()
        }
        return nameSora
    }
}
