package com.example.airlineapp.ui.home

import com.example.airlineapp.ui.BaseTest
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : BaseTest() {

    @Test
    fun `emit_error_when_origin_location_is_empty_when_search_flight_is_called`() {
        val viewModel = HomeViewModel()
        viewModel.onSearchFlightClick()
        assertEquals("Please provide origin location", viewModel.originError.value)
        assertEquals(null, viewModel.scheduleInfo.value)
    }

    @Test
    fun `emit_error_when_destination_location_is_empty_when_search_flight_is_called`() {
        val viewModel = HomeViewModel()
        viewModel.apply {
            origin.value = "Lagos"
            onSearchFlightClick()
        }
        assertEquals("Please provide destination location", viewModel.destinationError.value)
        assertEquals(null, viewModel.scheduleInfo.value)
    }

    @Test
    fun `emit_schedule_location_when_origin_destination_locations_are_present_when_search_flight_is_called`() {
        val viewModel = HomeViewModel()
        viewModel.apply {
            origin.value = "Lagos"
            destination.value = "France"
            onSearchFlightClick()
        }

        val scheduleLocation = viewModel.scheduleInfo.value
        assertEquals("LOS", scheduleLocation!!.origin.code)
        assertEquals("FRA", scheduleLocation.destination.code)
    }

}