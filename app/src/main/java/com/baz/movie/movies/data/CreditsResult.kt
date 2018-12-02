package com.baz.movie.movies.data

internal sealed class CreditsResult{

    data class Success(val credits: CreditsResponse) : CreditsResult()

    data class Progress(val loading: Boolean) : CreditsResult()

    data class Failed(val error: String) : CreditsResult()
}