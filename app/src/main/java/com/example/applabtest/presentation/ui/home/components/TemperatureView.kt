package com.example.applabtest.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.Grey
import com.example.applabtest.presentation.theme.Grey_2
import com.example.applabtest.presentation.theme.Purple
import com.example.applabtest.presentation.theme.Purple_Blue
import com.example.applabtest.domain.model.WeatherData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun TemperatureView(weatherData: WeatherData? = null) {

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Grey,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = weatherData?.city?.name ?: "Al Shamal",
                color = Purple,
                fontSize = 24.sp,
                lineHeight = 24.sp
            )
            Text(
                text = SimpleDateFormat("EEE, dd MMM, hh:mm a", Locale.getDefault()).format(Date()),
                fontSize = 14.sp,
                lineHeight = 14.sp,
                color = Grey_2.copy(alpha = 0.5f)
            )

            Text(
                text = weatherData?.current?.let { "${it.temperature.toInt()}${it.temperatureUnit}" } ?: "18°C",
                color = Purple,
                fontSize = 55.sp,
                lineHeight = 55.sp,
                fontWeight = FontWeight(450),
                modifier = Modifier.padding(top = 5.dp)
            )

            Text(
                text = weatherData?.current?.weatherType ?: "Partially Cloudy",
                color = Color.Black,
                fontSize = 22.sp,
                lineHeight = 22.sp
            )

            Text(
                text = weatherData?.current?.let { "Feels like ${it.feelsLike.toInt()}°" } ?: "Feels like 15°",
                color = Purple_Blue,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Box {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_up),
                        contentDescription = null,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .height(18.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .size(width = 12.dp, height = 1.8.dp)
                                .background(Purple)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }


                Text(
                    text = weatherData?.current?.let { "${it.temperatureMax.toInt()}°" } ?: "99°",
                    fontSize = 25.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight(450),
                    modifier = Modifier.padding(start = 4.dp)
                )

                Column(
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_down),
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Box(
                        modifier = Modifier
                            .size(width = 15.dp, height = 1.8.dp)
                            .background(Purple)
                    )
                }

                Text(
                    text = weatherData?.current?.let { "${it.temperatureMin.toInt()}°" } ?: "82°",
                    fontSize = 25.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight(450),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.ic_clouds),
            contentDescription = "Clouds",
            modifier = Modifier.size(140.dp)
        )
    }
}