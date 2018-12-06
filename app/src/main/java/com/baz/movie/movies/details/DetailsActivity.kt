package com.baz.movie.movies.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.baz.movie.BaseActivity
import com.baz.movie.R
import com.baz.movie.extensions.getViewModel
import com.baz.movie.extensions.observe
import com.baz.movie.movies.data.*
import com.baz.movie.util.ImageLoader
import com.baz.movie.util.ImageUrlHelper
import kotlinx.android.synthetic.main.activity_details.*
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
    private val movieId by lazy(NONE) { intent.getStringExtra(EXTRA_MOVIE_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        observeViewModel()
        viewModel.getMovieDetails(movieId)
    }

    private fun observeViewModel() {
        observeDetails()
        observeVideos()
        observeCredits()
    }

    private fun observeDetails() {
        viewModel.detailsResultLiveData.observe(this) {
            when (it) {
                is DetailsResult.Success -> onDetailsSuccess(it.details)
                is DetailsResult.Progress -> onDetailsProgress(it.loading)
                is DetailsResult.Failed -> onDetailsFailed(it.error)
            }
        }
    }

    private fun observeVideos() {
        viewModel.videosResultLiveData.observe(this) {
            when (it) {
                is VideosResult.Success -> onVideosSuccess(it.videos)
                is VideosResult.Progress -> onVideosProgress(it.loading)
                is VideosResult.Failed -> onVideosFailed(it.error)
            }
        }
    }

    private fun observeCredits() {
        viewModel.creditsResultLiveData.observe(this) {
            when (it) {
                is CreditsResult.Success -> onCreditsSuccess(it.credits)
                is CreditsResult.Progress -> onCreditsProgress(it.loading)
                is CreditsResult.Failed -> onCreditsFailed(it.error)
            }
        }
    }

    private fun onDetailsSuccess(detailsResponse: DetailsResponse) {
        detailsOverviewTextView.text = detailsResponse.overview
        detailsReleaseDateTextView.text = getString(R.string.releaseDate, detailsResponse.releaseDate)
        detailsRatingTextView.text = getString(R.string.rating, detailsResponse.voteAverage.toString())
        detailsCollapsingToolbarLayout.title = detailsResponse.title
        val bannerImageUrl = ImageUrlHelper().generateBannerImageUrl(detailsResponse.backdropPath)
        ImageLoader().loadImage(detailsBannerImageView, bannerImageUrl)
    }

    private fun onDetailsProgress(isLoading: Boolean) {

    }

    private fun onDetailsFailed(error: String) {

    }

    private fun onVideosSuccess(videosResponse: VideosResponse) {

    }

    private fun onVideosProgress(isLoading: Boolean) {

    }

    private fun onVideosFailed(error: String) {

    }

    private fun onCreditsSuccess(creditsResponse: CreditsResponse) {

    }

    private fun onCreditsProgress(isLoading: Boolean) {

    }

    private fun onCreditsFailed(error: String) {

    }
}
