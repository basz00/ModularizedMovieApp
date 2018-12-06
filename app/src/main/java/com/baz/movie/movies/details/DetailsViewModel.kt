package com.baz.movie.movies.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

internal class DetailsViewModel @Inject constructor(
        private val repository: DetailsRepository) : ViewModel() {

    private val detailsInputLiveData = MutableLiveData<String>()
    private val trailersInputLiveData = MutableLiveData<String>()
    private val creditsInputLiveData = MutableLiveData<String>()

    internal val detailsResultLiveData by lazy(NONE) {
        Transformations.switchMap(detailsInputLiveData, repository::getDetails)
    }
    internal val trailersResultLiveData by lazy(NONE) {
        Transformations.switchMap(trailersInputLiveData, repository::getTrailers)
    }
    internal val creditsResultLiveData by lazy(NONE) {
        Transformations.switchMap(creditsInputLiveData, repository::getCredits)
    }

    fun getMovieDetails(movieId: String) {
        detailsInputLiveData.value = movieId
        trailersInputLiveData.value = movieId
        creditsInputLiveData.value = movieId
    }
}