package com.mohamedtaha.imagine.ui.navigationview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.base.SearchListener
import com.mohamedtaha.imagine.databinding.FragmentElarbaoonElnawawyBinding
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.ui.home.activity.NavigationDrawaberActivity
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
        toolbar.showToolbar()
        toolbar.hideYoutubeIcon()

        return binding.root
    }

    private fun onclickSearchIcon() {
        (requireActivity() as NavigationDrawaberActivity).setCallbackSearch(object :
            SearchListener {
            override fun onSearch(string: String?) {
                if (!string.isNullOrEmpty() && string.length > 2)
                    viewModelElarbaoon.getElarbaoonElnawawyBySearch(requireContext(), string)
                else if (string.isNullOrEmpty())
                    viewModelElarbaoon.getElarbaoonElnawawy(requireContext())
                binding.viewModel = viewModelElarbaoon
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelElarbaoon.getElarbaoonElnawawy(requireActivity())
        binding.viewModel = viewModelElarbaoon
        adapterElarbaoonElnawawy =
            AdapterElarbaoonElnawawy(object : ClickListener<ElarbaoonElnawawyModel> {
                override fun onClick(view: View?, position: ElarbaoonElnawawyModel) {
                    val elnawawyModel = ElarbaoonElnawawyModel()
                    elnawawyModel.position = position.position
                    elnawawyModel.nameElhadeth = position.nameElhadeth
                    elnawawyModel.numberElhadeth = position.numberElhadeth
                    elnawawyModel.textElhadeth = position.textElhadeth
                    elnawawyModel.translateElhadeth = position.translateElhadeth
                    elnawawyModel.descriptionElhadeth = position.descriptionElhadeth
                    val action =
                        ElarbaoonElnawawyFragmentDirections.actionElarbaoonElnawawyFragmentToDescriptionElarbaoonFragment(
                            elnawawyModel
                        )
                    findNavController().navigate(action)
                }

            })
        binding.ElarbaoonElnawawyActivityRecyclerView.adapter = adapterElarbaoonElnawawy
        onclickSearchIcon()

    }
}