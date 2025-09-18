package com.example.applabtest.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.applabtest.presentation.theme.Cloud_Burst
import com.example.applabtest.presentation.theme.Grey
import com.example.applabtest.presentation.theme.Purple
import com.example.applabtest.presentation.theme.Purple_Blue

@Composable
fun HourlyForecast() {

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
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
        )
        {
            Text(
                text = "Now",
                color = Cloud_Burst,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_sunny),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(25.dp)

            )

            Text(
                text = "33°C",
                color = Purple,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ne),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "NE",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "23 km/h",
                color = Purple_Blue,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )


        }

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
        )
        {
            Text(
                text = "12 PM",
                color = Cloud_Burst,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_sunny_cloudy),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(25.dp)

            )

            Text(
                text = "33°C",
                color = Purple,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ne),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "NE",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "23 km/h",
                color = Purple_Blue,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )


        }

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
        )
        {
            Text(
                text = "1 PM",
                color = Cloud_Burst,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_raining_cloudy),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(25.dp)

            )

            Text(
                text = "33°C",
                color = Purple,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ne),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "NE",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "23 km/h",
                color = Purple_Blue,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )


        }

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
        )
        {
            Text(
                text = "2 PM",
                color = Cloud_Burst,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_full_raining),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(25.dp)

            )

            Text(
                text = "33°C",
                color = Purple,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ne),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "NE",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "23 km/h",
                color = Purple_Blue,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )


        }
    }


}