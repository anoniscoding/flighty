package com.example.airlineapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.airlineapp.data.ScheduleLocation
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var observerState: Observer<ScheduleLocation>

    //Prevents error : Method getMainLooper in android.os.Looper not mocked
    //Because livedata updates the its value on the main thread, this helps to bypass that
    @Rule
    @JvmField var rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `emit_error_when_origin_location_is_empty_when_search_flight_is_called`() {
        val viewModel = HomeViewModel()
        viewModel.onSearchFlightClick()
        assertEquals("Please provide origin location", viewModel.originError.value)
        assertEquals(null, viewModel.scheduleLocation.value)
    }

    @Test
    fun `emit_error_when_destination_location_is_empty_when_search_flight_is_called`() {
        val viewModel = HomeViewModel()
        viewModel.apply {
            origin.value =  "Lagos"
            onSearchFlightClick()
        }
        assertEquals("Please provide destination location", viewModel.destinationError.value)
        assertEquals(null, viewModel.scheduleLocation.value)
    }

    @Test
    fun `emit_schedule_location_when_origin_destination_locations_are_present`() {
        val viewModel = HomeViewModel()
        viewModel.apply {
            origin.value = "Lagos"
            destination.value = "France"
            onSearchFlightClick()
        }
        val scheduleLocation = viewModel.scheduleLocation.value
        assertEquals("LOS", scheduleLocation!!.origin.code)
        assertEquals("FRA", scheduleLocation.destination.code)

    }

}