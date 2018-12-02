package com.baz.movie.app

import com.baz.movie.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.connectTimeout(1, TimeUnit.MINUTES)
        httpClient.writeTimeout(1, TimeUnit.MINUTES)
        httpClient.addInterceptor(loggingInterceptor())
        httpClient.addInterceptor(AuthenticationInterceptor())
        return httpClient.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}