package com.example.domain.util

fun Int.mapWeatherType(): WeatherType {
    return when (this) {
        0 -> WeatherType.ClearSky
        1, 2, 3 -> WeatherType.MainlyClear
        45, 48 -> WeatherType.Foggy
        51, 53, 55 -> WeatherType.Drizzle
        56, 57 -> WeatherType.FreezingDrizzle
        61, 63, 65 -> WeatherType.Rain
        66, 67 -> WeatherType.FreezingRain
        95, 96, 99 -> WeatherType.Thunderstorm
        else -> WeatherType.Snow
    }
}
