package com.MohamedTaha.Imagine.New.mvp.interactor;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.MohamedTaha.Imagine.New.mvp.model.ImageModel;
import com.MohamedTaha.Imagine.New.mvp.presenter.ListSoundReaderPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.ListSoundReaderView;
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

import static com.MohamedTaha.Imagine.New.helper.Images.getData;

public class ListSoundReaderInteractor extends ViewModel implements ListSoundReaderPresenter {
    private ListSoundReaderView listSoundReaderView;
    private Context context;

    private CompositeDisposable disposable;

    public ListSoundReaderInteractor() {
        Log.d("TAG", "ListSoundReaderInteractor");
    }

    @Override
    public void onDestroy() {
        listSoundReaderView = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
            disposable.dispose();
        }
    }

    @Override
    public void getAllData() {
        disposable = new CompositeDisposable();
        listSoundReaderView.showProgress();

        Observable<List<ImageModel>> modelAzkarObservable = Observable.fromCallable(new Callable<List<ImageModel>>() {
            @Override
            public List<ImageModel> call() throws Exception {
                return getData(context);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<List<ImageModel>>() {
            @Override
            public void onNext(@NonNull List<ImageModel> modelAzkars) {
                if (listSoundReaderView != null) {
                    listSoundReaderView.showAllData(modelAzkars);
                    listSoundReaderView.showAnimation();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (listSoundReaderView != null) {
                    listSoundReaderView.hideProgress();
                }
            }

            @Override
            public void onComplete() {
                if (listSoundReaderView != null) {
                    listSoundReaderView.hideProgress();
                }
            }
        }));
    }

    @Override
    public void onBind(ListSoundReaderView listSoundReaderView, Context context) {
        this.listSoundReaderView = listSoundReaderView;
        this.context = context;

    }

    @Override
    public void setOnSearchViewListener(MaterialSearchView searchView) {
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                listSoundReaderView.showAfterSearch();
                listSoundReaderView.thereData();
            }
        });
    }

    @Override
    public void setOnQueryTextListener(MaterialSearchView searchView, List<ImageModel> imageModel) {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ImageModel> lstFound = new ArrayList<>();
                    for (ImageModel item : imageModel) {
                        if (item.getName_shekh().contains(newText))
                            lstFound.add(item);
                    }
                    if (!lstFound.isEmpty()) {
                        listSoundReaderView.showAfterQueryText(lstFound);
                        listSoundReaderView.thereData();

                    } else {
                        listSoundReaderView.noData();

                    }


                } else {
                    //If search is Null return default
                    listSoundReaderView.thereData();
                    listSoundReaderView.showAllData(imageModel);
                }
                return false;
            }
        });
    }
}