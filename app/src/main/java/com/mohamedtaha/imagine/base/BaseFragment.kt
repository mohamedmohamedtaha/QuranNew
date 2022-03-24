package com.mohamedtaha.imagine.base

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.ui.home.viewModel.SwarAndPartsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment:Fragment() {
    internal val viewModel: SwarAndPartsViewModel by viewModels()

    private lateinit var progressBar:View
    protected lateinit var toolbar: HasToolbar
    fun showProgressBar(){
        progressBar = requireActivity().findViewById(R.id.MainProgressBar) as View
        progressBar.visibility =View.VISIBLE
    }
    fun hideProgressBar(){
        progressBar = requireActivity().findViewById(R.id.MainProgressBar) as View
        progressBar.visibility = View.GONE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (requireActivity() is HasToolbar)
            toolbar = requireActivity() as HasToolbar
    }

}
















