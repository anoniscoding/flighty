package com.example.airlineapp.utils

object AppConstants {
    var INSTANCE : Constants = ProductionConstants
        private set
}

interface Constants {
    val BASE_URL : String
    val CLIENT_ID: String
    val CLIENT_SECRET: String
    val GRANT_TYPE: String
    val CONNECT_TIMEOUT: Long
    val READ_TIMEOUT: Long
    val WRITE_TIMEOUT: Long
    val TOKEN_URL: String
}

private object ProductionConstants : Constants {
    override val BASE_URL = "https://api.lufthansa.com/v1/"
    override val CLIENT_ID = "fukwyurm7a3v445myjz7pe9t"
    override val CLIENT_SECRET = "2Xd6V8KBvn"
    override val GRANT_TYPE = "client_credentials"
    override val TOKEN_URL: String = "https://api.lufthansa.com/v1/oauth/token"
    override val CONNECT_TIMEOUT: Long = 30
    override val WRITE_TIMEOUT: Long = 30
    override val READ_TIMEOUT: Long = 30
}

object PrefsConstants {
    val AUTH_TOKEN = "auth_token"
}