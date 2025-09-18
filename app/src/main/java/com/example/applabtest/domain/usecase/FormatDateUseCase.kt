package com.example.applabtest.domain.usecase

import com.example.applabtest.domain.model.WeatherData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class FormatDateUseCase @Inject constructor() {

    operator fun invoke(weatherData: WeatherData?, selectedDateIndex: Int): String {
        return when {
            weatherData == null -> SimpleDateFormat("EEE, MMM dd", Locale.getDefault()).format(Date())
            selectedDateIndex == 0 -> "Today"
            selectedDateIndex == 1 -> "Tomorrow"
            else -> {
                val currentDate = weatherData.dailyForecast.getOrNull(selectedDateIndex)?.date
                currentDate?.let { dateString ->
                    try {
                        val parts = dateString.split(", ")
                        if (parts.size >= 2) {
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
}