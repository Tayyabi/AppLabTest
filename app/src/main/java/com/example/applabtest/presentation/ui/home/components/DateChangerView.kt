package com.example.applabtest.presentation.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.Grey
import com.example.applabtest.presentation.theme.Grey_1

@Composable
fun DateChangerView(
    currentDate: String = "Today",
    canNavigateBack: Boolean = true,
    canNavigateForward: Boolean = true,
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {

        Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    color = if (canNavigateBack) Color.White else Color.Gray.copy(alpha = 0.3f),
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = if (canNavigateBack) Grey else Grey.copy(alpha = 0.5f),
                    shape = CircleShape
                )
                .clickable(enabled = canNavigateBack) { onPreviousClick() },
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(R.drawable.ic_keyboard_arrow_left),
                contentDescription = "Previous day",
                modifier = Modifier.size(24.dp)
            )
        }


        Spacer(modifier = Modifier.weight(1f))


        Row(
            modifier = Modifier
                .background(
                    color = Grey_1,
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = currentDate,
                color = Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))



        Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    color = if (canNavigateForward) Color.White else Color.Gray.copy(alpha = 0.3f),
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = if (canNavigateForward) Grey else Grey.copy(alpha = 0.5f),
                    shape = CircleShape
                )
                .clickable(enabled = canNavigateForward) { onNextClick() },
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(R.drawable.ic_keyboard_arrow_right),
                contentDescription = "Next day",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}