package com.MohamedTaha.Imagine.New.ui.fragments.elnawawy;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.databinding.FragmentDescriptionElhaedthBinding;
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
public class DescriptionElhaedthFragment extends Fragment {
    private FragmentDescriptionElhaedthBinding fragmentDescriptionElhaedthBinding;
    private Bundle bundle_for_save_position_elhadeth;
    private int position_elhadeth;
    private CompositeDisposable disposable;
    private String return_text_elhadeth = null;

    public DescriptionElhaedthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDescriptionElhaedthBinding = FragmentDescriptionElhaedthBinding.inflate(inflater, container, false);
        View view = fragmentDescriptionElhaedthBinding.getRoot();
        bundle_for_save_position_elhadeth = getArguments();
        if (bundle_for_save_position_elhadeth != null) {
            position_elhadeth = new Gson().fromJson(bundle_for_save_position_elhadeth.getString(POSITION), Integer.class);
            getDescriptionElhadeth(position_elhadeth);
        }
        return view;

    }

    private String getDescriptionElhadeth(int position_elhadeth) {
        disposable = new CompositeDisposable();
        Observable<String> observableGetdescriptionEllhadeth = Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String[] text_elhadeth = getResources().getStringArray(R.array.elarbaon_elnawawaya_decription_elhadeth);
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
        disposable.add(observableGetdescriptionEllhadeth.subscribeWith(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                return_text_elhadeth = s;
                fragmentDescriptionElhaedthBinding.DescriptionElhaedthFragmentTVDescriptionElhaseth.setText(return_text_elhadeth);
                fragmentDescriptionElhaedthBinding.DescriptionElhaedthFragmentTVDescriptionElhaseth.setMovementMethod(new ScrollingMovementMethod());

                Log.d("TAG", return_text_elhadeth);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                fragmentDescriptionElhaedthBinding.DescriptionElhaedthFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {
                fragmentDescriptionElhaedthBinding.DescriptionElhaedthFragmentProgressBar.setVisibility(View.GONE);
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
