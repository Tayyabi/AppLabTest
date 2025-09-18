package com.example.applabtest.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.AppLabTestTheme
import com.example.applabtest.presentation.theme.Blue
import com.example.applabtest.presentation.theme.Cloud_Burst
import com.example.applabtest.presentation.theme.Pinkish_Red
import com.example.applabtest.presentation.ui.home.components.DailyForecast
import com.example.applabtest.presentation.ui.home.components.DateChangerView
import com.example.applabtest.presentation.ui.home.components.HourlyForecast
import com.example.applabtest.presentation.ui.home.components.LocationBottomSheetContent
import com.example.applabtest.presentation.ui.home.components.OtherFields
import com.example.applabtest.presentation.ui.home.components.OtherInformation
import com.example.applabtest.presentation.ui.home.components.SideMenu
import com.example.applabtest.presentation.ui.home.components.TemperatureView
import com.example.applabtest.presentation.ui.home.components.ThunderWarning
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = true
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val scope = rememberCoroutineScope()



    DismissibleNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu { selected ->
                scope.launch { drawerState.close() }
            }
        }
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetDragHandle = null,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetContent = {
                LocationBottomSheetContent(
                    onBackPressed = {
                        scope.launch {
                            scaffoldState.bottomSheetState.partialExpand()
                        }
                    }
                )
            },
            sheetPeekHeight = 0.dp
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    CenterAlignedTopAppBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Pinkish_Red,
                                        Cloud_Burst
                                    )
                                )
                            ),
                        title = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        color = Color.Black.copy(alpha = 0.30f),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(horizontal = 10.dp, vertical = 8.dp)
                                    .clickable(onClick = {
                                        scope.launch {
                                            scaffoldState.bottomSheetState.expand()
                                        }
                                    })
                            ) {
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "Al Shamal, Qatar",
                                    color = Color.White,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )

                                Image(
                                    painter = painterResource(R.drawable.ic_keyboard_arrow_down),
                                    contentDescription = null,
                                    Modifier.padding(top = 3.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isOpen) {
                                        drawerState.close()
                                    } else {
                                        drawerState.open()
                                    }
                                }
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_side_menu),
                                    contentDescription = "Menu",
                                    tint = Color.White,
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                        },
                        actions = {

                            IconButton(onClick = {


                            }) {
                                /*Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = Color.White
                                )*/
                            }

                        },
                    )
                },
            )
            { values ->

                Box(
                    modifier = Modifier
                        .padding(values)
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Pinkish_Red,
                                    Cloud_Burst
                                )
                            )
                        )
                )
                {
                    Column {

                        Text(
                            modifier = Modifier.padding(start = 16.dp, top = 5.dp),
                            text = "Detailed Forecast",
                            color = Color.White,
                            fontSize = 20.sp
                        )

                        Column(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .fillMaxSize()
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(
                                        topStart = 15.dp,
                                        topEnd = 15.dp
                                    )
                                )
                                .padding(vertical = 8.dp)
                        ) {

                            DateChangerView()

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {

                                TemperatureView()

                                OtherInformation()

                                ThunderWarning()

                                Spacer(modifier = Modifier.height(10.dp))

                                OtherFields(
                                    id = R.drawable.ic_humidity,
                                    title = "Humidity",
                                    value = "34%"
                                )

                                OtherFields(
                                    id = R.drawable.ic_sun,
                                    title = "UV Index",
                                    value = "High 7"
                                )

                                OtherFields(
                                    id = R.drawable.ic_pressure,
                                    title = "Pressure",
                                    value = "29.8 IN"
                                )

                                OtherFields(
                                    id = R.drawable.ic_visibility,
                                    title = "Visibility",
                                    value = "10 mi",
                                    isLast = true
                                )

                                Text(
                                    modifier = Modifier.padding(start = 16.dp, top = 15.dp),
                                    text = "Hourly forecast ",
                                    color = Blue,
                                    fontSize = 20.sp
                                )

                                HourlyForecast()

                                Text(
                                    modifier = Modifier.padding(
                                        start = 16.dp,
                                        top = 15.dp,
                                        bottom = 15.dp
                                    ),
                                    text = "Daily forecast",
                                    color = Blue,
                                    fontSize = 20.sp
                                )

                                DailyForecast(id = R.drawable.ic_raining_cloudy)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppLabTestTheme {
        HomeScreen()
    }
}