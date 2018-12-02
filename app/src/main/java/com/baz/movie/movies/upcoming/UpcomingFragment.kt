package com.baz.movie.movies.upcoming


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baz.movie.BaseFragment

import com.baz.movie.R
import com.baz.movie.extensions.getViewModel
import com.baz.movie.extensions.observe
import com.baz.movie.movies.data.Movie
import com.baz.movie.movies.data.MovieResult
import com.baz.movie.movies.details.DetailsActivity
import com.baz.movie.movies.upcoming.adapter.UpcomingAdapter
import kotlinx.android.synthetic.main.fragment_upcoming.*
import kotlin.LazyThreadSafetyMode.NONE
import javax.inject.Inject

internal class UpcomingFragment : BaseFragment() {

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory

    private val adapter by lazy(NONE) { UpcomingAdapter(::startDetailsActivity) }
    private val viewModel by lazy(NONE) { getViewModel<UpcomingViewModel>(factory) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        observeViewModel()
        viewModel.getUpcomingMovies()
    }

    private fun observeViewModel() {
        viewModel.movieResultLiveData.observe(this) {
            when (it) {
                is MovieResult.Success -> retrieveMovies(it.movies)
                is MovieResult.Progress -> upcomingSwipeRefreshLayout.isRefreshing = it.loading
                is MovieResult.Failed -> showToast(it.error)
            }
        }
    }

    private fun initViews() {
        upcomingRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        upcomingRecyclerView.setHasFixedSize(true)
        upcomingRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        upcomingRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val gridLayoutManager = (recyclerView.layoutManager as GridLayoutManager)
                    val itemCount = gridLayoutManager.itemCount
                    val lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == (itemCount - 1)) {
                        viewModel.getUpcomingMovies()
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