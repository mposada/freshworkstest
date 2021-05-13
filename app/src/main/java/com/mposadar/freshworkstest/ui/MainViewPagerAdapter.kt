package com.mposadar.freshworkstest.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mposadar.freshworkstest.ui.favorites.FavoritesFragment
import com.mposadar.freshworkstest.ui.home.HomeFragment

class MainViewPagerAdapter(fragmentManager: FragmentManager, private val titles: List<String>):
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = TAB_COUNT

    override fun getItem(position: Int): Fragment {
        return when (position) {
            MainActivity.HOME_TAB -> HomeFragment.newInstance()
            MainActivity.HOME_FAVORITES -> FavoritesFragment.newInstance()
            else -> HomeFragment.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    companion object {
        const val TAB_COUNT = 2
    }
}