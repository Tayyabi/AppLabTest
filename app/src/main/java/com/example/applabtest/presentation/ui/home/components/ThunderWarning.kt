package com.example.applabtest.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.Purple
import com.example.applabtest.presentation.theme.Yellow

@Composable
fun ThunderWarning() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .background(
                color = Yellow,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(R.drawable.ic_warning),
            contentDescription = null
        )

        Text(
            text = stringResource(R.string.thunderstorm_warning),
            color = Purple,
            fontSize = 15.sp,
            lineHeight = 17.sp,
            modifier = Modifier.padding(start = 7.dp)
        )
    }
}