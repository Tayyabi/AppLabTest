package com.example.applabtest.data.repository

import com.example.applabtest.data.remote.api.WeatherApiService
import com.example.applabtest.data.mapper.CityMapper
import com.example.applabtest.data.mapper.WeatherMapper
import com.example.applabtest.domain.model.CitiesResponse
import com.example.applabtest.domain.model.WeatherResponse

interface WeatherRepository {
    suspend fun getCities(): Result<CitiesResponse>
    suspend fun getCurrentWeather(cityId: Int): Result<WeatherResponse>
}

class WeatherRepositoryImpl(
    private val apiService: WeatherApiService
) : WeatherRepository {

    override suspend fun getCities(): Result<CitiesResponse> {
        return try {
            val response = apiService.getCities()
            if (response.isSuccessful) {
                response.body()?.let { citiesDto ->
                    val cities = CityMapper.mapToDomain(citiesDto)
                    Result.success(cities)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("API call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentWeather(cityId: Int): Result<WeatherResponse> {
        return try {
            val response = apiService.getCurrentWeather(cityId)
            if (response.isSuccessful) {
                response.body()?.let { weatherDto ->
                    val weather = WeatherMapper.mapToDomain(weatherDto)
                    Result.success(weather)
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("API call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}