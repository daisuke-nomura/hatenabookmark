package com.kyaracter.hatenabookmark.presentation.main

import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.kyaracter.hatenabookmark.data.repository.CATEGORY

class MainPagerAdapter(fm: androidx.fragment.app.FragmentManager) : FragmentStatePagerAdapter(fm) {

    val tabs = mutableListOf("", "")

    val categories = mutableListOf(CATEGORY.GENERAL_HOT_ENTRY, CATEGORY.GENERAL_ENTRY_LIST)

    override fun getItem(position: Int): androidx.fragment.app.Fragment? {
        return when (position) {
            0 -> MainFragment.newInstance(categories[0])
            1 -> MainFragment.newInstance(categories[1])
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> tabs[0]
            1 -> tabs[1]
            else -> null
        }
    }

    fun refresh(categories: List<CATEGORY>) {
        this.categories.clear()
        this.categories.addAll(categories)
        this.notifyDataSetChanged()
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    companion object {
        fun newInstance(fm: androidx.fragment.app.FragmentManager,
                        categories: List<CATEGORY>,
                        tabs: List<String>): MainPagerAdapter {
            val adapter = MainPagerAdapter(fm)
            adapter.apply {
                this.categories.clear()
                this.categories.addAll(categories)
                this.tabs.clear()
                this.tabs.addAll(tabs)
            }
            return adapter
        }
    }
}
