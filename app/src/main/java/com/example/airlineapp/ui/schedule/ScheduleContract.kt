package com.example.airlineapp.ui.schedule

import com.example.airlineapp.data.Schedule
import com.example.airlineapp.data.ScheduleInfo
import io.reactivex.Observable

interface ScheduleContract {

    interface Repository {
        fun fetchSchedules(scheduleInfo: ScheduleInfo): Observable<List<Schedule>>
    }
}