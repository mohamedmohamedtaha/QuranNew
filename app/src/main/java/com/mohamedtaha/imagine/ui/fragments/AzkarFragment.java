package com.mohamedtaha.imagine.ui.fragments;

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
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedtaha.imagine.Adapter.AdapterForAzkar;
import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.mvp.model.ModelAzkar;
import com.mohamedtaha.imagine.mvp.view.AzkarFragmentView;
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.AzkarFragment_FloatingActionButton)
    FloatingActionButton AzkarFragmentFloatingActionButton;
    private AdapterForAzkar adapterForAzkar;
    private ArrayList<ModelAzkar> modelAzkar;
    private ArrayList<ModelAzkar> modelAzkarBundle;

    private Bundle bundle;
    public static final String SAVE_AZKAR = "save_azkar";
    public static final String SAVE_POTION_AZKAR = "save_poition_azkar";
//    @Inject
//    AzkarFragmentInteractor presenter;
//    @Inject
//    ReScrollUtil reScrollUtil;
//    @Inject
//    LinearLayoutManager linearLayoutManager;

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

//        AzkarFragmentComponent azkarFragmentComponent = DaggerAzkarFragmentComponent.builder()
//                .azkarFragmentModule(new AzkarFragmentModule(this, getActivity()))
//                .contextModule(new ContextModule(getActivity()))
//                .rescroUtilModule(new RescroUtilModule(AzkarFragmentFloatingActionButton, AzkarFragmentRecycleView))
//
//                .build();
//        azkarFragmentComponent.inject(this);
//        if (presenter.isNewlyCreated && savedInstanceState != null) {
//            presenter.restoreState(savedInstanceState);
//            Log.i("TAGO", " onSuccess presenter azkar ");
//        }
//        presenter.getAllData();
//        presenter.isNewlyCreated = false;
//      //  presenter.setOnSearchView(searchView);
//        reScrollUtil.onClickRecyclerView(R.id.AzkarFragment_FloatingActionButton);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //presenter.onDestroy();
        Log.i("TAGO", " onDestroy azkar fragment ");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
         //   presenter.saveState(outState);
        }
    }

    @Override
    public void showAfterQueryText(ArrayList<ModelAzkar> stringList) {
        modelAzkar = stringList;
        adapterForAzkar = new AdapterForAzkar(stringList, new AdapterForAzkar.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //the data to be based
//                ArrayList<ModelAzkar> data = modelAzkarBundle;
//                RxBusData.sendData(data);

                bundle.putString(SAVE_AZKAR, new Gson().toJson(modelAzkarBundle));
                        bundle.putInt(SAVE_POTION_AZKAR, modelAzkar.get(position).getPosition());
                       bundle.putBoolean(SwarFragment.SAVE_STATE, false);
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
    public void showAllINameAzkar(ArrayList<ModelAzkar> strings) {
        modelAzkarBundle = strings;
        modelAzkar = strings;
       // AzkarFragmentRecycleView.setLayoutManager(linearLayoutManager);
        adapterForAzkar = new AdapterForAzkar(modelAzkar, new AdapterForAzkar.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                bundle.putString(SAVE_AZKAR, new Gson().toJson(modelAzkar));
                bundle.putInt(SAVE_POTION_AZKAR, position);
                bundle.putBoolean(SwarFragment.SAVE_STATE, false);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        AzkarFragmentRecycleView.setAdapter(adapterForAzkar);
        adapterForAzkar.notifyDataSetChanged();
        //For feel when Search
      //  presenter.setOnQueryTextForAzkar(searchView, modelAzkar);
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
                bundle.putBoolean(SwarFragment.SAVE_STATE, false);
                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
            }
        });
        AzkarFragmentRecycleView.setAdapter(adapterForAzkar);
    }
}