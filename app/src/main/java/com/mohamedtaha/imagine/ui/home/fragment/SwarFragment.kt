package com.mohamedtaha.imagine.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentReadSwarBinding
import com.mohamedtaha.imagine.helper.images.getPositionForNameSwars
import com.mohamedtaha.imagine.mvp.model.ModelSora
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity
import com.mohamedtaha.imagine.ui.home.adapter.AdapterForPartsAndSwar
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SwarFragment : BaseFragment() {
    private lateinit var binding: FragmentReadSwarBinding
    private var state_fragment = 0
    @Inject
    lateinit var bundle: Bundle
    private var name_swar: List<ModelSora>? = null
    private var integers_bundle: List<Int>? = null
    private lateinit var adapterForPartsAndSwar: AdapterForPartsAndSwar

    //    @Inject
    //    SwarFragmentInteractor presenter;
    //    @Inject
    //    ReScrollUtil reScrollUtil;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReadSwarBinding.inflate(inflater, container, false)

        //
//        SwarFragmentComponent swarFragmentComponent = DaggerSwarFragmentComponent.builder()
//                .swarFragmentModule(new SwarFragmentModule(this,getActivity()))
//                .rescroUtilModule(new RescroUtilModule(ReadSwarFragmentFloatingActionButton, ReadSwarFragmentRecyclerView))
//                .contextModule(new ContextModule(getActivity()))
//                .build();
//        swarFragmentComponent.inject(this);
        //  requireActivity().title = getString(R.string.readswar)
        retainInstance = true
        if (savedInstanceState == null) {
            state_fragment = 0
            Log.d("TAG", "Current fragment  four is :$state_fragment")
        } else {
            state_fragment = savedInstanceState.getInt(SAVE_STATE_FOR_ROTATION)
            Log.d("TAG", "Current fragment  four is :$state_fragment")
        }
        //        presenter.getAllNameSour();
//        presenter.getAllImages();
        //  presenter.setOnSearchView(searchView);
        val linearLayoutManager: GridLayoutManager = object : GridLayoutManager(activity, 2) {
            override fun isLayoutRTL(): Boolean {
                return true
            }
        }
        binding.ReadSwarFragmentRecyclerView.layoutManager = linearLayoutManager
        // reScrollUtil.onClickRecyclerView(R.id.ReadSwarFragment_FloatingActionButton);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllSwar(requireActivity())
        viewModel.addImagesList()
        viewModel.allImages.observe(viewLifecycleOwner){
            integers_bundle = it
        }
        viewModel.modelSora.observe(viewLifecycleOwner) {
            adapterForPartsAndSwar = AdapterForPartsAndSwar(
                false, object : ClickListener {
                    override fun onClick(view: View?, position: Int) {
                        val bundle = Bundle()
                       // integers_bundle = getPositionForNameSwars(position,bundle)
                        viewModel.getPositionForNameSwars(position,bundle)
                        bundle.putIntegerArrayList(SAVE_IMAGES, integers_bundle as ArrayList<Int>?)
                        bundle.putBoolean(SAVE_STATE, true)
                        val intent = Intent(requireActivity(), SwipePagesActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                        requireActivity().overridePendingTransition(
                            R.anim.item_anim_slide_from_top,
                            R.anim.item_anim_no_thing
                        )
                    }

                })
            binding.ReadSwarFragmentRecyclerView.adapter = adapterForPartsAndSwar
            adapterForPartsAndSwar.submitList(it)
            Log.d("MOHArr", "it ${it}")


        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_STATE_FOR_ROTATION, state_fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        //presenter.onDestroy();
    }

//    override fun showAllINameSour(strings: List<ModelSora>) {
////        name_swar = strings
////        adapterForPartsAndSwar = AdapterForPartsAndSwar(
////            name_swar,
////            false
////        ) { view, position -> //presenter.getPosition(name_swar.get(position).getPosition(), bundle);
////            bundle!!.putIntegerArrayList(SAVE_IMAGES, integers_bundle as ArrayList<Int>?)
////            bundle!!.putBoolean(SAVE_STATE, true)
////            val intent = Intent(activity, SwipePagesActivity::class.java)
////            intent.putExtras(bundle!!)
////            startActivity(intent)
////            requireActivity().overridePendingTransition(
////                R.anim.item_anim_slide_from_top,
////                R.anim.item_anim_no_thing
////            )
////        }
////        binding.ReadSwarFragmentRecyclerView.adapter = adapterForPartsAndSwar
////        adapterForPartsAndSwar!!.notifyDataSetChanged()
////        //For feel when Search
////        // presenter.setOnQueryText(searchView, name_swar);
//    }
//
//    override fun showAllImages(integers: List<Int>) {
//        integers_bundle = integers
//    }

//    override fun showAnimation() {
//        //For animation
//        val controller = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_fall_dwon)
//        binding.ReadSwarFragmentRecyclerView.layoutAnimation = controller
//        binding.ReadSwarFragmentRecyclerView.scheduleLayoutAnimation()
//    }

    companion object {
        const val SAVE_IMAGES = "save_images"
        const val SAVE_STATE = "save_state"
        private const val SAVE_STATE_FOR_ROTATION = "save_sate_for_rotation"
    }
}