package com.baz.movie.movies.details

import androidx.lifecycle.LiveData
import com.baz.movie.movies.data.CreditsResult
import com.baz.movie.movies.data.DetailsResult
import com.baz.movie.movies.data.TrailersResult

internal interface DetailsRepository {

    fun getDetails(movieId: String): LiveData<DetailsResult>

    fun getTrailers(movieId: String): LiveData<TrailersResult>

    fun getCredits(movieId: String): LiveData<CreditsResult>
}