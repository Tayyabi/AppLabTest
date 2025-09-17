package com.example.applabtest.data.remote.api

import com.example.applabtest.data.remote.dto.CitiesResponseDto
import com.example.applabtest.data.remote.dto.WeatherResponseDto
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApiService {

    @POST("api/cities")
    suspend fun getCities(): Response<CitiesResponseDto>

    @POST("api/current_weather/city")
    suspend fun getCurrentWeather(
        @Query("city_id") cityId: Int
    ): Response<WeatherResponseDto>
}