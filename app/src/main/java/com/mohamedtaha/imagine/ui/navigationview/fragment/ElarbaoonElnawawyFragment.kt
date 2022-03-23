package com.mohamedtaha.imagine.ui.navigationview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentElarbaoonElnawawyBinding
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.ui.navigationview.adapter.AdapterElarbaoonElnawawy
import com.mohamedtaha.imagine.ui.navigationview.viewmodel.ElarbaoonElnawawyVieWModel
import com.mohamedtaha.imagine.util.ClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElarbaoonElnawawyFragment : BaseFragment() {
    private lateinit var binding: FragmentElarbaoonElnawawyBinding
    private val viewModelElarbaoon: ElarbaoonElnawawyVieWModel by activityViewModels()
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
                    it, object : ClickListener<Int> {
                        override fun onClick(view: View?, position: Int) {
                            val elnawawyModel = ElarbaoonElnawawyModel()
                            elnawawyModel.position = position
                            elnawawyModel.nameElhadeth = it[position].nameElhadeth
                            elnawawyModel.numberElhadeth = it[position].numberElhadeth
                            elnawawyModel.textElhadeth = it[position].textElhadeth
                            elnawawyModel.translateElhadeth = it[position].translateElhadeth
                            elnawawyModel.descriptionElhadeth = it[position].descriptionElhadeth
                            val action =
                                ElarbaoonElnawawyFragmentDirections.actionElarbaoonElnawawyFragmentToDescriptionElarbaoonFragment(
                                    elnawawyModel
                                )
                            findNavController().navigate(action)
                        }

                    })
            binding.ElarbaoonElnawawyActivityRecyclerView.adapter = adapterElarbaoonElnawawy

        }
//        val linearLayoutManager: GridLayoutManager = object : GridLayoutManager(
//            requireContext(), 2
//        ) {
//            override fun isLayoutRTL(): Boolean {
//                return true
//            }
//        }
//        binding.ElarbaoonElnawawyActivityRecyclerView.layoutManager = linearLayoutManager
        return binding.root
    }
}