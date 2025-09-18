package com.example.applabtest.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.Grey
import com.example.applabtest.presentation.theme.Grey_3
import com.example.applabtest.domain.model.WeatherData

@Composable
fun OtherInformation(weatherData: WeatherData? = null) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .padding(end = 6.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Image(
                painter = painterResource(R.drawable.ic_precip),
                contentDescription = null
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "PRECIP",
                    color = Grey_3,
                    fontSize = 12.sp,
                    lineHeight = 12.sp

                )
                Text(
                    text = weatherData?.current?.let { "${it.rain}%" } ?: "3%",
                    fontSize = 20.sp
                )
            }

        }

        Row(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .padding(start = 6.dp, end = 6.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {

            Image(
                painter = painterResource(R.drawable.ic_ne),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )

            Column {
                Text(
                    text = weatherData?.current?.windDirectionText ?: "NE",
                    fontSize = 19.sp,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }

        }

        Row(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .padding(start = 6.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {

            Image(
                painter = painterResource(R.drawable.ic_wind),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )


            Text(
                text = weatherData?.current?.let { "${it.windSpeed}\nkm/h" } ?: "23.3\nkm/h",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                modifier = Modifier.padding(start = 6.dp)
            )


        }
    }

}