package com.MohamedTaha.Imagine.New.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.mvp.interactor.SplashInteractor;
import com.MohamedTaha.Imagine.New.mvp.presenter.SplashPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.SplashView;
import com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment implements SplashView {
    public static final String SAVE_PAGE = "savepage";
    public static final String SAVE_ALL_IMAGES = "save_all_images";
    @BindView(R.id.textShow)
    TextView textShow;
    private SplashPresenter splashPresenter;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        splashPresenter = new SplashInteractor(this, getActivity());
        splashPresenter.goToSlider();
        return view;
    }

    @Override
    public void onAttachFragment(@NotNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void showAnimation() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.aminmation_splash);
        textShow.startAnimation(animation);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        splashPresenter.onDestroy();
    }

    @Override
    public void goToSlider() {
//        SliderFragment sliderFragment = new SliderFragment();
//        HelperClass.replece(sliderFragment, getFragmentManager(), R.id.Cycle_Splash_contener);
//        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        //  HelperClass.startActivity(getActivity(), SplashActivity.class);
        // getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        HelperClass.startActivity(getActivity(), NavigationDrawaberActivity.class);
            getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}