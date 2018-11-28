package com.baz.movie.movies.upcoming

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import javax.inject.Inject

internal class UpcomingViewModel @Inject constructor(
        private val repository: UpcomingRepository): ViewModel() {

    private val inputLiveData = MutableLiveData<Int>()

    private var lastPageLoaded = 1
    private var isRetrieving = false

    internal val movieResultLiveData by lazy(LazyThreadSafetyMode.NONE) {
        Transformations.switchMap(inputLiveData, repository::getUpcomingMovies)
    }

    fun getUpcomingMovies() {
        if (!isRetrieving) {
            inputLiveData.value = lastPageLoaded
            lastPageLoaded++
            isRetrieving = true
        }
    }

    fun allowRetrieve() {
        isRetrieving = false
    }
}