package com.mohamedtaha.imagine.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.helper.HelperClass;
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel;
import com.mohamedtaha.imagine.ui.fragments.DescriptionElarbaoonFragment;
import com.google.gson.Gson;

import static com.mohamedtaha.imagine.ui.activities.ElarbaoonElnawawyActivity.POSITION;


public class ContianerDescriptionElnawawyActivity extends AppCompatActivity {
    private ElarbaoonElnawawyModel position_elhadeth;
    private String name_elhadeth;
    private String number_elhadeth;
//    @Inject
//    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contianer_description_elnawawy);
//        ContianerDescriptionElnawawyComponent contianerDescriptionElnawawyComponent = DaggerContianerDescriptionElnawawyComponent.create();
//        contianerDescriptionElnawawyComponent.inject(this);
        Intent intent = getIntent();
        if (intent != null) {
            position_elhadeth = new Gson().fromJson(intent.getStringExtra(POSITION), ElarbaoonElnawawyModel.class);
        }
        DescriptionElarbaoonFragment descriptionElarbaoonFragment = new DescriptionElarbaoonFragment();
//        bundle.putString(POSITION, new Gson().toJson(position_elhadeth));
//        descriptionElarbaoonFragment.setArguments(bundle);
        HelperClass.replece(descriptionElarbaoonFragment, getSupportFragmentManager(), R.id.Cycle_Elarbaoon_Elnawawy_contener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
