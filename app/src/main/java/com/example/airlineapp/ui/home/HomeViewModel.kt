package com.example.airlineapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airlineapp.data.LocationData
import com.example.airlineapp.data.ScheduleLocation
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {
    val origin = MutableLiveData<String>()
    val originError = MutableLiveData<String>()

    val destination = MutableLiveData<String>()
    val destinationError = MutableLiveData<String>()

    val scheduleLocation = MutableLiveData<ScheduleLocation>()

    fun onSearchClick() {
        when {
            origin.value.isNullOrEmpty() -> originError.value = "Please provide origin location"
            destination.value.isNullOrEmpty() -> destinationError.value = "Please provide destination location"
            else -> {
                val originLocation = LocationData.valueOf(origin.value!!.toUpperCase())
                val destinationLocation = LocationData.valueOf(destination.value!!.toUpperCase())
                scheduleLocation.value =
                    ScheduleLocation(originLocation, destinationLocation)
            }
        }
    }
}

