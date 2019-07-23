package com.example.airlineapp.ui.schedule

import com.example.airlineapp.data.*
import com.example.airlineapp.extensions.convertTo
import com.example.airlineapp.data.ScheduleInfo
import com.example.airlineapp.request.LuftansaService
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class ScheduleRepository @Inject constructor(
    private val _luftansaService: LuftansaService,
    @Named("SCHEDULER_IO") private val _subscribeOnScheduler: Scheduler,
    @Named("SCHEDULER_MAIN_THREAD") private val _observeOnScheduler: Scheduler
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