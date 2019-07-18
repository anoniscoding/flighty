package com.example.airlineapp.extensions

import okhttp3.Interceptor
import okhttp3.Request

fun Interceptor.Chain.url(): String {
    return (request().tag() as Request).url().url().toString()
}