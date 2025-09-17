package com.example.applabtest.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.applabtest.presentation.theme.AppLabTestTheme
import com.example.applabtest.presentation.ui.home.HomeScreen

// Commented imports for previous implementation
/*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applabtest.di.AppModule
import com.example.applabtest.domain.model.City
import com.example.applabtest.presentation.viewmodel.WeatherViewModel
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppLabTestTheme {
                // Using the new HomeScreen with bottom sheet city selection
                HomeScreen()

                // Previous navigation-based implementation (commented out)
                // val navController = rememberNavController()
                // WeatherApp(navController = navController)
            }
        }
    }
}

// Previous navigation-based implementation (commented out)
/*
@Composable
fun WeatherApp(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "cities"
    ) {
        composable("cities") {
            CitiesScreen(
                onCityClick = { city ->
                    navController.navigate("weather/${city.id}")
                }
            )
        }
        composable("weather/{cityId}") { backStackEntry ->
            val cityId = backStackEntry.arguments?.getString("cityId")?.toIntOrNull() ?: 0
            WeatherDetailScreen(
                cityId = cityId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
*/

// Previous CitiesScreen implementation (commented out)
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(
    modifier: Modifier = Modifier,
    onCityClick: (City) -> Unit,
    viewModel: WeatherViewModel = viewModel(
        factory = AppModule.provideWeatherViewModelFactory()
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Weather App",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage?.let { error ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = "Error: $error",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            if (uiState.cities.isNotEmpty()) {
                Text(
                    text = "Available Cities",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                LazyColumn {
                    items(uiState.cities) { city ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = { onCityClick(city) }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = city.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = city.countryName,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                if (city.isQatar) {
                                    Text(
                                        text = "Qatar • ${city.utcOffset ?: ""}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                } else {
                                    Text(
                                        text = "World • ${city.utcOffset ?: ""}",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.secondary
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
*/

// Previous WeatherDetailScreen implementation (commented out)
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailScreen(
    cityId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel(
        factory = AppModule.provideWeatherViewModelFactory()
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(cityId) {
        val city = uiState.cities.find { it.id == cityId }
        city?.let { viewModel.loadWeatherForCity(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.weatherData?.city?.name ?: "Weather Details",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.errorMessage?.let { error ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = "Error: $error",
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            uiState.weatherData?.let { weather ->
                LazyColumn {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = weather.city.name,
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = weather.city.countryName,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Current Weather",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(text = "🌡️ Temperature: ${weather.current.temperature}${weather.current.temperatureUnit}")
                                Text(text = "🌡️ Feels Like: ${weather.current.feelsLike}°C")
                                Text(text = "🌡️ Min/Max: ${weather.current.temperatureMin}°C / ${weather.current.temperatureMax}°C")
                                Text(text = "💧 Humidity: ${weather.current.humidity}%")
                                Text(text = "🌬️ Wind: ${weather.current.windSpeed} m/s ${weather.current.windDirectionText ?: ""}")
                                Text(text = "🌫️ Visibility: ${weather.current.visibility} m")
                                Text(text = "☀️ UV Index: ${weather.current.uvIndex}")
                                Text(text = "🌤️ Condition: ${weather.current.weatherType}")
                                Text(text = "☁️ Clouds: ${weather.current.clouds}%")
                                Text(text = "🌧️ Rain: ${weather.current.rain} mm")
                            }
                        }
                    }

                    if (weather.dailyForecast.isNotEmpty()) {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "7-Day Forecast",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }

                        items(weather.dailyForecast.take(7)) { daily ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 2.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp)
                                ) {
                                    Text(
                                        text = daily.date,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = "${daily.temperatureMin}°C - ${daily.temperatureMax}°C • ${daily.weatherType}",
                                        style = MaterialTheme.typography.bodySmall
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
*/

// Previous preview (commented out)
/*
@Preview(showBackground = true)
@Composable
fun CitiesScreenPreview() {
    AppLabTestTheme {
        CitiesScreen(onCityClick = {})
    }
}
*/