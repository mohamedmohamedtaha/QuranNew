package com.mohamedtaha.imagine.ui.navigationview.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mohamedtaha.imagine.R
import com.mohamedtaha.imagine.databinding.ActivityElarbaoonElnawawyBinding
import com.mohamedtaha.imagine.util.SearchBarUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElarbaoonElnawawyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityElarbaoonElnawawyBinding
    private val navControllerDrawer by lazy { (supportFragmentManager.findFragmentById(R.id.fragmentContainerViewDrawable) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElarbaoonElnawawyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.elarbaoonElnawawyActivityTB)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.elarbaoonElnawawyFragment))
        setupActionBarWithNavController(navControllerDrawer, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navControllerDrawer.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val youTubeItem = menu.findItem(R.id.action_youtube)
        youTubeItem.isVisible = false
        val searchView = searchItem.actionView as SearchView
        SearchBarUtils.setSearchIcons(searchView, R.id.search_button, R.drawable.ic_search)
        SearchBarUtils.setSearchIcons(searchView, R.id.search_close_btn, R.drawable.ic_close_search)
        SearchBarUtils.setSearchIcons(searchView, R.id.search_close_btn, R.drawable.ic_close_search)
        SearchBarUtils.setSearchTextColor(searchView)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
            return true
        }
        return false
    }
}