package com.example.applabtest.data.mapper

import com.example.applabtest.data.remote.dto.*
import com.example.applabtest.domain.model.*

object WeatherMapper {

    fun mapToDomain(dto: WeatherResponseDto): WeatherResponse {
        val result = dto.response.result

        return WeatherResponse(
            status = dto.response.status,
            weatherData = WeatherData(
                city = result.toCityDomain(),
                current = result.currentWeather.toDomain(),
                dailyForecast = result.dailyWeather?.take(7)?.map { it.toDomain() } ?: emptyList()
            )
        )
    }

    private fun WeatherResultDto.toCityDomain(): WeatherCity {
        return WeatherCity(
            id = this.cityId,
            name = this.name,
            nameAr = this.nameAr,
            country = this.country,
            countryName = this.countryName,
            latitude = this.latitude,
            longitude = this.longitude,
            utcOffset = this.utcOffset
        )
    }

    private fun CurrentWeatherDto.toDomain(): CurrentWeather {
        return CurrentWeather(
            temperature = this.temperature,
            temperatureUnit = this.temperatureUnit,
            feelsLike = this.feelsLike,
            temperatureMin = this.temperatureMin,
            temperatureMax = this.temperatureMax,
            humidity = this.humidity,
            pressure = this.pressure,
            windSpeed = this.windPower,
            windDirection = this.windDirection,
            windDirectionText = this.windDirectionText,
            visibility = this.visibility,
            uvIndex = this.uvIndex,
            weatherType = this.weatherType,
            weatherTypeAr = this.weatherTypeAr,
            weatherIcon = this.weatherIcon,
            clouds = this.clouds,
            rain = this.rain,
            sunrise = this.sunrise,
            sunset = this.sunset
        )
    }

    private fun DailyWeatherDto.toDomain(): DailyWeather {
        return DailyWeather(
            date = this.date,
            temperature = this.temperature,
            temperatureMin = this.temperatureMin,
            temperatureMax = this.temperatureMax,
            weatherType = this.weatherType,
            weatherTypeAr = this.weatherTypeAr,
            weatherIcon = this.weatherIcon,
            humidity = this.humidity,
            pressure = this.pressure,
            windSpeed = this.windSpeed,
            clouds = this.clouds,
            rain = this.rain
        )
    }
}