package com.mohamedtaha.imagine.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.helper.HelperClass;
import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity;
import com.mohamedtaha.imagine.mvp.view.SplashFragmentView;

import org.jetbrains.annotations.NotNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashFragment extends Fragment implements SplashFragmentView {
    @BindView(R.id.textShow)
    TextView textShow;
//    @Inject
//    SplashFragmentInteractor splashFragmentPresenter;
    private static final String TAG = "SplashFragment";

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
//        ComponentSplashFragment componentSplashFragment = DaggerComponentSplashFragment.builder().
//                moduleSplashFragment(new ModuleSplashFragment(this, getActivity())).build();
//        componentSplashFragment.inject(this);
//     //   splashFragmentPresenter.onBind(this, getActivity());
//        splashFragmentPresenter.goToSlider();
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
        //splashFragmentPresenter.onDestroy();
    }

    @Override
    public void goToSlider() {
        HelperClass.startActivity(getActivity(), NavigationDrawaberActivity.class);
        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}