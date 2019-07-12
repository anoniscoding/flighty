package com.example.airlineapp.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    @JvmStatic
    @Named("SCHEDULER_MAIN_THREAD")
    fun provideAndroidScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @JvmStatic
    @Named("SCHEDULER_IO")
    fun provideSchedulerIO(): Scheduler = Schedulers.io()
}