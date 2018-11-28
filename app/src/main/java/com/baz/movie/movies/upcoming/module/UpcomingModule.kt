package com.baz.movie.movies.upcoming.module

import com.baz.movie.movies.upcoming.UpcomingApi
import com.baz.movie.movies.upcoming.UpcomingRemoteRepository
import com.baz.movie.movies.upcoming.UpcomingRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class UpcomingModule {

    @Provides
    fun providesNowPlayingApi(retrofit: Retrofit) = retrofit.create(UpcomingApi::class.java)

    @Provides
    fun providesNowPlayingRepository(api: UpcomingApi): UpcomingRepository {
        return UpcomingRemoteRepository(api)
    }
}