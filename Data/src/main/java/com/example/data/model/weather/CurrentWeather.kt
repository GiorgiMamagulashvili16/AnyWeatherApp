package com.example.data.model.weather

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    val temperature: Double,
    val time: String,
    @SerializedName("weathercode")
    val weatherCode: Int,
    @SerializedName("winddirection")
    val windDirection: Int,
    @SerializedName("windspeed")
    val windSpeed: Double
)