package com.example.presentation.screens.main_weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.weather.WeatherInfo
import com.example.domain.model.weather.WeatherModel
import com.example.presentation.R
import org.koin.androidx.compose.getViewModel

@Composable
fun MainWeatherScreen() {
    val vm = getViewModel<MainWeatherVm>()
    val currentWeather = vm.currentWeather.collectAsState().value
    val hourlyWeather = vm.hourlyWeatherData.collectAsState().value
    Column(Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.main_bg_color))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Box(Modifier.clip(RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center) {
                if (currentWeather.isLoading) {
                    LinearProgressIndicator()
                }
                currentWeather.errorMessage?.let {
                    Text(text = it, fontSize = 21.sp, color = Color.Red)
                }
                currentWeather.data?.let {
                    CurrentWeather(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = colorResource(id = R.color.bg_color)),
                        currentWeather = it
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
            Box(modifier = Modifier.clip(RoundedCornerShape(12.dp))) {
                if (hourlyWeather.isLoading) {
                    LinearProgressIndicator()
                }
                hourlyWeather.errorMessage?.let {
                    Text(text = it, fontSize = 21.sp, color = Color.Red)
                }
                hourlyWeather.data?.let {
                    TodayWeather(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = colorResource(id = R.color.bg_color))
                            .padding(10.dp),
                        weatherModel = it
                    )
                }
            }
        }
    }
}

@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    currentWeather: WeatherInfo,
) {
    val currentHr = currentWeather.time.hour
    val hrText = if (currentHr < 12) "$currentHr AM" else "$currentHr PM"
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(30.dp))
        Text(text = hrText, textAlign = TextAlign.Center, color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = currentWeather.weatherType.iconRes),
            contentDescription = "",
            modifier = Modifier.size(160.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "${currentWeather.weatherType.title}", color = Color.Black, fontSize = 19.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "${currentWeather.temperature}°C",
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 31.sp
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "${currentWeather.windSpeed}", color = Color.Black)
            Image(
                painter = painterResource(id = R.drawable.ic_wind),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun TodayWeather(
    modifier: Modifier = Modifier,
    weatherModel: WeatherModel,
) {
    Column(modifier = modifier) {
        Text(text = "Today",
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth())
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(items = weatherModel.weatherMap[0]!!) { item ->
                HourlyWeatherItem(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(10.dp),
                    weatherInfo = item
                )
            }
        }
    }

}

@Composable
fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    weatherInfo: WeatherInfo,
) {
    val currentHr = weatherInfo.time.hour
    val hrText = if (currentHr < 12) "$currentHr AM" else "$currentHr PM"
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = hrText, color = Color.Blue, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Image(painter = painterResource(id = weatherInfo.weatherType.iconRes),
            contentDescription = "",
            modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "${weatherInfo.temperature}°C", color = Color.Blue, fontSize = 14.sp)
    }
}