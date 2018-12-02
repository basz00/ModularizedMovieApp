package com.baz.movie.movies.data

sealed class DetailsResult {

    data class Success(val details: DetailsResponse) : DetailsResult()

    data class Progress(val loading: Boolean) : DetailsResult()

    data class Failed(val error: String) : DetailsResult()
}