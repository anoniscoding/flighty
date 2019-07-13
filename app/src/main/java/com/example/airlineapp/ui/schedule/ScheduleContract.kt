package com.example.airlineapp.ui.schedule

import com.example.airlineapp.data.Schedule
import com.example.airlineapp.ui.home.ScheduleLocation
import io.reactivex.Observable

interface ScheduleContract {

    interface Repository {
        fun getAirlineSchedules(scheduleLocation: ScheduleLocation): Observable<List<Schedule>>
    }
}