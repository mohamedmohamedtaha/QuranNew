package com.mohamedtaha.imagine.base

import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.ui.home.viewModel.SwarAndPartsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment:Fragment() {
    internal val viewModel: SwarAndPartsViewModel by viewModels()

    private lateinit var progressBar:View
    fun showProgressBar(){
        progressBar = requireActivity().findViewById(R.id.progressBar) as View
        progressBar.visibility =View.VISIBLE
    }
    fun hidePropgressBar(){
        progressBar = requireActivity().findViewById(R.id.progressBar) as View
        progressBar.visibility = View.GONE
    }
}