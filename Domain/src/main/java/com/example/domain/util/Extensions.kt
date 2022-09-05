package com.example.domain.util

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZonedDateTime
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
    Log.d("FORMDA","${SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(this)}")
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    return DateFormat.getTimeInstance(DateFormat.SHORT).format(date.parse(this))
}