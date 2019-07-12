package com.example.airlineapp.di

import androidx.lifecycle.ViewModel
import com.example.airlineapp.ui.home.HomeViewModel
import com.example.airlineapp.ui.schedule.ScheduleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun bindScheduleScreenViewModel(scheduleViewModel: ScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeScreenViewModel(homeViewModel: HomeViewModel): ViewModel
}