package com.example.applabtest.presentation.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.applabtest.R
import com.example.applabtest.presentation.theme.AppLabTestTheme
import com.example.applabtest.presentation.theme.Blue
import com.example.applabtest.presentation.theme.Cloud_Burst
import com.example.applabtest.presentation.theme.Grey
import com.example.applabtest.presentation.theme.Grey_1
import com.example.applabtest.presentation.theme.Grey_2
import com.example.applabtest.presentation.theme.Grey_3
import com.example.applabtest.presentation.theme.Grey_4
import com.example.applabtest.presentation.theme.Grey_5
import com.example.applabtest.presentation.theme.Grey_7
import com.example.applabtest.presentation.theme.Pinkish_Red
import com.example.applabtest.presentation.theme.Purple
import com.example.applabtest.presentation.theme.Purple_1
import com.example.applabtest.presentation.theme.Purple_Blue
import com.example.applabtest.presentation.theme.Yellow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

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
//                        Icon(
//                            imageVector = Icons.Default.Search,
//                            contentDescription = "Search",
//                            tint = Color.White
//                        )
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

@Composable
fun HourlyForecast() {

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "Now",
                color = Cloud_Burst,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_sunny),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(25.dp)

            )

            Text(
                text = "33°C",
                color = Purple,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ne),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "NE",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "23 km/h",
                color = Purple_Blue,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )


        }

        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "12 PM",
                color = Cloud_Burst,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_sunny_cloudy),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(25.dp)

            )

            Text(
                text = "33°C",
                color = Purple,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ne),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "NE",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "23 km/h",
                color = Purple_Blue,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )


        }

        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "1 PM",
                color = Cloud_Burst,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_raining_cloudy),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(25.dp)

            )

            Text(
                text = "33°C",
                color = Purple,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ne),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "NE",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "23 km/h",
                color = Purple_Blue,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )


        }

        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "2 PM",
                color = Cloud_Burst,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Image(
                painter = painterResource(R.drawable.ic_full_raining),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(25.dp)

            )

            Text(
                text = "33°C",
                color = Purple,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_ne),
                    contentDescription = null,
                    modifier = Modifier.size(15.dp)
                )
                Text(
                    text = "NE",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Text(
                text = "23 km/h",
                color = Purple_Blue,
                fontSize = 12.sp,
                lineHeight = 14.sp
            )


        }
    }


}

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
            text = "Thunderstorms likely to continue for the next several hours",
            color = Purple,
            fontSize = 15.sp,
            lineHeight = 17.sp,
            modifier = Modifier.padding(start = 7.dp)
        )
    }
}

@Composable
fun OtherInformation() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .padding(end = 6.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Image(
                painter = painterResource(R.drawable.ic_precip),
                contentDescription = null
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "PRECIP",
                    color = Grey_3,
                    fontSize = 12.sp,
                    lineHeight = 12.sp

                )
                Text(
                    text = "3%",
                    fontSize = 20.sp
                )
            }

        }

        Row(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .padding(start = 6.dp, end = 6.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {

            Image(
                painter = painterResource(R.drawable.ic_ne),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )

            Column {
                Text(
                    text = "NE",
                    fontSize = 19.sp,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }

        }

        Row(
            modifier = Modifier
                .weight(1f)
                .height(60.dp)
                .padding(start = 6.dp)
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {

            Image(
                painter = painterResource(R.drawable.ic_wind),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )


            Text(
                text = "23.3\nkm/h",
                fontSize = 16.sp,
                lineHeight = 16.sp,
                modifier = Modifier.padding(start = 6.dp)
            )


        }
    }

}

@Composable
fun TemperatureView() {

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Grey,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Al Shamal",
                color = Purple,
                fontSize = 24.sp,
                lineHeight = 24.sp
            )
            Text(
                text = "Thu, 19 May, 11:00 AM",
                fontSize = 14.sp,
                lineHeight = 14.sp,
                color = Grey_2.copy(alpha = 0.5f)
            )

            Text(
                text = "18°C",
                color = Purple,
                fontSize = 59.sp,
                lineHeight = 59.sp,
                fontWeight = FontWeight(450),
                modifier = Modifier.padding(top = 5.dp)
            )

            Text(
                text = "Partialy Cloudy",
                color = Color.Black,
                fontSize = 24.sp,
                lineHeight = 24.sp
            )

            Text(
                text = "Feels like 15°",
                color = Purple_Blue,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Box {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_up),
                        contentDescription = null,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .height(18.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .size(width = 12.dp, height = 1.8.dp)
                                .background(Purple)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }


                Text(
                    text = "99°",
                    fontSize = 25.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight(450),
                    modifier = Modifier.padding(start = 4.dp)
                )

                Column(
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_down),
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Box(
                        modifier = Modifier
                            .size(width = 15.dp, height = 1.8.dp)
                            .background(Purple)
                    )
                }

                Text(
                    text = "82°",
                    fontSize = 25.sp,
                    lineHeight = 25.sp,
                    fontWeight = FontWeight(450),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.ic_clouds),
            contentDescription = "Clouds",
            modifier = Modifier.size(140.dp)
        )
    }
}

@Composable
fun DateChangerView() {
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
                    color = Color.White,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(R.drawable.ic_keyboard_arrow_left),
                contentDescription = "Back",
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
                text = "19th May,2022",
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
                    color = Color.White,
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Grey,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        )
        {
            Image(
                painter = painterResource(R.drawable.ic_keyboard_arrow_right),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun CustomTabSelector(
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    // Animation for sliding indicator
    val indicatorOffset by animateFloatAsState(
        targetValue = if (selectedTab == "Qatar") 0f else 1f,
        animationSpec = tween(300),
        label = "Tab Indicator"
    )

    // Calculate the offset based on actual container size
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
        // Sliding White Background Indicator
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

        // Tab Labels Row
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

@Composable
private fun CityListItem(
    city: String,
    temperature: String = "25°C",
    onClick: () -> Unit,
    showDivider: Boolean = true
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = city,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = temperature,
                fontSize = 14.sp,
                color = Purple,
                fontWeight = FontWeight.Medium
            )
        }

        if (showDivider) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Grey_4)
            )
        }
    }
}

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
        // Back Button Row
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



        // Search Field - Only show for Worldwide tab
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
                    focusedContainerColor = Grey_1,
                    unfocusedContainerColor = Grey_1,
                    disabledContainerColor = Grey_1,
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
                        color = Grey_3,
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppLabTestTheme {
        HomeScreen()
    }
}