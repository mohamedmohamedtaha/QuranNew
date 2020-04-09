package com.MohamedTaha.Imagine.New.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.New.Adapter.PagerAdapterElarbaoonElnawawy;
import com.MohamedTaha.Imagine.New.databinding.FragmentDescriptionElarbaoonBinding;
import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.google.gson.Gson;

import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.NAME_ELHADETH;
import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.NUMBER_ELHADETH;
import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.POSITION;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionElarbaoonFragment extends Fragment {
    private FragmentDescriptionElarbaoonBinding fragmentDescriptionElarbaoonBinding;
    private ElarbaoonElnawawyModel position_elhadeth;
    private String name_elhadeth;
    private String number_elhadeth;


    public DescriptionElarbaoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDescriptionElarbaoonBinding = FragmentDescriptionElarbaoonBinding.inflate(inflater, container, false);
        View view = fragmentDescriptionElarbaoonBinding.getRoot();
        Bundle bundle = getArguments();
        if (bundle != null) {
            position_elhadeth = new Gson().fromJson(bundle.getString(POSITION), ElarbaoonElnawawyModel.class);
//            name_elhadeth = new Gson().fromJson(bundle.getString(NAME_ELHADETH), String.class);
//            number_elhadeth = new Gson().fromJson(bundle.getString(NUMBER_ELHADETH), String.class);
            fragmentDescriptionElarbaoonBinding.DescriptionElarbaoonFragmentTVNumberElhadeth.setText(position_elhadeth.getNumber_elhadeth()+"/" );
            fragmentDescriptionElarbaoonBinding.DescriptionElarbaoonFragmentTVNameElhadeth.setText(position_elhadeth.getName_elhadeth());
        }
        PagerAdapterElarbaoonElnawawy pagerAdapterElarbaoonElnawawy = new PagerAdapterElarbaoonElnawawy(getActivity(), getChildFragmentManager(), position_elhadeth.getPosition());
        fragmentDescriptionElarbaoonBinding.DescriptionElarbaoonFragmentViewPager.setAdapter(pagerAdapterElarbaoonElnawawy);
        fragmentDescriptionElarbaoonBinding.DescriptionElarbaoonFragmentViewPager.setCurrentItem(2);
        fragmentDescriptionElarbaoonBinding.DescriptionElarbaoonFragmentTabLayout.setupWithViewPager(fragmentDescriptionElarbaoonBinding.DescriptionElarbaoonFragmentViewPager);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentDescriptionElarbaoonBinding = null;
    }


}
