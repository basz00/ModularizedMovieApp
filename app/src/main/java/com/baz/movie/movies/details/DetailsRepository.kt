package com.baz.movie.movies.details

import androidx.lifecycle.LiveData
import com.baz.movie.movies.data.CreditsResult
import com.baz.movie.movies.data.DetailsResult
import com.baz.movie.movies.data.VideosResult

internal interface DetailsRepository {

    fun getDetails(movieId: String): LiveData<DetailsResult>

    fun getVideos(movieId: String): LiveData<VideosResult>

    fun getCredits(movieId: String): LiveData<CreditsResult>
}