package com.example.airlineapp.ui.map

import android.content.Intent
import com.example.airlineapp.R
import com.example.airlineapp.data.LocationData
import com.example.airlineapp.data.ScheduleInfo
import com.example.airlineapp.ui.schedule.ScheduleFragment
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MapActivityTest {
    private lateinit var activity: MapsActivity

    @Before
    fun setup() {
        val scheduleInfo = ScheduleInfo(LocationData.LAGOS, LocationData.FRANCE)
        val intent = Intent().apply { putExtra(ScheduleFragment.SCHEDULE_INFO_TAG, scheduleInfo) }
        activity = Robolectric.buildActivity(MapsActivity::class.java, intent)
            .create().get()
    }

    @Test
    fun `maps_activity_should_start_successfully`() {
        assertNotNull(activity)
    }

    @Test
    fun `maps_activity_contains_map_fragment`() {
        val map = activity.supportFragmentManager.findFragmentById(R.id.map)
        assertNotNull(map)
    }
}