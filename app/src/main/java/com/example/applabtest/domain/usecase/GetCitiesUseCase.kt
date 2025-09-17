package com.example.applabtest.domain.usecase

import com.example.applabtest.data.repository.WeatherRepository
import com.example.applabtest.domain.model.CitiesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCitiesUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(): Result<CitiesResponse> {
        return withContext(Dispatchers.IO) {
            repository.getCities()
        }
    }
}