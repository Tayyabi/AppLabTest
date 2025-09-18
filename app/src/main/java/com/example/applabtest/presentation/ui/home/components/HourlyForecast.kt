package com.example.applabtest.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.Cloud_Burst
import com.example.applabtest.presentation.theme.Grey
import com.example.applabtest.presentation.theme.Purple
import com.example.applabtest.presentation.theme.Purple_Blue
import com.example.applabtest.domain.model.HourlyWeather

@Composable
fun HourlyForecast(
    hourlyData: List<HourlyWeather> = emptyList()
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        hourlyData.take(8).forEachIndexed { index, hourlyWeather ->
            HourlyForecastItem(
                hourlyWeather = hourlyWeather,
                isFirst = index == 0
            )
        }
    }
}

@Composable
fun HourlyForecastItem(
    hourlyWeather: HourlyWeather,
    isFirst: Boolean = false
) {
    Column(
        modifier = Modifier
            .padding(end = 10.dp)
            .border(
                width = 1.dp,
                color = Grey,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isFirst) "Now" else hourlyWeather.time,
            color = Cloud_Burst,
            fontSize = 14.sp,
            lineHeight = 14.sp
        )

        Image(
            painter = painterResource(getWeatherIcon(hourlyWeather.weatherIcon)),
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .size(25.dp)
        )

        Text(
            text = "${hourlyWeather.temperature}${hourlyWeather.temperatureUnit}",
            color = Purple,
            fontSize = 14.sp,
            lineHeight = 14.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(getWindDirectionIcon(hourlyWeather.windDirectionText)),
                contentDescription = null,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = hourlyWeather.windDirectionText ?: "N",
                fontSize = 11.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Text(
            text = "${hourlyWeather.windPower} ${hourlyWeather.windPowerUnit}",
            color = Purple_Blue,
            fontSize = 12.sp,
            lineHeight = 14.sp
        )
    }
}

// Helper function to get weather icon based on weather icon string
fun getWeatherIcon(weatherIcon: String?): Int {
    return when (weatherIcon) {
        "01d", "01n" -> R.drawable.ic_sunny
        "02d", "02n" -> R.drawable.ic_sunny_cloudy
        "03d", "03n" -> R.drawable.ic_clouds
        "04d", "04n" -> R.drawable.ic_raining_cloudy
        "09d", "09n" -> R.drawable.ic_full_raining
        "10d", "10n" -> R.drawable.ic_raining_cloudy
        "11d", "11n" -> R.drawable.ic_full_raining
        "13d", "13n" -> R.drawable.ic_clouds
        "50d", "50n" -> R.drawable.ic_clouds
        else -> R.drawable.ic_sunny
    }
}

// Helper function to get wind direction icon
fun getWindDirectionIcon(windDirection: String?): Int {
    return when (windDirection) {
        "N", "NE", "E", "SE", "S", "SW", "W", "NW" -> R.drawable.ic_ne
        else -> R.drawable.ic_ne
    }
}