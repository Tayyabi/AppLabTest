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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.applabtest.R
import com.example.applabtest.core.utils.LocaleHelper
import com.example.applabtest.domain.model.City
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

@Composable
fun HomeScreenRoot(
    onLanguageChanged: (String) -> Unit = {}
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCities()
    }

    HomeScreen(
        uiState = uiState,
        viewModel = viewModel,
        currentDate = viewModel.getCurrentDateString(),
        canNavigateBack = viewModel.canNavigateBackward(),
        canNavigateForward = viewModel.canNavigateForward(),
        onCitySelected = { city ->
            viewModel.loadWeatherForCity(city)
        },
        onPreviousDay = {
            viewModel.navigateToPreviousDay()
        },
        onNextDay = {
            viewModel.navigateToNextDay()
        },
        onLanguageChanged = { languageCode ->
            viewModel.changeLanguage(languageCode)
            onLanguageChanged(languageCode)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: WeatherUiState = WeatherUiState(),
    viewModel: HomeViewModel? = null,
    currentDate: String = "Today",
    canNavigateBack: Boolean = false,
    canNavigateForward: Boolean = false,
    onCitySelected: (City) -> Unit = {},
    onPreviousDay: () -> Unit = {},
    onNextDay: () -> Unit = {},
    onLanguageChanged: (String) -> Unit = {}
) {
    // Determine layout direction based on selected language
    val layoutDirection = if (LocaleHelper.isRTL(uiState.selectedLanguage)) {
        LayoutDirection.Rtl
    } else {
        LayoutDirection.Ltr
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = true
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val scope = rememberCoroutineScope()

    var selectedCity by remember { mutableStateOf<City?>(null) }

    // Close drawer when language changes
    LaunchedEffect(uiState.selectedLanguage) {
        if (drawerState.isOpen) {
            drawerState.close()
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        DismissibleNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                selectedLanguage = uiState.selectedLanguage,
                onMenuClick = { selected ->
                    scope.launch { drawerState.close() }
                },
                onLanguageChanged = onLanguageChanged
            )
        }
    ) {
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetDragHandle = null,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetContent = {
                LocationBottomSheetContent(
                    cities = uiState.cities,
                    isLoading = uiState.isLoading,
                    errorMessage = uiState.errorMessage,
                    onCitySelected = { city ->
                        selectedCity = city
                        onCitySelected(city)
                        scope.launch {
                            scaffoldState.bottomSheetState.partialExpand()
                        }
                    },
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
                                    text = selectedCity?.name ?: "Select Your City",
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

                            DateChangerView(
                                currentDate = currentDate,
                                canNavigateBack = canNavigateBack,
                                canNavigateForward = canNavigateForward,
                                onPreviousClick = onPreviousDay,
                                onNextClick = onNextDay
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                            ) {

                                TemperatureView(weatherData = uiState.weatherData?.let {
                                    viewModel?.getWeatherDataForSelectedDate()
                                })

                                OtherInformation(weatherData = uiState.weatherData?.let {
                                    viewModel?.getWeatherDataForSelectedDate()
                                })

                                ThunderWarning()

                                Spacer(modifier = Modifier.height(10.dp))

                                OtherFields(
                                    id = R.drawable.ic_humidity,
                                    title = "Humidity",
                                    value = (uiState.weatherData?.let { viewModel?.getWeatherDataForSelectedDate() })?.current?.let { "${it.humidity}%" } ?: "34%"
                                )

                                OtherFields(
                                    id = R.drawable.ic_sun,
                                    title = "UV Index",
                                    value = (uiState.weatherData?.let { viewModel?.getWeatherDataForSelectedDate() })?.current?.uvIndex ?: "High 7"
                                )

                                OtherFields(
                                    id = R.drawable.ic_pressure,
                                    title = "Pressure",
                                    value = (uiState.weatherData?.let { viewModel?.getWeatherDataForSelectedDate() })?.current?.let { "${it.pressure} hPa" } ?: "29.8 IN"
                                )

                                OtherFields(
                                    id = R.drawable.ic_visibility,
                                    title = "Visibility",
                                    value = (uiState.weatherData?.let { viewModel?.getWeatherDataForSelectedDate() })?.current?.let { "${it.visibility / 1000} km" } ?: "10 mi",
                                    isLast = true
                                )

                                Text(
                                    modifier = Modifier.padding(start = 16.dp, top = 15.dp),
                                    text = "Hourly forecast ",
                                    color = Blue,
                                    fontSize = 20.sp
                                )

                                HourlyForecast(
                                    hourlyData = uiState.weatherData?.let {
                                        viewModel?.getHourlyDataForSelectedDate()
                                    } ?: emptyList()
                                )

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

                                DailyForecast(
                                    dailyWeatherList = uiState.weatherData?.dailyForecast ?: emptyList()
                                )
                            }
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