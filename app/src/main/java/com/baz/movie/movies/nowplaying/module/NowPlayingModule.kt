package com.baz.movie.movies.nowplaying.module

import com.baz.movie.movies.nowplaying.NowPlayingApi
import com.baz.movie.movies.nowplaying.NowPlayingRemoteRepository
import com.baz.movie.movies.nowplaying.NowPlayingRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class NowPlayingModule {

    @Provides
    fun providesNowPlayingApi(retrofit: Retrofit) = retrofit.create(NowPlayingApi::class.java)

    @Provides
    fun providesNowPlayingRepository(api: NowPlayingApi): NowPlayingRepository {
        return NowPlayingRemoteRepository(api)
    }
}