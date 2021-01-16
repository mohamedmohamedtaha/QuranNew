package com.MohamedTaha.Imagine.New.mvp.interactor;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.New.mvp.presenter.GridViewFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.GridViewFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.helper.Images.addImagesList;
import static com.MohamedTaha.Imagine.New.helper.Images.getPositionForNameSwars;

public class GridViewFragmentInteractor extends ViewModel implements GridViewFragmentPresenter {
    private GridViewFragmentView fragmentView;
    private FragmentActivity activity;
    private List<ModelSora> name_Sroa;
    private String[] a = null;
    private String[] nzol_elsora = null;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ExecutorService executorService = null;
    private int numberImage =0;

    public GridViewFragmentInteractor() {
    }

    @Override
    public void onBind(GridViewFragmentView fragmentView, FragmentActivity context) {
        this.fragmentView = fragmentView;
        activity = context;

    }

    @Override
    public void getPosition(int position, Bundle bundle) {
        getPositionForNameSwars(position, bundle);
    }

    @Override
    public void getAllNameSour() {
        Observable<List<ModelSora>> observable = Observable.fromCallable(new Callable<List<ModelSora>>() {
            @Override
            public List<ModelSora> call() throws Exception {
                name_Sroa = new ArrayList<>();
                a = activity.getResources().getStringArray(R.array.name_allSwar);
                nzol_elsora = activity.getResources().getStringArray(R.array.nzolElswar);
                for (int i = 0; i < a.length; i++) {
                    ModelSora name_Sroa_local = new ModelSora();
                    name_Sroa_local.setName_sora(a[i]);
                    name_Sroa_local.setPosition(i);
                    name_Sroa_local.setNzol_elsora(nzol_elsora[i]);
                    name_Sroa.add(name_Sroa_local);
                }
                return name_Sroa;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(observable.subscribeWith(new DisposableObserver<List<ModelSora>>() {
            @Override
            public void onNext(@NonNull List<ModelSora> modelSoras) {
                if (fragmentView != null) {
                    fragmentView.showAllINameSour(name_Sroa);
                    fragmentView.thereData();
                    fragmentView.showAnimation();
                    Log.i("SetNameSora", "onNext");

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
                return addImagesList();
            }
        }).subscribeOn(Schedulers.io())
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

    @Override
    public void setOnSearchView(MaterialSearchView materialSearchView) {
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                fragmentView.showAfterSearch();
                fragmentView.thereData();
                // fragmentView.hideProgress();
            }

        });

    }

    @Override
    public void setOnQueryText(MaterialSearchView materialSearchView, List<ModelSora> name_swar) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<ModelSora> stringList = new ArrayList<>();
                    for (ModelSora item : name_swar) {
                        if (item.getName_sora().contains(newText))
                            stringList.add(item);
                    }
                    if (!stringList.isEmpty()) {
                        fragmentView.showAfterQueryText(stringList);
                        fragmentView.thereData();
                    } else {
                        fragmentView.isEmpty();
                    }

                } else {
                    fragmentView.thereData();
                    fragmentView.showAllINameSour(name_swar);
                }
                return false;
            }
        });
    }
}