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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.MohamedTaha.Imagine.New.Adapter.AdapterElarbaoonElnawawy;
import com.MohamedTaha.Imagine.New.R;
import com.MohamedTaha.Imagine.New.databinding.ActivityElarbaoonElnawawyBinding;
import com.MohamedTaha.Imagine.New.mvp.interactor.ElarbaoonElnwawyInteractor;
import com.MohamedTaha.Imagine.New.mvp.model.ElarbaoonElnawawyModel;
import com.MohamedTaha.Imagine.New.mvp.presenter.ElarbaoonElnwawyPresenter;
import com.MohamedTaha.Imagine.New.mvp.view.ElarbaoonElnwawyView;
import com.google.gson.Gson;

import java.util.List;

public class ElarbaoonElnawawyActivity extends AppCompatActivity implements ElarbaoonElnwawyView {
    public static final String POSITION = "position";
    public static final String NAME_ELHADETH = "name_elhadeth";
    public static final String NUMBER_ELHADETH = "number_elhadeth";

    private ActivityElarbaoonElnawawyBinding activityElarbaoonElnawawyBinding;
    private ElarbaoonElnwawyPresenter elarbaoonElnwawyPresenter;
    private List<ElarbaoonElnawawyModel> elnawawyModelList;
    private Menu globalMenu;
    private AdapterElarbaoonElnawawy adapterElarbaoonElnawawy;
    private ElarbaoonElnawawyModel elnawawyModel = new ElarbaoonElnawawyModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityElarbaoonElnawawyBinding = ActivityElarbaoonElnawawyBinding.inflate(getLayoutInflater());
        View view = activityElarbaoonElnawawyBinding.getRoot();
        setContentView(view);
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityTVElarbaoonElnawawy.setText(getString(R.string.el_arbaoon_elnawawy));
        elarbaoonElnwawyPresenter = new ElarbaoonElnwawyInteractor(this, getApplicationContext());
        custom_toolbar();
        elarbaoonElnwawyPresenter.getAllData();
        elarbaoonElnwawyPresenter.setOnSearchView(activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivitySearchView);
        Log.d("TAG", "Elnawawy");
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 2) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityRecyclerView.setLayoutManager(linearLayoutManager);
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
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);
    }

    @Override
    public void isEmpty() {
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityTVNoData.setVisibility(View.VISIBLE);
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void thereData() {
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityTVNoData.setVisibility(View.GONE);
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityProgressBar.setVisibility(View.GONE);

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
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);
        adapterElarbaoonElnawawy.notifyDataSetChanged();
        //For feel when Search
        elarbaoonElnwawyPresenter.setOnQueryTextForElhadeth(activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivitySearchView, elnawawyModelList);

    }

    @Override
    public void showAnimation() {
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_fall_dwon);
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityRecyclerView.setLayoutAnimation(controller);
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityRecyclerView.scheduleLayoutAnimation();
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
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);

    }

    public void custom_toolbar() {
        setSupportActionBar(activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivityTB);
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
        activityElarbaoonElnawawyBinding.ElarbaoonElnawawyActivitySearchView.setMenuItem(SearchItem);
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
}
