package com.mohamedtaha.imagine.ui.navigationview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mohamedtaha.imagine.adapter.AdapterElarbaoonElnawawy
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentElarbaoonElnawawyBinding
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.ui.navigationview.viewmodel.ElarbaoonElnawawyVieWModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElarbaoonElnawawyFragment : BaseFragment() {
    private lateinit var binding: FragmentElarbaoonElnawawyBinding
    private val viewModelElarbaoon: ElarbaoonElnawawyVieWModel by viewModels()
    private var adapterElarbaoonElnawawy: AdapterElarbaoonElnawawy? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentElarbaoonElnawawyBinding.inflate(inflater, container, false)
        showToolbar()
        viewModelElarbaoon.getElarbaoonElnawawy(requireActivity())
        viewModelElarbaoon.elarbaoonElnawawy.observe(viewLifecycleOwner) {
            adapterElarbaoonElnawawy =
                AdapterElarbaoonElnawawy(
                    it
                ) { position ->
                    val elnawawyModel = ElarbaoonElnawawyModel()
                    elnawawyModel.position = position
                    elnawawyModel.nameElhadeth = it[position].nameElhadeth
                    elnawawyModel.numberElhadeth = it[position].numberElhadeth
                    val action =
                        ElarbaoonElnawawyFragmentDirections.actionElarbaoonElnawawyFragment2ToDescriptionElarbaoonFragment(
                            elnawawyModel
                        )
                    findNavController().navigate(action)
                }
            binding.ElarbaoonElnawawyActivityRecyclerView.adapter = adapterElarbaoonElnawawy

        }
        val linearLayoutManager: GridLayoutManager = object : GridLayoutManager(
            requireContext(), 2
        ) {
            override fun isLayoutRTL(): Boolean {
                return true
            }
        }
        binding.ElarbaoonElnawawyActivityRecyclerView.layoutManager = linearLayoutManager
        return binding.root
    }
}