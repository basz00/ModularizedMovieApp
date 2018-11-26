package com.baz.movie.movies.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.baz.movie.BaseFragment
import kotlin.LazyThreadSafetyMode.NONE

import com.baz.movie.R
import com.baz.movie.extensions.getViewModel
import com.baz.movie.extensions.observe
import com.baz.movie.movies.data.MovieResult
import com.baz.movie.movies.nowplaying.adapter.NowPlayingAdapter
import kotlinx.android.synthetic.main.fragment_now_playing.*
import javax.inject.Inject

internal class NowPlayingFragment : BaseFragment() {

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory

    private val adapter by lazy(NONE) { NowPlayingAdapter() }
    private val viewModel by lazy(NONE) { getViewModel<NowPlayingViewModel>(factory) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        nowPlayingRecyclerView.setHasFixedSize(true)
        nowPlayingRecyclerView.adapter = adapter
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movieResultLiveData.observe(this) {
            when (it) {
                is MovieResult.Success -> ""
                is MovieResult.Progress -> ""
                is MovieResult.Failed -> ""
            }
        }
    }
}