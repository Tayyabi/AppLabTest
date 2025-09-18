package com.example.applabtest.presentation.ui.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.AppLabTestTheme
import com.example.applabtest.presentation.theme.Cloud_Burst
import com.example.applabtest.presentation.theme.Pinkish_Red
import com.example.applabtest.presentation.theme.Purple_1
import com.example.applabtest.presentation.theme.Yellow_1

@Composable
fun SideMenu(
    onMenuClick: (String) -> Unit
) {

    var selectedTab by remember { mutableStateOf("EN") }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val menuItems = listOf(
        "Dashboard",
        "Weather News",
        "Rain Radar",
        "Weather Maps",
        "Weather Stations",
        "Notifications Center",
        "Monthly Reports",
        "Worldwide Cities",
        "About Us",
        "Settings",
        "Contact Us",
        "Disclaimer"
    )

    Box(
        modifier = Modifier
            .width(screenWidth * 0.75f) // ✅ drawer takes 75% of screen
            .fillMaxHeight(),
        contentAlignment = Alignment.BottomCenter
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Cloud_Burst,
                            Purple_1
                        )
                    )
                )
                .padding(start = 20.dp, bottom = 50.dp)
                .verticalScroll(rememberScrollState())
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp)
                    .padding(horizontal = 16.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_clouds), // replace with your image
                    contentDescription = "Clouds Header",
                    modifier = Modifier.size(130.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = "Menu",
                color = Yellow_1,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            )

            // Menu options
            menuItems.forEach { item ->
                Text(
                    text = item,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMenuClick(item) }
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                )
            }

            LanguageTabSelector(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .width(120.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ){
                Image(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = null
                )

                Text(
                    text = "Share app",
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.ic_side_menu_bottom),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }

}



@Composable
private fun LanguageTabSelector(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    val indicatorOffset by animateFloatAsState(
        targetValue = if (selectedTab == "EN") 0f else 1f,
        animationSpec = tween(300),
        label = "Tab Indicator"
    )

    val tabOffsetDp by remember {
        derivedStateOf {
            if (containerSize.width > 0) {
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
                color = Color.White.copy(alpha = 0.3f),
                shape = RoundedCornerShape(6.dp)
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
                    shape = RoundedCornerShape(6.dp)
                )
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            TabItem(
                text = "EN",
                isSelected = selectedTab == "EN",
                onClick = { onTabSelected("EN") },
                modifier = Modifier.weight(1f)
            )

            TabItem(
                text = "AR",
                isSelected = selectedTab == "AR",
                onClick = { onTabSelected("AR") },
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
            color = if (isSelected) Pinkish_Red else Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SideMenuPreview() {
    AppLabTestTheme {
        SideMenu(onMenuClick = {})
    }
}