package com.beside.hackathon.data.model.user

data class JwtToken(
    val accessToken: String,
    val refreshToken: String
)
