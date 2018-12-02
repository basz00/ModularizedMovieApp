package com.baz.movie.movies.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baz.movie.BaseFragment
import kotlin.LazyThreadSafetyMode.NONE

import com.baz.movie.R
import com.baz.movie.extensions.getViewModel
import com.baz.movie.extensions.observe
import com.baz.movie.movies.data.Movie
import com.baz.movie.movies.data.MovieResult
import com.baz.movie.movies.details.DetailsActivity
import com.baz.movie.movies.nowplaying.adapter.NowPlayingAdapter
import kotlinx.android.synthetic.main.fragment_now_playing.*
import javax.inject.Inject

internal class NowPlayingFragment : BaseFragment() {

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory

    private val adapter by lazy(NONE) { NowPlayingAdapter(::startDetailsActivity) }
    private val viewModel by lazy(NONE) { getViewModel<NowPlayingViewModel>(factory) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        observeViewModel()
        viewModel.getNowPlayingMovies()
    }

    private fun observeViewModel() {
        viewModel.movieResultLiveData.observe(this) {
            when (it) {
                is MovieResult.Success -> retrieveMovies(it.movies)
                is MovieResult.Progress -> nowPlayingSwipeRefreshLayout.isRefreshing = it.loading
                is MovieResult.Failed -> showToast(it.error)
            }
        }
    }

    private fun initViews() {
        nowPlayingRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        nowPlayingRecyclerView.setHasFixedSize(true)
        nowPlayingRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        nowPlayingRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val gridLayoutManager = (recyclerView.layoutManager as GridLayoutManager)
                    val itemCount = gridLayoutManager.itemCount
                    val lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == (itemCount - 1)) {
                        viewModel.getNowPlayingMovies()
                    }
                }
            }
        })
    }

    private fun retrieveMovies(movies: List<Movie>) {
        viewModel.allowRetrieve()
        adapter.submitList(movies)
    }

    private fun startDetailsActivity(movieId: String) {
        activity?.let { DetailsActivity.invoke(it, movieId) }
    }
}