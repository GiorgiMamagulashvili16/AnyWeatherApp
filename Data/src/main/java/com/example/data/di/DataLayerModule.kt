package com.example.data.di

import com.example.data.datasource.network.*
import com.example.data.repository.cities.CitiesRepositoryImpl
import com.example.data.repository.weather.WeatherRepositoryImpl
import com.example.data.util.HeaderInterceptor
import com.example.data.util.NetworkConnectionInterceptor
import com.example.data.util.NetworkConstants.CITY_API_BASE_URL
import com.example.data.util.NetworkConstants.CITY_API_BASE_URL_NAMED
import com.example.data.util.NetworkConstants.CITY_API_CLIENT
import com.example.data.util.NetworkConstants.CITY_API_RETROFIT
import com.example.data.util.NetworkConstants.WEATHER_API_BASE_URL
import com.example.data.util.NetworkConstants.WEATHER_API_BASE_URL_NAMED
import com.example.data.util.NetworkConstants.WEATHER_API_CLIENT
import com.example.data.util.NetworkConstants.WEATHER_API_RETROFIT
import com.example.data.util.location.LocationServiceImpl
import com.example.domain.repository.cities.CitiesRepository
import com.example.domain.repository.weather.WeatherRepository
import com.example.domain.util.location.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule = module {
    single(named(CITY_API_BASE_URL_NAMED)) { CITY_API_BASE_URL }
    single(named(WEATHER_API_BASE_URL_NAMED)) { WEATHER_API_BASE_URL }

    single { HeaderInterceptor() }

    single { NetworkConnectionInterceptor() }
    single<OkHttpClient>(named(CITY_API_CLIENT)) {
        provideCityApiHttpClient(get(), get())
    }

    single(named(WEATHER_API_CLIENT)) {
        provideWeatherApiHttpClient(get())
    }
//    single(named(WEATHER_API_CLIENT)) {
//        provideOkHttpClient(networkConnectionInterceptor = get())
//    }

    single(named(CITY_API_RETROFIT)) {
        retrofitInstance(baseUrl = get(named(CITY_API_BASE_URL_NAMED)),
            okHttpClient = get(qualifier = named(CITY_API_CLIENT)))
    }
    single(named(WEATHER_API_RETROFIT)) {
        retrofitInstance(baseUrl = get(named(WEATHER_API_BASE_URL_NAMED)),
            okHttpClient = get(named(WEATHER_API_CLIENT)))
    }

    single {
        provideCityApiService(get(named(CITY_API_RETROFIT)))
    }
    single {
        provideWeatherApiService(retrofit = get(named(WEATHER_API_RETROFIT)))
    }
}

val repositoryModule = module {
    single<CitiesRepository> { CitiesRepositoryImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}

@OptIn(ExperimentalCoroutinesApi::class)
val locationModule = module {
    single {
        FusedLocationProviderClient(androidApplication())
    }
    single<LocationService> {
        LocationServiceImpl(
            fusedLocation = get(),
            app = androidApplication(),
        )
    }
}