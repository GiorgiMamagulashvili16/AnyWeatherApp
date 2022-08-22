package com.example.data.datasource.network.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val url = chain.request().url.newBuilder().addQueryParameter("X-Api-Key", "rW8uEWnbGIfi9DBmufkUtQ==ymf9Q9h7oCkd90sa").build()
        request.url(url)
        return chain.proceed(request = request.build())
    }
}