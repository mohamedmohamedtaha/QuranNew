package com.MohamedTaha.Imagine.New.ui.fragments.elnawawy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.databinding.FragmentTextElhadethBinding;
import com.google.gson.Gson;

import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.POSITION;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextElhadethFragment extends Fragment {
    private FragmentTextElhadethBinding fragmentTextElhadethBinding;
    private Bundle bundle_for_save_position_elhadeth;
    private int position_elhadeth;
    private CompositeDisposable disposable;
    private String return_text_elhadeth = null;
    private String text_elhadeth_depended_on_position = null;

    public TextElhadethFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTextElhadethBinding = FragmentTextElhadethBinding.inflate(inflater,container,false);
        View view = fragmentTextElhadethBinding.getRoot();
        bundle_for_save_position_elhadeth = getArguments();
        if (bundle_for_save_position_elhadeth != null) {
            position_elhadeth = new Gson().fromJson(bundle_for_save_position_elhadeth.getString(POSITION), Integer.class);
            getTextElhadeth(position_elhadeth);

        }
        return view;
    }

    private String getTextElhadeth(int position_elhadeth) {
        disposable = new CompositeDisposable();
        Observable<String> observableGetTextElhadeth = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String[] text_elhadeth = getResources().getStringArray(R.array.elarbaon_elnawawaya_text_elhadeth);
                for (int i = 0; i < text_elhadeth.length; i++) {
                    Log.d("TAG", text_elhadeth[i]);
                    if (position_elhadeth == i) {
                        return_text_elhadeth = text_elhadeth[i];
                        break;
                    }
                }
                return return_text_elhadeth;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(observableGetTextElhadeth.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                return_text_elhadeth = s;
                fragmentTextElhadethBinding.TextElhadethFragmentTVTextElhaseth.setText(return_text_elhadeth);
                fragmentTextElhadethBinding.TextElhadethFragmentTVTextElhaseth.setMovementMethod(new ScrollingMovementMethod());

                Log.d("TAG", return_text_elhadeth);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                fragmentTextElhadethBinding.TextElhadethFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {
                fragmentTextElhadethBinding.TextElhadethFragmentProgressBar.setVisibility(View.GONE);
            }

        }));

        return return_text_elhadeth;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
            if (disposable != null && !disposable.isDisposed()) {
                disposable.clear();
                disposable.dispose();
            }
    }
}
