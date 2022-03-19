package com.mohamedtaha.imagine.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.ui.home.adapter.ImageAdapter
import com.mohamedtaha.imagine.databinding.FragmentSoundBinding
import com.mohamedtaha.imagine.mvp.model.ImageModel
import com.mohamedtaha.imagine.mvp.view.ListSoundReaderView
import com.mohamedtaha.imagine.ui.home.viewModel.SoundViewModel
import com.mohamedtaha.imagine.ui.home.viewModel.SwarAndPartsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by MANASATT on 20/08/17.
 */
@AndroidEntryPoint
class SoundFragment : BaseFragment() {
    internal val soundViewModel: SoundViewModel by activityViewModels()
    private lateinit var binding: FragmentSoundBinding
    private lateinit var imageAdapter: ImageAdapter

    //    @BindView(R.id.Fragment_Sound_RecyclerView)
    //    AutofitRecyclerView FragmentSoundRecyclerView;
    private var imageModel: List<ImageModel>? = null

    //    @Inject
    //    ListSoundReaderInteractor presenter;
    //    @Inject
    //    ReScrollUtil reScrollUtil;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSoundBinding.inflate(inflater, container, false)
        //  presenter.setOnSearchViewListener(searchView);
//        val linearLayoutManager = object : GridLayoutManager(requireActivity(), 2) {
//            override fun isLayoutRTL(): Boolean {
//                return true
//            }
//        }
//        binding.FragmentSoundRecyclerView.setLayoutManager(linearLayoutManager);
//        binding.FragmentSoundRecyclerView.setHasFixedSize(true);
        //reScrollUtil.onClickRecyclerView(R.id.Fragment_Sound_FloatingActionButton);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        soundViewModel.getData(requireContext())
        binding.viewModel = soundViewModel
        soundViewModel.sound.observe(viewLifecycleOwner){
            val imageAdapter = ImageAdapter(requireActivity())
            binding.FragmentSoundRecyclerView.adapter = imageAdapter
            imageAdapter.setData(it)
        }





//        viewModel.sound.observe(viewLifecycleOwner) {
//            imageAdapter = ImageAdapter(it, requireActivity())
//            binding.FragmentSoundRecyclerView.adapter = imageAdapter
//        }
    }
//
//    override fun showAnimation() {
//        //For animation
//        val controller = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_fall_dwon)
//        //        FragmentSoundRecyclerView.setLayoutAnimation(controller);
////        FragmentSoundRecyclerView.scheduleLayoutAnimation();
//    }
}