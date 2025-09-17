package com.example.applabtest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("Response")
    val response: WeatherWrapperDto
)

data class WeatherWrapperDto(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("result")
    val result: WeatherResultDto
)

data class WeatherResultDto(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeatherDto,
    @SerializedName("daily_weather")
    val dailyWeather: List<DailyWeatherDto>?,
    @SerializedName("hourly_data")
    val hourlyData: List<HourlyDataDto>?,
    @SerializedName("city_id")
    val cityId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_ar")
    val nameAr: String?,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("country_name_ar")
    val countryNameAr: String?,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("utc_offset")
    val utcOffset: String?
)

data class CurrentWeatherDto(
    @SerializedName("time")
    val time: Long,
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("temperature_unit")
    val temperatureUnit: String,
    @SerializedName("weather_type")
    val weatherType: String,
    @SerializedName("weather_type_ar")
    val weatherTypeAr: String?,
    @SerializedName("weather_icon")
    val weatherIcon: String?,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("humidity_unit")
    val humidityUnit: String,
    @SerializedName("wind_power")
    val windPower: Double,
    @SerializedName("wind_power_unit")
    val windPowerUnit: String,
    @SerializedName("wind_direction")
    val windDirection: Int,
    @SerializedName("wind_direction_text")
    val windDirectionText: String?,
    @SerializedName("wind_direction_text_ar")
    val windDirectionTextAr: String?,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("visibility_unit")
    val visibilityUnit: String,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("feels_like_unit")
    val feelsLikeUnit: String,
    @SerializedName("temperature_min")
    val temperatureMin: Double,
    @SerializedName("temperature_max")
    val temperatureMax: Double,
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("pressure_unit")
    val pressureUnit: String,
    @SerializedName("rain")
    val rain: Int,
    @SerializedName("rain_unit")
    val rainUnit: String,
    @SerializedName("uv_index")
    val uvIndex: String
)

data class DailyWeatherDto(
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("temperature_min")
    val temperatureMin: Double,
    @SerializedName("temperature_max")
    val temperatureMax: Double,
    @SerializedName("weather_type")
    val weatherType: String,
    @SerializedName("weather_type_ar")
    val weatherTypeAr: String?,
    @SerializedName("weather_icon")
    val weatherIcon: String?,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("rain")
    val rain: Int,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("date")
    val date: String
)

data class HourlyDataDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("day_details")
    val dayDetails: List<HourlyDetailDto>?
)

data class HourlyDetailDto(
    @SerializedName("time")
    val time: String,
    @SerializedName("temperature")
    val temperature: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("weather_type")
    val weatherType: String,
    @SerializedName("weather_type_ar")
    val weatherTypeAr: String?,
    @SerializedName("weather_icon")
    val weatherIcon: String?,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("time_hr_qatar")
    val timeHrQatar: String,
    @SerializedName("wind_power")
    val windPower: Int,
    @SerializedName("wind_direction")
    val windDirection: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("visibility")
    val visibility: Int
)