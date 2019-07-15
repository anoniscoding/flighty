package com.example.airlineapp.di

import android.app.Application
import com.example.airlineapp.data.LuftansaAuth
import com.example.airlineapp.data.LuftansaService
import com.example.airlineapp.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit


@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Reusable
    @JvmStatic
    fun provideOkHttpClient(application: Application, tokenInterceptor: TokenInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val tenMb: Long = 10 * 1024 * 1024
        val cache = Cache(cacheDir, tenMb)
        val dispatcher = Dispatcher().apply { maxRequests = 1 }

        return OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .cache(cache)
            .connectTimeout(AppConstants.INSTANCE.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.INSTANCE.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.INSTANCE.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.INSTANCE.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Reusable
    @JvmStatic
    fun provideLuftansaService(retrofit: Retrofit): LuftansaService = retrofit.create(
        LuftansaService::class.java
    )

    @Provides
    @Reusable
    @JvmStatic
    fun provideLuftansaAuth(retrofit: Retrofit): LuftansaAuth = retrofit.create(
        LuftansaAuth::class.java
    )
}