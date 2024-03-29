package com.mohamedtaha.imagine.ui.navigationview.fragment.elnawawy

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mohamedtaha.imagine.base.BaseFragment
import com.mohamedtaha.imagine.databinding.FragmentTranslateBinding
import com.mohamedtaha.imagine.datastore.Session.TEXT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TranslateElhadethFragment : BaseFragment() {
    private lateinit var binding: FragmentTranslateBinding

    @Inject
    lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTranslateBinding.inflate(inflater, container, false)
        bundle = requireArguments()
        val translateElhadeth = Gson().fromJson(bundle.getString(TEXT), String::class.java)
        binding.TranslateElhadethFragmentTVTranslateElhaseth.text = translateElhadeth
        binding.TranslateElhadethFragmentTVTranslateElhaseth.movementMethod =
            ScrollingMovementMethod()
        return binding.root
    }
}