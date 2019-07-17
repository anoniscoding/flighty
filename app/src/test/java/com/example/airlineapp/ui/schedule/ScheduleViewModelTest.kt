package com.example.airlineapp.ui.schedule

import com.example.airlineapp.data.*
import com.example.airlineapp.ui.BaseTest
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ScheduleViewModelTest: BaseTest() {
    @Mock
    private lateinit var _repository: ScheduleContract.Repository

    @Test
    fun `throw_error_when_fetch_schedules_request_fails`() {
        val error = Throwable("request error message")
        val scheduleLocation = ScheduleInfo(LocationData.LAGOS, LocationData.FRANCE)
        `when`(_repository.fetchSchedules(scheduleLocation)).thenReturn(Observable.error(error))
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

        `when`(_repository.fetchSchedules(scheduleLocation)).thenReturn(Observable.just(schedules))
        val viewModel = ScheduleViewModel(_repository)
        viewModel.fetchSchedules(scheduleLocation)
        assertEquals(schedules.size.toString(), viewModel.totalSchedules.value)
        assertEquals(schedules, viewModel.schedules.value)
    }
}