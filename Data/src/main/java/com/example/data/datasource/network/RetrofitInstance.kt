package com.example.data.datasource.network

import com.example.data.datasource.network.cities.GetCitiesService
import com.example.data.datasource.network.weather.WeatherService
import com.example.data.util.HeaderInterceptor
import com.example.data.util.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun retrofitInstance(baseUrl: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()

fun provideOkHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor, headerInterceptor: HeaderInterceptor? = null) {
    val client = OkHttpClient().newBuilder().addInterceptor(networkConnectionInterceptor)
    headerInterceptor?.let {
        client.addInterceptor(it).build()
    } ?: run {
        client.build()
    }
}

fun provideCityApiService(retrofit: Retrofit): GetCitiesService =
    retrofit.create(GetCitiesService::class.java)

fun provideCityApiHttpClient(
    networkConnectionInterceptor: NetworkConnectionInterceptor,
    headerInterceptor: HeaderInterceptor,
): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(networkConnectionInterceptor)
        .addInterceptor(headerInterceptor).build()
}

fun provideWeatherApiService(retrofit: Retrofit): WeatherService =
    retrofit.create(WeatherService::class.java)

fun provideWeatherApiHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(networkConnectionInterceptor).build()
}