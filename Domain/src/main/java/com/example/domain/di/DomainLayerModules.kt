package com.example.domain.di

import com.example.domain.usecase.GetCitiesUsecase
import com.example.domain.usecase.weather.GetHourlyWeather
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetCitiesUsecase(get())
    }
    single {
        GetHourlyWeather(get())
    }
}