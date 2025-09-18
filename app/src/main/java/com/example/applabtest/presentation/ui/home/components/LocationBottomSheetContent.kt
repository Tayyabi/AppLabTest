package com.example.applabtest.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.Grey_1
import com.example.applabtest.presentation.theme.Grey_3
import com.example.applabtest.presentation.theme.Grey_7
import com.example.applabtest.presentation.theme.Grey_8
import com.example.applabtest.presentation.theme.Grey_9
import com.example.applabtest.presentation.theme.Purple

@Composable
fun LocationBottomSheetContent(
    onBackPressed: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("Qatar") }
    var searchText by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val insets = WindowInsets.navigationBars.asPaddingValues()

    val qatarCities = remember {
        listOf(
            "Al Shamal", "Doha", "Al Rayyan", "Al Wakrah", "Al Khor",
            "Umm Salal", "Al Daayen", "Madinat ash Shamal"
        )
    }

    val worldwideCities = remember {
        listOf(
            "New York", "London", "Paris", "Tokyo", "Sydney",
            "Dubai", "Singapore", "Berlin", "Toronto", "Mumbai"
        )
    }

    val currentCities = if (selectedTab == "Qatar") qatarCities else worldwideCities

    Column(
        modifier = Modifier
            .padding(bottom = insets.calculateBottomPadding())
            .fillMaxWidth()
            .height(screenHeight * 0.95f)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { onBackPressed() },
                colors = androidx.compose.material3.ButtonDefaults.textButtonColors(
                    contentColor = Purple,
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "Back",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        CustomTabSelector(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )



        if (selectedTab == "Worldwide") {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = {
                    Text(
                        text = "Add City",
                        color = Grey_3
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Grey_3,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { /* Handle audio input */ }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_mic),
                            contentDescription = "Audio",
                            tint = Grey_3,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Grey_8,
                    unfocusedContainerColor = Grey_8,
                    disabledContainerColor = Grey_8,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .border(
                        width = 1.dp,
                        color = Grey_9,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Grey_7)
                .padding(16.dp)
        ) {
            Text(
                text = if (selectedTab == "Qatar") "Qatar - Cities" else "Worldwide - Cities",
                color = Color.Black,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            itemsIndexed(currentCities) { index, city ->
                CityListItem(
                    city = city,
                    onClick = { /* Handle city selection */ },
                    showDivider = index < currentCities.size - 1
                )
            }
        }
    }
}