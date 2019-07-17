package com.example.airlineapp.di

import android.app.Application
import com.example.airlineapp.request.LuftansaAuth
import com.example.airlineapp.request.LuftansaService
import com.example.airlineapp.di.NetworkModule.NetworkConstant.CONNECT_TIMEOUT
import com.example.airlineapp.di.NetworkModule.NetworkConstant.READ_TIMEOUT
import com.example.airlineapp.di.NetworkModule.NetworkConstant.WRITE_TIMEOUT
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
    fun provideOkHttpClient(application: Application, authInterceptor: AuthInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val tenMb: Long = 10 * 1024 * 1024
        val cache = Cache(cacheDir, tenMb)
        val dispatcher = Dispatcher().apply { maxRequests = 1 }

        return OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .cache(cache)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
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

    object NetworkConstant{
        const val CONNECT_TIMEOUT: Long = 30
        const val WRITE_TIMEOUT: Long = 30
        const val READ_TIMEOUT: Long = 30
    }
}