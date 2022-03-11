package com.mohamedtaha.imagine.ui.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mohamedtaha.imagine.Adapter.AdapterElarbaoonElnawawy;
import com.mohamedtaha.imagine.R;
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel;
import com.mohamedtaha.imagine.mvp.view.ElarbaoonElnwawyView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ElarbaoonElnawawyActivity extends AppCompatActivity implements ElarbaoonElnwawyView {
    public static final String POSITION = "position";
    public static final String NAME_ELHADETH = "name_elhadeth";
    public static final String NUMBER_ELHADETH = "number_elhadeth";
    @BindView(R.id.ElarbaoonElnawawyActivity_TB)
    Toolbar ElarbaoonElnawawyActivityTB;
    @BindView(R.id.ElarbaoonElnawawyActivity_TV_Elarbaoon_Elnawawy)
    TextView ElarbaoonElnawawyActivityTVElarbaoonElnawawy;
//    @BindView(R.id.ElarbaoonElnawawyActivitySearch_View)
//    MaterialSearchView ElarbaoonElnawawyActivitySearchView;
    @BindView(R.id.ElarbaoonElnawawyActivity_RecyclerView)
    RecyclerView ElarbaoonElnawawyActivityRecyclerView;
    @BindView(R.id.ElarbaoonElnawawyActivity_ProgressBar)
    ProgressBar ElarbaoonElnawawyActivityProgressBar;
    @BindView(R.id.ElarbaoonElnawawyActivity_TV_No_Data)
    TextView ElarbaoonElnawawyActivityTVNoData;
    @BindView(R.id.ElarbaoonElnawawyActivity_FloatingActionButton)
    FloatingActionButton ElarbaoonElnawawyActivityFloatingActionButton;

//    @Inject
//    ElarbaoonElnwawyInteractor elarbaoonElnwawyPresenter;
//    @Inject
//    ReScrollUtil reScrollUtil;
//    private List<ElarbaoonElnawawyModel> elnawawyModelList;
//    @Inject
//    ElarbaoonElnawawyModel elnawawyModel;
    private Menu globalMenu;
    private AdapterElarbaoonElnawawy adapterElarbaoonElnawawy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elarbaoon_elnawawy);
        ButterKnife.bind(this);
//        ElarbaoonElnawawyComponent elarbaoonElnawawyComponent = DaggerElarbaoonElnawawyComponent.builder()
//                .elarbaoonElnawawyModule(new ElarbaoonElnawawyModule(this, this))
//                .rescroUtilModule(new RescroUtilModule(ElarbaoonElnawawyActivityFloatingActionButton, ElarbaoonElnawawyActivityRecyclerView))
//                .build();
//        elarbaoonElnawawyComponent.inject(this);

        ElarbaoonElnawawyActivityTVElarbaoonElnawawy.setText(getString(R.string.el_arbaoon_elnawawy));
        custom_toolbar();
     //   elarbaoonElnwawyPresenter.getAllData();
        //elarbaoonElnwawyPresenter.setOnSearchView(ElarbaoonElnawawyActivitySearchView);
        Log.d("TAG", "Elnawawy");
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 2) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        ElarbaoonElnawawyActivityRecyclerView.setLayoutManager(linearLayoutManager);
    //    reScrollUtil.onClickRecyclerView(R.id.ElarbaoonElnawawyActivity_FloatingActionButton);
    }

    @Override
    public void showDataAfterQueryText(List<ElarbaoonElnawawyModel> stringList) {
     //   elnawawyModelList = stringList;
        adapterElarbaoonElnawawy = new AdapterElarbaoonElnawawy(stringList, new AdapterElarbaoonElnawawy.ClickListener() {
            @Override
            public void onClick(int position) {
//                elnawawyModel.setPosition(elnawawyModelList.get(position).getPosition());
//                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
//                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
//                openFragmentElnawary(elnawawyModel);
            }
        });
        ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);
    }

    @Override
    public void isEmpty() {
        ElarbaoonElnawawyActivityTVNoData.setVisibility(View.VISIBLE);
        ElarbaoonElnawawyActivityRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        ElarbaoonElnawawyActivityTVNoData.setVisibility(View.GONE);
        ElarbaoonElnawawyActivityRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        ElarbaoonElnawawyActivityProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ElarbaoonElnawawyActivityProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // elarbaoonElnwawyPresenter.onDestroy();
    }

    @Override
    public void showAllElahadeth(List<ElarbaoonElnawawyModel> strings) {
//        elnawawyModelList = strings;
//        adapterElarbaoonElnawawy = new AdapterElarbaoonElnawawy(elnawawyModelList, new AdapterElarbaoonElnawawy.ClickListener() {
//            @Override
//            public void onClick(int position) {
//                elnawawyModel.setPosition(position);
//                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
//                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
//                openFragmentElnawary(elnawawyModel);
//            }
//        });
        ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);
        adapterElarbaoonElnawawy.notifyDataSetChanged();
        //For feel when Search
     //   elarbaoonElnwawyPresenter.setOnQueryTextForElhadeth(ElarbaoonElnawawyActivitySearchView, elnawawyModelList);
    }

    @Override
    public void showAnimation() {
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_fall_dwon);
        ElarbaoonElnawawyActivityRecyclerView.setLayoutAnimation(controller);
        ElarbaoonElnawawyActivityRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void showAfterSearch() {
//        adapterElarbaoonElnawawy = new AdapterElarbaoonElnawawy(elnawawyModelList, new AdapterElarbaoonElnawawy.ClickListener() {
//            @Override
//            public void onClick(int position) {
//                elnawawyModel.setPosition(position);
//                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
//                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
//                openFragmentElnawary(elnawawyModel);
//            }
//        });
//        ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);
    }

    public void custom_toolbar() {
        setSupportActionBar(ElarbaoonElnawawyActivityTB);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //for delete label for Activity
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem SearchItem = menu.findItem(R.id.action_search);
        //ElarbaoonElnawawyActivitySearchView.setMenuItem(SearchItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return false;
    }

    private void openFragmentElnawary(ElarbaoonElnawawyModel elnawawyModel) {
        Intent startActivity = new Intent(getApplicationContext(), ContianerDescriptionElnawawyActivity.class);
        startActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity.putExtra(POSITION, new Gson().toJson(elnawawyModel));
        startActivity(startActivity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }

    @Override
    public void onBackPressed() {
//        if (ElarbaoonElnawawyActivitySearchView.isSearchOpen()) {
//            ElarbaoonElnawawyActivitySearchView.closeSearch();
//        } else {
//            super.onBackPressed();
//        }
    }
}
