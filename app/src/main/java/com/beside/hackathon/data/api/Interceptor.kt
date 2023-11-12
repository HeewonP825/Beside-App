package com.beside.hackathon.data.api

import android.content.SharedPreferences
import android.util.Log
import com.beside.hackathon.core.utils.Constant.BASE_URL
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val prefs: SharedPreferences
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val originRequest = response.request
        if(originRequest.header("Authorization").isNullOrEmpty()){
            return null
        }
        val refreshToken = runBlocking {
            prefs.getString("refreshToken", "")
        }
        val refreshRequest = Request.Builder()
            .url("$BASE_URL/auth/token")
            .post("".toRequestBody())
            .addHeader("authorization", "Bearer ${refreshToken!!}")
            .build()
        val refreshResponse = OkHttpClient().newCall(refreshRequest).execute()
        Log.d("refreshResponse", "refresh :${refreshResponse.code}, ${refreshResponse.body}")
        if(refreshResponse.code == 201) {
            val gson = Gson()
            val refreshResponseJson = gson.fromJson(refreshResponse.body?.string(), Map::class.java)
            val newAccessToken = refreshResponseJson["accessKey"].toString()
            prefs.edit().putString("accessToken", newAccessToken).apply()
            val newRequest = originRequest.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "Bearer $newAccessToken")
                .build()
            return newRequest
        }else{
            prefs.edit().remove("accessToken").apply()
            prefs.edit().remove("refreshToken").apply()
        }
        return null

    }

}

class HeaderInterceptor @Inject constructor(
    private val prefs: SharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(chain.request().headers["Auth"] == "false"){
            val newRequest = chain.request().newBuilder()
                .removeHeader("Auth")
                .build()
            return chain.proceed(newRequest)
        }

        var token = ""
        runBlocking {
            token = ("Bearer " + prefs.getString("accessToken", ""))
            Log.d("token", token)
        }
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        val response = chain.proceed(newRequest)


        return response
    }
}