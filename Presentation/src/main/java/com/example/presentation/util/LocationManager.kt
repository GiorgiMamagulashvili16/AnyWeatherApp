package com.example.presentation.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationManager(
    private val context: Context,
) {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    fun getLocation(onSuccess: (LocationManagerState) -> Unit) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        if (ActivityCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            onSuccess.invoke(LocationManagerState(error = "app does not have location permissions"))
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            it?.let {
                onSuccess.invoke(LocationManagerState(lat = it.latitude, long = it.longitude))
            } ?: kotlin.run {
                onSuccess.invoke(LocationManagerState(error = "Location is null"))
            }
        }
    }
}