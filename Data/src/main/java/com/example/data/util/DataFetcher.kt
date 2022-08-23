package com.example.data.util

import com.example.domain.util.ResponseState
import retrofit2.Response

inline fun <T,D> fetchData(
    response: Response<T>,
    onMapData: (T) -> D
): ResponseState<D> {
    return try {
        if (response.isSuccessful) {
            val body = response.body()
            body?.let { ResponseState(data = onMapData.invoke(it)) } ?: ResponseState(errorMessage = "data is Null")
        } else {
            ResponseState(errorMessage = "Network request error occured")
        }
    } catch (e: Exception) {
        ResponseState(errorMessage = e.localizedMessage ?: "unknown error occured")
    }
}