package com.mohamedtaha.imagine.util

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.mohamedtaha.imagine.R

object SearchBarUtils {
     fun  setSearchIcons(searchView: SearchView, idSearchIcon:Int, drawable:Int){
        //Change icon
        val searchIcon = searchView.findViewById(idSearchIcon) as ImageView
        searchIcon.setImageResource(drawable)

    }
     fun setSearchTextColor(searchView: SearchView){
        //Change color for search icon
        val searchAutoComplete = searchView.findViewById(R.id.search_src_text) as SearchView.SearchAutoComplete
        searchAutoComplete.setHintTextColor(ContextCompat.getColor(searchView.context, android.R.color.white))
        searchAutoComplete.setTextColor(ContextCompat.getColor(searchView.context, android.R.color.white))

    }

}