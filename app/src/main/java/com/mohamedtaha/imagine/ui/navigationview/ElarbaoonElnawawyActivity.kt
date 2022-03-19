package com.mohamedtaha.imagine.ui.navigationview

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.adapter.AdapterElarbaoonElnawawy
import com.mohamedtaha.imagine.databinding.ActivityElarbaoonElnawawyBinding
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.ui.activities.ContianerDescriptionElnawawyActivity
import com.mohamedtaha.imagine.ui.home.viewModel.ElarbaoonElnawawyVieWModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElarbaoonElnawawyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityElarbaoonElnawawyBinding
    private val viewModel: ElarbaoonElnawawyVieWModel by viewModels()

    //    @Inject
    //    ElarbaoonElnwawyInteractor elarbaoonElnwawyPresenter;
    //    @Inject
    //    ReScrollUtil reScrollUtil;
    //    private List<ElarbaoonElnawawyModel> elnawawyModelList;
    //    @Inject
    //    ElarbaoonElnawawyModel elnawawyModel;
    private val globalMenu: Menu? = null
    private var adapterElarbaoonElnawawy: AdapterElarbaoonElnawawy? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElarbaoonElnawawyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getElarbaoonElnawawy(this)
        viewModel.elarbaoonElnawawy.observe(this){
                    adapterElarbaoonElnawawy =  AdapterElarbaoonElnawawy(it, object: AdapterElarbaoonElnawawy.ClickListener {
                        override fun onClick(position: Int) {
                            val elnawawyModel = ElarbaoonElnawawyModel()
                                elnawawyModel.position = position
                                elnawawyModel.nameElhadeth = it[position].nameElhadeth
                                elnawawyModel.numberElhadeth = it[position].numberElhadeth
                                openFragmentElnawary(elnawawyModel);


                        }

                    })
            binding.ElarbaoonElnawawyActivityRecyclerView.adapter = adapterElarbaoonElnawawy

        }
        //        ElarbaoonElnawawyComponent elarbaoonElnawawyComponent = DaggerElarbaoonElnawawyComponent.builder()
//                .elarbaoonElnawawyModule(new ElarbaoonElnawawyModule(this, this))
//                .rescroUtilModule(new RescroUtilModule(ElarbaoonElnawawyActivityFloatingActionButton, ElarbaoonElnawawyActivityRecyclerView))
//                .build();
//        elarbaoonElnawawyComponent.inject(this);
//        ElarbaoonElnawawyActivityTVElarbaoonElnawawy!!.text =
//            getString(R.string.el_arbaoon_elnawawy)
        custom_toolbar()
        //   elarbaoonElnwawyPresenter.getAllData();
        //elarbaoonElnwawyPresenter.setOnSearchView(ElarbaoonElnawawyActivitySearchView);
        Log.d("TAG", "Elnawawy")
        val linearLayoutManager: GridLayoutManager = object : GridLayoutManager(
            applicationContext, 2
        ) {
            override fun isLayoutRTL(): Boolean {
                return true
            }
        }
        binding.ElarbaoonElnawawyActivityRecyclerView.layoutManager = linearLayoutManager
        //    reScrollUtil.onClickRecyclerView(R.id.ElarbaoonElnawawyActivity_FloatingActionButton);
    }


//    override fun showDataAfterQueryText(stringList: List<ElarbaoonElnawawyModel>) {
//        //   elnawawyModelList = stringList;
//        adapterElarbaoonElnawawy = AdapterElarbaoonElnawawy(stringList) {
//            //                elnawawyModel.setPosition(elnawawyModelList.get(position).getPosition());
////                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
////                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
////                openFragmentElnawary(elnawawyModel);
//        }
//        binding.ElarbaoonElnawawyActivityRecyclerView.adapter = adapterElarbaoonElnawawy
//    }

//    override fun isEmpty() {
//        binding.ElarbaoonElnawawyActivityTVNoData.visibility = View.VISIBLE
//        binding.ElarbaoonElnawawyActivityRecyclerView.visibility = View.GONE
//    }
//
//    override fun thereData() {
//        binding.ElarbaoonElnawawyActivityTVNoData.visibility = View.GONE
//        binding.ElarbaoonElnawawyActivityRecyclerView.visibility = View.VISIBLE
//    }
//
//    override fun showProgress() {
//        binding.ElarbaoonElnawawyActivityProgressBar.visibility = View.VISIBLE
//    }
//
//    override fun hideProgress() {
//        binding.ElarbaoonElnawawyActivityProgressBar.visibility = View.GONE
//    }

    override fun onDestroy() {
        super.onDestroy()
        // elarbaoonElnwawyPresenter.onDestroy();
    }

//    override fun showAllElahadeth(strings: List<ElarbaoonElnawawyModel>) {
////        elnawawyModelList = strings;
////        adapterElarbaoonElnawawy = new AdapterElarbaoonElnawawy(elnawawyModelList, new AdapterElarbaoonElnawawy.ClickListener() {
////            @Override
////            public void onClick(int position) {
////                elnawawyModel.setPosition(position);
////                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
////                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
////                openFragmentElnawary(elnawawyModel);
////            }
////        });
//        binding.ElarbaoonElnawawyActivityRecyclerView.adapter = adapterElarbaoonElnawawy
//        adapterElarbaoonElnawawy!!.notifyDataSetChanged()
//        //For feel when Search
//        //   elarbaoonElnwawyPresenter.setOnQueryTextForElhadeth(ElarbaoonElnawawyActivitySearchView, elnawawyModelList);
//    }
//
//    override fun showAnimation() {
//        val controller = AnimationUtils.loadLayoutAnimation(
//            applicationContext, R.anim.layout_fall_dwon
//        )
//        binding.ElarbaoonElnawawyActivityRecyclerView.layoutAnimation = controller
//        binding.ElarbaoonElnawawyActivityRecyclerView.scheduleLayoutAnimation()
//    }
//
//    override fun showAfterSearch() {
////        adapterElarbaoonElnawawy = new AdapterElarbaoonElnawawy(elnawawyModelList, new AdapterElarbaoonElnawawy.ClickListener() {
////            @Override
////            public void onClick(int position) {
////                elnawawyModel.setPosition(position);
////                elnawawyModel.setName_elhadeth(elnawawyModelList.get(position).getName_elhadeth());
////                elnawawyModel.setNumber_elhadeth(elnawawyModelList.get(position).getNumber_elhadeth());
////                openFragmentElnawary(elnawawyModel);
////            }
////        });
////        ElarbaoonElnawawyActivityRecyclerView.setAdapter(adapterElarbaoonElnawawy);
//    }

    fun custom_toolbar() {
        setSupportActionBar(binding.ElarbaoonElnawawyActivityTB)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        //for delete label for Activity
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val SearchItem = menu.findItem(R.id.action_search)
        //ElarbaoonElnawawyActivitySearchView.setMenuItem(SearchItem);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return false
    }

    private fun openFragmentElnawary(elnawawyModel: ElarbaoonElnawawyModel) {
        val startActivity =
            Intent(applicationContext, ContianerDescriptionElnawawyActivity::class.java)
        startActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity.putExtra(POSITION, Gson().toJson(elnawawyModel))
        startActivity(startActivity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }
    }

    override fun onBackPressed() {
//        if (ElarbaoonElnawawyActivitySearchView.isSearchOpen()) {
//            ElarbaoonElnawawyActivitySearchView.closeSearch();
//        } else {
//            super.onBackPressed();
//        }
    }

    companion object {
        const val POSITION = "position"
        const val NAME_ELHADETH = "name_elhadeth"
        const val NUMBER_ELHADETH = "number_elhadeth"
    }
}