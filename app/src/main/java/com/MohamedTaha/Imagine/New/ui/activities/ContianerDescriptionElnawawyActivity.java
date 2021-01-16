package com.MohamedTaha.Imagine.New.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.helper.HelperClass;
import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.MohamedTaha.Imagine.New.ui.fragments.DescriptionElarbaoonFragment;
import com.google.gson.Gson;

import static com.MohamedTaha.Imagine.New.ui.activities.ElarbaoonElnawawyActivity.POSITION;


public class ContianerDescriptionElnawawyActivity extends AppCompatActivity {
      //private ActivityContianerDescriptionElnawawyBinding activity_contianer_description_elnawawy;
    private ElarbaoonElnawawyModel position_elhadeth;
    private String name_elhadeth;
    private String number_elhadeth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contianer_description_elnawawy);
            //activity_contianer_description_elnawawy = DataBindingUtil.setContentView(this,R.layout.activity_contianer_description_elnawawy);
        Intent intent = getIntent();
        if (intent != null) {
            position_elhadeth = new Gson().fromJson(intent.getStringExtra(POSITION), ElarbaoonElnawawyModel.class);
        }

        DescriptionElarbaoonFragment descriptionElarbaoonFragment = new DescriptionElarbaoonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(POSITION, new Gson().toJson(position_elhadeth));
//        bundle.putString(NAME_ELHADETH,new Gson().toJson(name_elhadeth));
//        bundle.putString(NUMBER_ELHADETH,new Gson().toJson(number_elhadeth));

        descriptionElarbaoonFragment.setArguments(bundle);
        HelperClass.replece(descriptionElarbaoonFragment, getSupportFragmentManager(), R.id.Cycle_Elarbaoon_Elnawawy_contener);
        // overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity_contianer_description_elnawawy = null;
    }
}
