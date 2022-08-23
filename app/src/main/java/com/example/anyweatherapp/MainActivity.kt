package com.example.anyweatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.anyweatherapp.ui.theme.AnyWeatherAppTheme
import com.example.presentation.common.CustomTextDialog
import com.example.presentation.screens.main_weather.MainWeatherScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnyWeatherAppTheme {
                val showDialog = remember { mutableStateOf(false) }
                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ))
                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(key1 = lifecycleOwner, effect = {
                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_START) {
                            permissionState.launchMultiplePermissionRequest()
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)

                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                })

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    permissionState.permissions.forEach { perm ->
                        when (perm.permission) {
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION -> {
                                when {
                                    perm.hasPermission -> {
                                        MainWeatherScreen()
                                    }
                                    perm.shouldShowRationale -> {
                                        showDialog.value = true
                                    }
                                    perm.shouldShowRationale.not() && perm.hasPermission.not() -> {
                                        DeniedPermissionMessage()
                                    }
                                }
                            }
                        }
                    }
                }
                if (showDialog.value) {
                    CustomTextDialog(
                        onDismiss = { showDialog.value = it },
                        positiveButtonText = "Request",
                        messageText = "Any Weather needs this permission to work correctly",
                        onButtonClick = {
                            showDialog.value = it
                            permissionState.launchMultiplePermissionRequest()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DeniedPermissionMessage() {

    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(140.dp))
        Text(
            text = "AnyWeather has not location permission or it is denied \n You can enable it in the app" + "settings.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Image(painterResource(
                id = R.drawable.map),
                contentDescription = "",
                modifier = Modifier
                    .size(120.dp),
                alignment = Alignment.Center
            )
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnyWeatherAppTheme {

    }
}