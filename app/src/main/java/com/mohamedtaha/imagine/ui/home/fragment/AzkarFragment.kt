package com.mohamedtaha.imagine.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.base.SearchListener
import com.mohamedtaha.imagine.databinding.FragmentAzkarBinding
import com.mohamedtaha.imagine.mvp.model.ModelAzkar
import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity
import com.mohamedtaha.imagine.ui.home.activity.SwipePagesActivity
import com.mohamedtaha.imagine.ui.home.adapter.AdapterForAzkar
import com.mohamedtaha.imagine.ui.home.fragment.SwarFragment.Companion.SAVE_STATE
import com.mohamedtaha.imagine.ui.home.viewModel.AzkarViewModel
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class AzkarFragment
    : BaseFragment() {
    internal val azkarViewModel: AzkarViewModel by viewModels()

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        azkarViewModel.getAllAzkar(requireContext())
       binding.viewModel = azkarViewModel
        azkarViewModel.azkar.observe(viewLifecycleOwner){
        binding.AzkarFragmentRecycleView.adapter = AdapterForAzkar(object : ClickListener<Int> {
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
        onclickSearchIcon()
    }
    private fun onclickSearchIcon(){
        (requireActivity() as NavigationDrawaberActivity).setCallbackSearch(object : SearchListener{
            override fun onSearch(string: String?) {
                if (!string.isNullOrEmpty() && string.length >2)
                    azkarViewModel.getAllAzkarBySearch(requireContext(),string)
                else if( string.isNullOrEmpty())
                    azkarViewModel.getAllAzkar(requireContext())
                binding.viewModel = azkarViewModel
            }

        })
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