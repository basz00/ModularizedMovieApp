package com.baz.movie.movies.details

import com.baz.movie.movies.data.CreditsResponse
import com.baz.movie.movies.data.DetailsResponse
import com.baz.movie.movies.data.VideosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DetailsApi {

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movieId: String): Call<CreditsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<DetailsResponse>

    @GET("movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id") movieId: String): Call<VideosResponse>
}