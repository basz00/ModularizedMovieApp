package com.baz.movie.movies.popular


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
import com.baz.movie.movies.adapter.MoviesAdapter
import com.baz.movie.movies.data.Movie
import com.baz.movie.movies.data.MovieResult
import kotlinx.android.synthetic.main.fragment_popular.*
import javax.inject.Inject

internal class PopularFragment : BaseFragment() {

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory

    private val adapter by lazy(LazyThreadSafetyMode.NONE) { MoviesAdapter() }
    private val viewModel by lazy(LazyThreadSafetyMode.NONE) { getViewModel<PopularViewModel>(factory) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
        observeViewModel()
        viewModel.getPopularMovies()
    }

    private fun observeViewModel() {
        viewModel.movieResultLiveData.observe(this) {
            when (it) {
                is MovieResult.Success -> retrieveMovies(it.movies)
                is MovieResult.Progress -> popularSwipeRefreshLayout.isRefreshing = it.loading
                is MovieResult.Failed -> showToast(it.error)
            }
        }
    }

    private fun initViews() {
        popularRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        popularRecyclerView.setHasFixedSize(true)
        popularRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        popularRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val gridLayoutManager = (recyclerView.layoutManager as GridLayoutManager)
                    val itemCount = gridLayoutManager.itemCount
                    val lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition()
                    if (lastVisibleItemPosition == (itemCount - 1)) {
                        viewModel.getPopularMovies()
                    }
                }
            }
        })
    }

    private fun retrieveMovies(movies: List<Movie>) {
        viewModel.allowRetrieve()
        adapter.submitList(movies)
    }
}