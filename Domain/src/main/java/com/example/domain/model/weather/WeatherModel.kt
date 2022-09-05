package com.example.domain.model.weather

import com.example.domain.util.WeatherType
import java.time.LocalDateTime

data class WeatherModel(
    val weatherMap: Map<Int, List<WeatherInfo>>,
)

data class WeatherInfo(
    val time: LocalDateTime,
    val temperature: Double,
    val weatherType: WeatherType,
    val windSpeed: Double,
)