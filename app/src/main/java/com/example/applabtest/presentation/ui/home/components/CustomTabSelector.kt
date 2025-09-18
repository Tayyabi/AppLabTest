package com.example.applabtest.presentation.ui.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.presentation.theme.Purple
import com.example.applabtest.presentation.theme.Purple_1


@Composable
fun CustomTabSelector(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    val indicatorOffset by animateFloatAsState(
        targetValue = if (selectedTab == "Qatar") 0f else 1f,
        animationSpec = tween(300),
        label = "Tab Indicator"
    )

    val tabOffsetDp by remember {
        derivedStateOf {
            if (containerSize.width > 0) {
                // Container has 8dp padding, so usable width needs to account for that
                // The white indicator should move exactly half the container width
                val usableWidthPx = containerSize.width - with(density) { 8.dp.toPx() }
                with(density) { (usableWidthPx / 2 * indicatorOffset).toDp() }
            } else {
                0.dp
            }
        }
    }

    Box(
        modifier = modifier
            .background(
                color = Purple_1,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)
            .onSizeChanged { containerSize = it }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(40.dp)
                .offset(x = tabOffsetDp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            TabItem(
                text = "Qatar",
                isSelected = selectedTab == "Qatar",
                onClick = { onTabSelected("Qatar") },
                modifier = Modifier.weight(1f)
            )

            TabItem(
                text = "Worldwide",
                isSelected = selectedTab == "Worldwide",
                onClick = { onTabSelected("Worldwide") },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Purple else Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}