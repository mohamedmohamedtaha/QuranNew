package com.mohamedtaha.imagine.ui.fragments.elnawawy;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.libraryTextView.TextViewEx;
import com.mohamedtaha.imagine.ui.navigationview.ElarbaoonElnawawyActivity;
import com.google.gson.Gson;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateElhadethFragment extends Fragment {
    @BindView(R.id.TranslateElhadethFragment_TV_Translate_Elhaseth)
    TextViewEx TranslateElhadethFragmentTVTranslateElhaseth;
    @BindView(R.id.TranslateElhadethFragment_ProgressBar)
    ProgressBar TranslateElhadethFragmentProgressBar;
    //private FragmentTranslateBinding fragmentTranslateBinding;
    private Bundle bundle_for_save_position_elhadeth;
    private int position_elhadeth;
    private CompositeDisposable disposable;
    private String return_translate_elhadeth = null;
    private String translate_elhadeth_depended_on_position = null;


    public TranslateElhadethFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        fragmentTranslateBinding = FragmentTranslateBinding.inflate(inflater);
//        View view = fragmentTranslateBinding.getRoot();
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        ButterKnife.bind(this, view);
        bundle_for_save_position_elhadeth = getArguments();
        if (bundle_for_save_position_elhadeth != null) {
            position_elhadeth = new Gson().fromJson(bundle_for_save_position_elhadeth.getString(ElarbaoonElnawawyActivity.POSITION), Integer.class);
            getTranslateForElhadeth(position_elhadeth);
        }
        return view;
    }

    private void getTranslateForElhadeth(int position_elhadeth) {
        disposable = new CompositeDisposable();
        Observable<String> observableGetTranslateElhadeth = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String[] text_elhadeth = getResources().getStringArray(R.array.elarbaon_elnawawaya_translate_elhadeth);
                for (int i = 0; i < text_elhadeth.length; i++) {
                    Log.d("TAG", text_elhadeth[i]);
                    if (position_elhadeth == i) {
                        return_translate_elhadeth = text_elhadeth[i];
                        break;
                    }
                }
                return return_translate_elhadeth;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposable.add(observableGetTranslateElhadeth.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                translate_elhadeth_depended_on_position = s;
                TranslateElhadethFragmentTVTranslateElhaseth.setText(translate_elhadeth_depended_on_position);
                TranslateElhadethFragmentTVTranslateElhaseth.setMovementMethod(new ScrollingMovementMethod());

                Log.d("TAG", translate_elhadeth_depended_on_position);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                TranslateElhadethFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {
                TranslateElhadethFragmentProgressBar.setVisibility(View.GONE);
            }

        }));
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
