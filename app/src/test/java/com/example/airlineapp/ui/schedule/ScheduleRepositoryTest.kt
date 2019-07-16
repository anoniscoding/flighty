package com.example.airlineapp.ui.schedule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.airlineapp.data.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class ScheduleRepositoryTest {

    //Prevents error : Method getMainLooper in android.os.Looper not mocked
    //Because livedata updates the its value on the main thread, this helps to bypass that
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var _luftansaService: LuftansaService

    @Test
    fun `throw_error_when_get_airline_schedules_request_fails`() {
        val date = GregorianCalendar(2014, Calendar.FEBRUARY, 11).time
        val error = Throwable("request error message")
        val scheduleLocation = ScheduleInfo(LocationData.LAGOS, LocationData.FRANCE, date)
        `when`(_luftansaService.fetchSchedules("LOS", "FRA", "2014-02-11")).thenReturn(Observable.error(error))
        val scheduleRepository = ScheduleRepository(_luftansaService, Schedulers.trampoline(), Schedulers.trampoline())
        scheduleRepository.getAirlineSchedules(scheduleLocation).test().assertError(error)
    }

    @Test
    fun `retrieve_schedules_when_get_airline_schedules_request_is_success`() {
        val schedules = listOf(
            Schedule().apply {
                totalJourney = TotalJourney()
                flights = listOf(
                    Flight(),
                    Flight(),
                    Flight()
                )
            },
            Schedule().apply {
                totalJourney = TotalJourney()
                flights = listOf(
                    Flight(),
                    Flight(),
                    Flight()
                )
            }
        )

        val scheduleResource = ScheduleResource().apply {
            schedule = schedules
        }

        val jsonStr = Gson().toJson(scheduleResource)
        val result = Gson().fromJson<JsonObject>(jsonStr, JsonObject::class.java)

        val date = GregorianCalendar(2014, Calendar.FEBRUARY, 11).time
        val scheduleLocation = ScheduleInfo(LocationData.LAGOS, LocationData.FRANCE, date)
        `when`(_luftansaService.fetchSchedules("LOS", "FRA", "2014-02-11")).thenReturn(Observable.just(result))
        val scheduleRepository = ScheduleRepository(_luftansaService, Schedulers.trampoline(), Schedulers.trampoline())
        scheduleRepository.getAirlineSchedules(scheduleLocation).test().assertComplete().assertNoErrors()
    }

}