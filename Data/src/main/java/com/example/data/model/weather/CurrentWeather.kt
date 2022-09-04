package com.example.data.model.weather

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    val time: List<String>,
    @SerializedName("temperature_2m")
    val temperature: List<Double>,
    @SerializedName("weathercode")
    val weatherCode: List<Double>,
    @SerializedName("windspeed_10m")
    val windSpeed: List<Double>,
)