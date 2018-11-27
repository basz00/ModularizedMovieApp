package com.baz.movie.movies.nowplaying

import com.baz.movie.movies.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NowPlayingApi {

    @GET("now_playing")
    fun nowPlaying(@Query("page") page: Int): Call<MovieResponse>
}