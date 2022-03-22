package com.mohamedtaha.imagine.ui.navigationview.fragment.elnawawy

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentDescriptionElhaedthBinding
import com.mohamedtaha.imagine.datastore.Session.POSITION
import com.mohamedtaha.imagine.ui.navigationview.viewmodel.ElarbaoonElnawawyVieWModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DescriptionElhaedthFragment : BaseFragment() {
    private lateinit var binding: FragmentDescriptionElhaedthBinding
    private val elarbaoonElnawawyVieWModel: ElarbaoonElnawawyVieWModel by viewModels()

    @Inject
    lateinit var bundle: Bundle
    private var position_elhadeth = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionElhaedthBinding.inflate(inflater, container, false)

        bundle = requireArguments()
        position_elhadeth = Gson().fromJson(
            bundle.getString(POSITION),
            Int::class.java
        )
        elarbaoonElnawawyVieWModel.getDescriptionElhadeth(requireContext(), position_elhadeth)
        elarbaoonElnawawyVieWModel.descriptionElhadeth.observe(viewLifecycleOwner) {
            binding.DescriptionElhaedthFragmentTVDescriptionElhaseth.text = it
            binding.DescriptionElhaedthFragmentTVDescriptionElhaseth.movementMethod =
                ScrollingMovementMethod()
        }
        return binding.root
    }
}