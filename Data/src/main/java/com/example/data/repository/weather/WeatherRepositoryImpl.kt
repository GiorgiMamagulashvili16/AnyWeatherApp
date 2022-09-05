package com.example.data.repository.weather

import com.example.data.datasource.network.weather.WeatherService
import com.example.data.model.weather.toWeatherMapResponse
import com.example.data.util.fetchData
import com.example.domain.model.weather.WeatherModel
import com.example.domain.repository.weather.WeatherRepository
import com.example.domain.util.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class WeatherRepositoryImpl(
    private val weatherApi: WeatherService,
) : WeatherRepository {
    override suspend fun getDailyWeather(
        lat: Double,
        long: Double,
        dailyParam: String,
    ): ResponseState<WeatherModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getHourlyWeather(lat: Double, long: Double): ResponseState<WeatherModel> =
        withContext(Dispatchers.IO) {
            return@withContext fetchData(response = weatherApi.getHourlyWeather(
                lat = lat,
                long = long,
                timeZone = TimeZone.getDefault().id),
                onMapData = {
                    WeatherModel(it.weatherData.toWeatherMapResponse())
                }
            )
        }
}