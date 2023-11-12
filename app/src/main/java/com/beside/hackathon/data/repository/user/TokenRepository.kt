package com.beside.hackathon.data.repository.user

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton


class TokenRepository @Inject constructor(
    private val prefs: SharedPreferences
){

    fun getFcmToken(): String? {
        return prefs.getString("fcmToken", null)
    }

    fun saveFcmToken(fcmToken: String) {
        prefs.edit().putString("fcmToken", fcmToken).apply()
    }
    fun getAccessToken(): String? {
        return prefs.getString("accessToken", null)
    }
    fun getRefreshToken(): String? {
        return prefs.getString("refreshToken", null)
    }

    fun setAccessToken(accessToken: String) {
        prefs.edit().putString("accessToken", accessToken).apply()
    }
    fun setRefreshToken(refreshToken: String) {
        prefs.edit().putString("refreshToken", refreshToken).apply()
    }
    fun removeToken() {
        prefs.edit().remove("refreshToken").apply()
        prefs.edit().remove("accessToken").apply()
    }

}