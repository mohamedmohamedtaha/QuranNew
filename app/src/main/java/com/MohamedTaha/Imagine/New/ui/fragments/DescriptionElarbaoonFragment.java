package com.MohamedTaha.Imagine.New.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.MohamedTaha.Imagine.New.Adapter.PagerAdapterElarbaoonElnawawy;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.databinding.FragmentDescriptionElarbaoonBinding;
import com.google.gson.Gson;

import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.POSITION;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionElarbaoonFragment extends Fragment {
    private FragmentDescriptionElarbaoonBinding fragmentDescriptionElarbaoonBinding;
    private int position_elhadeth;

    public DescriptionElarbaoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDescriptionElarbaoonBinding = FragmentDescriptionElarbaoonBinding.inflate(inflater,container,false);
        View view = fragmentDescriptionElarbaoonBinding.getRoot();
        Bundle bundle = getArguments();

        if (bundle != null){
            position_elhadeth = new Gson().fromJson(bundle.getString(POSITION),Integer.class);
        }
        PagerAdapterElarbaoonElnawawy pagerAdapterElarbaoonElnawawy = new PagerAdapterElarbaoonElnawawy(getActivity(), getChildFragmentManager(),position_elhadeth);
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
