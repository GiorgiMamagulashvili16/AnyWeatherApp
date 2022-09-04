package com.example.data.datasource.network.weather

import com.example.data.model.weather.HourlyWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/v1/forecast?hourly=temperature_2m,weathercode,windspeed_10m")
    suspend fun getHourlyWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("timezone") timeZone: String,
    ): Response<HourlyWeatherResponse>
}