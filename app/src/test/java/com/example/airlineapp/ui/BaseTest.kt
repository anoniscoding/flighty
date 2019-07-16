package com.example.airlineapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule

open class BaseTest {
    //Prevents error : Method getMainLooper in android.os.Looper not mocked
    //Because livedata updates the its value on the main thread, this helps to bypass that
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
}