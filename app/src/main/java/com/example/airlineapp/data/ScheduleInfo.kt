package com.example.airlineapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class ScheduleInfo(
    val origin: LocationData,
    val destination: LocationData,
    private val _fromStartDate: Date = Date()) : Parcelable {

    val fromStartDate: String
        get() = SimpleDateFormat("yyyy-MM-dd").format(_fromStartDate)
}

enum class LocationData(
    val code: String,
    val label: String,
    val lat: Double,
    val lng: Double
) {
    LAGOS("LOS", "Lagos", 6.465, 3.406),
    FRANCE("FRA", "France", 48.864, 2.349),
    ZURICH("ZRH", "Zurich", 47.378, 8.540),
    TORONTO("YYZ", "Toronto", 43.700, -79.4163);

    override fun toString(): String = label
}