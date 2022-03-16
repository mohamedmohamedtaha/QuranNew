package com.mohamedtaha.imagine.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.ui.home.adapter.AdapterForPartsAndSwar
import com.mohamedtaha.imagine.databinding.FragmentPartsBinding
import com.mohamedtaha.imagine.mvp.model.ModelSora
import com.mohamedtaha.imagine.mvp.view.PartsFragmentView
import com.mohamedtaha.imagine.ui.activities.SwipePagesActivity
import com.mohamedtaha.imagine.ui.home.fragment.SwarFragment.Companion.SAVE_STATE
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class PartsFragment : BaseFragment(), PartsFragmentView {
    private lateinit var binding: FragmentPartsBinding
    private val name_part: List<ModelSora>? = null
    private lateinit var adapterNamePart: AdapterForPartsAndSwar
    @Inject
    lateinit var bundle :Bundle


    //    @Inject
    //    PartsFragmentsInteractor presenter;
    //    @Inject
    //    ReScrollUtil reScrollUtil;
    private var integers_bundle: List<Int>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPartsBinding.inflate(inflater, container, false)
        viewModel.getAllParts(requireContext())
        binding.viewModel = viewModel
        binding.PartsFragmentRecyclerView.adapter  =
            AdapterForPartsAndSwar(true, object : ClickListener {
                override fun onClick(view: View?, position: Int) {
                    viewModel.getPositionForNameParts(position,bundle)
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

        //        PartsFragmentComponent partsFragmentComponent = DaggerPartsFragmentComponent.builder()
//                .partsFragmentModule(new PartsFragmentModule(this, getActivity()))
//                .rescroUtilModule(new RescroUtilModule(PartsFragmentFloatingActionButton, PartsFragmentRecyclerView))
//                .contextModule(new ContextModule(getActivity()))
//                .build();
//        partsFragmentComponent.inject(this);
       // requireActivity().title = getString(R.string.read_parts)
        bundle = Bundle()
        //        presenter.getAllPartSoura();
//        presenter.getAllImages();
        // presenter.setOnSearchView(searchView);
//        val linearLayoutManager: GridLayoutManager = object : GridLayoutManager(activity, 2) {
//            override fun isLayoutRTL(): Boolean {
//                return true
//            }
//        }
//        binding.PartsFragmentRecyclerView.layoutManager = linearLayoutManager
        //reScrollUtil.onClickRecyclerView(R.id.PartsFragment_FloatingActionButton);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addImagesList()
        viewModel.allImages.observe(viewLifecycleOwner){
            integers_bundle = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //   presenter.onDestroy();
    }

    override fun showAfterSearch() {
//        adapterNamePart = new AdapterForPartsAndSwar(name_part, true, new AdapterForPartsAndSwar.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                //presenter.getPosition(name_part.get(position).getPosition(), bundle);
//                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
//                bundle.putBoolean(SAVE_STATE, true);
//                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
//            }
//        });
//        PartsFragmentRecyclerView.setAdapter(adapterNamePart);
    }

    override fun showAfterQueryText(stringList: List<ModelSora>) {
        //name_part.clear();
//        name_part = stringList;
//        adapterNamePart = new AdapterForPartsAndSwar(stringList, true, new AdapterForPartsAndSwar.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                //presenter.getPosition(name_part.get(position).getPosition(), bundle);
//                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
//                bundle.putBoolean(SAVE_STATE, true);
//                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
//            }
//        });
//        PartsFragmentRecyclerView.setAdapter(adapterNamePart);
    }

    override fun showAllImages(integers: List<Int>) {
        integers_bundle = integers
    }

    override fun isEmpty() {
        //    PartsFragmentTVNoData!!.visibility = View.VISIBLE
        binding.PartsFragmentRecyclerView.visibility = View.GONE
    }

    override fun thereData() {
        //  PartsFragmentTVNoData!!.visibility = View.GONE
        binding.PartsFragmentRecyclerView.visibility = View.VISIBLE
    }

    override fun showProgress() {
        //  PartsFragmentProgressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        //  PartsFragmentProgressBar!!.visibility = View.GONE
    }

    override fun showAllINamePart(strings: List<ModelSora>) {
//        name_part = strings;
//        adapterNamePart = new AdapterForPartsAndSwar(name_part, true, new AdapterForPartsAndSwar.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                //presenter.getPosition(name_part.get(position).getPosition(), bundle);
//                bundle.putIntegerArrayList(SAVE_IMAGES, (ArrayList<Integer>) integers_bundle);
//                bundle.putBoolean(SAVE_STATE, true);
//                Intent intent = new Intent(getActivity(), SwipePagesActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.item_anim_slide_from_top, R.anim.item_anim_no_thing);
//            }
//        });
//        PartsFragmentRecyclerView.setAdapter(adapterNamePart);
//        adapterNamePart.notifyDataSetChanged();
//        //For feel when Search
//       // presenter.setOnQueryText(searchView, name_part);
    }

    override fun showAnimation() {
        //For animation
        val controller = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_fall_dwon)
        binding.PartsFragmentRecyclerView.layoutAnimation = controller
        binding.PartsFragmentRecyclerView.scheduleLayoutAnimation()
    }

    companion object {
        const val SAVE_IMAGES = "save_images"
    }
}