package com.baz.movie.movies.popular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import javax.inject.Inject

internal class PopularViewModel @Inject constructor(
        private val repository: PopularRepository): ViewModel() {

    private val inputLiveData = MutableLiveData<Int>()

    private var lastPageLoaded = 1
    private var isRetrieving = false

    internal val movieResultLiveData by lazy(LazyThreadSafetyMode.NONE) {
        Transformations.switchMap(inputLiveData, repository::getPopularMovies)
    }

    fun getPopularMovies() {
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