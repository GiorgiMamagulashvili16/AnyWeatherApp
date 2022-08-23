package com.example.domain.model.weather

data class CurrentWeatherModel(
    val weatherCode: Int,
    val windSpeed: Double,
    val time: String,
    val temperature: Double
)
