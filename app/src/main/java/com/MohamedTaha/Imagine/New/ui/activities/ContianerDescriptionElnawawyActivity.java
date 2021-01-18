package com.MohamedTaha.Imagine.New.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.dagger2.component.ContianerDescriptionElnawawyComponent;
import com.MohamedTaha.Imagine.New.dagger2.component.DaggerContianerDescriptionElnawawyComponent;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.MohamedTaha.Imagine.New.ui.fragments.DescriptionElarbaoonFragment;
import com.google.gson.Gson;

import javax.inject.Inject;

import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.POSITION;


public class ContianerDescriptionElnawawyActivity extends AppCompatActivity {
    private ElarbaoonElnawawyModel position_elhadeth;
    private String name_elhadeth;
    private String number_elhadeth;
    @Inject
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contianer_description_elnawawy);
        ContianerDescriptionElnawawyComponent contianerDescriptionElnawawyComponent = DaggerContianerDescriptionElnawawyComponent.create();
        contianerDescriptionElnawawyComponent.inject(this);
        Intent intent = getIntent();
        if (intent != null) {
            position_elhadeth = new Gson().fromJson(intent.getStringExtra(POSITION), ElarbaoonElnawawyModel.class);
        }
        DescriptionElarbaoonFragment descriptionElarbaoonFragment = new DescriptionElarbaoonFragment();
        bundle.putString(POSITION, new Gson().toJson(position_elhadeth));
        descriptionElarbaoonFragment.setArguments(bundle);
        HelperClass.replece(descriptionElarbaoonFragment, getSupportFragmentManager(), R.id.Cycle_Elarbaoon_Elnawawy_contener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
