package com.example.applabtest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.applabtest.domain.model.City
import com.example.applabtest.domain.model.WeatherData
import com.example.applabtest.domain.usecase.GetCitiesUseCase
import com.example.applabtest.domain.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WeatherUiState(
    val isLoading: Boolean = false,
    val cities: List<City> = emptyList(),
    val selectedCity: City? = null,
    val weatherData: WeatherData? = null,
    val errorMessage: String? = null
)

class WeatherViewModel(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    init {
        loadCities()
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
                selectedCity = city,
                errorMessage = null
            )

            getCurrentWeatherUseCase(city.id).fold(
                onSuccess = { response ->
                    if (response.status) {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            weatherData = response.weatherData
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
                        errorMessage = throwable.message ?: "Failed to load weather data"
                    )
                }
            )
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}

class WeatherViewModelFactory(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(getCitiesUseCase, getCurrentWeatherUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}