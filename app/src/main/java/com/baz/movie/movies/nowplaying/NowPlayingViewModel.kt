package com.baz.movie.movies.nowplaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

internal class NowPlayingViewModel @Inject constructor(
        private val repository: NowPlayingRepository) : ViewModel() {

    private val inputLiveData = MutableLiveData<Int>()

    private var lastPageLoaded = 1
    private var isRetrieving = false

    internal val movieResultLiveData by lazy(NONE) {
        Transformations.switchMap(inputLiveData, repository::getNowPlayingMovies)
    }

    fun getNowPlayingMovies() {
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