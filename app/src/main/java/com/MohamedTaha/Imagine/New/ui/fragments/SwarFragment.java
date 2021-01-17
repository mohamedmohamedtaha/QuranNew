package com.MohamedTaha.Imagine.New.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.Adapter.AdapterForPartsAndSwar;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ReScrollUtil;
import com.MohamedTaha.Imagine.New.dagger2.component.DaggerSwarFragmentComponent;
import com.MohamedTaha.Imagine.New.dagger2.component.SwarFragmentComponent;
import com.MohamedTaha.Imagine.New.dagger2.module.ContextModule;
import com.MohamedTaha.Imagine.New.dagger2.module.RescroUtilModule;
import com.MohamedTaha.Imagine.New.dagger2.module.SwarFragmentModule;
import com.MohamedTaha.Imagine.New.mvp.interactor.SwarFragmentInteractor;
import com.MohamedTaha.Imagine.New.mvp.model.ModelSora;
import com.MohamedTaha.Imagine.New.mvp.view.SwarFragmentView;
import com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.searchView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SwarFragment extends Fragment implements SwarFragmentView {
    @BindView(R.id.ReadSwarFragment_TV_No_Data)
    TextView ReadSwarFragmentTVNoData;
    @BindView(R.id.ReadSwarFragment_RecyclerView)
    RecyclerView ReadSwarFragmentRecyclerView;
    @BindView(R.id.ReadSwarFragment_ProgressBar)
    ProgressBar ReadSwarFragmentProgressBar;
    public static final String SAVE_IMAGES = "save_images";
    public static final String SAVE_STATE = "save_state";
    private static final String SAVE_STATE_FOR_ROTATION = "save_sate_for_rotation";
    @BindView(R.id.ReadSwarFragment_FloatingActionButton)
    FloatingActionButton ReadSwarFragmentFloatingActionButton;
    private int state_fragment = 0;

    Bundle bundle;
    private List<ModelSora> name_swar;
    private List<Integer> integers_bundle;
    private AdapterForPartsAndSwar adapterForPartsAndSwar;
    @Inject
    SwarFragmentInteractor presenter;
    @Inject
    ReScrollUtil reScrollUtil;

    public SwarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_swar, container, false);
        ButterKnife.bind(this, view);

        SwarFragmentComponent swarFragmentComponent = DaggerSwarFragmentComponent.builder()
                .swarFragmentModule(new SwarFragmentModule(this,getActivity()))
                .rescroUtilModule(new RescroUtilModule(ReadSwarFragmentFloatingActionButton, ReadSwarFragmentRecyclerView))
                .contextModule(new ContextModule(getActivity()))
                .build();
        swarFragmentComponent.inject(this);
        getActivity().setTitle(getString(R.string.readswar));
        setRetainInstance(true);
        if (savedInstanceState == null) {
            state_fragment = 0;
            Log.d("TAG", "Current fragment  four is :" + state_fragment);

        } else {
            state_fragment = savedInstanceState.getInt(SAVE_STATE_FOR_ROTATION);
            Log.d("TAG", "Current fragment  four is :" + state_fragment);

        }
        bundle = new Bundle();
        presenter.getAllNameSour();
        presenter.getAllImages();
        presenter.setOnSearchView(searchView);


        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        ReadSwarFragmentRecyclerView.setLayoutManager(linearLayoutManager);
        reScrollUtil.onClickRecyclerView(R.id.ReadSwarFragment_FloatingActionButton);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_FOR_ROTATION, state_fragment);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showAfterSearch() {
        adapterForPartsAndSwar = new AdapterForPartsAndSwar(name_swar, false, new AdapterForPartsAndSwar.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_swar.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        ReadSwarFragmentRecyclerView.setAdapter(adapterForPartsAndSwar);
    }

    @Override
    public void showAfterQueryText(List<ModelSora> stringList) {
        name_swar = stringList;
        adapterForPartsAndSwar = new AdapterForPartsAndSwar(stringList, false, new AdapterForPartsAndSwar.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_swar.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        ReadSwarFragmentRecyclerView.setAdapter(adapterForPartsAndSwar);
    }

    @Override
    public void hideProgress() {
        ReadSwarFragmentProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        ReadSwarFragmentProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAllINameSour(List<ModelSora> strings) {
        name_swar = strings;
        adapterForPartsAndSwar = new AdapterForPartsAndSwar(name_swar, false, new AdapterForPartsAndSwar.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                presenter.getPosition(name_swar.get(position).getPosition(), bundle);
                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
                bundle.putBoolean(SAVE_STATE, true);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        ReadSwarFragmentRecyclerView.setAdapter(adapterForPartsAndSwar);
        adapterForPartsAndSwar.notifyDataSetChanged();
        //For feel when Search
        presenter.setOnQueryText(searchView, name_swar);
    }

    @Override
    public void showAllImages(List<Integer> integers) {
        integers_bundle = integers;
    }

    @Override
    public void isEmpty() {
        ReadSwarFragmentTVNoData.setVisibility(View.VISIBLE);
        ReadSwarFragmentRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        ReadSwarFragmentTVNoData.setVisibility(View.GONE);
        ReadSwarFragmentRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAnimation() {
        //For animation
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_fall_dwon);
        ReadSwarFragmentRecyclerView.setLayoutAnimation(controller);
        ReadSwarFragmentRecyclerView.scheduleLayoutAnimation();
    }
}