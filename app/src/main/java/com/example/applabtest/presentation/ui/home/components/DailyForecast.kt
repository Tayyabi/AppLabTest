package com.example.applabtest.presentation.ui.home.components

import androidx.annotation.DrawableRes
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
import com.example.applabtest.presentation.theme.Grey_4
import com.example.applabtest.presentation.theme.Grey_5
import com.example.applabtest.presentation.theme.Purple
import com.example.applabtest.presentation.theme.Purple_Blue

@Composable
fun DailyForecast(@DrawableRes id: Int, isLast: Boolean = false) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Grey_4)
    )

    Row(
        modifier = Modifier
            .background(Grey_5.copy(alpha = 0.70f))
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column {
            Text(
                text = "Saturday",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = Purple_Blue
            )

            Text(
                text = "33°C",
                fontSize = 18.sp,
                lineHeight = 18.sp,
                color = Purple
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id),
            contentDescription = null
        )

        Text(
            text = "Clear",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )

        Text(
            text = "29°/22°C",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Grey_4)
    )

    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column {
            Text(
                text = "Sunday",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = Purple_Blue
            )

            Text(
                text = "33°C",
                fontSize = 18.sp,
                lineHeight = 18.sp,
                color = Purple
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id),
            contentDescription = null
        )

        Text(
            text = "Clear",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )

        Text(
            text = "29°/22°C",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Grey_4)
    )

    Row(
        modifier = Modifier
            .background(Grey_5.copy(alpha = 0.70f))
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column {
            Text(
                text = "Monday",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = Purple_Blue
            )

            Text(
                text = "33°C",
                fontSize = 18.sp,
                lineHeight = 18.sp,
                color = Purple
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id),
            contentDescription = null
        )

        Text(
            text = "Clear",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )

        Text(
            text = "29°/22°C",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }

    if (isLast)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Grey_4)
        )
}