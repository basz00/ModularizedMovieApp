package com.baz.movie.app

import okhttp3.Interceptor
import okhttp3.Response

internal class AuthenticationInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("", "")
        val request = builder.build()
        return chain.proceed(request)
    }
}