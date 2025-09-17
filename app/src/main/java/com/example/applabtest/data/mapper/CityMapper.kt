package com.example.applabtest.data.mapper

import com.example.applabtest.data.remote.dto.CitiesResponseDto
import com.example.applabtest.data.remote.dto.CityDto
import com.example.applabtest.domain.model.CitiesResponse
import com.example.applabtest.domain.model.City

object CityMapper {

    fun mapToDomain(dto: CitiesResponseDto): CitiesResponse {
        val allCities = mutableListOf<City>()

        // Add Qatar cities
        dto.response.result.cities.qatar?.let { qatarCities ->
            allCities.addAll(qatarCities.map { it.toDomain(isQatar = true) })
        }

        // Add World cities
        dto.response.result.cities.world?.let { worldCities ->
            allCities.addAll(worldCities.map { it.toDomain(isQatar = false) })
        }

        return CitiesResponse(
            status = dto.response.status,
            cities = allCities
        )
    }

    private fun CityDto.toDomain(isQatar: Boolean): City {
        return City(
            id = this.cityId,
            name = this.name,
            country = this.country,
            countryName = this.countryName,
            latitude = this.latitude,
            longitude = this.longitude,
            utcOffset = this.utcOffset,
            nameAr = this.nameAr,
            isQatar = isQatar
        )
    }
}