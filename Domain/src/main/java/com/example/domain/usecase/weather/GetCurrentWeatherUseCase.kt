package com.example.domain.usecase.weather

import com.example.domain.model.weather.WeatherInfo
import com.example.domain.model.weather.WeatherModel
import java.time.LocalDateTime

class GetCurrentWeatherUseCase {

    fun execute(model: WeatherModel): WeatherInfo? {
        val nowTime = LocalDateTime.now()
        return  model.weatherMap[0]?.find {
            val hr = if (nowTime.minute < 30) nowTime.hour else nowTime.hour + 1
            it.time.hour == hr
        }
    }
}