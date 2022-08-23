package com.example.presentation.screens.main_weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CityModel
import com.example.domain.usecase.GetCitiesUsecase
import com.example.domain.util.ResponseState
import com.example.presentation.util.LocationManager
import com.example.presentation.util.LocationManagerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainWeatherVm(
    private val getCitiesUsecase: GetCitiesUsecase,
    private val locationManager: LocationManager,
) : ViewModel() {

    private val _screenState = MutableStateFlow<ResponseState<List<CityModel>>>(ResponseState())
    val screenState: StateFlow<ResponseState<List<CityModel>>> = _screenState

    private val _locationState = MutableStateFlow<LocationManagerState>(LocationManagerState())
    val locationState: StateFlow<LocationManagerState> = _locationState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            locationManager.getLocation {
                _locationState.value = it
            }
            _screenState.emit(ResponseState(isLoading = true))
            val response = getCitiesUsecase.getCities("tb")

            response.data?.let {
                _screenState.emit(ResponseState(data = it))
            } ?: kotlin.run {
                _screenState.emit(ResponseState(errorMessage = response.errorMessage))
            }
        }
    }
}