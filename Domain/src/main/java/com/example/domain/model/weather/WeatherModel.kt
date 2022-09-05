package com.example.domain.model.weather

import com.example.domain.util.WeatherType

data class WeatherModel(
    val weatherMap: Map<Int, List<WeatherInfo>>,
)

data class WeatherInfo(
    val time:String,
    val temperature: Double,
    val weatherType: WeatherType,
    val windSpeed: Double,
)