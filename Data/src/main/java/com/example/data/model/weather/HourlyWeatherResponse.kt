package com.example.data.model.weather

import com.example.domain.model.weather.WeatherInfo
import com.example.domain.util.formatToDate
import com.example.domain.util.mapWeatherType
import com.google.gson.annotations.SerializedName

data class HourlyWeatherResponse(
   @SerializedName("hourly")
   val weatherData: CurrentWeather
) {
    data class WeatherDataWithIndex(
        val index: Int,
        val model: WeatherInfo
    )


}

fun CurrentWeather.toWeatherMapResponse(): Map<Int,List<WeatherInfo>> {
    return time.mapIndexed { index, time ->
        val temperature = temperature[index]
        val weatherCode = weatherCode[index]
        val windSpeed = windSpeed[index]

        val weatherInfoModel = WeatherInfo(
            time = time.formatToDate(),
            temperature = temperature,
            weatherType = weatherCode.toInt().mapWeatherType(),
            windSpeed = windSpeed,

            )
        HourlyWeatherResponse.WeatherDataWithIndex(
            index = index,
            model = weatherInfoModel
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.model }
    }
}