package com.example.data.datasource.network.util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkConnectionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        if (!isConnected())
//            throw NetworkConnectionException()
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        return try {
            Runtime.getRuntime().exec(COMMAND).waitFor() == PING_TIME
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        private const val COMMAND = "ping -c 1 google.com"
        private const val PING_TIME = 0
    }
}