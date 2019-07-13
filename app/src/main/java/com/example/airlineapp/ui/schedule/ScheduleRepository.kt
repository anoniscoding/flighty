package com.example.airlineapp.ui.schedule

import com.example.airlineapp.data.*
import com.example.airlineapp.extensions.convertTo
import com.example.airlineapp.data.ScheduleLocation
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.text.SimpleDateFormat
import java.util.*

class ScheduleRepository(
    private val _luftansaService: LuftansaService,
    private val _subscribeOnScheduler: Scheduler,
    private val _observeOnScheduler: Scheduler
) : ScheduleContract.Repository {

    override fun getAirlineSchedules(scheduleLocation: ScheduleLocation): Observable<List<Schedule>> {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val startDate = format.format(Date())
        return _luftansaService.fetchSchedules(scheduleLocation.origin.code(), scheduleLocation.destination.code(), startDate)
            .subscribeOn(_subscribeOnScheduler)
            .map {
                val scheduleResource = it.convertTo(ScheduleResource::class.java) ?: ScheduleResource()
                scheduleResource.schedule
            }
            .observeOn(_observeOnScheduler)
    }
}