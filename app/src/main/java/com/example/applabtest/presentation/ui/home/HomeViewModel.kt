package com.example.applabtest.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applabtest.domain.model.City
import com.example.applabtest.domain.model.HourlyWeather
import com.example.applabtest.domain.model.WeatherData
import com.example.applabtest.domain.usecase.GetCitiesUseCase
import com.example.applabtest.domain.usecase.GetCurrentWeatherUseCase
import com.example.applabtest.domain.usecase.GetLanguagePreferenceUseCase
import com.example.applabtest.domain.usecase.SetLanguagePreferenceUseCase
import com.example.applabtest.domain.usecase.FormatDateUseCase
import com.example.applabtest.domain.usecase.GetWeatherDataForDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class WeatherUiState(
    val isLoading: Boolean = false,
    val cities: List<City> = emptyList(),
    val selectedCity: City? = null,
    val weatherData: WeatherData? = null,
    val selectedDateIndex: Int = 0, // Index in dailyForecast (0 = current day)
    val errorMessage: String? = null,
    val selectedLanguage: String = "EN"
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getLanguagePreferenceUseCase: GetLanguagePreferenceUseCase,
    private val setLanguagePreferenceUseCase: SetLanguagePreferenceUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val getWeatherDataForDateUseCase: GetWeatherDataForDateUseCase
) : ViewModel()  {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        loadSavedLanguage()
    }

    private fun loadSavedLanguage() {
        val savedLanguage = getLanguagePreferenceUseCase()
        _uiState.value = _uiState.value.copy(selectedLanguage = savedLanguage)
    }

    fun changeLanguage(languageCode: String) {
        setLanguagePreferenceUseCase(languageCode)
        _uiState.value = _uiState.value.copy(selectedLanguage = languageCode)
    }

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
        return formatDateUseCase(currentState.weatherData, currentState.selectedDateIndex)
    }

    fun canNavigateForward(): Boolean {
        val currentState = _uiState.value
        val maxIndex = currentState.weatherData?.dailyForecast?.size?.minus(1) ?: 0
        return currentState.selectedDateIndex < maxIndex
    }

    fun canNavigateBackward(): Boolean {
        return _uiState.value.selectedDateIndex > 0
    }

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

    fun getHourlyDataForSelectedDate(): List<HourlyWeather> {
        val currentState = _uiState.value
        return getWeatherDataForDateUseCase.getHourlyDataForDate(
            currentState.weatherData,
            currentState.selectedDateIndex
        )
    }
}