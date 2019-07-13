package com.example.airlineapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScheduleLocation(val origin: LocationData, val destination: LocationData) : Parcelable

enum class LocationData(private val code: String, private val label: String) {
    LAGOS("LOS", "Lagos"),
    FRANCE("FRA", "France"),
    ZURICH("ZRH", "Zurich"),
    TORONTO("YYZ", "Toronto");

    override fun toString(): String = label
    fun code(): String = code
    fun label(): String = label
}