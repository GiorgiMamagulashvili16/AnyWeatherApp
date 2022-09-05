package com.example.presentation.screens.main_weather

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CityModel
import com.example.domain.model.weather.WeatherInfo
import com.example.domain.model.weather.WeatherModel
import com.example.domain.usecase.weather.GetCurrentWeatherUseCase
import com.example.domain.usecase.weather.GetHourlyWeather
import com.example.domain.util.ResponseState
import com.example.domain.util.location.LocationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainWeatherVm(
    private val getHourlyWeather: GetHourlyWeather,
    private val locationService: LocationService,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow<ResponseState<List<CityModel>>>(ResponseState())
    val screenState: StateFlow<ResponseState<List<CityModel>>> = _screenState

    private val _hourlyWeatherData = MutableStateFlow<ResponseState<WeatherModel>>(ResponseState())
    val hourlyWeatherData: StateFlow<ResponseState<WeatherModel>> = _hourlyWeatherData

    private val _currentWeather = MutableStateFlow<ResponseState<WeatherInfo>>(ResponseState())
    val currentWeather: StateFlow<ResponseState<WeatherInfo>> = _currentWeather

    init {
        getHourlyWeather()
    }

    private fun getHourlyWeather() = viewModelScope.launch(Dispatchers.IO) {
        _hourlyWeatherData.emit(ResponseState(isLoading = true))
        locationService.getLocation { loc ->
            loc?.let { location ->

                viewModelScope.launch {
                    val response = getHourlyWeather.getHourlyWeather(42.116710, 44.056576)

                    response.data?.let {
                        _hourlyWeatherData.emit(ResponseState(data = it))
                        _currentWeather.emit(ResponseState(data = getCurrentWeatherUseCase.execute(it)))
                    } ?: kotlin.run {
                        _hourlyWeatherData.emit(ResponseState(errorMessage = response.errorMessage))
                    }
                }
            } ?:  kotlin.run {
                _hourlyWeatherData.value = ResponseState(errorMessage = "null")
            }
        }
    }
}