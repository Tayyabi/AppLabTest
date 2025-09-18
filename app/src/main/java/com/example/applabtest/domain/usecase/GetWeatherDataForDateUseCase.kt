package com.example.applabtest.domain.usecase

import com.example.applabtest.domain.model.WeatherData
import com.example.applabtest.domain.model.CurrentWeather
import com.example.applabtest.domain.model.DailyWeather
import com.example.applabtest.domain.model.HourlyWeather
import javax.inject.Inject

class GetWeatherDataForDateUseCase @Inject constructor() {

    operator fun invoke(weatherData: WeatherData?, selectedDateIndex: Int): WeatherDataForDate? {
        if (weatherData == null) return null

        return when (selectedDateIndex) {
            0 -> {
                // Current day
                WeatherDataForDate(
                    current = weatherData.current,
                    daily = weatherData.dailyForecast.firstOrNull()
                )
            }
            else -> {
                // Future days
                val daily = weatherData.dailyForecast.getOrNull(selectedDateIndex)
                WeatherDataForDate(
                    current = null,
                    daily = daily
                )
            }
        }
    }

    fun getHourlyDataForDate(weatherData: WeatherData?, selectedDateIndex: Int): List<HourlyWeather> {
        if (weatherData == null) return emptyList()

        return when (selectedDateIndex) {
            0 -> {
                // Current day - get today's hourly data
                weatherData.hourlyData.firstOrNull()?.dayDetails ?: emptyList()
            }
            else -> {
                // Future days - get specific day's hourly data
                weatherData.hourlyData.getOrNull(selectedDateIndex)?.dayDetails ?: emptyList()
            }
        }
    }
}

data class WeatherDataForDate(
    val current: CurrentWeather?,
    val daily: DailyWeather?
)