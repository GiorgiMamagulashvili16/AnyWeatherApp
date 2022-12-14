package com.example.data.model.weather

import com.google.gson.annotations.SerializedName

data class DailyWeatherResponse(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather,
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,
    val hourly: HashMap<String, List<Any>>,
    @SerializedName("hourly_units")
    val hourlyUnits: HashMap<String, String>,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
)