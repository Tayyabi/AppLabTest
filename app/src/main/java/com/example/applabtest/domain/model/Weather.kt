package com.example.applabtest.domain.model

data class WeatherResponse(
    val status: Boolean,
    val weatherData: WeatherData
)

data class WeatherData(
    val city: WeatherCity,
    val current: CurrentWeather,
    val dailyForecast: List<DailyWeather>
)

data class WeatherCity(
    val id: Int,
    val name: String,
    val nameAr: String?,
    val country: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    val utcOffset: String?
)

data class CurrentWeather(
    val temperature: Double,
    val temperatureUnit: String,
    val feelsLike: Double,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val windDirectionText: String?,
    val visibility: Int,
    val uvIndex: String,
    val weatherType: String,
    val weatherTypeAr: String?,
    val weatherIcon: String?,
    val clouds: Int,
    val rain: Int,
    val sunrise: Long,
    val sunset: Long
)

data class DailyWeather(
    val date: String,
    val temperature: Double,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val weatherType: String,
    val weatherTypeAr: String?,
    val weatherIcon: String?,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val clouds: Int,
    val rain: Int
)