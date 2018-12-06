package com.baz.movie.movies.data

internal sealed class TrailersResult{

    data class Success(val trailers: List<YoutubeTrailer>) : TrailersResult()

    data class Progress(val loading: Boolean) : TrailersResult()

    data class Failed(val error: String) : TrailersResult()
}