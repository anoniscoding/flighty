package com.example.airlineapp.di

import com.example.airlineapp.ui.home.HomeFragment
import com.example.airlineapp.ui.schedule.ScheduleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelFactoryModule::class, ViewModelModule::class, DependencyModule::class])
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun bindHomeScreen(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindScheduleScreen(): ScheduleFragment
}