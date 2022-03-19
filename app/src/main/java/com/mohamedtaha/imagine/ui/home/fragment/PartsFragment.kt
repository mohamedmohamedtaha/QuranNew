package com.mohamedtaha.imagine.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentPartsBinding
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity
import com.mohamedtaha.imagine.ui.home.adapter.AdapterForPartsAndSwar
import com.mohamedtaha.imagine.ui.home.fragment.SwarFragment.Companion.SAVE_STATE
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class PartsFragment : BaseFragment() {
    private lateinit var binding: FragmentPartsBinding

    @Inject
    lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPartsBinding.inflate(inflater, container, false)
        viewModel.getAllParts(requireContext())
        binding.viewModel = viewModel

        val adapter = AdapterForPartsAndSwar(true, object : ClickListener<Int> {
            override fun onClick(view: View?, position: Int) {
                viewModel.getPositionForNameParts(position, bundle)
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
        binding.PartsFragmentRecyclerView.adapter = adapter
        showAnimation()

//        val linearLayoutManager: GridLayoutManager = object : GridLayoutManager(activity, 2) {
//            override fun isLayoutRTL(): Boolean {
//                return true
//            }
//        }
//        binding.PartsFragmentRecyclerView.layoutManager = linearLayoutManager
        //reScrollUtil.onClickRecyclerView(R.id.PartsFragment_FloatingActionButton);
        return binding.root
    }
//
//    override fun showAllINamePart(strings: List<ModelSora>) {
////        name_part = strings;
////        adapterNamePart = new AdapterForPartsAndSwar(name_part, true, new AdapterForPartsAndSwar.ClickListener() {
////            @Override
////            public void onClick(View view, int position) {
////                //presenter.getPosition(name_part.get(position).getPosition(), bundle);
////                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
////                bundle.putBoolean(SAVE_STATE, true);
////                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
////                intent.putExtras(bundle);
////                startActivity(intent);
////                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
////            }
////        });
////        PartsFragmentRecyclerView.setAdapter(adapterNamePart);
////        adapterNamePart.notifyDataSetChanged();
////        //For feel when Search
////       // presenter.setOnQueryText(searchView, name_part);
//    }

    fun showAnimation() {
        //For animation
        val controller =
            AnimationUtils.loadLayoutAnimation(requireActivity(), R.anim.layout_fall_dwon)
        binding.PartsFragmentRecyclerView.layoutAnimation = controller
        binding.PartsFragmentRecyclerView.scheduleLayoutAnimation()

    }
}