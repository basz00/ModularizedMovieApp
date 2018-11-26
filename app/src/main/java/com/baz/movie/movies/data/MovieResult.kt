package com.baz.movie.movies.data

internal sealed class MovieResult {

    data class Success(val movies: List<Movie>) : MovieResult()

    data class Progress(val loading: Boolean) : MovieResult()

    data class Failed(val error: String) : MovieResult()
}