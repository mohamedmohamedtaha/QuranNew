package com.mohamedtaha.imagine.mvp.interactor;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.helper.images;
import com.mohamedtaha.imagine.mvp.model.ModelSora;
import com.mohamedtaha.imagine.mvp.presenter.SwarFragmentPresenter;
import com.mohamedtaha.imagine.mvp.view.SwarFragmentView;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SwarFragmentInteractor extends ViewModel implements SwarFragmentPresenter {
    List<ModelSora> name_Sroa;
    CompositeDisposable disposable;
    private SwarFragmentView fragmentView;
    private Context context;

    private String[] a = null;
    private String[] nzol_elsora = null;

    private ExecutorService executorService = null;
    private int numberImage = 0;
    int threadCount =Runtime.getRuntime().availableProcessors();
    ExecutorService threaPoolExecutor = Executors.newFixedThreadPool(threadCount);
    Scheduler scheduler = Schedulers.from(threaPoolExecutor);

    public SwarFragmentInteractor(SwarFragmentView fragmentView, Context context) {
        this.fragmentView = fragmentView;
        this.context = context;
    }

    @Override
    public void getPosition(int position, Bundle bundle) {
        images.getPositionForNameSwars(position, bundle);
    }

    @Override
    public void getAllNameSour() {
        Observable<List<ModelSora>> observable = Observable.fromCallable(new Callable<List<ModelSora>>() {
            @Override
            public List<ModelSora> call() throws Exception {
                a = context.getResources().getStringArray(R.array.name_allSwar);
                nzol_elsora = context.getResources().getStringArray(R.array.nzolElswar);
//                for (int i = 0; i < a.length; i++) {
//                    ModelSora name_Sroa_local = new ModelSora();
//                    name_Sroa_local.setName_sora(a[i]);
//                    name_Sroa_local.setPosition(i);
//                    name_Sroa_local.setNzol_elsora(nzol_elsora[i]);
//                    name_Sroa.add(name_Sroa_local);
//                    Log.d("ODD", "call subscribeOn: " + name_Sroa.get(i) + " : : " + Thread.currentThread().getName() + " threadCount is : " + threadCount);
//                }
                return name_Sroa;
            }
        }).subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(observable.subscribeWith(new DisposableObserver<List<ModelSora>>() {
            @Override
            public void onNext(@NonNull List<ModelSora> modelSoras) {
                if (fragmentView != null) {
                    fragmentView.showAllINameSour(name_Sroa);
                    fragmentView.thereData();
                    fragmentView.showAnimation();
                    Log.i("SetNameSora", "onNext");
                    Log.d("ODD", "observeOn call: " +  name_Sroa + " : : "+Thread.currentThread().getName());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (fragmentView != null) {
                    fragmentView.hideProgress();
                }
            }

            @Override
            public void onComplete() {
                if (fragmentView != null) {
                    fragmentView.hideProgress();
                }
                Log.i("addImages", "onCompleted");
            }
        }));
    }

//    @Override
//    public void getAllImages() {
//        fragmentView.showProgress();
//        Observable<List<Integer>> modelAzkarObservable = Observable.fromCallable(new Callable<List<Integer>>() {
//            @Override
//            public List<Integer> call() throws Exception {
//                return addImagesList();
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<List<Integer>>() {
//            @Override
//            public void onNext(@NonNull List<Integer> integers) {
//                if (fragmentView != null) {
//                    fragmentView.showAllImages(integers);
//                    Log.i("addImages", "onNext");
//                }
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                if (fragmentView != null) {
//                    fragmentView.hideProgress();
//                }
//            }
//
//            @Override
//            public void onComplete() {
//                if (fragmentView != null) {
//                    fragmentView.hideProgress();
//                }
//                Log.i("addImages", "onCompleted");
//            }
//        }));
//    }

    @Override
    public void getAllImages() {
        fragmentView.showProgress();
        Observable<List<Integer>> modelAzkarObservable = Observable.fromCallable(new Callable<List<Integer>>() {
            @Override
            public List<Integer> call() throws Exception {
                return images.addImagesList();
            }
        }).subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<List<Integer>>() {
            @Override
            public void onNext(@NonNull List<Integer> integers) {
                if (fragmentView != null) {
                    fragmentView.showAllImages(integers);
                    Log.i("addImages", "onNext");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (fragmentView != null) {
                    fragmentView.hideProgress();
                }
            }

            @Override
            public void onComplete() {
                if (fragmentView != null) {
                    fragmentView.hideProgress();
                }
                Log.i("addImages", "onCompleted");
            }
        }));
    }

    @Override
    public void onDestroy() {
        fragmentView = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
            disposable.dispose();
        }
    }
//
//    @Override
//    public void setOnSearchView(MaterialSearchView materialSearchView) {
//        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//                fragmentView.showAfterSearch();
//                fragmentView.thereData();
//                // fragmentView.hideProgress();
//            }
//
//        });
//
//    }
//
//    @Override
//    public void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar) {
//        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (newText != null && !newText.isEmpty()) {
//                    List<ModelSora> stringList = new ArrayList<>();
//                    for (ModelSora item : name_swar) {
//                        if (item.getName_sora().contains(newText))
//                            stringList.add(item);
//                    }
//                    if (!stringList.isEmpty()) {
//                        fragmentView.showAfterQueryText(stringList);
//                        fragmentView.thereData();
//                    } else {
//                        fragmentView.isEmpty();
//                    }
//
//                } else {
//                    fragmentView.thereData();
//                    fragmentView.showAllINameSour(name_swar);
//                }
//                return false;
//            }
//        });
//    }
}