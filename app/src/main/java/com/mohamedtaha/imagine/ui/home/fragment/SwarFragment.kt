package com.mohamedtaha.imagine.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentReadSwarBinding
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity
import com.mohamedtaha.imagine.ui.home.adapter.AdapterForPartsAndSwar
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SwarFragment : BaseFragment() {
    private lateinit var binding: FragmentReadSwarBinding
    private var stateFragment = 0
    @Inject
    lateinit var bundle: Bundle
    private var integersBundle: List<Int>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReadSwarBinding.inflate(inflater, container, false)
        viewModel.addImagesList()
        binding.viewModel = viewModel
        binding.ReadSwarFragmentRecyclerView.adapter = AdapterForPartsAndSwar(
            false, object : ClickListener {
                override fun onClick(view: View?, position: Int) {
                    val bundle = Bundle()
                    viewModel.getPositionForNameSwars(position, bundle)
                    bundle.putIntegerArrayList(SAVE_IMAGES, integersBundle as ArrayList<Int>?)
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

        if (savedInstanceState == null) {
            stateFragment = 0
            Log.d("TAG", "Current fragment  four is :$stateFragment")
        } else {
            stateFragment = savedInstanceState.getInt(SAVE_STATE_FOR_ROTATION)
            Log.d("TAG", "Current fragment  four is :$stateFragment")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllSwar(requireActivity())
        viewModel.allImages.observe(viewLifecycleOwner) {
            integersBundle = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_STATE_FOR_ROTATION, stateFragment)
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