package com.baz.movie.app

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthenticationInterceptor : Interceptor {

    private companion object {

        private const val API_KEY = "2846728077248553eda70aff745e453f"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestOriginal = chain.request()
        val httpUrl = requestOriginal.url().newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
        val request = requestOriginal.newBuilder()
                .url(httpUrl)
                .build()
        return chain.proceed(request)
    }
}