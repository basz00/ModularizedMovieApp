package com.baz.movie.movies.popular

import com.baz.movie.movies.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PopularApi{

    @GET("popular")
    fun popular(@Query("page") page: Int): Call<MovieResponse>
}