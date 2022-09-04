package com.example.domain.util.location

import android.location.Location

interface LocationService {

    suspend fun getLocation(callBack:  (Location?) -> Unit)
}