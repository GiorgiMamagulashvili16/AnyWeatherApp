package com.example.presentation.di

import com.example.presentation.screens.main_weather.MainWeatherVm
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    viewModel { MainWeatherVm(getHourlyWeather = get(), locationService = get()) }
}