package com.beside.hackathon.data.repository.user

import com.beside.hackathon.data.api.ApiService
import com.beside.hackathon.data.model.user.Interest
import com.beside.hackathon.data.model.user.JwtToken
import com.beside.hackathon.data.model.user.LoginRequest
import com.beside.hackathon.data.model.user.SignUpRequest
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(email: String, password: String) : JwtToken {
        val body = LoginRequest(email, password)
        return apiService.login(body)
    }

    suspend fun signUp(
        account: String,
        password: String,
        nickname: String,
        schoolName: String,
        interest: Interest
    ){
        val body = SignUpRequest(account,password,nickname,schoolName, interest)
        return apiService.signUp(body)
    }

    suspend fun idValidation(account:String):Boolean{
        return apiService.idValidation(account)
    }
    suspend fun nicknameValidation(nickname: String):Boolean{
        return apiService.nicknameValidation(nickname)
    }
}