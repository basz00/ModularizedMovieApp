package com.baz.movie.movies.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baz.movie.extensions.retrofitCallback
import com.baz.movie.movies.data.*
import javax.inject.Inject

internal class DetailsRemoteRepository @Inject constructor(
        private val api: DetailsApi) : DetailsRepository {

    private val detailsResultLiveData = MutableLiveData<DetailsResult>()
    private val trailersResultLiveData = MutableLiveData<TrailersResult>()
    private val creditsResultLiveData = MutableLiveData<CreditsResult>()

    override fun getDetails(movieId: String): LiveData<DetailsResult> {
        detailsResultLiveData.value = DetailsResult.Progress(true)
        api.getMovieDetails(movieId).enqueue(retrofitCallback(::onDetailsSuccess, ::onDetailsError))
        return detailsResultLiveData
    }

    override fun getTrailers(movieId: String): LiveData<TrailersResult> {
        trailersResultLiveData.value = TrailersResult.Progress(true)
        api.getVideos(movieId).enqueue(retrofitCallback(::onTrailersSuccess, ::onTrailersError))
        return trailersResultLiveData
    }

    override fun getCredits(movieId: String): LiveData<CreditsResult> {
        creditsResultLiveData.value = CreditsResult.Progress(true)
        api.getMovieCredits(movieId).enqueue(retrofitCallback(::onCreditsSuccess, ::onCreditsError))
        return creditsResultLiveData
    }

    private fun onDetailsSuccess(detailsResponse: DetailsResponse?) {
        detailsResultLiveData.value = DetailsResult.Progress(false)
        detailsResponse?.let {
            detailsResultLiveData.value = DetailsResult.Success(it)
        }
    }

    private fun onDetailsError(error: String) {
        detailsResultLiveData.value = DetailsResult.Progress(false)
        detailsResultLiveData.value = DetailsResult.Failed(error)
    }

    private fun onTrailersSuccess(videosResponse: VideosResponse?) {
        trailersResultLiveData.value = TrailersResult.Progress(false)
        videosResponse?.let { it ->
            val trailers = it.results.map {
                YoutubeTrailer(it.id, it.key, it.name)
            }
            trailersResultLiveData.value = TrailersResult.Success(trailers)
        }
    }

    private fun onTrailersError(error: String) {
        trailersResultLiveData.value = TrailersResult.Progress(false)
        trailersResultLiveData.value = TrailersResult.Failed(error)
    }

    private fun onCreditsSuccess(creditsResponse: CreditsResponse?) {
        creditsResultLiveData.value = CreditsResult.Progress(false)
        creditsResponse?.let {
            creditsResultLiveData.value = CreditsResult.Success(it)
        }
    }

    private fun onCreditsError(error: String) {
        creditsResultLiveData.value = CreditsResult.Progress(false)
        creditsResultLiveData.value = CreditsResult.Failed(error)
    }
}