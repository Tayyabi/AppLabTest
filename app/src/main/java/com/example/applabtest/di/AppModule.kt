package com.example.applabtest.di

import com.example.applabtest.data.remote.api.NetworkModule
import com.example.applabtest.data.remote.api.WeatherApiService
import com.example.applabtest.data.repository.WeatherRepository
import com.example.applabtest.data.repository.WeatherRepositoryImpl
import com.example.applabtest.domain.usecase.GetCitiesUseCase
import com.example.applabtest.domain.usecase.GetCurrentWeatherUseCase
import com.example.applabtest.presentation.viewmodel.WeatherViewModelFactory

object AppModule {

    fun provideWeatherApiService(): WeatherApiService {
        return NetworkModule.provideWeatherApiService()
    }

    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepositoryImpl(provideWeatherApiService())
    }

    fun provideGetCitiesUseCase(): GetCitiesUseCase {
        return GetCitiesUseCase(provideWeatherRepository())
    }

    fun provideGetCurrentWeatherUseCase(): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(provideWeatherRepository())
    }

    fun provideWeatherViewModelFactory(): WeatherViewModelFactory {
        return WeatherViewModelFactory(
            provideGetCitiesUseCase(),
            provideGetCurrentWeatherUseCase()
        )
    }
}