package com.example.airlineapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airlineapp.data.LocationData
import com.example.airlineapp.data.ScheduleInfo
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {
    val origin = MutableLiveData<String>()
    val originError = MutableLiveData<String>()

    val destination = MutableLiveData<String>()
    val destinationError = MutableLiveData<String>()

    private val _scheduleInfo = MutableLiveData<ScheduleInfo>()
    val scheduleInfo : LiveData<ScheduleInfo> = _scheduleInfo

    fun onSearchFlightClick() {
        when {
            origin.value.isNullOrEmpty() -> originError.value = "Please provide origin location"
            destination.value.isNullOrEmpty() -> destinationError.value = "Please provide destination location"
            else -> emitScheduleInfo()
        }
    }

    private fun emitScheduleInfo() {
        val originLocation = LocationData.valueOf(origin.value!!.toUpperCase())
        val destinationLocation = LocationData.valueOf(destination.value!!.toUpperCase())
        _scheduleInfo.value = ScheduleInfo(originLocation, destinationLocation)
    }
}

