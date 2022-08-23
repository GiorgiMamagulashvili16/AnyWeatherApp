package com.example.domain.di

import com.example.domain.usecase.GetCitiesUsecase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetCitiesUsecase(get())
    }
}