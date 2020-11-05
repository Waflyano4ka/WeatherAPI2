package com.example.weatherapi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class DataWeather (
    var temp: Double,
    var feels_like: Double,
    var temp_min: Double,
    var temp_max: Double,
    var pressure: Int, // Давление
    var humidity: Int, // Влажность
    var visibility: Int,
    var wind_speed: Int,
    var wind_deg: Int,
    var clouds: String,
    var name: String
): Parcelable

@Parcelize
data class DataCity (
    var id: Int,
    var name: String
): Parcelable