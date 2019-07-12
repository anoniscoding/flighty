package com.example.airlineapp.ui.schedule

import com.example.airlineapp.data.Flight
import com.example.airlineapp.data.LuftansaSchedule
import com.example.airlineapp.data.LuftansaService
import com.example.airlineapp.extensions.convertTo
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.*

class ScheduleRepository(
    private val _luftansaService: LuftansaService,
    private val _subscribeOnScheduler: Scheduler,
    private val _observeOnScheduler: Scheduler
) : ScheduleContract.Repository {

    override fun getAirlineSchedules(origin: String, destination: String): Observable<List<Flight>> {
        return _luftansaService.fetchSchedules(origin, destination, Date().date.toString())
            .subscribeOn(_subscribeOnScheduler)
            .observeOn(_observeOnScheduler)
            .map {
                val schedule = it.convertTo(LuftansaSchedule::class.java) ?: LuftansaSchedule()
                schedule.flights
            }
    }
}