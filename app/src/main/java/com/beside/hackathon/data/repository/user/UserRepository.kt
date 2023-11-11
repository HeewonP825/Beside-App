package com.beside.hackathon.data.repository.user

import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.user.JwtToken
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(email: String, password: String) : JwtToken {
        return JwtToken("token","refreshToken")
    }
}