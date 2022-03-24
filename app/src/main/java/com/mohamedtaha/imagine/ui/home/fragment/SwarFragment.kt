package com.mohamedtaha.imagine.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.base.SearchListener
import com.mohamedtaha.imagine.databinding.FragmentReadSwarBinding
import com.mohamedtaha.imagine.mvp.model.ModelSora
import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity
import com.mohamedtaha.imagine.ui.home.activity.SwipePagesActivity
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReadSwarBinding.inflate(inflater, container, false)
        viewModel.getAllSwar(requireActivity())
        binding.viewModel = viewModel
        binding.ReadSwarFragmentRecyclerView.adapter = AdapterForPartsAndSwar(
            false, object : ClickListener<ModelSora> {
                override fun onClick(view: View?, position: ModelSora) {
                    val bundle = Bundle()
                    viewModel.getPositionForNameSwars(position.position, bundle)
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

        showAnimation()
        if (savedInstanceState == null) {
            stateFragment = 0
            Log.d("TAG", "Current fragment  four is :$stateFragment")
        } else {
            stateFragment = savedInstanceState.getInt(SAVE_STATE_FOR_ROTATION)
            Log.d("TAG", "Current fragment  four is :$stateFragment")
        }

        //Search icon
        (requireActivity() as NavigationDrawaberActivity).setCallbackSearch(object :
            SearchListener {
            override fun onSearch(string: String?) {
                if (!string.isNullOrEmpty() && string.length >= 2)
                viewModel.getSwarBySearch(requireContext(), string)
                else if(string.isNullOrEmpty())
                    viewModel.getAllSwar(requireContext())
                binding.viewModel = viewModel
            }

        })
        return binding.root
    }

    private fun showAnimation() {
        val controller =
            AnimationUtils.loadLayoutAnimation(requireActivity(), R.anim.layout_fall_dwon)
        binding.ReadSwarFragmentRecyclerView.layoutAnimation = controller
        binding.ReadSwarFragmentRecyclerView.scheduleLayoutAnimation()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_STATE_FOR_ROTATION, stateFragment)
    }
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