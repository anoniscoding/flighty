package com.example.airlineapp.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.airlineapp.data.Schedule
import com.example.airlineapp.data.ScheduleLocation
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(private val _repository: ScheduleContract.Repository): ViewModel() {

    private val _compositeDisposable = CompositeDisposable()

    private val _schedules = MutableLiveData<List<Schedule>>()
    val schedules : LiveData<List<Schedule>> = _schedules

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun fetchSchedules(location: ScheduleLocation) {
        _compositeDisposable.add(
            _repository.getAirlineSchedules(location).subscribe(this::onAirlineScheduleSuccess, this::onError)
        )
    }

    private fun onAirlineScheduleSuccess(flightSchedules: List<Schedule>) {
        _schedules.value = flightSchedules
    }

    private fun onError(error: Throwable) {
        _errorMsg.value = error.message
    }

    override fun onCleared() {
        _compositeDisposable.clear()
        super.onCleared()
    }
}