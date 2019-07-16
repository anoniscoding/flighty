package com.example.airlineapp.ui.splash

import android.content.Intent
import com.example.airlineapp.ui.LandingScreenActivity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.robolectric.Robolectric
import org.junit.Before
import org.junit.Test
import org.robolectric.RobolectricTestRunner
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowApplication
import org.robolectric.shadows.ShadowLooper


@RunWith(RobolectricTestRunner::class)
class SplashActivityTest {
    private lateinit var activity: SplashActivity

    @Before
    fun setup() {
        activity = Robolectric.setupActivity(SplashActivity::class.java)
    }

    @Test
    fun `splash_should_move_to_landing_screen_activity_after_time_elapses`() {
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
        val instance = ShadowApplication.getInstance()
        val nextStartedActivity = instance.nextStartedActivity
        val expectedIntent = Intent(activity, LandingScreenActivity::class.java)

        assertNotNull(nextStartedActivity)
        assertEquals(expectedIntent.component, nextStartedActivity.component)
    }
}