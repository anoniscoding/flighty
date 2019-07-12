package com.example.airlineapp.extensions

import com.google.gson.Gson
import com.google.gson.JsonObject

const val DATA_LABEL = "data"

fun <T> JsonObject.convertTo(requestName: String, classOfT: Class<T>) : T? {
    return Gson().fromJson(get(DATA_LABEL).asJsonObject.get(requestName), classOfT)
}

fun <T> JsonObject.convertTo(classOfT: Class<T>) : T? {
    return Gson().fromJson(this, classOfT)
}