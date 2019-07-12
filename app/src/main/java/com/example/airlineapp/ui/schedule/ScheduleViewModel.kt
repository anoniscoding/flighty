package com.example.airlineapp.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airlineapp.data.Flight
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(private val _repository: ScheduleContract.Repository): ViewModel() {

    private val _compositeDisposable = CompositeDisposable()

    private val _flights = MutableLiveData<List<Flight>>()
    val flights : LiveData<List<Flight>> = _flights

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun fetchSchedules(origin: String, destination: String) {
        _compositeDisposable.add(
            _repository.getAirlineSchedules(origin, destination).subscribe(this::onAirlineScheduleSuccess, this::onError)
        )
    }

    private fun onAirlineScheduleSuccess(flightSchedules: List<Flight>) {
        _flights.value = flightSchedules
    }

    private fun onError(error: Throwable) {
        _errorMsg.value = error.message
    }

    override fun onCleared() {
        _compositeDisposable.clear()
        super.onCleared()
    }
}