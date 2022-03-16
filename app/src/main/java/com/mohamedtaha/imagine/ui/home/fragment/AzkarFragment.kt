package com.mohamedtaha.imagine.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentAzkarBinding
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity
import com.mohamedtaha.imagine.ui.home.adapter.AdapterForAzkar
import com.mohamedtaha.imagine.ui.home.fragment.SwarFragment.Companion.SAVE_STATE
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class AzkarFragment  //    @Inject
//    AzkarFragmentInteractor presenter;
//    @Inject
//    ReScrollUtil reScrollUtil;
//    @Inject
//    LinearLayoutManager linearLayoutManager;
    : BaseFragment() {
    private lateinit var binding: FragmentAzkarBinding
    private var adapterForAzkar: AdapterForAzkar? = null
    private var modelAzkar: ArrayList<ModelAzkar>? = null
    private var modelAzkarBundle: ArrayList<ModelAzkar>? = null

    @Inject
    lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAzkarBinding.inflate(inflater, container, false)
//        AzkarFragmentComponent azkarFragmentComponent = DaggerAzkarFragmentComponent.builder()
//                .azkarFragmentModule(new AzkarFragmentModule(this, getActivity()))
//                .contextModule(new ContextModule(getActivity()))
//                .rescroUtilModule(new RescroUtilModule(AzkarFragmentFloatingActionButton, AzkarFragmentRecycleView))
//
//                .build();
//        azkarFragmentComponent.inject(this);
//        if (presenter.isNewlyCreated && savedInstanceState != null) {
//            presenter.restoreState(savedInstanceState);
//            Log.i("TAGO", " onSuccess presenter azkar ");
//        }
//        presenter.getAllData();
//        presenter.isNewlyCreated = false;
//      //  presenter.setOnSearchView(searchView);
//        reScrollUtil.onClickRecyclerView(R.id.AzkarFragment_FloatingActionButton);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllAzkar(requireContext())
       binding.viewModel = viewModel
        viewModel.azkar.observe(viewLifecycleOwner){
        binding.AzkarFragmentRecycleView.adapter = AdapterForAzkar(object : ClickListener {
            override fun onClick(view: View?, position: Int) {
                bundle.putString(SAVE_AZKAR, Gson().toJson(it))
                bundle.putInt(SAVE_POTION_AZKAR, position)
                bundle.putBoolean(SAVE_STATE, false)
                val intent = Intent(activity, SwipePagesActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
                requireActivity().overridePendingTransition(
                    R.anim.item_anim_slide_from_top,
                    R.anim.item_anim_no_thing
                )
            }
        })}
    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter.onDestroy();
        Log.i("TAGO", " onDestroy azkar fragment ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            //   presenter.saveState(outState);
        }
    }

//    override fun showAfterQueryText(stringList: ArrayList<ModelAzkar>) {
//        modelAzkar = stringList
//        adapterForAzkar = AdapterForAzkar(stringList) { view, position -> //the data to be based
////                ArrayList<ModelAzkar> data = modelAzkarBundle;
////                RxBusData.sendData(data);
//            bundle!!.putString(SAVE_AZKAR, Gson().toJson(modelAzkarBundle))
//            bundle!!.putInt(SAVE_POTION_AZKAR, modelAzkar!![position].position)
//            bundle!!.putBoolean(SwarFragment.SAVE_STATE, false)
//            val intent = Intent(activity, SwipePagesActivity::class.java)
//            intent.putExtras(bundle!!)
//            startActivity(intent)
//            requireActivity().overridePendingTransition(
//                R.anim.item_anim_slide_from_top,
//                R.anim.item_anim_no_thing
//            )
//        }
//     //   AzkarFragmentRecycleView!!.adapter = adapterForAzkar
//    }
//
//    override fun showAllINameAzkar(strings: ArrayList<ModelAzkar>) {
//        modelAzkarBundle = strings
//        modelAzkar = strings
//        // AzkarFragmentRecycleView.setLayoutManager(linearLayoutManager);
//        adapterForAzkar = AdapterForAzkar(modelAzkar) { view, position ->
//            bundle!!.putString(SAVE_AZKAR, Gson().toJson(modelAzkar))
//            bundle!!.putInt(SAVE_POTION_AZKAR, position)
//            bundle!!.putBoolean(SwarFragment.SAVE_STATE, false)
//            val intent = Intent(activity, SwipePagesActivity::class.java)
//            intent.putExtras(bundle!!)
//            startActivity(intent)
//            requireActivity().overridePendingTransition(
//                R.anim.item_anim_slide_from_top,
//                R.anim.item_anim_no_thing
//            )
//        }
//        //AzkarFragmentRecycleView!!.adapter = adapterForAzkar
//        adapterForAzkar!!.notifyDataSetChanged()
//        //For feel when Search
//        //  presenter.setOnQueryTextForAzkar(searchView, modelAzkar);
//    }
//
//    override fun showAnimation() {
//        //For animation
//        val controller = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_fall_dwon)
//      //  AzkarFragmentRecycleView!!.layoutAnimation = controller
//       // AzkarFragmentRecycleView!!.scheduleLayoutAnimation()
//    }
//
//    override fun showAfterSearch() {
//        adapterForAzkar = AdapterForAzkar(modelAzkar) { view, position ->
//            bundle!!.putString(SAVE_AZKAR, Gson().toJson(modelAzkar))
//            bundle!!.putInt(SAVE_POTION_AZKAR, position)
//            bundle!!.putBoolean(SwarFragment.SAVE_STATE, false)
//            val intent = Intent(activity, SwipePagesActivity::class.java)
//            intent.putExtras(bundle!!)
//            startActivity(intent)
//            requireActivity().overridePendingTransition(
//                R.anim.item_anim_slide_from_top,
//                R.anim.item_anim_no_thing
//            )
//        }
//     //   AzkarFragmentRecycleView!!.adapter = adapterForAzkar
//    }

    companion object {
        const val SAVE_AZKAR = "save_azkar"
        const val SAVE_POTION_AZKAR = "save_poition_azkar"
    }
}