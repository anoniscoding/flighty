package com.example.airlineapp.di

import android.app.Application
import com.example.airlineapp.data.LuftansaService
import com.example.airlineapp.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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
    fun provideOkHttpClient(application: Application): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val bearerInterceptor = Interceptor {
                    val newRequest  = it.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + "j8cqxwkq7u8mk4xejbw7cff3")
                        .build()
                    it.proceed(newRequest)
        }

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val tenMb: Long = 10 * 1024 * 1024
        val cache = Cache(cacheDir, tenMb)

        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(bearerInterceptor)
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
        LuftansaService::class.java)
}