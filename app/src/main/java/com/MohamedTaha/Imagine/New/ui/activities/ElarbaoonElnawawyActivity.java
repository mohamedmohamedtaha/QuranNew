package com.MohamedTaha.Imagine.New.ui.activities;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MohamedTaha.Imagine.New.Adapter.AdapterElarbaoonElnawawy;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.ReScrollUtil;
import com.MohamedTaha.Imagine.New.mvp.interactor.ElarbaoonElnwawyInteractor;
import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.MohamedTaha.Imagine.New.mvp.presenter.ElarbaoonElnwawyPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.ElarbaoonElnwawyView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @BindView(R.id.ElarbaoonElnawawyActivitySearch_View)
    MaterialSearchView ElarbaoonElnawawyActivitySearchView;
    @BindView(R.id.ElarbaoonElnawawyActivity_RecyclerView)
    RecyclerView ElarbaoonElnawawyActivityRecyclerView;
    @BindView(R.id.ElarbaoonElnawawyActivity_ProgressBar)
    ProgressBar ElarbaoonElnawawyActivityProgressBar;
    @BindView(R.id.ElarbaoonElnawawyActivity_TV_No_Data)
    TextView ElarbaoonElnawawyActivityTVNoData;
    @BindView(R.id.ElarbaoonElnawawyActivity_FloatingActionButton)
    FloatingActionButton ElarbaoonElnawawyActivityFloatingActionButton;

    // private ActivityElarbaoonElnawawyBinding activityElarbaoonElnawawyBinding;
    private ElarbaoonElnwawyPresenter elarbaoonElnwawyPresenter;
    private List<ElarbaoonElnawawyModel> elnawawyModelList;
    private Menu globalMenu;
    private AdapterElarbaoonElnawawy adapterElarbaoonElnawawy;
    private ElarbaoonElnawawyModel elnawawyModel = new ElarbaoonElnawawyModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elarbaoon_elnawawy);
        ButterKnife.bind(this);
        // activityElarbaoonElnawawyBinding = DataBindingUtil.setContentView(this,R.layout.activity_elarbaoon_elnawawy);
//
//        activityElarbaoonElnawawyBinding = ActivityElarbaoonElnawawyBinding.inflate(getLayoutInflater());
//        View view = activityElarbaoonElnawawyBinding.getRoot();
//        setContentView(view);
        ElarbaoonElnawawyActivityTVElarbaoonElnawawy.setText(getString(R.string.el_arbaoon_elnawawy));
        //  elarbaoonElnwawyPresenter = new ElarbaoonElnwawyInteractor(this, getApplicationContext());
        //elarbaoonElnwawyPresenter = new ElarbaoonElnwawyInteractor(this, getApplicationContext());
        elarbaoonElnwawyPresenter = new ViewModelProvider(this).get(ElarbaoonElnwawyInteractor.class);
        elarbaoonElnwawyPresenter.onBind(this, getApplicationContext());

        custom_toolbar();
        //onClickRecyclerView();
        elarbaoonElnwawyPresenter.getAllData();
        elarbaoonElnwawyPresenter.setOnSearchView(ElarbaoonElnawawyActivitySearchView);
        Log.d("TAG", "Elnawawy");
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 2) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        ElarbaoonElnawawyActivityRecyclerView.setLayoutManager(linearLayoutManager);
        ReScrollUtil reScrollUtil = new ReScrollUtil(ElarbaoonElnawawyActivityFloatingActionButton,
                ElarbaoonElnawawyActivityRecyclerView);
        reScrollUtil.onClickRecyclerView(R.id.ElarbaoonElnawawyActivity_FloatingActionButton);
    }

    @Override
    public void showDataAfterQueryText(List<ElarbaoonElnawawyModel> stringList) {
        elnawawyModelList = stringList;
        adapterElarbaoonElnawawy = new AdapterElarbaoonElnawawy(stringList, new AdapterElarbaoonElnawawy.ClickListener() {
            @Override
            public void onClick(int position) {
                elnawawyModel.setPosition(elnawawyModelList.get(position).getPosition());
                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
                openFragmentElnawary(elnawawyModel);
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
        elarbaoonElnwawyPresenter.onDestroy();
    }

    @Override
    public void showAllElahadeth(List<ElarbaoonElnawawyModel> strings) {
        elnawawyModelList = strings;
        adapterElarbaoonElnawawy = new AdapterElarbaoonElnawawy(elnawawyModelList, new AdapterElarbaoonElnawawy.ClickListener() {
            @Override
            public void onClick(int position) {
                elnawawyModel.setPosition(position);
                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
                openFragmentElnawary(elnawawyModel);
            }
        });
        ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);
        adapterElarbaoonElnawawy.notifyDataSetChanged();
        //For feel when Search
        elarbaoonElnwawyPresenter.setOnQueryTextForElhadeth(ElarbaoonElnawawyActivitySearchView, elnawawyModelList);

    }

    @Override
    public void showAnimation() {
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_fall_dwon);
        ElarbaoonElnawawyActivityRecyclerView.setLayoutAnimation(controller);
        ElarbaoonElnawawyActivityRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void showAfterSearch() {
        adapterElarbaoonElnawawy = new AdapterElarbaoonElnawawy(elnawawyModelList, new AdapterElarbaoonElnawawy.ClickListener() {
            @Override
            public void onClick(int position) {
                elnawawyModel.setPosition(position);
                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
                openFragmentElnawary(elnawawyModel);
            }
        });
        ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);

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
        ElarbaoonElnawawyActivitySearchView.setMenuItem(SearchItem);
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

//    //For Delete those menus from that page
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        globalMenu = menu;
//        globalMenu.findItem(R.id.action_send_us).setVisible(false);
//        globalMenu.findItem(R.id.action_share).setVisible(false);
//        globalMenu.findItem(R.id.action_rate).setVisible(false);
//        globalMenu.findItem(R.id.action_settings).setVisible(false);
//        globalMenu.findItem(R.id.use_way).setVisible(false);
//        globalMenu.findItem(R.id.el_arbaoon_elnawawy).setVisible(false);
//        return super.onPrepareOptionsMenu(globalMenu);
//    }

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
        if (ElarbaoonElnawawyActivitySearchView.isSearchOpen()) {
            ElarbaoonElnawawyActivitySearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
