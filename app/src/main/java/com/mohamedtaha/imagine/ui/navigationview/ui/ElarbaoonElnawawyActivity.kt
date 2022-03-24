package com.mohamedtaha.imagine.ui.navigationview.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.base.HasToolbar
import com.mohamedtaha.imagine.base.SearchListener
import com.mohamedtaha.imagine.databinding.ActivityElarbaoonElnawawyBinding
import com.mohamedtaha.imagine.util.SearchBarUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElarbaoonElnawawyActivity : AppCompatActivity(), HasToolbar {
    private var mMenu: Menu? = null
    private var searchListener: SearchListener? = null
    private lateinit var binding: ActivityElarbaoonElnawawyBinding
    private val navControllerDrawer by lazy { (supportFragmentManager.findFragmentById(R.id.fragmentContainerViewDrawable) as NavHostFragment).navController }
    fun setCallbackSearchListener(searchListener: SearchListener) {
        this.searchListener = searchListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElarbaoonElnawawyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.elarbaoonElnawawyActivityTB)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.elarbaoonElnawawyFragment))
        setupActionBarWithNavController(navControllerDrawer, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navControllerDrawer.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        inflate(menu)
        hideYoutubeIcon()
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchListener?.onSearch(newText)
                return true
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return false
    }

    override fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.menu)
    }

    override fun getToolbar(): Toolbar {
        return binding.elarbaoonElnawawyActivityTB
    }

    override fun inflate(menu: Menu) {
        mMenu = menu
        binding.elarbaoonElnawawyActivityTB.inflateMenu(R.menu.menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        SearchBarUtils.setSearchIcons(searchView, R.id.search_button, R.drawable.ic_search)
        SearchBarUtils.setSearchIcons(searchView, R.id.search_close_btn, R.drawable.ic_close_search)
        SearchBarUtils.setSearchIcons(searchView, R.id.search_close_btn, R.drawable.ic_close_search)
        SearchBarUtils.setSearchTextColor(searchView)
    }

    override fun showToolbar() {
        binding.elarbaoonElnawawyActivityTB.visibility = View.VISIBLE
    }

    override fun hideToolbar() {
        binding.elarbaoonElnawawyActivityTB.visibility = View.GONE
    }

    override fun hideYoutubeIcon() {
        mMenu?.findItem(R.id.action_youtube)?.isVisible = false
    }

    override fun showYoutubeIcon() {
        mMenu?.findItem(R.id.action_youtube)?.isVisible = true
    }

    override fun hideSearchIcon() {
        mMenu?.findItem(R.id.action_search)?.isVisible = false
    }

    override fun showSearchIcon() {
        mMenu?.findItem(R.id.action_search)?.isVisible = true
    }
}