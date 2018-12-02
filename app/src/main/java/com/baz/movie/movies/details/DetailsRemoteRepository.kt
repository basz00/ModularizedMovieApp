package com.baz.movie.movies.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baz.movie.extensions.retrofitCallback
import com.baz.movie.movies.data.*
import javax.inject.Inject

internal class DetailsRemoteRepository @Inject constructor(
        private val api: DetailsApi) : DetailsRepository {

    private val detailsResultLiveData = MutableLiveData<DetailsResult>()
    private val videosResultLiveData = MutableLiveData<VideosResult>()
    private val creditsResultLiveData = MutableLiveData<CreditsResult>()

    override fun getDetails(movieId: String): LiveData<DetailsResult> {
        detailsResultLiveData.value = DetailsResult.Progress(true)
        api.getMovieDetails(movieId).enqueue(retrofitCallback(::onDetailsSuccess, ::onDetailsError))
        return detailsResultLiveData
    }

    override fun getVideos(movieId: String): LiveData<VideosResult> {
        videosResultLiveData.value = VideosResult.Progress(true)
        api.getVideos(movieId).enqueue(retrofitCallback(::onVideosSuccess, ::onVideosError))
        return videosResultLiveData
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

    private fun onVideosSuccess(videosResponse: VideosResponse?) {
        videosResultLiveData.value = VideosResult.Progress(false)
        videosResponse?.let {
            videosResultLiveData.value = VideosResult.Success(it)
        }
    }

    private fun onVideosError(error: String) {
        videosResultLiveData.value = VideosResult.Progress(false)
        videosResultLiveData.value = VideosResult.Failed(error)
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