package com.example.applabtest.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.Grey_4
import com.example.applabtest.presentation.theme.Grey_5
import com.example.applabtest.presentation.theme.Purple
import com.example.applabtest.presentation.theme.Purple_Blue
import com.example.applabtest.domain.model.DailyWeather

@Composable
fun DailyForecast(dailyWeatherList: List<DailyWeather> = emptyList()) {
    dailyWeatherList.drop(1).forEachIndexed { index, dailyWeather ->
        // Top divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Grey_4)
        )

        // Daily forecast row
        Row(
            modifier = Modifier
                .background(
                    if (index % 2 == 0) Grey_5.copy(alpha = 0.70f) else Color.White
                )
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = getDayName(dailyWeather.date, index),
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Purple_Blue
                )

                Text(
                    text = "${dailyWeather.temperature.toInt()}°C",
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    color = Purple
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(getDailyWeatherIcon(dailyWeather.weatherIcon)),
                contentDescription = null
            )

            Text(
                text = dailyWeather.weatherType,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 10.dp)
            )

            Text(
                text = "${dailyWeather.temperatureMax.toInt()}°/${dailyWeather.temperatureMin.toInt()}°C",
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        // Bottom divider for last item
        if (index == dailyWeatherList.drop(1).size - 1) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Grey_4)
            )
        }
    }
}

// Helper function to get day name from date
fun getDayName(dateString: String, index: Int): String {
    return when (index) {
        0 -> "Tomorrow"
        else -> {
            try {
                // Parse the date string from API (e.g., "Thu, Sep 18, 2025 11:00 AM")
                val parts = dateString.split(", ")
                if (parts.isNotEmpty()) {
                    parts[0] // Return the day part (e.g., "Thu")
                } else {
                    "Day ${index + 2}"
                }
            } catch (e: Exception) {
                "Day ${index + 2}"
            }
        }
    }
}

// Helper function to get weather icon for daily forecast
fun getDailyWeatherIcon(weatherIcon: String?): Int {
    return when (weatherIcon) {
        "01d", "01n" -> R.drawable.ic_sunny
        "02d", "02n" -> R.drawable.ic_sunny_cloudy
        "03d", "03n" -> R.drawable.ic_sunny_cloudy
        "04d", "04n" -> R.drawable.ic_raining_cloudy
        "09d", "09n" -> R.drawable.ic_full_raining
        "10d", "10n" -> R.drawable.ic_raining_cloudy
        "11d", "11n" -> R.drawable.ic_full_raining
        "13d", "13n" -> R.drawable.ic_sunny_cloudy
        "50d", "50n" -> R.drawable.ic_full_raining
        else -> R.drawable.ic_sunny
    }
}