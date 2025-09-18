package com.example.applabtest.domain.usecase

import com.example.applabtest.domain.repositories.WeatherRepository
import com.example.applabtest.domain.model.CitiesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(): Result<CitiesResponse> {
        return withContext(Dispatchers.IO) {
            repository.getCities()
        }
    }
}