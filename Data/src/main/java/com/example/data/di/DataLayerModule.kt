package com.example.data.di

import com.example.data.datasource.network.*
import com.example.data.datasource.network.cities.GetCitiesService
import com.example.data.datasource.network.weather.WeatherService
import com.example.data.repository.cities.CitiesRepositoryImpl
import com.example.data.util.HeaderInterceptor
import com.example.data.util.NetworkConnectionInterceptor
import com.example.data.util.NetworkConstants.CITY_API_BASE_URL
import com.example.data.util.NetworkConstants.CITY_API_BASE_URL_NAMED
import com.example.data.util.NetworkConstants.CITY_API_CLIENT
import com.example.data.util.NetworkConstants.CITY_API_RETROFIT
import com.example.data.util.NetworkConstants.CITY_API_SERVICE_NAMED
import com.example.data.util.NetworkConstants.WEATHER_API_BASE_URL
import com.example.data.util.NetworkConstants.WEATHER_API_BASE_URL_NAMED
import com.example.data.util.NetworkConstants.WEATHER_API_CLIENT
import com.example.data.util.NetworkConstants.WEATHER_API_RETROFIT
import com.example.data.util.NetworkConstants.WEATHER_API_SERVICE_NAMED
import com.example.domain.repository.cities.CitiesRepository
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single(named(CITY_API_BASE_URL_NAMED)) { CITY_API_BASE_URL }
    single(named(WEATHER_API_BASE_URL_NAMED)) { WEATHER_API_BASE_URL }

    single { HeaderInterceptor() }

    single { NetworkConnectionInterceptor() }
    single<OkHttpClient>(named(CITY_API_CLIENT)){
        provideCityApiHttpClient(get(),get())
    }

    single(named(WEATHER_API_CLIENT)) {
        provideOkHttpClient(networkConnectionInterceptor = get())
    }

    single (named(CITY_API_RETROFIT)){
        retrofitInstance(baseUrl = get(named(CITY_API_BASE_URL_NAMED)), okHttpClient = get(qualifier = named(CITY_API_CLIENT)))
    }
    single(named(WEATHER_API_RETROFIT)) {
        retrofitInstance(baseUrl = get(named(WEATHER_API_BASE_URL_NAMED)), okHttpClient = get(named(WEATHER_API_CLIENT)))
    }

    single() {
        provideCityApiService(get(named(CITY_API_RETROFIT)))
    }
//    single(named(WEATHER_API_SERVICE_NAMED)) {
//        provideService(retrofit = get(named(WEATHER_API_RETROFIT)), service = WeatherService::class.java)
//    }
}

val repositoryModule = module {
    single<CitiesRepository> { CitiesRepositoryImpl(get()) }
}