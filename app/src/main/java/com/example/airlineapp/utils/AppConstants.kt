package com.example.airlineapp.utils

object AppConstants {
    const val PRODUCTION_LABEL = "PRODUCTION"
    const val STAGING_LABEL = "STAGING"

    var INSTANCE : Constants = StagingConstants
        private set

    fun setup(environment: String) {
        if (environment == PRODUCTION_LABEL) {
            INSTANCE = ProductionConstants
        } else {
            INSTANCE = StagingConstants
        }
    }
}

interface Constants {
    val BASE_URL : String
}

object StagingConstants : Constants {
    override val BASE_URL = "https://api.lufthansa.com/v1/"
}

object ProductionConstants : Constants {
    override  val BASE_URL = "https://api.lufthansa.com/v1/"
}