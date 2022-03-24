package com.mohamedtaha.imagine.ui.navigationview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentDescriptionElarbaoonBinding
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.ui.navigationview.adapter.PagerAdapterElarbaoonElnawawy
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
        //toolbar.setToolbar(toolbar.getToolbar())
        toolbar.hideToolbar()
        val safeArgs: DescriptionElarbaoonFragmentArgs by navArgs()
        val elarbaoonElnawawy = safeArgs.elarbaoonElnawawyModel
        binding.DescriptionElarbaoonFragmentTVNumberElhadeth.text =
            "${elarbaoonElnawawy.numberElhadeth}"
        binding.DescriptionElarbaoonFragmentTVNameElhadeth.text = elarbaoonElnawawy.nameElhadeth
        setupViewPager(elarbaoonElnawawy)

        return binding.root
    }

    private fun setupViewPager(elarbaoonElnawawyMode: ElarbaoonElnawawyModel) {
        val pagerAdapterElarbaoonElnawawy =
            PagerAdapterElarbaoonElnawawy(this, elarbaoonElnawawyMode)
        binding.DescriptionElarbaoonFragmentViewPager.adapter = pagerAdapterElarbaoonElnawawy
        binding.DescriptionElarbaoonFragmentViewPager.offscreenPageLimit = 1
        binding.DescriptionElarbaoonFragmentViewPager.currentItem = 2
        val titles = arrayOf(
            getString(R.string.translate_elhadeth),
            getString(R.string.description_elhadeth),
            getString(R.string.text_elhadeth)
        )
        TabLayoutMediator(
            binding.DescriptionElarbaoonFragmentTabLayout,
            binding.DescriptionElarbaoonFragmentViewPager
        ) { tab, tabPosition ->
            tab.text = titles[tabPosition]
        }.attach()
    }

}
















