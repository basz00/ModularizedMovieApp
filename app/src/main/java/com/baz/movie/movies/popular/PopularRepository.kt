package com.baz.movie.movies.popular

import androidx.lifecycle.LiveData
import com.baz.movie.movies.data.MovieResult

internal interface PopularRepository{

    fun getPopularMovies(page: Int): LiveData<MovieResult>
}