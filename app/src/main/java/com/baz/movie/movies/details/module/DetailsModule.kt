package com.baz.movie.movies.details.module

import com.baz.movie.movies.details.DetailsApi
import com.baz.movie.movies.details.DetailsRemoteRepository
import com.baz.movie.movies.details.DetailsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class DetailsModule {

    @Provides
    fun providesDetailsApi(retrofit: Retrofit) = retrofit.create(DetailsApi::class.java)

    @Provides
    fun providesDetailsRepository(api: DetailsApi): DetailsRepository {
        return DetailsRemoteRepository(api)
    }
}