package com.example.data.repository.cities

import com.example.data.datasource.network.cities.GetCitiesService
import com.example.data.util.fetchData
import com.example.domain.model.CityModel
import com.example.domain.repository.cities.CitiesRepository
import com.example.domain.util.ResponseState

class CitiesRepositoryImpl(
    private val citiesService: GetCitiesService,
) : CitiesRepository {
    override suspend fun getCities(nameQuery: String): ResponseState<List<CityModel>> {
        return fetchData(
            response = citiesService.getCities(nameQuery),
            onMapData = { it.map { it.toCityModel() } })
    }
}