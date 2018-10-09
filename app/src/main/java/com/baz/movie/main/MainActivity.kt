package com.baz.movie.main

import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import com.baz.movie.BaseActivity
import com.baz.movie.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mainViewPagerAdapter: FragmentPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        mainViewPagerAdapter = FragmentPagerAdapter(this, supportFragmentManager)
        mainViewPager.adapter = mainViewPagerAdapter
        mainTabLayout.setupWithViewPager(mainViewPager)
    }
}
