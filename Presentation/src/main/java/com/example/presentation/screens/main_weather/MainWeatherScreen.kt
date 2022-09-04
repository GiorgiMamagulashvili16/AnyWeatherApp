package com.example.presentation.screens.main_weather

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel

@Composable
fun MainWeatherScreen() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Hello gela!")

    }
    val vm = getViewModel<MainWeatherVm>()
    val locationState = vm.locationState.collectAsState().value

    val isHourlyWeatherFetched = remember {
        mutableStateOf(false)
    }
//    if (isHourlyWeatherFetched.value.not() && locationState?.latitude != null && locationState.longitude != null) {
//        isHourlyWeatherFetched.value = true
//        vm.getHourlyWeather()
//    }
    Log.d("RESPONSESTATE", "${vm.hourlyWeatherData.collectAsState().value}")
    Log.d("RESPONSESTATE", "${vm.locationState.collectAsState().value}")
}