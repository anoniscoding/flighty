package com.example.airlineapp.data

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.*

interface LuftansaService {

    @Headers("Content-Type:application/json")
    @GET("operations/schedules/")
    fun fetchSchedules(@Query("origin") origin: String,
                    @Query("destination") destination: String,
                    @Query("fromDateTime") fromDateTime: String): Observable<JsonObject>
}