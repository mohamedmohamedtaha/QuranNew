package com.mohamedtaha.imagine.ui.navigationview.fragment.elnawawy

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mohamedtaha.imagine.databinding.FragmentTextElhadethBinding
import com.mohamedtaha.imagine.datastore.Session.TEXT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TextElhadethFragment : Fragment() {
    private lateinit var binding: FragmentTextElhadethBinding

    @Inject
    lateinit var bundle: Bundle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTextElhadethBinding.inflate(inflater, container, false)
        bundle = requireArguments()
        val textElhadeth = Gson().fromJson(bundle.getString(TEXT), String::class.java)
        binding.TextElhadethFragmentTVTextElhaseth.text = textElhadeth
        binding.TextElhadethFragmentTVTextElhaseth.movementMethod = ScrollingMovementMethod()
        return binding.root
    }
}