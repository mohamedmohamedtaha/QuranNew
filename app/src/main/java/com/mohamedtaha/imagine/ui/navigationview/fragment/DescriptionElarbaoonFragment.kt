package com.mohamedtaha.imagine.ui.navigationview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mohamedtaha.imagine.adapter.PagerAdapterElarbaoonElnawawy
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentDescriptionElarbaoonBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionElarbaoonFragment : BaseFragment() {
    private lateinit var binding: FragmentDescriptionElarbaoonBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDescriptionElarbaoonBinding.inflate(inflater, container, false)
        deleteToolbar()
        val safeArgs: DescriptionElarbaoonFragmentArgs by navArgs()
        val elarbaoonElnawawy = safeArgs.elarbaoonElnawawyModel
        binding.DescriptionElarbaoonFragmentTVNumberElhadeth.text ="${elarbaoonElnawawy.numberElhadeth}"

        binding.DescriptionElarbaoonFragmentTVNameElhadeth.text =
            elarbaoonElnawawy.nameElhadeth
        val pagerAdapterElarbaoonElnawawy = PagerAdapterElarbaoonElnawawy(
            activity, childFragmentManager, elarbaoonElnawawy.position
        )
        binding.DescriptionElarbaoonFragmentViewPager.adapter = pagerAdapterElarbaoonElnawawy
        binding.DescriptionElarbaoonFragmentViewPager.currentItem = 2
        binding.DescriptionElarbaoonFragmentTabLayout.setupWithViewPager(
            binding.DescriptionElarbaoonFragmentViewPager
        )
        return binding.root
    }

}