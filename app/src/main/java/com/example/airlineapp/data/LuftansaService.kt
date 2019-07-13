package com.example.airlineapp.data

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.*

interface LuftansaService {

    @Headers("Content-Type:application/json")
    @GET("operations/schedules/{origin}/{destination}/{fromDateTime}?directFlights=0&limit=20")
    fun fetchSchedules(@Path("origin") origin: String,
                       @Path("destination") destination: String,
                       @Path("fromDateTime") fromDateTime: String): Observable<JsonObject>
}