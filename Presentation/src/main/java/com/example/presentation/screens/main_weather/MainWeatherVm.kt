package com.example.presentation.screens.main_weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CityModel
import com.example.domain.usecase.GetCitiesUsecase
import com.example.domain.util.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainWeatherVm(
    private val getCitiesUsecase: GetCitiesUsecase,
) : ViewModel() {

    private val _screenState = MutableStateFlow<ResponseState<List<CityModel>>>(ResponseState())
    val screenState: StateFlow<ResponseState<List<CityModel>>> = _screenState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.emit(ResponseState(isLoading = true))
           val response =  getCitiesUsecase.getCities("tb")

            response.data?.let {
                _screenState.emit(ResponseState(data = it))
            } ?: kotlin.run {
                _screenState.emit(ResponseState(errorMessage = response.errorMessage))
            }
        }
    }
}