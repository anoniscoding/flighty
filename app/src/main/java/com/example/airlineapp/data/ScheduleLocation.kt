package com.example.airlineapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScheduleLocation(val origin: LocationData, val destination: LocationData) : Parcelable

enum class LocationData(
    private val code: String,
    private val label: String,
    private val lat: Double,
    private val lng: Double
) {
    LAGOS("LOS", "Lagos", 6.465, 3.406),
    FRANCE("FRA", "France", 48.864, 2.349),
    ZURICH("ZRH", "Zurich", 47.378, 8.540),
    TORONTO("YYZ", "Toronto", 43.700, -79.4163);

    override fun toString(): String = label
    fun code(): String = code
    fun label(): String = label
    fun lat(): Double = lat
    fun lng(): Double = lng
}