package com.example.airlineapp.di

import android.content.SharedPreferences
import com.example.airlineapp.data.LuftansaAuth
import com.example.airlineapp.utils.AppConstants
import com.example.airlineapp.utils.HttpCodes
import com.example.airlineapp.utils.PrefsConstants.AUTH_TOKEN
import dagger.Lazy
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val _auth: Lazy<LuftansaAuth>, private val _prefs: SharedPreferences) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = _prefs.getString(AUTH_TOKEN, "")
        val request: Request = getRequestWithOrWithoutHeaders(chain, token)
        val response = chain.proceed(request)

        if (response.code() == HttpCodes.UNAUTHORIZED.code) {
            synchronized(this) {
                val newToken = regenerateToken()
                _prefs.edit().putString(AUTH_TOKEN, newToken).apply()
                val newRequest = getRequestWithAuthorizationHeader(chain, newToken)
                return chain.proceed(newRequest)
            }
        } else {
            return response
        }
    }

    private fun getRequestWithOrWithoutHeaders(chain: Interceptor.Chain, token: String?): Request {
        return if (getRequestUrl(chain) != AppConstants.INSTANCE.TOKEN_URL) {
            getRequestWithAuthorizationHeader(chain, token)
        } else {
            chain.request().newBuilder().build()
        }
    }

    private fun getRequestUrl(chain: Interceptor.Chain) = (chain.request().tag() as Request).url().url().toString()

    private fun getRequestWithAuthorizationHeader(chain: Interceptor.Chain, token: String?): Request {
        return chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
    }

    private fun regenerateToken(): String {
        val result = _auth.get().fetchToken(
            AppConstants.INSTANCE.CLIENT_ID,
            AppConstants.INSTANCE.CLIENT_SECRET,
            AppConstants.INSTANCE.GRANT_TYPE
        ).blockingGet()

        val accessToken = result.get("access_token").toString()
        return accessToken.substring(1, accessToken.length - 1) //remove extra quotes from token
    }
}