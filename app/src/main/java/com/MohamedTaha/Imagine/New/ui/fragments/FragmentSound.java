package com.MohamedTaha.Imagine.New.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.Adapter.ImageAdapter;
import com.MohamedTaha.Imagine.New.AutofitRecyclerView;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ReScrollUtil;
import com.MohamedTaha.Imagine.New.mvp.interactor.ListSoundReaderInteractor;
import com.MohamedTaha.Imagine.New.mvp.model.ImageModel;
import com.MohamedTaha.Imagine.New.mvp.presenter.ListSoundReaderPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.ListSoundReaderView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.searchView;

/**
 * Created by MANASATT on 20/08/17.
 */


public class FragmentSound extends Fragment implements ListSoundReaderView {
    //    @BindView(R.id.Fragment_Sound_GridView)
//    GridView FragmentSoundGridView;
    @BindView(R.id.Fragment_Sound_ProgressBar)
    ProgressBar FragmentSoundProgressBar;
    @BindView(R.id.Fragment_Sound_TV_No_Data)
    TextView FragmentSoundTVNoData;
    @BindView(R.id.Fragment_Sound_FloatingActionButton)
    FloatingActionButton FragmentSoundFloatingActionButton;
    Unbinder unbinder;
    ImageAdapter imageAdapter;
    @BindView(R.id.Fragment_Sound_RecyclerView)
    AutofitRecyclerView FragmentSoundRecyclerView;
    private List<ImageModel> imageModel;
    private ListSoundReaderPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sound, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        getActivity().setTitle(getString(R.string.listen_swar));
        presenter = new ViewModelProvider(this).get(ListSoundReaderInteractor.class);
        presenter.onBind(this, getActivity());
        presenter.getAllData();

        presenter.setOnSearchViewListener(searchView);
//        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2) {
//            @Override
//            protected boolean isLayoutRTL() {
//                return true;
//            }
//        };
//        FragmentSoundRecyclerView.setLayoutManager(linearLayoutManager);
//        FragmentSoundRecyclerView.setHasFixedSize(true);
        ReScrollUtil reScrollUtil = new ReScrollUtil(FragmentSoundFloatingActionButton, FragmentSoundRecyclerView);
        reScrollUtil.onClickRecyclerView(R.id.Fragment_Sound_FloatingActionButton);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        FragmentSoundProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        FragmentSoundProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void noData() {
        FragmentSoundTVNoData.setVisibility(View.VISIBLE);
        FragmentSoundRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        FragmentSoundTVNoData.setVisibility(View.GONE);
        FragmentSoundRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAllData(List<ImageModel> imageModels) {
        imageModel = imageModels;
        imageAdapter = new ImageAdapter(imageModel, getActivity());
        FragmentSoundRecyclerView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
        //For feel when Search
        presenter.setOnQueryTextListener(searchView, imageModel);
    }

    @Override
    public void showAfterSearch() {
        imageAdapter = new ImageAdapter(imageModel, getActivity());
        FragmentSoundRecyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void showAfterQueryText(List<ImageModel> lstFound) {
        imageAdapter = new ImageAdapter(lstFound, getActivity());
        FragmentSoundRecyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void showAnimation() {
        //For animation
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_fall_dwon);
        FragmentSoundRecyclerView.setLayoutAnimation(controller);
        FragmentSoundRecyclerView.scheduleLayoutAnimation();
    }
}