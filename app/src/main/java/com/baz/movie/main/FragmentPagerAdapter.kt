package com.baz.movie.main

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.baz.movie.R
import com.baz.movie.movies.nowplaying.NowPlayingFragment
import com.baz.movie.movies.popular.PopularFragment
import com.baz.movie.movies.upcoming.UpcomingFragment

internal class FragmentPagerAdapter(private val context: Context, manager: FragmentManager) : FragmentPagerAdapter(manager) {

//    private val fragments = listOf(NowPlayingFragment(), UpcomingFragment(), PopularFragment())
//    private val titleResources = listOf(R.string.nowPlaying, R.string.upcoming, R.string.popular)
private val fragments = listOf(NowPlayingFragment())
    private val titleResources = listOf(R.string.nowPlaying)

    override fun getItem(position: Int) = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): String = context.getString(titleResources[position])
}