package com.example.domain.repository.weather

import com.example.domain.model.weather.WeatherModel
import com.example.domain.util.ResponseState

interface WeatherRepository {

    suspend fun getDailyWeather(lat: Double, long:Double, dailyParam: String): ResponseState<WeatherModel>
    suspend fun getHourlyWeather(lat: Double,long: Double) : ResponseState<WeatherModel>
}