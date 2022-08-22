package com.example.data.datasource.network.cities

import com.example.data.model.cities.CitiesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetCitiesService {

    @GET("/v1/city?")
    suspend fun getCities(
        @Query("name") nameQuery: String,
        @Query("limit") limit: Int = 100,
    ): Response<CitiesResponse>

}