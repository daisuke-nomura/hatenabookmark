package com.kyaracter.hatenabookmark.presentation.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.kyaracter.hatenabookmark.R
import com.kyaracter.hatenabookmark.data.repository.CATEGORY
import com.kyaracter.hatenabookmark.databinding.MainActivityBinding
import com.kyaracter.hatenabookmark.presentation.detail.DetailActivity
import com.kyaracter.hatenabookmark.presentation.detail.PARAM_URL
import com.kyaracter.hatenabookmark.presentation.main.entity.RssItem
import com.kyaracter.hatenabookmark.util.viewModelOf
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.pager_fragment.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: MainActivityBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: MainPagerAdapter

    private var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        setSupportActionBar(toolbar)

        viewModel = viewModelOf(viewModelFactory).get(MainViewModel::class.java)

        adapter = MainPagerAdapter.newInstance(supportFragmentManager,
                listOf(CATEGORY.GENERAL_HOT_ENTRY, CATEGORY.GENERAL_ENTRY_LIST),
                listOf(getString(R.string.tab1), getString(R.string.tab2)))
        pager.adapter = adapter

        tab_layout.setupWithViewPager(pager)
        tab_layout.tabGravity = com.google.android.material.tabs.TabLayout.GRAVITY_FILL
        tab_layout.tabMode = com.google.android.material.tabs.TabLayout.MODE_FIXED

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.isDrawerSlideAnimationEnabled = false
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()

        if (isFirst) {
            Handler().postDelayed({
                onNavigationItemSelected(nav_view.menu.getItem(0))
            }, 100)
            isFirst = false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_general -> {
                change(listOf(CATEGORY.GENERAL_HOT_ENTRY, CATEGORY.GENERAL_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_general)
            }
            R.id.nav_social -> {
                change(listOf(CATEGORY.SOCIAL_HOT_ENTRY, CATEGORY.SOCIAL_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_social)
            }
            R.id.nav_economics -> {
                change(listOf(CATEGORY.ECONOMICS_HOT_ENTRY, CATEGORY.ECONOMICS_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_economics)
            }
            R.id.nav_life -> {
                change(listOf(CATEGORY.LIFE_HOT_ENTRY, CATEGORY.LIFE_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_life)
            }
            R.id.nav_knowledge -> {
                change(listOf(CATEGORY.KNOWLEDGE_HOT_ENTRY, CATEGORY.KNOWLEDGE_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_knowledge)
            }
            R.id.nav_it -> {
                change(listOf(CATEGORY.IT_HOT_ENTRY, CATEGORY.IT_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_it)
            }
            R.id.nav_entertainment -> {
                change(listOf(CATEGORY.ENTERTAINMENT_HOT_ENTRY, CATEGORY.ENTERTAINMENT_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_entertainment)
            }
            R.id.nav_game -> {
                change(listOf(CATEGORY.GAME_HOT_ENTRY, CATEGORY.GAME_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_game)
            }
            R.id.nav_fun -> {
                change(listOf(CATEGORY.FUN_HOT_ENTRY, CATEGORY.FUN_ENTRY_LIST))
                toolbar.title = this.resources.getString(R.string.menu_fun)
            }
//            R.id.nav_video -> {
//                change(listOf(CATEGORY.SOCIAL_HOT_ENTRY, CATEGORY.SOCIAL_ENTRY_LIST))
//            }
//            R.id.nav_setting -> {
//
//            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun change(categories: List<CATEGORY>) {
        adapter.refresh(categories)
        pager.currentItem = 0
    }

    fun show(rssItem: RssItem) {
        startActivity(Intent(this, DetailActivity::class.java).apply {
            putExtra(PARAM_URL, rssItem.url)
        })
    }
}
