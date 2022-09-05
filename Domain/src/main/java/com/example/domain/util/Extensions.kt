package com.example.domain.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

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

fun String.formatToDate(): String {

    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    return DateFormat.getTimeInstance(DateFormat.SHORT).format(date.parse(this))
}