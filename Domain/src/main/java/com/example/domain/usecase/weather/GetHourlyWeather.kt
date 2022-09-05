package com.example.domain.usecase.weather

import com.example.domain.model.weather.WeatherModel
import com.example.domain.repository.weather.WeatherRepository
import com.example.domain.util.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetHourlyWeather(private val weatherRepository: WeatherRepository) {

     suspend fun getHourlyWeather(lat: Double, long: Double): ResponseState<WeatherModel> =
         withContext(Dispatchers.IO) {
             return@withContext weatherRepository.getHourlyWeather(lat, long)
         }
}