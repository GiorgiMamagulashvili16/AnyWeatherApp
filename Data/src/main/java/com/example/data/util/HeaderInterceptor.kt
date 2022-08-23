package com.example.data.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.header("X-Api-Key","rW8uEWnbGIfi9DBmufkUtQ==ymf9Q9h7oCkd90sa")
        return chain.proceed(request = request.build())
    }
}