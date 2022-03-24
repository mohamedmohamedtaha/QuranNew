package com.mohamedtaha.imagine.base

import android.view.Menu
import androidx.appcompat.widget.Toolbar

interface HasToolbar {
    fun setToolbar(toolbar: Toolbar)
    fun getToolbar():Toolbar
    fun showToolbar()
    fun inflate(menu: Menu)
    fun hideToolbar()
    fun hideYoutubeIcon()
    fun showYoutubeIcon()
    fun hideSearchIcon()
    fun showSearchIcon()
}