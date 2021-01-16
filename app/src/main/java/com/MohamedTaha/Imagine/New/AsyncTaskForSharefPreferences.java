package com.MohamedTaha.Imagine.New;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AsyncTaskForSharefPreferences {
    private Context context;
    private CompositeDisposable disposable;
    String repearAsync = null;
    private SharedPreferences sharedPreferences;
    private String repear;

    public AsyncTaskForSharefPreferences(Context context, SharedPreferences sharedPreferences) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    public  String getSharedPreferencesThreadSeperate(){
        disposable = new CompositeDisposable();
        Observable<String> modelAzkarObservable = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                    //getString Retrieve a String value from the Preference
                    repearAsync = sharedPreferences.getString(context.getString(R.string.settings_method_key),
                            context.getString(R.string.settings_method_default));
                    Log.d("TAG", "doInBackground - Thread call" + Thread.currentThread().getId() + "Name : "
                            + Thread.currentThread().getName() );
                } catch (Exception e) {
                }
                return repearAsync;
            }
        }).subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread());
        disposable.add(modelAzkarObservable.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull String repearAsync) {
                if (repearAsync != null) {
                    repear = repearAsync;
                    Log.d("TAG", "onNext Prefrences  ");
                    Log.d("TAG", "doInBackground - Thread " + Thread.currentThread().getId() + "Name : "
                            + Thread.currentThread().getName() );
                }
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                if (repearAsync != null) {
                    Log.d("TAG", "onError Prefrences ");

                }
            }

            @Override
            public void onComplete() {
                if (repearAsync != null) {
                    Log.d("TAG", "onComplete Prefrences ");
                    Log.d("TAG", "doInBackground - Thread " + Thread.currentThread().getId() + "Name : "
                            + Thread.currentThread().getName() );
                }
            }
        }));
        return repear;
    }

}
