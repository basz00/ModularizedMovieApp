package com.baz.movie.movies.nowplaying

import androidx.lifecycle.LiveData
import com.baz.movie.movies.data.MovieResult

internal interface NowPlayingRepository {

    fun getNowPlayingMovies(): LiveData<MovieResult>
}