package com.beside.hackathon.data.model.user

data class LoginRequest(
    val account: String,
    val password: String,
    val fcmToken: String,
)
