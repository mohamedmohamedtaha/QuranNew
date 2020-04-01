package com.MohamedTaha.Imagine.New.mvp.interactor;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.New.mvp.presenter.PartsFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.PartsFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.helper.Images.addImagesList;
import static com.MohamedTaha.Imagine.New.helper.Images.getPositionForNameParts;

public class PartsFragmentsInteractor implements PartsFragmentPresenter {
    private PartsFragmentView partsFragmentView;
    private String[] name_parts;
    private Context context;
    private List<ModelSora> name_part_list = new ArrayList<>();
    //private Subscription subscription_name_sora;
    private CompositeDisposable disposable = new CompositeDisposable();


    public PartsFragmentsInteractor(PartsFragmentView partsFragmentView, FragmentActivity fragmentActivity) {
        this.context = fragmentActivity;
        this.partsFragmentView = partsFragmentView;

    }

    @Override
    public void getAllImages() {
        if (partsFragmentView != null) {
            partsFragmentView.showProgress();
            Observable<List<Integer>> modelAzkarObservable = Observable.fromCallable(new Callable<List<Integer>>() {
                @Override
                public List<Integer> call() throws Exception {
                    return addImagesList();
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<List<Integer>>(){
                @Override
                public void onNext(@NonNull List<Integer> integers) {
                    if (partsFragmentView != null) {
                        partsFragmentView.showAllImages(integers);
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    if (partsFragmentView != null) {
                        partsFragmentView.hideProgress();
                    }
                }

                @Override
                public void onComplete() {
                    if (partsFragmentView != null) {
                        partsFragmentView.hideProgress();
                    }
                }
            }));


        }
    }

    @Override
    public void getPosition(int position, Bundle bundle) {
        getPositionForNameParts(position, bundle);
    }

    @Override
    public void getAllPartSoura() {
        Observable<List<ModelSora>> modelAzkarObservable = Observable.fromCallable(new Callable<List<ModelSora>>() {
            @Override
            public List<ModelSora> call() throws Exception {
                try {
                    if (partsFragmentView != null) {
                        name_parts = context.getResources().getStringArray(R.array.allParts);
                        for (int i = 0; i < name_parts.length; i++) {
                            ModelSora name_Sroa_local = new ModelSora();
                            name_Sroa_local.setName_part(name_parts[i]);
                            name_Sroa_local.setPosition(i);
                            name_part_list.add(name_Sroa_local);
                        }
                    }
                } catch (Exception e) {
                }
                return name_part_list;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<List<ModelSora>>(){
            @Override
            public void onNext(@NonNull List<ModelSora> modelSoras) {
                if (partsFragmentView != null) {
                    partsFragmentView.showAllINamePart(modelSoras);
                    partsFragmentView.showAnimation();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (partsFragmentView != null) {
                    partsFragmentView.hideProgress();
                }
            }

            @Override
            public void onComplete() {
                if (partsFragmentView != null) {
                    partsFragmentView.hideProgress();
                }
            }
        }));
    }

    @Override
    public void onDestroy() {
        this.partsFragmentView = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
            disposable.dispose();

        }
    }

    @Override
    public void setOnSearchView(MaterialSearchView materialSearchView) {
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                partsFragmentView.showAfterSearch();
                partsFragmentView.thereData();
            }
        });
    }

    @Override
    public void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_part) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelSora> stringList = new ArrayList<>();
                    for (ModelSora item : name_part) {
                        if (item.getName_part().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        partsFragmentView.showAfterQueryText(stringList);
                        partsFragmentView.thereData();
                    } else {
                        partsFragmentView.isEmpty();
                    }

                } else {
                    partsFragmentView.thereData();
                    partsFragmentView.showAllINamePart(name_part);
                }
                return false;
            }
        });
    }
}