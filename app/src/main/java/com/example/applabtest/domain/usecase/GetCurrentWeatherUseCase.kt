package com.example.applabtest.domain.usecase

import com.example.applabtest.data.repository.WeatherRepository
import com.example.applabtest.domain.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityId: Int): Result<WeatherResponse> {
        return withContext(Dispatchers.IO) {
            repository.getCurrentWeather(cityId)
        }
    }
}