package com.example.presentation.screens.main_weather

import com.example.domain.model.weather.WeatherModel

data class MainScreenState(
    val isLoading: Boolean = false,
    val hourlyDataFetched: WeatherModel? = null,
)
