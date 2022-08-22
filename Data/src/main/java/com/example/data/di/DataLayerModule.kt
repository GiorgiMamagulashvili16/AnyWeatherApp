package com.example.data.di

import com.example.data.datasource.network.cities.GetCitiesService
import com.example.data.datasource.network.provideOkHttpClient
import com.example.data.datasource.network.provideService
import com.example.data.datasource.network.retrofitInstance
import com.example.data.datasource.network.util.HeaderInterceptor
import com.example.data.datasource.network.util.NetworkConnectionInterceptor
import com.example.data.datasource.network.util.NetworkConstants.CITY_API_BASE_URL
import com.example.data.datasource.network.util.NetworkConstants.CITY_API_BASE_URL_NAMED
import com.example.data.datasource.network.util.NetworkConstants.CITY_API_CLIENT
import com.example.data.datasource.network.util.NetworkConstants.CITY_API_RETROFIT
import org.koin.core.qualifier.named
import org.koin.dsl.module

val network = module {
    single(named(CITY_API_BASE_URL_NAMED)) { CITY_API_BASE_URL }

    single { HeaderInterceptor() }

    single { NetworkConnectionInterceptor() }

    single(named(CITY_API_CLIENT)){
        provideOkHttpClient(networkConnectionInterceptor = get(), headerInterceptor = get())
    }

    single (named(CITY_API_RETROFIT)){
        retrofitInstance(baseUrl = get(named(CITY_API_BASE_URL)), okHttpClient = get(named(CITY_API_CLIENT)))
    }

    single {
        provideService(retrofit = get(named(CITY_API_RETROFIT)), service = GetCitiesService::class.java)
    }
}