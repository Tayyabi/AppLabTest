package com.example.applabtest.domain.model

data class CitiesResponse(
    val status: Boolean,
    val cities: List<City>
)

data class City(
    val id: Int,
    val name: String,
    val country: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    val utcOffset: String?,
    val nameAr: String?,
    val isQatar: Boolean
)