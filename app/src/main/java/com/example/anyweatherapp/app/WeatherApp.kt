package com.example.anyweatherapp.app

import android.app.Application
import com.example.data.di.locationModule
import com.example.data.di.networkModule
import com.example.data.di.repositoryModule
import com.example.domain.di.useCaseModule
import com.example.presentation.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            modules(listOf(repositoryModule,
                useCaseModule,
                mainScreenModule,
                networkModule,
                locationModule))
        }
    }
}