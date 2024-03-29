package com.mohamedtaha.imagine.mvp.interactor;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.mvp.presenter.ElarbaoonElnwawyPresenter;
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel;
import com.mohamedtaha.imagine.mvp.view.ElarbaoonElnwawyView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
//@ScopeFragment
public class ElarbaoonElnwawyInteractor extends ViewModel implements ElarbaoonElnwawyPresenter {
    private ElarbaoonElnwawyView elarbaoonElnwawyView;
    private Context context;
    private CompositeDisposable disposable;
    private List<ElarbaoonElnawawyModel> elnawawyModelList;
    private String[] number_elhadeth;
    private String[] name_elhadeth;
    private String[] text_elhadeth;
    private String[] description_elhadeth;
    private String[] transelate_elhadeth;

    public ElarbaoonElnwawyInteractor(ElarbaoonElnwawyView elarbaoonElnwawyView, Context context) {
        this.elarbaoonElnwawyView = elarbaoonElnwawyView;
        this.context = context;

    }

//    @Override
//    public void onBind(ElarbaoonElnwawyView elarbaoonElnwawyView, Context context) {
//        this.elarbaoonElnwawyView = elarbaoonElnwawyView;
//        this.context = context;
//    }

    @Override
    public void getAllData() {
        disposable = new CompositeDisposable();
        elarbaoonElnwawyView.showProgress();
        Observable<List<ElarbaoonElnawawyModel>> observableForGetElahadeth = Observable.fromCallable(new Callable<List<ElarbaoonElnawawyModel>>() {
            @Override
            public List<ElarbaoonElnawawyModel> call() throws Exception {
                try {
                    elnawawyModelList = new ArrayList<>();
                    number_elhadeth = context.getResources().getStringArray(R.array.elarbaon_elnawawaya_number_elhadeth);
                    name_elhadeth = context.getResources().getStringArray(R.array.elarbaon_elnawawaya_name_elhadeth);
                    text_elhadeth = context.getResources().getStringArray(R.array.elarbaon_elnawawaya_text_elhadeth);
                    description_elhadeth = context.getResources().getStringArray(R.array.elarbaon_elnawawaya_decription_elhadeth);
                    transelate_elhadeth = context.getResources().getStringArray(R.array.elarbaon_elnawawaya_translate_elhadeth);
                    for (int i = 0; i < number_elhadeth.length; i++) {
                        ElarbaoonElnawawyModel elnawawyModel = new ElarbaoonElnawawyModel();
                        elnawawyModel.setNumberElhadeth(number_elhadeth[i]);
                        elnawawyModel.setNameElhadeth(name_elhadeth[i]);
                        elnawawyModel.setTextElhadeth(text_elhadeth[i]);
                        elnawawyModel.setDescriptionElhadeth(description_elhadeth[i]);
                        elnawawyModel.setTranslateElhadeth(transelate_elhadeth[i]);
                        elnawawyModel.setPosition(i);
                        elnawawyModelList.add(elnawawyModel);
                    }
                } catch (Exception e) {

                }
                return elnawawyModelList;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(observableForGetElahadeth.subscribeWith(new DisposableObserver<List<ElarbaoonElnawawyModel>>() {

            @Override
            public void onNext(@NonNull List<ElarbaoonElnawawyModel> elarbaoonElnawawyModels) {
                if (elarbaoonElnwawyView != null) {
                    elarbaoonElnwawyView.showAllElahadeth(elarbaoonElnawawyModels);
                    elarbaoonElnwawyView.showAnimation();

                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (elarbaoonElnwawyView != null)
                    elarbaoonElnwawyView.hideProgress();
            }

            @Override
            public void onComplete() {
                if (elarbaoonElnwawyView != null)
                    elarbaoonElnwawyView.hideProgress();
            }
        }));

    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
            disposable.dispose();
        }
        elarbaoonElnwawyView = null;
    }


//    @Override
//    public void setOnSearchView(MaterialSearchView materialSearchView) {
//        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                elarbaoonElnwawyView.showAfterSearch();
//                elarbaoonElnwawyView.thereData();
//            }
//        });
//
//    }
//
//    @Override
//    public void setOnQueryTextForElhadeth(MaterialSearchView materialSearchView, List<ElarbaoonElnawawyModel> name_elhadeth) {
//        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (newText != null && !newText.isEmpty()) {
//                    List<ElarbaoonElnawawyModel> stringList = new ArrayList<>();
//                    for (ElarbaoonElnawawyModel item : name_elhadeth) {
//                        if (item.getName_elhadeth().contains(newText))
//                            stringList.add(item);
//                    }
//                    if (!stringList.isEmpty()) {
//                        elarbaoonElnwawyView.showDataAfterQueryText(stringList);
//                        elarbaoonElnwawyView.thereData();
//                    } else {
//                        elarbaoonElnwawyView.isEmpty();
//                    }
//
//                } else {
//                    elarbaoonElnwawyView.thereData();
//                    elarbaoonElnwawyView.showAllElahadeth(name_elhadeth);
//                }
//                return false;
//            }
//        });
//
//    }

}
