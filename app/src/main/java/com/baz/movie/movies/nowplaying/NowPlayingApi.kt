package com.baz.movie.movies.nowplaying

import com.baz.movie.movies.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

internal interface NowPlayingApi{

    @GET("movie/now_playing")
    fun nowPlaying(): Call<MovieResponse>
}