package com.example.domain.model.weather

data class WeatherModel(
    val currentWeather: CurrentWeatherModel,
    val weatherMap: Map<String, List<Any>>,
)