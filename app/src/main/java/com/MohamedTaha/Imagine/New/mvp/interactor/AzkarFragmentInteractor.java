package com.MohamedTaha.Imagine.New.mvp.interactor;

import android.content.Context;
import android.util.Log;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;
import com.MohamedTaha.Imagine.New.mvp.presenter.AzkarFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.AzkarFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class AzkarFragmentInteractor implements AzkarFragmentPresenter {
    private static final String TAG = "TAG";
    private AzkarFragmentView azkarFragmentView;
    private List<ModelAzkar> modelAzkar;
    private Context context;
    private CompositeDisposable disposable;

    String[] array_azkar;
    String[] array_describe_azkar;

    public AzkarFragmentInteractor(AzkarFragmentView azkarFragmentView, Context context) {
        this.azkarFragmentView = azkarFragmentView;
        this.context = context;
    }

    @Override
    public void getAllData() {
        disposable = new CompositeDisposable();
        azkarFragmentView.showProgress();
        Observable<List<ModelAzkar>> modelAzkarObservable = Observable.fromCallable(new Callable<List<ModelAzkar>>() {
            @Override
            public List<ModelAzkar> call() throws Exception {
                try {
                    modelAzkar = new ArrayList<>();
                    array_azkar = context.getResources().getStringArray(R.array.azkar);
                    array_describe_azkar = context.getResources().getStringArray(R.array.describe_azkar);
                    for (int i = 0; i < array_azkar.length; i++) {
                        ModelAzkar modelAzkarLocal = new ModelAzkar();
                        modelAzkarLocal.setName_azkar(array_azkar[i]);
                        modelAzkarLocal.setDescribe_azkar(array_describe_azkar[i]);
                        modelAzkarLocal.setPosition(i);
                        modelAzkar.add(modelAzkarLocal);
                    }
                } catch (Exception e) {
                }
                return modelAzkar;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add( modelAzkarObservable.subscribeWith(new DisposableObserver<List<ModelAzkar>>() {
            @Override
            public void onNext(@NonNull List<ModelAzkar> modelAzkars) {
                if (azkarFragmentView != null) {
                    azkarFragmentView.showAllINameAzkar(modelAzkars);
                    azkarFragmentView.showAnimation();
                    Log.d(TAG, "onNext  " );

                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (azkarFragmentView != null) {
                    azkarFragmentView.hideProgress();
                    Log.d(TAG, "onError  " );

                }
            }

            @Override
            public void onComplete() {
                if (azkarFragmentView != null) {
                    azkarFragmentView.hideProgress();
                    Log.d(TAG, "onComplete  " );

                }
            }
        }));
    }

    @Override
    public void onDestroy() {
        this.azkarFragmentView = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
            disposable.dispose();
            Log.d(TAG, "disposable cleared  " );
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
                azkarFragmentView.showAfterSearch();
                azkarFragmentView.thereData();
            }
        });
    }

    @Override
    public void setOnQueryTextForAzkar(MaterialSearchView materialSearchView, List<ModelAzkar> name_azkar) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelAzkar> stringList = new ArrayList<>();
                    for (ModelAzkar item : name_azkar) {
                        if (item.getName_azkar().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        azkarFragmentView.showAfterQueryText(stringList);
                        azkarFragmentView.thereData();
                    } else {
                        azkarFragmentView.isEmpty();
                    }

                } else {
                    azkarFragmentView.thereData();
                    azkarFragmentView.showAllINameAzkar(name_azkar);
                }
                return false;
            }
        });
    }
}
