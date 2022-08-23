package com.example.domain.usecase

import com.example.domain.model.CityModel
import com.example.domain.repository.cities.CitiesRepository
import com.example.domain.util.ResponseState

class GetCitiesUsecase(private val citiesRepository: CitiesRepository) {

    suspend fun getCities(nameQuery: String) : ResponseState<List<CityModel>>  = citiesRepository.getCities(nameQuery)
}