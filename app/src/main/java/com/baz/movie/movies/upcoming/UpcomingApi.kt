package com.baz.movie.movies.upcoming

import com.baz.movie.movies.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UpcomingApi{

    @GET("movie/upcoming")
    fun upcoming(@Query("page") page: Int): Call<MovieResponse>
}