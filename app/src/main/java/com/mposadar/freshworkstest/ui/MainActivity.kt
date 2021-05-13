package com.mposadar.freshworkstest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mposadar.freshworkstest.R
import com.mposadar.freshworkstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = MainViewPagerAdapter(supportFragmentManager, listOf(
                getString(R.string.home),
                getString(R.string.favourites),
        ))

        with(binding) {
            tabLayout.setupWithViewPager(pager)
            pager.adapter = pagerAdapter
        }
    }

    companion object {
        const val HOME_TAB = 0
        const val HOME_FAVORITES = 1
    }
}