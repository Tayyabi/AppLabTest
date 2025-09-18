package com.example.applabtest.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applabtest.domain.model.City
import com.example.applabtest.domain.model.WeatherData
import com.example.applabtest.domain.model.DailyWeather
import com.example.applabtest.domain.model.HourlyWeather
import com.example.applabtest.domain.usecase.GetCitiesUseCase
import com.example.applabtest.domain.usecase.GetCurrentWeatherUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WeatherUiState(
    val isLoading: Boolean = false,
    val cities: List<City> = emptyList(),
    val selectedCity: City? = null,
    val weatherData: WeatherData? = null,
    val selectedDateIndex: Int = 0, // Index in dailyForecast (0 = current day)
    val errorMessage: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel()  {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun loadCities() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            getCitiesUseCase().fold(
                onSuccess = { response ->
                    if (response.status) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            cities = response.cities
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = "Failed to load cities"
                        )
                    }
                },
                onFailure = { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unknown error occurred"
                    )
                }
            )
        }
    }

    fun loadWeatherForCity(city: City) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                selectedCity = city
            )

            getCurrentWeatherUseCase(city.id).fold(
                onSuccess = { response ->
                    if (response.status) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            weatherData = response.weatherData,
                            selectedDateIndex = 0 // Reset to current day when loading new city
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = "Failed to load weather data"
                        )
                    }
                },
                onFailure = { throwable ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unknown error occurred"
                    )
                }
            )
        }
    }

    fun navigateToNextDay() {
        val currentState = _uiState.value
        val maxIndex = currentState.weatherData?.dailyForecast?.size?.minus(1) ?: 0
        if (currentState.selectedDateIndex < maxIndex) {
            _uiState.value = currentState.copy(
                selectedDateIndex = currentState.selectedDateIndex + 1
            )
        }
    }

    fun navigateToPreviousDay() {
        val currentState = _uiState.value
        if (currentState.selectedDateIndex > 0) {
            _uiState.value = currentState.copy(
                selectedDateIndex = currentState.selectedDateIndex - 1
            )
        }
    }

    fun getCurrentDateString(): String {
        val currentState = _uiState.value
        return when {
            currentState.weatherData == null -> SimpleDateFormat("EEE, MMM dd", Locale.getDefault()).format(Date())
            currentState.selectedDateIndex == 0 -> "Today"
            currentState.selectedDateIndex == 1 -> "Tomorrow"
            else -> {
                val dailyWeather = currentState.weatherData.dailyForecast.getOrNull(currentState.selectedDateIndex)
                dailyWeather?.date?.let { dateString ->
                    // Parse and reformat the date from API
                    try {
                        val parts = dateString.split(", ")
                        if (parts.size >= 3) {
                            "${parts[0]}, ${parts[1]}"
                        } else {
                            dateString
                        }
                    } catch (e: Exception) {
                        dateString
                    }
                } ?: SimpleDateFormat("EEE, MMM dd", Locale.getDefault()).format(Date())
            }
        }
    }

    fun canNavigateForward(): Boolean {
        val currentState = _uiState.value
        val maxIndex = currentState.weatherData?.dailyForecast?.size?.minus(1) ?: 0
        return currentState.selectedDateIndex < maxIndex
    }

    fun canNavigateBackward(): Boolean {
        return _uiState.value.selectedDateIndex > 0
    }

    // Helper function to get current weather data based on selected date
    fun getCurrentWeatherForSelectedDate(): Any? {
        val currentState = _uiState.value
        return when {
            currentState.weatherData == null -> null
            currentState.selectedDateIndex == 0 -> currentState.weatherData.current
            else -> {
                // For future dates, use daily forecast data
                currentState.weatherData.dailyForecast.getOrNull(currentState.selectedDateIndex)
            }
        }
    }

    // Helper function to get temperature data for selected date
    fun getTemperatureForSelectedDate(): Triple<Double?, Double?, Double?> {
        val currentState = _uiState.value
        return when {
            currentState.weatherData == null -> Triple(null, null, null)
            currentState.selectedDateIndex == 0 -> {
                val current = currentState.weatherData.current
                Triple(current.temperature, current.temperatureMin, current.temperatureMax)
            }
            else -> {
                val daily = currentState.weatherData.dailyForecast.getOrNull(currentState.selectedDateIndex)
                Triple(daily?.temperature, daily?.temperatureMin, daily?.temperatureMax)
            }
        }
    }

    // Helper function to get weather conditions for selected date
    fun getWeatherConditionsForSelectedDate(): Pair<String?, String?> {
        val currentState = _uiState.value
        return when {
            currentState.weatherData == null -> Pair(null, null)
            currentState.selectedDateIndex == 0 -> {
                val current = currentState.weatherData.current
                Pair(current.weatherType, current.temperatureUnit)
            }
            else -> {
                val daily = currentState.weatherData.dailyForecast.getOrNull(currentState.selectedDateIndex)
                Pair(daily?.weatherType, daily?.let { "°C" })
            }
        }
    }

    // Helper function to get wind data for selected date
    fun getWindDataForSelectedDate(): Triple<Double?, String?, String?> {
        val currentState = _uiState.value
        return when {
            currentState.weatherData == null -> Triple(null, null, null)
            currentState.selectedDateIndex == 0 -> {
                val current = currentState.weatherData.current
                Triple(current.windSpeed, current.windDirectionText, "m/s")
            }
            else -> {
                val daily = currentState.weatherData.dailyForecast.getOrNull(currentState.selectedDateIndex)
                Triple(daily?.windSpeed, null, "m/s")
            }
        }
    }

    // Helper function to get humidity and other data for selected date
    fun getOtherDataForSelectedDate(): Map<String, String?> {
        val currentState = _uiState.value
        return when {
            currentState.weatherData == null -> emptyMap()
            currentState.selectedDateIndex == 0 -> {
                val current = currentState.weatherData.current
                mapOf(
                    "humidity" to "${current.humidity}%",
                    "pressure" to "${current.pressure} hPa",
                    "visibility" to "${current.visibility / 1000} km",
                    "uvIndex" to current.uvIndex,
                    "rain" to "${current.rain}%"
                )
            }
            else -> {
                val daily = currentState.weatherData.dailyForecast.getOrNull(currentState.selectedDateIndex)
                mapOf(
                    "humidity" to daily?.let { "${it.humidity}%" },
                    "pressure" to daily?.let { "${it.pressure} hPa" },
                    "rain" to daily?.let { "${it.rain}%" }
                )
            }
        }
    }

    // Get weather data for the currently selected date - this modifies the current weather to show selected date data
    fun getWeatherDataForSelectedDate(): WeatherData? {
        val currentState = _uiState.value
        val originalData = currentState.weatherData ?: return null

        return when (currentState.selectedDateIndex) {
            0 -> originalData // Current day - return as is
            else -> {
                // For future dates, modify the current weather to show daily forecast data
                val daily = originalData.dailyForecast.getOrNull(currentState.selectedDateIndex) ?: return originalData

                originalData.copy(
                    current = originalData.current.copy(
                        temperature = daily.temperature,
                        temperatureMin = daily.temperatureMin,
                        temperatureMax = daily.temperatureMax,
                        weatherType = daily.weatherType,
                        humidity = daily.humidity,
                        pressure = daily.pressure,
                        windSpeed = daily.windSpeed,
                        clouds = daily.clouds,
                        rain = daily.rain
                    )
                )
            }
        }
    }

    // Get hourly data for the currently selected date
    fun getHourlyDataForSelectedDate(): List<HourlyWeather> {
        val currentState = _uiState.value
        val weatherData = currentState.weatherData ?: return emptyList()

        return when (currentState.selectedDateIndex) {
            0 -> {
                // For current day, return the first day's hourly data if available
                weatherData.hourlyData.firstOrNull()?.dayDetails ?: emptyList()
            }
            else -> {
                // For future dates, get hourly data for the corresponding date
                weatherData.hourlyData.getOrNull(currentState.selectedDateIndex)?.dayDetails ?: emptyList()
            }
        }
    }
}