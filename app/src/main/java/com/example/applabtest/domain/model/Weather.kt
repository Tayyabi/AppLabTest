package com.example.applabtest.domain.model

data class WeatherResponse(
    val status: Boolean,
    val weatherData: WeatherData
)

data class WeatherData(
    val city: WeatherCity,
    val current: CurrentWeather,
    val dailyForecast: List<DailyWeather>,
    val hourlyData: List<HourlyWeatherDay>
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
    val rain: Double,
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
    val rain: Double
)

data class HourlyWeatherDay(
    val date: String,
    val dayDetails: List<HourlyWeather>
)

data class HourlyWeather(
    val time: String,
    val temperature: Double,
    val humidity: Int,
    val weatherType: String,
    val weatherTypeAr: String?,
    val warningText: String?,
    val warningTextAr: String?,
    val weatherIcon: String?,
    val timestamp: Long,
    val timeHrQatar: String?,
    val timeHrUtc: String?,
    val windPower: Double,
    val windDirection: Int,
    val windDirectionText: String?,
    val rain: Double,
    val pressure: Int,
    val visibility: Int,
    val visibilityUnit: String,
    val pressureUnit: String,
    val rainUnit: String,
    val humidityUnit: String,
    val temperatureUnit: String,
    val windPowerUnit: String
)