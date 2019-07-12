package com.example.airlineapp.ui.schedule

import com.example.airlineapp.data.Flight
import io.reactivex.Observable

interface ScheduleContract {

    interface Repository {
        fun getAirlineSchedules(origin: String, destination: String): Observable<List<Flight>>
    }
}