package com.MohamedTaha.Imagine.New.mvp.interactor;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;
import com.MohamedTaha.Imagine.New.mvp.presenter.AzkarFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.AzkarFragmentView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
public class AzkarFragmentInteractor extends ViewModel implements AzkarFragmentPresenter {
    @Inject
    CompositeDisposable disposable;
    private static final String SAVE_INSTANCE_AZKAR = "save_instance_azkar";
    public boolean isNewlyCreated = true;
    private static final String TAG = "TAG";
    private AzkarFragmentView azkarFragmentView;
    private ArrayList<ModelAzkar> modelAzkar;
    private Context context;
    String[] array_azkar;
    String[] array_describe_azkar;

    @Inject
    public AzkarFragmentInteractor(AzkarFragmentView azkarFragmentView, Context context) {
        this.azkarFragmentView = azkarFragmentView;
        this.context = context;
    }

    @Override
    public void getAllData() {
        azkarFragmentView.showProgress();
        Observable<ArrayList<ModelAzkar>> modelAzkarObservable = Observable.fromCallable(new Callable<ArrayList<ModelAzkar>>() {
            @Override
            public ArrayList<ModelAzkar> call() throws Exception {
                try {
                    if (modelAzkar == null) {
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
                    }
                } catch (Exception e) {
                }
                return modelAzkar;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<ArrayList<ModelAzkar>>() {
            @Override
            public void onNext(@NonNull ArrayList<ModelAzkar> modelAzkars) {
                if (azkarFragmentView != null) {
                    azkarFragmentView.showAllINameAzkar(modelAzkars);
                    azkarFragmentView.showAnimation();
                    Log.d(TAG, "onNext  ");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (azkarFragmentView != null) {
                    azkarFragmentView.hideProgress();
                    Log.d(TAG, "onError  ");
                }
            }

            @Override
            public void onComplete() {
                if (azkarFragmentView != null) {
                    azkarFragmentView.hideProgress();
                    Log.d(TAG, "onComplete  ");
                }
            }
        }));


//        Observable<ArrayList<ModelAzkar>> modelAzkarObservable = (Observable<ArrayList<ModelAzkar>>) Observable.fromCallable(new Callable<ArrayList<ModelAzkar>>() {
//            @Override
//            public ArrayList<ModelAzkar> call() throws Exception {
//                try {
//                    if (modelAzkar  == null){
//                        modelAzkar = new ArrayList<>();
//                        array_azkar = context.getResources().getStringArray(R.array.azkar);
//                        array_describe_azkar = context.getResources().getStringArray(R.array.describe_azkar);
//                        for (int i = 0; i < array_azkar.length; i++) {
//                            ModelAzkar modelAzkarLocal = new ModelAzkar();
//                            modelAzkarLocal.setName_azkar(array_azkar[i]);
//                            modelAzkarLocal.setDescribe_azkar(array_describe_azkar[i]);
//                            modelAzkarLocal.setPosition(i);
//                            modelAzkar.add(modelAzkarLocal);
//                        }
//                    }} catch (Exception e) {
//                }
//
//
//                return modelAzkar;
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(result ->{
//                    if (azkarFragmentView != null) {
//                        azkarFragmentView.showAllINameAzkar(result);
//                        azkarFragmentView.showAnimation();
//                        Log.d(TAG, "onNext  " );
//
//                    }
//
//                });
    }


    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.clear();
            disposable.dispose();
            Log.d("TAGO", "disposable cleared  ");
        }
        this.azkarFragmentView = null;
    }

    @Override
    public void saveState(Bundle outState) {
        outState.putParcelableArrayList(SAVE_INSTANCE_AZKAR, modelAzkar);
    }

    @Override
    public void restoreState(Bundle outState) {
        modelAzkar = outState.getParcelableArrayList(SAVE_INSTANCE_AZKAR);
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
    public void setOnQueryTextForAzkar(MaterialSearchView materialSearchView, ArrayList<ModelAzkar> name_azkar) {
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                modelAzkar = new ArrayList<>();
                if (newText != null && !newText.isEmpty()) {
                    ArrayList<ModelAzkar> stringList = new ArrayList<>();
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
                    modelAzkar = name_azkar;
                    azkarFragmentView.showAllINameAzkar(modelAzkar);
                }
                return false;
            }
        });
    }
}