package com.example.domain.repository.cities

import com.example.domain.model.CityModel
import com.example.domain.util.ResponseState
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    suspend fun getCities(nameQuery: String) : ResponseState<List<CityModel>>
}