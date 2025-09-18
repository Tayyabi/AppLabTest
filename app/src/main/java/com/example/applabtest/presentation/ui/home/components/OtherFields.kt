package com.example.applabtest.presentation.ui.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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

@Composable
fun OtherFields(@DrawableRes id: Int, title: String, value: String, isLast: Boolean = false) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Grey_4)
    )

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(id),
            contentDescription = null
        )

        Text(
            text = title,
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = value,
            color = Color.Black,
            fontSize = 14.sp
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