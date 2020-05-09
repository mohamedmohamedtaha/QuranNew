package com.MohamedTaha.Imagine.New.Adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ui.fragments.elnawawy.DescriptionElhaedthFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.elnawawy.TextElhadethFragment;
import com.MohamedTaha.Imagine.New.ui.fragments.elnawawy.TranslateElhadethFragment;
import com.google.gson.Gson;

import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.POSITION;

public class PagerAdapterElarbaoonElnawawy extends FragmentPagerAdapter {
    private Context context;
    private int position_elhadeth;

    public PagerAdapterElarbaoonElnawawy(Context context, @NonNull FragmentManager fm,int position_elhadeth) {
        super(fm);
        this.context = context;
        this.position_elhadeth = position_elhadeth;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putString(POSITION,new Gson().toJson(position_elhadeth));
                TranslateElhadethFragment translateElhadethFragment = new TranslateElhadethFragment();
                translateElhadethFragment.setArguments(bundle);
                return translateElhadethFragment;
            case 1:
                bundle.putString(POSITION,new Gson().toJson(position_elhadeth));
                DescriptionElhaedthFragment descriptionElhaedthFragment = new DescriptionElhaedthFragment();
                descriptionElhaedthFragment.setArguments(bundle);

                return descriptionElhaedthFragment;
            default:
                bundle.putString(POSITION,new Gson().toJson(position_elhadeth));
                TextElhadethFragment textElhadethFragment = new TextElhadethFragment();
                textElhadethFragment.setArguments(bundle);
                return textElhadethFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.translate_elhadeth);
            case 1:
                return context.getString(R.string.description_elhadeth);
            default:
                return context.getString(R.string.text_elhadeth);

        }
    }
}
