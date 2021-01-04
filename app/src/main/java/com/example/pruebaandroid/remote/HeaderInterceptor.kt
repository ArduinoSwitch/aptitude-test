package com.example.pruebaandroid.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return getGenericHeaderResponse(chain, originalRequest)
    }

    private fun getGenericHeaderResponse(
        chain: Interceptor.Chain,
        originalRequest: Request
    ): Response {
        return chain.proceed(originalRequest.newBuilder()
            .header("Accept","application/json")
            .build()
        )
    }
}

//@Headers("Accept: application/json")