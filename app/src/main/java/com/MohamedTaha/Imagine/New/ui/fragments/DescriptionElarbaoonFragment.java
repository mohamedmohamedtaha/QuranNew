package com.MohamedTaha.Imagine.New.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.MohamedTaha.Imagine.New.Adapter.PagerAdapterElarbaoonElnawawy;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.POSITION;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionElarbaoonFragment extends Fragment {
    @BindView(R.id.DescriptionElarbaoonFragment_TabLayout)
    TabLayout DescriptionElarbaoonFragmentTabLayout;
    @BindView(R.id.DescriptionElarbaoonFragment_TV_Name_elhadeth)
    TextView DescriptionElarbaoonFragmentTVNameElhadeth;
    @BindView(R.id.DescriptionElarbaoonFragment_TV_Number_elhadeth)
    TextView DescriptionElarbaoonFragmentTVNumberElhadeth;
    @BindView(R.id.View)
    android.view.View View;
    @BindView(R.id.DescriptionElarbaoonFragment_ViewPager)
    ViewPager DescriptionElarbaoonFragmentViewPager;
    private ElarbaoonElnawawyModel position_elhadeth;
    private String name_elhadeth;
    private String number_elhadeth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description_elarbaoon, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            position_elhadeth = new Gson().fromJson(bundle.getString(POSITION), ElarbaoonElnawawyModel.class);
            DescriptionElarbaoonFragmentTVNumberElhadeth.setText(position_elhadeth.getNumber_elhadeth() + "/");
            DescriptionElarbaoonFragmentTVNameElhadeth.setText(position_elhadeth.getName_elhadeth());
        }
        PagerAdapterElarbaoonElnawawy pagerAdapterElarbaoonElnawawy = new PagerAdapterElarbaoonElnawawy(getActivity(), getChildFragmentManager(),
                position_elhadeth.getPosition());
        DescriptionElarbaoonFragmentViewPager.setAdapter(pagerAdapterElarbaoonElnawawy);
        DescriptionElarbaoonFragmentViewPager.setCurrentItem(2);
        DescriptionElarbaoonFragmentTabLayout.setupWithViewPager(DescriptionElarbaoonFragmentViewPager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}