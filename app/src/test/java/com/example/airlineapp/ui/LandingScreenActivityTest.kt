package com.example.airlineapp.ui

import com.example.airlineapp.R
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LandingScreenActivityTest {
    private lateinit var activity: LandingScreenActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(LandingScreenActivity::class.java!!)
            .create().start().resume().get()
    }

    @Test
    fun `landing_screen_activity_should_load_home_fragment`() {
        val map = activity.supportFragmentManager.findFragmentById(R.id.fragment_container)
        Assert.assertNotNull(map)
    }
}