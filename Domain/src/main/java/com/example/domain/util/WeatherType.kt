package com.example.domain.util

import com.example.domain.R

enum class WeatherType(
    val title: String,
    val iconRes: Int
) {
    ClearSky(
        title = "Clear Sky",
        iconRes = R.drawable.ic_clear_sky
    ),
    MainlyClear(
        title = "Mainly clear",
        iconRes = R.drawable.ic_mainly_clear
    ),
    Foggy(
        title = "Foggy",
        iconRes = R.drawable.ic_foggy_weather
    ),
    Drizzle(
        title = "Drizzle",
        iconRes = R.drawable.ic_drizzle_rain
    ),
    FreezingDrizzle(
        title = "Freezing Drizzle",
        iconRes = R.drawable.ic_drizzle_rain
    ),
    Rain(
        title = "Rain",
        iconRes = R.drawable.ic_rain
    ),
    FreezingRain(
        title = "Freezing Rain",
        iconRes = R.drawable.ic_rain
    ),
    Snow(
        title = "Snow",
        iconRes = R.drawable.ic_snow
    ),
    Thunderstorm(
        title = "Thunderstorm",
        iconRes = R.drawable.ic_storm
    )
}
