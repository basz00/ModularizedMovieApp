package com.baz.movie.movies.popular.module

import com.baz.movie.movies.popular.PopularApi
import com.baz.movie.movies.popular.PopularRemoteRepository
import com.baz.movie.movies.popular.PopularRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class PopularModule {

    @Provides
    fun providesNowPlayingApi(retrofit: Retrofit) = retrofit.create(PopularApi::class.java)

    @Provides
    fun providesNowPlayingRepository(api: PopularApi): PopularRepository {
        return PopularRemoteRepository(api)
    }
}