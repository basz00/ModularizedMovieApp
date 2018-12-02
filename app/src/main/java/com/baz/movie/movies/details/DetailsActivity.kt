package com.baz.movie.movies.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baz.movie.BaseActivity
import com.baz.movie.R
import com.baz.movie.extensions.getViewModel
import com.baz.movie.extensions.observe
import com.baz.movie.movies.data.CreditsResult
import com.baz.movie.movies.data.DetailsResult
import com.baz.movie.movies.data.VideosResult
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

internal class DetailsActivity : BaseActivity() {

    companion object {

        private const val EXTRA_MOVIE_ID = "extraMovieId"

        fun invoke(context: Context, movieId: String) {
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            }
            context.startActivity(intent)
        }
    }

    @Inject
    internal lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy(NONE) { getViewModel<DetailsViewModel>(factory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        observeViewModel()
    }

    private fun observeViewModel() {
        observeDetails()
        observeVideos()
        observeCredits()
    }

    private fun observeDetails() {
        viewModel.detailsResultLiveData.observe(this) {
            when (it) {
                is DetailsResult.Success -> ""
                is DetailsResult.Progress -> ""
                is DetailsResult.Failed -> ""
            }
        }
    }

    private fun observeVideos() {
        viewModel.videosResultLiveData.observe(this) {
            when (it) {
                is VideosResult.Success -> ""
                is VideosResult.Progress -> ""
                is VideosResult.Failed -> ""
            }
        }
    }

    private fun observeCredits() {
        viewModel.creditsResultLiveData.observe(this) {
            when (it) {
                is CreditsResult.Success -> ""
                is CreditsResult.Progress -> ""
                is CreditsResult.Failed -> ""
            }
        }
    }

}
