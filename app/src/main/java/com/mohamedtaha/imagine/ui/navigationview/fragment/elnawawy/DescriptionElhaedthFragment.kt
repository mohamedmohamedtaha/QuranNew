package com.mohamedtaha.imagine.ui.navigationview.fragment.elnawawy

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentDescriptionElhaedthBinding
import com.mohamedtaha.imagine.datastore.Session.TEXT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DescriptionElhaedthFragment : BaseFragment() {
    private lateinit var binding: FragmentDescriptionElhaedthBinding

    @Inject
    lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionElhaedthBinding.inflate(inflater, container, false)
        bundle = requireArguments()
        val descriptionElhadeth = Gson().fromJson(bundle.getString(TEXT), String::class.java)
        binding.DescriptionElhaedthFragmentTVDescriptionElhaseth.text = descriptionElhadeth
        binding.DescriptionElhaedthFragmentTVDescriptionElhaseth.movementMethod =
            ScrollingMovementMethod()
        return binding.root
    }
}