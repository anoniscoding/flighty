package com.example.airlineapp.di

import com.example.airlineapp.ui.schedule.ScheduleContract
import com.example.airlineapp.ui.schedule.ScheduleRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DependencyModule {

    @Binds
    abstract fun bindScheduleUseCase(scheduleContract: ScheduleRepository): ScheduleContract.Repository
}