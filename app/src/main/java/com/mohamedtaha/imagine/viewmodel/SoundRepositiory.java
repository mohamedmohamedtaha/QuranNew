package com.mohamedtaha.imagine.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.mvp.model.ImageModel;

import java.util.ArrayList;

public class SoundRepositiory {
    private ArrayList<ImageModel> name_sora = new ArrayList<>();
    private Application application;
    private MutableLiveData<ArrayList<ImageModel>> mutableLiveDataNameSora = new MutableLiveData<>();
    String[] ArrayLinkYaserEldosary;
    String[] ArrayLinkElhosaryMgwad;
    String[] ArrayLinkElmenshawyHafs;
    String[] ArrayLinkElmenshawyMgwad;
    String[] ArrayLinkAbdlaElgeheny;
    String[] ArrayLinkMAHER_ELMEAQLY;
    String[] ArrayLinkAbdelbasetMgwad;
    String[] ArrayLinkSoaadElsherem;
    String[] ArrayLinkALTABLAWY;
    String[] ArrayLinkElklbanyHafs;
    String[] ArrayLinkElsodes;
    String[] ArrayLinkMsharyElafasy;
    String[] ArrayLinkElhosaryHAfs;
    String[] ArrayLinkMohamedGbrer;
    String[] ArrayLinkNaserElqatamy;
    String[] ArrayLinkElagamy;
    String[] ArrayLinkElbana;
    String[] ArrayLinkElqarashy;
    String[] ArrayLinkElqasem;
    String[] ArrayLinkBder;
    String[] ArrayLinkAhmedElhawashy, ArrayLinkAhmedEltrabolsy, ArrayLinkAhmedKhedrEltrabolsy, ArrayLinkAhmedSaber, ArrayLinkAhmedAmer,
            ArrayLinkAhmedNeanea, ArrayLinkAkramAlAalakmi, ArrayLinkEbrahemElakhdar, ArrayLinkEbraghemeldosary, ArrayLinkEbraghem_eldosaryWarsh,
            ArrayLinkEldokalyMohamed, ArrayLinkElzenMohamed, ArrayLinkEloyonElkorashy, ArrayLinkElfalehMohamed, ArrayLinkYasenElgazaery,
            ArrayLinkBnderBelela, ArrayLinkJamalShaker, ArrayLinkJamanElesemy, ArrayLinkZaky, ArrayLinkSoufiKlf, ArrayLinkSoufi,
            ArrayLinkbdelazezZahrani, ArrayLinkAliElhozefy, ArrayLinkAliElhozefyHafs, ArrayLinkAlyElswesy, ArrayLinkOmarElqzabry,
            ArrayLinkAbdelhakemAlabdlah, ArrayLinklinkesMoftahElsltny, ArrayLinkNabelElrefay, ArrayLinkWaledElnaahy, ArrayLinkYaserElmazroay,
            ArrayLinkYosefElshereay, ArraylinkesTwfeaElsayegh, ArraylinkesRamyEldeaas, ArraylinkesSahlYassen, ArraylinkesAbdelbariElthebety,
            ArraylinkesAbdelrhmanEloasi, ArraylinkesElhosaryWarsh, ArraylinkesWadeaElyamany, ArraylinkesMoftahElsalatnyEldawary,
            ArraylinkesMoftahElsalatnyEnbZakor, ArraylinkesMoftahHafs, ArraylinkesHatemFared, ArraylinkesKhalidElmohana,
            ArraylinkesKhalidAbdElkafy, linkesKhaledElgalel, linkesKhaledElqahtamny, linkesKhalefaEltenegy, linkesSadElghamdy,
            linkeSadElmeqren, linkesSyedRamadan, linkesElshatry, linkesShahrzadTaher, linkesHKM, linkesSalahHashim, linkesBuKhtr,
            linkesTareq, linkesRyan, linkesBary, linkesAbdelbasetHAfs, linkesAbdelbasetWarsh, linkesAbdelrhmanMaged, linkesSoufiHfs,
            linkesAbdelazezAhmed, linkeKkhalf, linkesAbdalah, linkesBsfr, linkesKyat, linkesGulan, linkesmohsin_harthi, linkesObk,
            linkesKanakeri, linkesWdod, linkesAjbr, linkesHafz, linkesFrs_a, linkesShakshFs, linkesAyyub, linkesEarawi, linkesLhdan,
            linkesRashad, linkesShah, linkesAbdullahDoris, linkesM_krm, linkesMkreem_warsh, linkesKhan, linkesMrifai, linkesSheimy,
            linkesBna_mjwds, linkesMustafa, linkesLahoni, linkesRa3ad, linkesHarthi, linkesBilal, linkesNasser_almajed, linkesNamh,
            linkesHani, linkesSalamah, linkesYahya, linkesNoah;


    public SoundRepositiory(Application application) {
        this.application = application;
    }

    public MutableLiveData<ArrayList<ImageModel>> getMutableLiveDataNameSora(int position) {
        //Fetch the data in String.xml file
        ArrayLinkElhosaryMgwad = application.getResources().getStringArray(R.array.linkesElhosaryMgwad);
        ArrayLinkAbdelbasetMgwad = application.getResources().getStringArray(R.array.linkesAbdelbasetMgwad);
        ArrayLinkMAHER_ELMEAQLY = application.getResources().getStringArray(R.array.linkesMaherElmaqly);
        ArrayLinkSoaadElsherem = application.getResources().getStringArray(R.array.linkesSoaadElsherem);
        ArrayLinkALTABLAWY = application.getResources().getStringArray(R.array.linkesEltblawy);
        ArrayLinkElklbanyHafs = application.getResources().getStringArray(R.array.linkesElklbany);
        ArrayLinkAbdlaElgeheny = application.getResources().getStringArray(R.array.linkesAbdlaElgeheny);
        ArrayLinkElmenshawyMgwad = application.getResources().getStringArray(R.array.linkesElmenshawyMgwad);
        ArrayLinkElmenshawyHafs = application.getResources().getStringArray(R.array.linkesElmenshawyHafs);
        ArrayLinkYaserEldosary = application.getResources().getStringArray(R.array.linkesyasser);
        ArrayLinkElsodes = application.getResources().getStringArray(R.array.linkesElsodes);
        ArrayLinkMsharyElafasy = application.getResources().getStringArray(R.array.linkesMsharyElafasy);
        ArrayLinkMohamedGbrer = application.getResources().getStringArray(R.array.linkesMohamedGbrer);
        ArrayLinkElhosaryHAfs = application.getResources().getStringArray(R.array.linkesElhosaryHAfs);
        ArrayLinkNaserElqatamy = application.getResources().getStringArray(R.array.linkesNaserElqatamy);
        ArrayLinkElagamy = application.getResources().getStringArray(R.array.linkesElagamy);
        ArrayLinkElbana = application.getResources().getStringArray(R.array.linkesElbana);
        ArrayLinkElqarashy = application.getResources().getStringArray(R.array.linkesElqarashy);
        ArrayLinkElqasem = application.getResources().getStringArray(R.array.linkesElqasem);
        ArrayLinkBder = application.getResources().getStringArray(R.array.linkesBder);
        ArrayLinkAhmedElhawashy = application.getResources().getStringArray(R.array.linkesAhmedElhawashy);
        ArrayLinkAhmedEltrabolsy = application.getResources().getStringArray(R.array.linkesAhmedEltrabolsy);
        ArrayLinkAhmedKhedrEltrabolsy = application.getResources().getStringArray(R.array.linkesAhmedKhedrEltrabolsy);
        ArrayLinkAhmedSaber = application.getResources().getStringArray(R.array.linkesAhmedSaber);
        ArrayLinkAhmedAmer = application.getResources().getStringArray(R.array.linkesAhmedAmer);
        ArrayLinkAhmedNeanea = application.getResources().getStringArray(R.array.linkesAhmedNeanea);
        ArrayLinkAkramAlAalakmi = application.getResources().getStringArray(R.array.linkesAkramAlAalakmi);
        ArrayLinkEbrahemElakhdar = application.getResources().getStringArray(R.array.linkesEbrahemElakhdar);
        ArrayLinkEbraghemeldosary = application.getResources().getStringArray(R.array.linkesEbrahemEldosary);
        ArrayLinkEbraghem_eldosaryWarsh = application.getResources().getStringArray(R.array.linkesEbrahemEldosaryWarsh);
        ArrayLinkEldokalyMohamed = application.getResources().getStringArray(R.array.linkesEldokalyMohamed);
        ArrayLinkElzenMohamed = application.getResources().getStringArray(R.array.linkesElzenMohamed);
        ArrayLinkEloyonElkorashy = application.getResources().getStringArray(R.array.linkesEloyonElkorashy);
        ArrayLinkElfalehMohamed = application.getResources().getStringArray(R.array.linkesElfalemMohamed);
        ArrayLinkYasenElgazaery = application.getResources().getStringArray(R.array.linkesYasenElgazaery);
        ArrayLinkBnderBelela = application.getResources().getStringArray(R.array.linkesBnderBelela);
        ArrayLinkJamalShaker = application.getResources().getStringArray(R.array.linkesJamalShaker);
        ArrayLinkJamanElesemy = application.getResources().getStringArray(R.array.linkesJamanElesemy);
        ArrayLinkZaky = application.getResources().getStringArray(R.array.linkesZaky);
        ArrayLinkSoufiKlf = application.getResources().getStringArray(R.array.linkesSoufiKlf);
        ArrayLinkSoufi = application.getResources().getStringArray(R.array.linkesSoufi);
        ArrayLinkbdelazezZahrani = application.getResources().getStringArray(R.array.linkesAbdelazezZahrani);
        ArrayLinkAliElhozefy = application.getResources().getStringArray(R.array.linkesAlyElhozefy);
        ArrayLinkAliElhozefyHafs = application.getResources().getStringArray(R.array.linkesAliAlyElhozefyHafs);
        ArrayLinkAlyElswesy = application.getResources().getStringArray(R.array.linkesAlyElswesy);
        ArrayLinkOmarElqzabry = application.getResources().getStringArray(R.array.linkesOmarElqzabry);
        ArrayLinkAbdelhakemAlabdlah = application.getResources().getStringArray(R.array.linkesAbdelhakemAlabdlah);
        ArrayLinklinkesMoftahElsltny = application.getResources().getStringArray(R.array.linkesMoftahElsltny);
        ArrayLinkNabelElrefay = application.getResources().getStringArray(R.array.linkesNabelElrefay);
        ArrayLinkWaledElnaahy = application.getResources().getStringArray(R.array.linkesWaledElnaahy);
        ArrayLinkYaserElmazroay = application.getResources().getStringArray(R.array.linkesYaserElmazroay);
        ArrayLinkYosefElshereay = application.getResources().getStringArray(R.array.linkesYousef);
        ArraylinkesTwfeaElsayegh = application.getResources().getStringArray(R.array.linkesTwfeaElsayegh);
        ArraylinkesRamyEldeaas = application.getResources().getStringArray(R.array.linkesRamyEldeaas);
        ArraylinkesSahlYassen = application.getResources().getStringArray(R.array.linkesSahlYassen);
        ArraylinkesAbdelbariElthebety = application.getResources().getStringArray(R.array.linkesAbdelbariElthebety);
        ArraylinkesAbdelrhmanEloasi = application.getResources().getStringArray(R.array.linkesAbdelrhmanEloasi);
        ArraylinkesElhosaryWarsh = application.getResources().getStringArray(R.array.linkesElhosaryWarsh);
        ArraylinkesWadeaElyamany = application.getResources().getStringArray(R.array.linkesWadeaElyamany);
        ArraylinkesMoftahElsalatnyEldawary = application.getResources().getStringArray(R.array.linkesMoftahElsalatnyEldawary);
        ArraylinkesMoftahElsalatnyEnbZakor = application.getResources().getStringArray(R.array.linkesMoftahElsalatnyEnbZakor);
        ArraylinkesMoftahHafs = application.getResources().getStringArray(R.array.linkesMoftahHafs);
        ArraylinkesHatemFared = application.getResources().getStringArray(R.array.linkesHatemFared);
        ArraylinkesKhalidElmohana = application.getResources().getStringArray(R.array.linkesKhalidElmohana);
        ArraylinkesKhalidAbdElkafy = application.getResources().getStringArray(R.array.linkesKhalidAbdElkafy);
        linkesKhaledElgalel = application.getResources().getStringArray(R.array.linkesKhaledElgalel);
        linkesKhaledElqahtamny = application.getResources().getStringArray(R.array.linkesKhaledElqahtamny);
        linkesKhalefaEltenegy = application.getResources().getStringArray(R.array.linkesKhalefaEltenegy);
        linkesSadElghamdy = application.getResources().getStringArray(R.array.linkesSadElghamdy);
        linkeSadElmeqren = application.getResources().getStringArray(R.array.linkeSadElmeqren);
        linkesSyedRamadan = application.getResources().getStringArray(R.array.linkesSyedRamadan);
        linkesElshatry = application.getResources().getStringArray(R.array.linkesElshatry);
        linkesShahrzadTaher = application.getResources().getStringArray(R.array.linkesShahrzadTaher);
        linkesHKM = application.getResources().getStringArray(R.array.linkesHKM);
        linkesSalahHashim = application.getResources().getStringArray(R.array.linkesSalahHashim);
        linkesBuKhtr = application.getResources().getStringArray(R.array.linkesBuKhtr);
        linkesTareq = application.getResources().getStringArray(R.array.linkesTareq);
        linkesRyan = application.getResources().getStringArray(R.array.linkesRyan);
        linkesBary = application.getResources().getStringArray(R.array.linkesBary);
        linkesAbdelbasetHAfs = application.getResources().getStringArray(R.array.linkesAbdelbasetHAfs);
        linkesAbdelbasetWarsh = application.getResources().getStringArray(R.array.linkesAbdelbasetWarsh);
        linkesAbdelrhmanMaged = application.getResources().getStringArray(R.array.linkesAbdelrhmanMaged);
        linkesSoufiHfs = application.getResources().getStringArray(R.array.linkesSoufiHfs);
        linkesAbdelazezAhmed = application.getResources().getStringArray(R.array.linkesAbdelazezAhmed);
        linkeKkhalf = application.getResources().getStringArray(R.array.linkeKkhalf);
        linkesAbdalah = application.getResources().getStringArray(R.array.linkesAbdalah);
        linkesBsfr = application.getResources().getStringArray(R.array.linkesBsfr);
        linkesKyat = application.getResources().getStringArray(R.array.linkesKyat);
        linkesGulan = application.getResources().getStringArray(R.array.linkesGulan);
        linkesmohsin_harthi = application.getResources().getStringArray(R.array.linkesmohsin_harthi);
        linkesObk = application.getResources().getStringArray(R.array.linkesObk);
        linkesKanakeri = application.getResources().getStringArray(R.array.linkesKanakeri);
        linkesWdod = application.getResources().getStringArray(R.array.linkesWdod);
        linkesAjbr = application.getResources().getStringArray(R.array.linkesAjbr);
        linkesHafz = application.getResources().getStringArray(R.array.linkesHafz);
        linkesFrs_a = application.getResources().getStringArray(R.array.linkesFrs_a);
        linkesShakshFs = application.getResources().getStringArray(R.array.linkesShakshFs);
        linkesAyyub = application.getResources().getStringArray(R.array.linkesAyyub);
        linkesEarawi = application.getResources().getStringArray(R.array.linkesEarawi);
        linkesLhdan = application.getResources().getStringArray(R.array.linkesLhdan);
        linkesRashad = application.getResources().getStringArray(R.array.linkesRashad);
        linkesShah = application.getResources().getStringArray(R.array.linkesShah);
        linkesAbdullahDoris = application.getResources().getStringArray(R.array.linkesAbdullahDoris);
        linkesM_krm = application.getResources().getStringArray(R.array.linkesM_krm);
        linkesMkreem_warsh = application.getResources().getStringArray(R.array.linkesMkreem_warsh);
        linkesKhan = application.getResources().getStringArray(R.array.linkesKhan);
        linkesMrifai = application.getResources().getStringArray(R.array.linkesMrifai);
        linkesSheimy = application.getResources().getStringArray(R.array.linkesSheimy);
        linkesBna_mjwds = application.getResources().getStringArray(R.array.linkesBna_mjwds);
        linkesMustafa = application.getResources().getStringArray(R.array.linkesMustafa);
        linkesLahoni = application.getResources().getStringArray(R.array.linkesLahoni);
        linkesRa3ad = application.getResources().getStringArray(R.array.linkesRa3ad);
        linkesHarthi = application.getResources().getStringArray(R.array.linkesHarthi);
        linkesBilal = application.getResources().getStringArray(R.array.linkesBilal);
        linkesNasser_almajed = application.getResources().getStringArray(R.array.linkesNasser_almajed);
        linkesNamh = application.getResources().getStringArray(R.array.linkesNamh);
        linkesHani = application.getResources().getStringArray(R.array.linkesHani);
        linkesSalamah = application.getResources().getStringArray(R.array.linkesSalamah);
        linkesYahya = application.getResources().getStringArray(R.array.linkesYahya);
        linkesNoah = application.getResources().getStringArray(R.array.linkesNoah);
        getShekhAyat(position);
        mutableLiveDataNameSora.setValue(name_sora);
        return mutableLiveDataNameSora;
    }

    private void getShekhAyat(int posision) {
        String[] soar = null;
        String[] positions = null;
        soar = application.getResources().getStringArray(R.array.name_allSwar);
        positions = application.getResources().getStringArray(R.array.positions);

        switch (posision) {
            case 0:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkEldokalyMohamed[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.dokaly_mohamed));
                    model.setUrlImage(R.drawable.aldokaly_mohamed);
                    name_sora.add(model);
                }
                break;
            case 1:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElzenMohamed[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.elzen_mohamed));
                    model.setUrlImage(R.drawable.elzen_mohamed);
                    name_sora.add(model);
                }
                break;
            case 2:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkEloyonElkorashy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.al_ouoon_alkushi));
                    model.setUrlImage(R.drawable.al_ouoon_alkushi);
                    name_sora.add(model);
                }
                break;
            case 3:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElfalehMohamed[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.alfateh_mohamed));
                    model.setUrlImage(R.drawable.alfateh_mohamed);
                    name_sora.add(model);
                }
                break;
            case 4:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesElshatry[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.shatri));
                    model.setUrlImage(R.drawable.shatri);
                    name_sora.add(model);
                }
                break;
            case 5:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElagamy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ahmed_elagamy));
                    model.setUrlImage(R.drawable.elagamy);
                    name_sora.add(model);
                }
                break;

            case 6:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAhmedKhedrEltrabolsy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ahmed_eltrabolsy));
                    model.setUrlImage(R.drawable.ahmed_khdr_eltrabolsy);

                    name_sora.add(model);
                }
                break;
            case 7:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAhmedEltrabolsy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ahmed_eltrabolsy));
                    model.setUrlImage(R.drawable.ahmed_khdr_eltrabolsy);
                    name_sora.add(model);
                }
                break;
            case 8:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAhmedElhawashy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ahmed_elhawashy));
                    model.setUrlImage(R.drawable.ahmed_elhawashy);
                    name_sora.add(model);
                }
                break;
            case 9:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAhmedSaber[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ahmed_saber));
                    model.setUrlImage(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 10:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAhmedAmer[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ahmed_amer));
                    model.setUrlImage(R.drawable.ahmed_amer);
                    name_sora.add(model);
                }
                break;
            case 11:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAhmedNeanea[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ahmed_naeane));
                    model.setUrlImage(R.drawable.ahmed_neanea);
                    name_sora.add(model);
                }
                break;
            case 12:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAkramAlAalakmi[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.akram_al_aalakmi));
                    model.setUrlImage(R.drawable.akram_al_aalakmi);
                    name_sora.add(model);
                }
                break;
            case 13:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkEbrahemElakhdar[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ibrahim_al_akhdar));
                    model.setUrlImage(R.drawable.ebrahem_khoder);
                    name_sora.add(model);
                }
                break;
            case 14:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkEbraghemeldosary[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ibrahim_al_dosary));
                    model.setUrlImage(R.drawable.ebrahem_eldosary);
                    name_sora.add(model);
                }
                break;
            case 15:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkEbraghem_eldosaryWarsh[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ibrahim_al_dosary));
                    model.setUrlImage(R.drawable.ebrahem_eldosary);
                    name_sora.add(model);
                }
                break;

            case 16:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkBnderBelela[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.bnder_belela));
                    model.setUrlImage(R.drawable.belela);
                    name_sora.add(model);
                }
                break;
            case 17:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesTwfeaElsayegh[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.tawfik_sayegh));
                    model.setUrlImage(R.drawable.tawfik_sayegh);
                    name_sora.add(model);
                }
                break;
            case 18:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkJamalShaker[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.jamal_shaker));
                    model.setUrlImage(R.drawable.jamal_shaker);
                    name_sora.add(model);
                }
                break;
            case 19:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkJamanElesemy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.jaman_elesemy));
                    model.setUrlImage(R.drawable.jaman_elesemy);
                    name_sora.add(model);
                }
                break;

            case 20:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesHatemFared[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.hatem_fared));
                    model.setUrlImage(R.drawable.hatem_fared);
                    name_sora.add(model);
                }
                break;
            case 21:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesKhalidElmohana[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.khaled_elmehana));
                    model.setUrlImage(R.drawable.khaled_elmehana);
                    name_sora.add(model);
                }
                break;
            case 22:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesKhalidAbdElkafy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.khaled_abdelkafy));
                    model.setUrlImage(R.drawable.khaled_abdelkafy);
                    name_sora.add(model);
                }
                break;
            case 23:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesKhaledElgalel[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.jleel));
                    model.setUrlImage(R.drawable.jleel);
                    name_sora.add(model);
                }
                break;
            case 24:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesKhaledElqahtamny[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.qht));
                    model.setUrlImage(R.drawable.qht);
                    name_sora.add(model);
                }
                break;
            case 25:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesKhalefaEltenegy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.tnjy));
                    model.setUrlImage(R.drawable.tnjy);
                    name_sora.add(model);
                }
                break;

            case 26:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesRamyEldeaas[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ramy_eldabs));
                    model.setUrlImage(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 27:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkZaky[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.zaki_daghistani));
                    model.setUrlImage(R.drawable.zaki_daghistani);
                    name_sora.add(model);
                }
                break;
            case 28:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesSahlYassen[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.sahl_yassen));
                    model.setUrlImage(R.drawable.sahl_yassen);
                    name_sora.add(model);
                }
                break;
            case 29:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesSadElghamdy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.s_gmd));
                    model.setUrlImage(R.drawable.s_gmd);
                    name_sora.add(model);
                }
                break;
            case 30:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkeSadElmeqren[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.saad));
                    model.setUrlImage(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 31:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkSoaadElsherem[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.soud_elsherem));
                    model.setUrlImage(R.drawable.sherem);
                    name_sora.add(model);
                }
                break;
            case 32:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesSyedRamadan[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.sayed));
                    model.setUrlImage(R.drawable.syed_rmdan);
                    name_sora.add(model);
                }
                break;
            case 33:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesShahrzadTaher[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.taher));
                    model.setUrlImage(R.drawable.taher);
                    name_sora.add(model);
                }
                break;
            case 34:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesHKM[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.hkm));
                    model.setUrlImage(R.drawable.hkm);
                    name_sora.add(model);
                }
                break;
            case 35:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesSalahHashim[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.salah_hashim_m));
                    model.setUrlImage(R.drawable.salah_hashim_m);
                    name_sora.add(model);
                }
                break;
            case 36:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkBder[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.slah_elbadry));
                    model.setUrlImage(R.drawable.bder);
                    name_sora.add(model);
                }
                break;
            case 37:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesBuKhtr[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.bu_khtr));
                    model.setUrlImage(R.drawable.bu_khtr);
                    name_sora.add(model);
                }
                break;
            case 38:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesTareq[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.tareq));
                    model.setUrlImage(R.drawable.tarek_abdelany);
                    name_sora.add(model);
                }
                break;
            case 39:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesRyan[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ryan));
                    model.setUrlImage(R.drawable.ryan);
                    name_sora.add(model);
                }
                break;
            case 40:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElklbanyHafs[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.adel_elkalbany));
                    model.setUrlImage(R.drawable.elklbany);
                    name_sora.add(model);
                }
                break;
            case 41:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesAbdelbasetHAfs[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelbaset));
                    model.setUrlImage(R.drawable.abdeelbaset);
                    name_sora.add(model);
                }
                break;
            case 42:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesAbdelbasetWarsh[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelbaset));
                    model.setUrlImage(R.drawable.abdeelbaset);
                    name_sora.add(model);
                }
                break;
            case 43:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAbdelbasetMgwad[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelbaset));
                    model.setUrlImage(R.drawable.abdeelbaset);
                    name_sora.add(model);
                }
                break;
            case 44:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAbdlaElgeheny[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdellah_elgeheny));
                    model.setUrlImage(R.drawable.elgeheny);
                    name_sora.add(model);
                }
                break;
            case 45:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElqasem[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelrhman_elqasem));
                    model.setUrlImage(R.drawable.elqasem);
                    name_sora.add(model);
                }
                break;
            case 46:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkSoufi[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelrshed_sofy));
                    model.setUrlImage(R.drawable.soufi_hfs);
                    name_sora.add(model);
                }
                break;
            case 47:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkSoufiKlf[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelrshed_sofy));
                    model.setUrlImage(R.drawable.soufi_hfs);
                    name_sora.add(model);
                }
                break;
            case 48:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkbdelazezZahrani[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelazez_elzahrany));
                    model.setUrlImage(R.drawable.abdelazez_elzahrany);
                    name_sora.add(model);
                }
                break;

            case 49:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesAbdelbariElthebety[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelbara_elthebety));
                    model.setUrlImage(R.drawable.abdelbara_elthebety);
                    name_sora.add(model);
                }
                break;
            case 50:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesAbdelrhmanEloasi[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelrhman_elosa));
                    model.setUrlImage(R.drawable.abdelrhman_elosa);
                    name_sora.add(model);
                }
                break;
            case 51:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesBary[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.bari));
                    model.setUrlImage(R.drawable.abdelbary);
                    name_sora.add(model);
                }
                break;
            case 52:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElsodes[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdelrhman_elsodes));
                    model.setUrlImage(R.drawable.elsodes);
                    name_sora.add(model);
                }
                break;
            case 53:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesAbdelrhmanMaged[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.a_majed));
                    model.setUrlImage(R.drawable.a_majed);
                    name_sora.add(model);
                }
                break;
            case 54:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesSoufiHfs[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.soufi_hfs));
                    model.setUrlImage(R.drawable.soufi_hfs);
                    name_sora.add(model);
                }
                break;
            case 55:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesAbdelazezAhmed[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.a_ahmed));
                    model.setUrlImage(R.drawable.a_ahmed);
                    name_sora.add(model);
                }
                break;
            case 56:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkeKkhalf[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.khalf));
                    model.setUrlImage(R.drawable.khalf);
                    name_sora.add(model);
                }
                break;
            case 57:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesAbdalah[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.abdullahk));
                    model.setUrlImage(R.drawable.abdullahk);
                    name_sora.add(model);
                }
                break;
            case 58:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesBsfr[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.bsfr));
                    model.setUrlImage(R.drawable.bsfr);
                    name_sora.add(model);
                }
                break;
            case 59:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesKyat[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.kyat));
                    model.setUrlImage(R.drawable.kyat);
                    name_sora.add(model);
                }
                break;
            case 60:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesGulan[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.gulan));
                    model.setUrlImage(R.drawable.gulan);
                    name_sora.add(model);
                }
                break;
            case 61:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesmohsin_harthi[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mohsin_harthi));
                    model.setUrlImage(R.drawable.mohsin_harthi);
                    name_sora.add(model);
                }
                break;
            case 62:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesObk[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.obk));
                    model.setUrlImage(R.drawable.obk);
                    name_sora.add(model);
                }
                break;
            case 63:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesKanakeri[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.kanakeri));
                    model.setUrlImage(R.drawable.kanakeri);
                    name_sora.add(model);
                }
                break;
            case 64:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesWdod[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.wdod));
                    model.setUrlImage(R.drawable.wdod);
                    name_sora.add(model);
                }
                break;
            case 65:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesAjbr[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.a_jbr));
                    model.setUrlImage(R.drawable.a_jbr);
                    name_sora.add(model);
                }
                break;

            case 66:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAliElhozefy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ali_elhozefy));
                    model.setUrlImage(R.drawable.ali_elhozefy);
                    name_sora.add(model);
                }
                break;
            case 67:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAliElhozefyHafs[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ali_elhozefy));
                    model.setUrlImage(R.drawable.ali_elhozefy);
                    name_sora.add(model);
                }
                break;
            case 68:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAlyElswesy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ali_swiesy));
                    model.setUrlImage(R.drawable.ali_swiesy);
                    name_sora.add(model);
                }
                break;
            case 69:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesHafz[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.hafz));
                    model.setUrlImage(R.drawable.hafz);
                    name_sora.add(model);
                }
                break;
            case 70:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkOmarElqzabry[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.omar_kzabri));
                    model.setUrlImage(R.drawable.omar_kzabri);
                    name_sora.add(model);
                }
                break;
            case 71:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesFrs_a[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.frs_a));
                    model.setUrlImage(R.drawable.frs_a);
                    name_sora.add(model);
                }
                break;
            case 72:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkMAHER_ELMEAQLY[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.maher_elmeaqly));
                    model.setUrlImage(R.drawable.almaqely);
                    name_sora.add(model);
                }
                break;
            case 73:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesShakshFs[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.shaksh));
                    model.setUrlImage(R.drawable.shaksh);
                    name_sora.add(model);
                }
                break;
            case 74:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesAyyub[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ayyub));
                    model.setUrlImage(R.drawable.ayyub);
                    name_sora.add(model);
                }
                break;

            case 75:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesM_krm[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.m_krm));
                    model.setUrlImage(R.drawable.m_krm);
                    name_sora.add(model);
                }
                break;
            case 76:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesMkreem_warsh[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.m_krm));
                    model.setUrlImage(R.drawable.m_krm);
                    name_sora.add(model);
                }
                break;
            case 77:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesEarawi[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.earawi));
                    model.setUrlImage(R.drawable.earawi);
                    name_sora.add(model);
                }
                break;
            case 78:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesShah[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.shah));
                    model.setUrlImage(R.drawable.shah);
                    name_sora.add(model);
                }
                break;
            case 79:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesRashad[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.rashad));
                    model.setUrlImage(R.drawable.rashad);
                    name_sora.add(model);
                }
                break;
            case 80:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesLhdan[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.lhdan));
                    model.setUrlImage(R.drawable.lhdan);
                    name_sora.add(model);
                }
                break;
            case 81:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesKhan[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.khan));
                    model.setUrlImage(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;

            case 82:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElmenshawyMgwad[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mohamed_elmenshawyM));
                    model.setUrlImage(R.drawable.elmenshawy);
                    name_sora.add(model);
                }
                break;
            case 83:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElmenshawyHafs[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mohamed_elmenshawyH));
                    model.setUrlImage(R.drawable.elmenshawy);
                    name_sora.add(model);
                }
                break;

            case 84:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkALTABLAWY[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mohamed_eltblawy));
                    model.setUrlImage(R.drawable.eltblawy);
                    name_sora.add(model);
                }
                break;
            case 85:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkAbdelhakemAlabdlah[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mohamed_abd_elhakem));
                    model.setUrlImage(R.drawable.mohamed_abd_elhakem);
                    name_sora.add(model);
                }
                break;
            case 86:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesAbdullahDoris[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mohamed_abd_elhakem));
                    model.setUrlImage(R.drawable.mohamed_abd_elhakem);
                    name_sora.add(model);
                }
                break;
            case 87:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkMohamedGbrer[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mohamed_gebrer));
                    model.setUrlImage(R.drawable.mohamedgbrer);
                    name_sora.add(model);
                }
                break;
            case 88:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkMsharyElafasy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mshary_elafasy));
                    model.setUrlImage(R.drawable.msharyelafasy);
                    name_sora.add(model);
                }
                break;

            case 89:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesMrifai[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mrifai));
                    model.setUrlImage(R.drawable.mrifai);
                    name_sora.add(model);
                }
                break;
            case 90:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesSheimy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.sheimy));
                    model.setUrlImage(R.drawable.sheimy);
                    name_sora.add(model);
                }
                break;
            case 91:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElhosaryMgwad[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mahmoud_elhosaryM));
                    model.setUrlImage(R.drawable.elhosary);
                    name_sora.add(model);
                }
                break;
            case 92:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesElhosaryWarsh[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mahmoud_elhosaryM));
                    model.setUrlImage(R.drawable.elhosary);
                    name_sora.add(model);
                }
                break;
            case 93:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElhosaryHAfs[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mahmoud_elhosaryH));
                    model.setUrlImage(R.drawable.elhosary);
                    name_sora.add(model);
                }
                break;
            case 94:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElbana[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mahmoud_elbana));
                    model.setUrlImage(R.drawable.elbana);
                    name_sora.add(model);
                }
                break;
            case 95:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesBna_mjwds[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mahmoud_elbana));
                    model.setUrlImage(R.drawable.elbana);
                    name_sora.add(model);
                }
                break;
            case 96:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesMustafa[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.mustafa));
                    model.setUrlImage(R.drawable.mustafa);
                    name_sora.add(model);
                }
                break;
            case 97:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesLahoni[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.lahoni));
                    model.setUrlImage(R.drawable.lahoni);
                    name_sora.add(model);
                }
                break;
            case 98:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesRa3ad[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.ra3ad));
                    model.setUrlImage(R.drawable.ra3ad);
                    name_sora.add(model);
                }
                break;
            case 99:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesHarthi[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.harthi));
                    model.setUrlImage(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 100:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesMoftahElsalatnyEldawary[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.moftah_elslty));
                    model.setUrlImage(R.drawable.moftah_elslty);
                    name_sora.add(model);
                }
                break;
            case 101:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesMoftahElsalatnyEnbZakor[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.moftah_elslty));
                    model.setUrlImage(R.drawable.moftah_elslty);
                    name_sora.add(model);
                }
                break;
            case 102:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesMoftahHafs[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.moftah_elslty));
                    model.setUrlImage(R.drawable.moftah_elslty);
                    name_sora.add(model);
                }
                break;
            case 103:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinklinkesMoftahElsltny[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.moftah_elslty));
                    model.setUrlImage(R.drawable.moftah_elslty);
                    name_sora.add(model);
                }
                break;
            case 104:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesBilal[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.bilal));
                    model.setUrlImage(R.drawable.bilal);
                    name_sora.add(model);
                }
                break;
            case 105:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkNaserElqatamy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.naser_elqatamy));
                    model.setUrlImage(R.drawable.naserelqatamy);
                    name_sora.add(model);
                }
                break;

            case 106:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesNasser_almajed[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.nasser_almajed));
                    model.setUrlImage(R.drawable.nasser_almajed);
                    name_sora.add(model);
                }
                break;
            case 107:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkNabelElrefay[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.nabel_elrefaay));
                    model.setUrlImage(R.drawable.nabel_elrefaay);
                    name_sora.add(model);
                }
                break;
            case 108:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesNamh[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.namh));
                    model.setUrlImage(R.drawable.namh);
                    name_sora.add(model);
                }
                break;
            case 109:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArraylinkesWadeaElyamany[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.wadea_elyamany));
                    model.setUrlImage(R.drawable.wadea_elyamany);
                    name_sora.add(model);
                }
                break;
            case 110:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkYaserEldosary[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.yaser_eldosary));
                    model.setUrlImage(R.drawable.eldosary);
                    name_sora.add(model);
                }
                break;
            case 111:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesHani[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.hani));
                    model.setUrlImage(R.drawable.hani);
                    name_sora.add(model);
                }
                break;
            case 112:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkWaledElnaahy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.waled_elnaehy));
                    model.setUrlImage(R.mipmap.logo);
                    name_sora.add(model);
                }
                break;
            case 113:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkYaserElmazroay[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.yaser_elmazroay));
                    model.setUrlImage(R.drawable.yaser_elmazroay);
                    name_sora.add(model);
                }
                break;
            case 114:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkElqarashy[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.yaser_elqarashy));
                    model.setUrlImage(R.drawable.elqarashy);
                    name_sora.add(model);
                }
                break;
            case 115:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesSalamah[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.salamah));
                    model.setUrlImage(R.drawable.salamah);
                    name_sora.add(model);
                }
                break;
            case 116:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkYasenElgazaery[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.yasen_elgazaery));
                    model.setUrlImage(R.drawable.yasen_elgazaery);
                    name_sora.add(model);
                }
                break;
            case 117:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesYahya[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.yahya));
                    model.setUrlImage(R.drawable.yahya);
                    name_sora.add(model);
                }
                break;
            case 118:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(linkesNoah[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.noah));
                    model.setUrlImage(R.drawable.noah);
                    name_sora.add(model);
                }
                break;
            case 119:
                for (int i = 0; i < soar.length; ++i) {
                    ImageModel model = new ImageModel();
                    model.setNameSora(soar[i]);
                    model.setSoraLink(ArrayLinkYosefElshereay[i]);
                    model.setPosition(i);
                    model.setNameShekh(application.getString(R.string.yosem_elshereay));
                    model.setUrlImage(R.drawable.yosem_elshereay);
                    name_sora.add(model);
                }
                break;
            default:
                Toast.makeText(application.getApplicationContext(), "لم يتم ادخال قائمة بعد", Toast.LENGTH_SHORT).show();
        }
    }
}