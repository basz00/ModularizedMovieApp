package com.baz.movie.movies.data

internal sealed class VideosResult{

    data class Success(val videos: VideosResponse) : VideosResult()

    data class Progress(val loading: Boolean) : VideosResult()

    data class Failed(val error: String) : VideosResult()
}