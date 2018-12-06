package com.baz.movie.movies.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.baz.movie.BaseActivity
import com.baz.movie.R
import com.baz.movie.extensions.getViewModel
import com.baz.movie.extensions.observe
import com.baz.movie.movies.data.*
import com.baz.movie.movies.details.adapter.CastsAdapter
import com.baz.movie.util.ImageLoader
import com.baz.movie.util.ImageUrlHelper
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE
import androidx.recyclerview.widget.GridLayoutManager.HORIZONTAL
import com.baz.movie.movies.details.adapter.TrailersAdapter

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
    private val castsAdapter by lazy(NONE) { CastsAdapter() }
    private val trailersAdapter by lazy(NONE) { TrailersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        observeViewModel()
        initViews()
        viewModel.getMovieDetails(movieId)
    }

    private fun initViews() {
        detailsCastsRecyclerView.layoutManager = GridLayoutManager(this, 1,
                HORIZONTAL, false)
        detailsCastsRecyclerView.setHasFixedSize(true)
        detailsCastsRecyclerView.adapter = castsAdapter
        detailsTrailersRecyclerView.layoutManager = GridLayoutManager(this, 1,
                HORIZONTAL, false)
        detailsTrailersRecyclerView.setHasFixedSize(true)
        detailsTrailersRecyclerView.adapter = trailersAdapter
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
        viewModel.trailersResultLiveData.observe(this) {
            when (it) {
                is TrailersResult.Success -> onTrailersSuccess(it.trailers)
                is TrailersResult.Progress -> onTrailersProgress(it.loading)
                is TrailersResult.Failed -> onTrailersFailed(it.error)
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

    private fun onTrailersSuccess(trailers: List<YoutubeTrailer>) {
        trailersAdapter.submitList(trailers)
    }

    private fun onTrailersProgress(isLoading: Boolean) {

    }

    private fun onTrailersFailed(error: String) {

    }

    private fun onCreditsSuccess(creditsResponse: CreditsResponse) {
        castsAdapter.submitList(creditsResponse.cast)
    }

    private fun onCreditsProgress(isLoading: Boolean) {

    }

    private fun onCreditsFailed(error: String) {

    }
}
