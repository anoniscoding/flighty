package com.example.airlineapp.di

import com.example.airlineapp.request.LuftansaService
import com.example.airlineapp.ui.schedule.ScheduleContract
import com.example.airlineapp.ui.schedule.ScheduleRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Module
object DependencyModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideHomeModel(luftansaService: LuftansaService,
                         @Named("SCHEDULER_IO") _subscribeOnScheduler: Scheduler,
                         @Named("SCHEDULER_MAIN_THREAD") _observeOnScheduler: Scheduler
    ): ScheduleContract.Repository {
        return ScheduleRepository(luftansaService, _subscribeOnScheduler, _observeOnScheduler)
    }
}