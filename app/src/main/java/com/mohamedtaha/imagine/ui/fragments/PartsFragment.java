package com.mohamedtaha.imagine.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedtaha.imagine.Adapter.AdapterForPartsAndSwar;
import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.mvp.model.ModelSora;
import com.mohamedtaha.imagine.mvp.view.PartsFragmentView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartsFragment extends Fragment implements PartsFragmentView {
    public static final String SAVE_IMAGES = "save_images";
    Bundle bundle;
    @BindView(R.id.PartsFragment_RecyclerView)
    RecyclerView PartsFragmentRecyclerView;
    @BindView(R.id.PartsFragment_TV_No_Data)
    TextView PartsFragmentTVNoData;
    @BindView(R.id.PartsFragment_ProgressBar)
    ProgressBar PartsFragmentProgressBar;
    @BindView(R.id.PartsFragment_FloatingActionButton)
    FloatingActionButton PartsFragmentFloatingActionButton;

    private List<ModelSora> name_part;
    private AdapterForPartsAndSwar adapterNamePart;
//    @Inject
//    PartsFragmentsInteractor presenter;
//    @Inject
//    ReScrollUtil reScrollUtil;
    private List<Integer> integers_bundle;

    public PartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parts, container, false);
        ButterKnife.bind(this, view);
//        PartsFragmentComponent partsFragmentComponent = DaggerPartsFragmentComponent.builder()
//                .partsFragmentModule(new PartsFragmentModule(this, getActivity()))
//                .rescroUtilModule(new RescroUtilModule(PartsFragmentFloatingActionButton, PartsFragmentRecyclerView))
//                .contextModule(new ContextModule(getActivity()))
//                .build();
//        partsFragmentComponent.inject(this);

        getActivity().setTitle(getString(R.string.read_parts));
        bundle = new Bundle();
//        presenter.getAllPartSoura();
//        presenter.getAllImages();
       // presenter.setOnSearchView(searchView);

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        PartsFragmentRecyclerView.setLayoutManager(linearLayoutManager);
        //reScrollUtil.onClickRecyclerView(R.id.PartsFragment_FloatingActionButton);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
     //   presenter.onDestroy();
    }

    @Override
    public void showAfterSearch() {
//        adapterNamePart = new AdapterForPartsAndSwar(name_part, true, new AdapterForPartsAndSwar.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                //presenter.getPosition(name_part.get(position).getPosition(), bundle);
//                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
//                bundle.putBoolean(SAVE_STATE, true);
//                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
//            }
//        });
//        PartsFragmentRecyclerView.setAdapter(adapterNamePart);
    }

    @Override
    public void showAfterQueryText(List<ModelSora> stringList) {
        //name_part.clear();
//        name_part = stringList;
//        adapterNamePart = new AdapterForPartsAndSwar(stringList, true, new AdapterForPartsAndSwar.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                //presenter.getPosition(name_part.get(position).getPosition(), bundle);
//                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
//                bundle.putBoolean(SAVE_STATE, true);
//                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
//            }
//        });
//        PartsFragmentRecyclerView.setAdapter(adapterNamePart);
    }

    @Override
    public void showAllImages(List<Integer> integers) {
        integers_bundle = integers;
    }

    @Override
    public void isEmpty() {
        PartsFragmentTVNoData.setVisibility(View.VISIBLE);
        PartsFragmentRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        PartsFragmentTVNoData.setVisibility(View.GONE);
        PartsFragmentRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        PartsFragmentProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        PartsFragmentProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showAllINamePart(List<ModelSora> strings) {
//        name_part = strings;
//        adapterNamePart = new AdapterForPartsAndSwar(name_part, true, new AdapterForPartsAndSwar.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                //presenter.getPosition(name_part.get(position).getPosition(), bundle);
//                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
//                bundle.putBoolean(SAVE_STATE, true);
//                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
//            }
//        });
//        PartsFragmentRecyclerView.setAdapter(adapterNamePart);
//        adapterNamePart.notifyDataSetChanged();
//        //For feel when Search
//       // presenter.setOnQueryText(searchView, name_part);
    }

    @Override
    public void showAnimation() {
        //For animation
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_fall_dwon);
        PartsFragmentRecyclerView.setLayoutAnimation(controller);
        PartsFragmentRecyclerView.scheduleLayoutAnimation();
    }

}