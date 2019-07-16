package com.example.airlineapp.utils

object AppConstants {
    var INSTANCE : Constants = ProductionConstants
        private set
}

interface Constants {
    val BASE_URL : String
}

private object ProductionConstants : Constants {
    override val BASE_URL = "https://api.lufthansa.com/v1/"
}

object PrefsConstants {
    val AUTH_TOKEN = "auth_token"
}