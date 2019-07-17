package com.example.airlineapp.ui.schedule

import com.example.airlineapp.data.*
import com.example.airlineapp.extensions.convertTo
import com.example.airlineapp.data.ScheduleInfo
import com.example.airlineapp.request.LuftansaService
import io.reactivex.Observable
import io.reactivex.Scheduler

class ScheduleRepository(
    private val _luftansaService: LuftansaService,
    private val _subscribeOnScheduler: Scheduler,
    private val _observeOnScheduler: Scheduler
) : ScheduleContract.Repository {

    override fun fetchSchedules(scheduleInfo: ScheduleInfo): Observable<List<Schedule>> {
        return _luftansaService.fetchSchedules(scheduleInfo.origin.code, scheduleInfo.destination.code, scheduleInfo.fromStartDate)
            .subscribeOn(_subscribeOnScheduler)
            .map {
                val scheduleResource = it.convertTo(ScheduleResource::class.java) ?: ScheduleResource()
                scheduleResource.schedule
            }
            .observeOn(_observeOnScheduler)
    }
}