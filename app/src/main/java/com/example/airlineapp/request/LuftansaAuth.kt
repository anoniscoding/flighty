package com.example.airlineapp.request

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LuftansaAuth {
    @FormUrlEncoded
    @POST("https://api.lufthansa.com/v1/oauth/token")
    fun fetchToken(@Field("client_id") clientId: String, @Field("client_secret") clientSecret: String, @Field("grant_type") grantType: String): Single<JsonObject>
}