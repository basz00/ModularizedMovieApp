package com.baz.movie.movies.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.baz.movie.extensions.retrofitCallback
import com.baz.movie.movies.data.MovieResult
import com.baz.movie.movies.data.MovieResponse
import javax.inject.Inject

internal class NowPlayingRemoteRepository @Inject constructor(
        private val api: NowPlayingApi) : NowPlayingRepository {

    private val movieResultLiveData = MutableLiveData<MovieResult>()

    override fun getNowPlayingMovies(): LiveData<MovieResult> {
        movieResultLiveData.value = MovieResult.Progress(true)
        api.nowPlaying().enqueue(retrofitCallback(::onSuccess, ::onFailed))
        return movieResultLiveData
    }

    private fun onSuccess(movieResponse: MovieResponse?) {
        movieResultLiveData.value = MovieResult.Progress(false)
        movieResponse?.movies?.let {
            movieResultLiveData.value = MovieResult.Success(it)
        }
    }

    private fun onFailed(error: String) {
        movieResultLiveData.value = MovieResult.Progress(false)
        movieResultLiveData.value = MovieResult.Failed(error)
    }
}