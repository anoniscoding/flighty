package com.example.airlineapp.ui.schedule

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.airlineapp.data.*
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import junit.framework.Assert.assertEquals

@RunWith(MockitoJUnitRunner::class)
class ScheduleViewModelTest {

    //Prevents error : Method getMainLooper in android.os.Looper not mocked
    //Because livedata updates the its value on the main thread, this helps to bypass that
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var _repository: ScheduleContract.Repository

    @Test
    fun `throw_error_when_fetch_schedules_request_fails`() {
        val error = Throwable("request error message")
        val scheduleLocation = ScheduleInfo(LocationData.LAGOS, LocationData.FRANCE)
        `when`(_repository.getAirlineSchedules(scheduleLocation)).thenReturn(Observable.error(error))
        val viewModel = ScheduleViewModel(_repository)
        viewModel.fetchSchedules(scheduleLocation)
        assertEquals("request error message", viewModel.errorMsg.value)
    }

    @Test
    fun `retrieve_schedules_list_on_fetch_schedules_success`() {
        val scheduleLocation = ScheduleInfo(LocationData.LAGOS, LocationData.FRANCE)
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

        `when`(_repository.getAirlineSchedules(scheduleLocation)).thenReturn(Observable.just(schedules))
        val viewModel = ScheduleViewModel(_repository)
        viewModel.fetchSchedules(scheduleLocation)
        assertEquals(schedules.size.toString(), viewModel.totalSchedules.value)
        assertEquals(schedules, viewModel.schedules.value)
    }
}