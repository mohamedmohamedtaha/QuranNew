package com.MohamedTaha.Imagine.New.ui.fragments.elnawawy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.databinding.FragmentTranslateBinding;
import com.google.gson.Gson;

import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.POSITION;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateElhadethFragment extends Fragment {
    private FragmentTranslateBinding fragmentTranslateBinding;
    private Bundle bundle;
    private int position_elhadeth;

    public TranslateElhadethFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTranslateBinding = FragmentTranslateBinding.inflate(inflater,container,false);
        View view = fragmentTranslateBinding.getRoot();
       // return inflater.inflate(R.layout.fragment_translate, container, false);

        bundle = getArguments();
        if (bundle != null) {
            position_elhadeth = new Gson().fromJson(bundle.getString(POSITION), Integer.class);
        }
        Toast.makeText(getActivity(), "position is :" + position_elhadeth, Toast.LENGTH_SHORT).show();
        return view;
    }
}
