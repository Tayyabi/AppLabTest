package com.example.applabtest.domain.repositories

import com.example.applabtest.domain.model.CitiesResponse
import com.example.applabtest.domain.model.WeatherResponse

interface WeatherRepository {
    suspend fun getCities(): Result<CitiesResponse>
    suspend fun getCurrentWeather(cityId: Int): Result<WeatherResponse>
}