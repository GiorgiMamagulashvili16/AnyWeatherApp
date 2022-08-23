package com.example.data.model.cities

import com.example.domain.model.CityModel
import com.google.gson.annotations.SerializedName

data class CityDto(
    val country: String,
    @SerializedName("is_capital")
    val isCapital: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val population: Int
) {
    fun toCityModel() = CityModel(latitude, longitude, name)
}