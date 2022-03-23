package com.mohamedtaha.imagine.ui.navigationview.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.Gson
import com.mohamedtaha.imagine.datastore.Session.TEXT
import com.mohamedtaha.imagine.mvp.model.ElarbaoonElnawawyModel
import com.mohamedtaha.imagine.ui.navigationview.fragment.elnawawy.DescriptionElhaedthFragment
import com.mohamedtaha.imagine.ui.navigationview.fragment.elnawawy.TextElhadethFragment
import com.mohamedtaha.imagine.ui.navigationview.fragment.elnawawy.TranslateElhadethFragment

class PagerAdapterElarbaoonElnawawy(
    fm: Fragment,
    private val elarbaoonElnawawyModel: ElarbaoonElnawawyModel
) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        return when (position) {
            0 -> {
                bundle.putString(TEXT, Gson().toJson(elarbaoonElnawawyModel.translateElhadeth))
                val translateElhadethFragment = TranslateElhadethFragment()
                translateElhadethFragment.arguments = bundle
                translateElhadethFragment
            }
            1 -> {
                bundle.putString(TEXT, Gson().toJson(elarbaoonElnawawyModel.descriptionElhadeth))
                val descriptionElhaedthFragment = DescriptionElhaedthFragment()
                descriptionElhaedthFragment.arguments = bundle
                descriptionElhaedthFragment
            }
            else -> {
                bundle.putString(TEXT, Gson().toJson(elarbaoonElnawawyModel.textElhadeth))
                val textElhadethFragment = TextElhadethFragment()
                textElhadethFragment.arguments = bundle
                textElhadethFragment
            }
        }
    }
}