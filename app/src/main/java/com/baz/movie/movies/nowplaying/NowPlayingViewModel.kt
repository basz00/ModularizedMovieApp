package com.baz.movie.movies.nowplaying

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

internal class NowPlayingViewModel @Inject constructor(
        private val repository: NowPlayingRepository) : ViewModel() {

    private val trigger = AtomicInteger()
    private val inputLiveData = MutableLiveData<Int>()

    internal val movieResultLiveData by lazy(NONE) {
        Transformations.switchMap(inputLiveData) { repository.getNowPlayingMovies() }
    }

    fun getNowPlayingMoies() {
        inputLiveData.value = trigger.incrementAndGet()
    }
}