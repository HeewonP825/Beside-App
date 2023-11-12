package com.beside.hackathon.data.model.user

import com.google.gson.annotations.SerializedName

data class JwtToken(
    val accessToken: String,
    val refreshToken: String
)
