package com.MohamedTaha.Imagine.New.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.Adapter.AdapterForAzkar;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.interactor.AzkarFragmentInteractor;
import com.MohamedTaha.Imagine.New.mvp.model.ModelAzkar;
import com.MohamedTaha.Imagine.New.mvp.presenter.AzkarFragmentPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.AzkarFragmentView;
import com.MohamedTaha.Imagine.New.ui.activities.SwipePagesActivity;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.New.ui.activities.NavigationDrawaberActivity.searchView;
import static com.MohamedTaha.Imagine.New.ui.fragments.GridViewFragment.SAVE_STATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class AzkarFragment extends Fragment implements AzkarFragmentView {
    @BindView(R.id.AzkarFragmentRecycleView)
    RecyclerView AzkarFragmentRecycleView;
    @BindView(R.id.AzkarFragment_TV_No_Data)
    TextView AzkarFragmentTVNoData;
    @BindView(R.id.AzkarFragment_ProgressBar)
    ProgressBar AzkarFragmentProgressBar;
    private AdapterForAzkar adapterForAzkar;
    private List<ModelAzkar> modelAzkar;
    private List<ModelAzkar> modelAzkarBundle;

    Bundle bundle;
    public static final String SAVE_AZKAR = "save_azkar";
    public static final String SAVE_POTION_AZKAR = "save_poition_azkar";
    private AzkarFragmentPresenter presenter;
    LinearLayoutManager linearLayoutManager;

    public AzkarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_azkar, container, false);
        ButterKnife.bind(this, view);
        bundle = new Bundle();
        getActivity().setTitle(getString(R.string.azkar_elmosle));
        presenter = new AzkarFragmentInteractor(this, getActivity());
        presenter.getAllData();
        presenter.setOnSearchView(searchView);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showAfterQueryText(List<ModelAzkar> stringList) {
        modelAzkar = stringList;
        adapterForAzkar = new AdapterForAzkar(stringList, new AdapterForAzkar.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                bundle.putString(SAVE_AZKAR, new Gson().toJson(modelAzkarBundle));
                bundle.putInt(SAVE_POTION_AZKAR, modelAzkar.get(position).getPosition());
                bundle.putBoolean(SAVE_STATE, false);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        AzkarFragmentRecycleView.setAdapter(adapterForAzkar);
    }

    @Override
    public void isEmpty() {
        AzkarFragmentTVNoData.setVisibility(View.VISIBLE);
        AzkarFragmentRecycleView.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        AzkarFragmentTVNoData.setVisibility(View.GONE);
        AzkarFragmentRecycleView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        AzkarFragmentProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        AzkarFragmentProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showAllINameAzkar(List<ModelAzkar> strings) {
        modelAzkarBundle = strings;
        modelAzkar = strings;
        linearLayoutManager = new LinearLayoutManager(getActivity());
        AzkarFragmentRecycleView.setLayoutManager(linearLayoutManager);
        adapterForAzkar = new AdapterForAzkar(modelAzkar, new AdapterForAzkar.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                bundle.putString(SAVE_AZKAR, new Gson().toJson(modelAzkar));
                bundle.putInt(SAVE_POTION_AZKAR, position);
                bundle.putBoolean(SAVE_STATE, false);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);

            }
        });

        AzkarFragmentRecycleView.setAdapter(adapterForAzkar);
        adapterForAzkar.notifyDataSetChanged();
        //For feel when Search
        presenter.setOnQueryTextForAzkar(searchView, modelAzkar);
    }

    @Override
    public void showAnimation() {
        //For animation
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_fall_dwon);
        AzkarFragmentRecycleView.setLayoutAnimation(controller);
        AzkarFragmentRecycleView.scheduleLayoutAnimation();
    }

    @Override
    public void showAfterSearch() {
        adapterForAzkar = new AdapterForAzkar(modelAzkar, new AdapterForAzkar.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                bundle.putString(SAVE_AZKAR, new Gson().toJson(modelAzkar));
                bundle.putInt(SAVE_POTION_AZKAR, position);
                bundle.putBoolean(SAVE_STATE, false);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        AzkarFragmentRecycleView.setAdapter(adapterForAzkar);
    }
}