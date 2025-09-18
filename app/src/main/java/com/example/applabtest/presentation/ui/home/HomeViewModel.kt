package com.example.applabtest.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applabtest.domain.model.City
import com.example.applabtest.domain.model.WeatherData
import com.example.applabtest.domain.usecase.GetCitiesUseCase
import com.example.applabtest.domain.usecase.GetCurrentWeatherUseCase
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
}