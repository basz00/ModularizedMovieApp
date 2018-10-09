package com.baz.movie.main

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.baz.movie.R
import com.baz.movie.nowplaying.NowPlayingFragment
import com.baz.movie.popular.PopularFragment
import com.baz.movie.upcoming.UpcomingFragment

internal class FragmentPagerAdapter(private val context: Context, manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragments = listOf(NowPlayingFragment(), UpcomingFragment(), PopularFragment())
    private val titleResources = listOf(R.string.nowPlaying, R.string.upcoming, R.string.popular)

    override fun getItem(position: Int) = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): String = context.getString(titleResources[position])
}