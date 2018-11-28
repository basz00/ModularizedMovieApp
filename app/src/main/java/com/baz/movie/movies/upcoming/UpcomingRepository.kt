package com.baz.movie.movies.upcoming

import androidx.lifecycle.LiveData
import com.baz.movie.movies.data.MovieResult

internal interface UpcomingRepository{

    fun getUpcomingMovies(page: Int): LiveData<MovieResult>
}