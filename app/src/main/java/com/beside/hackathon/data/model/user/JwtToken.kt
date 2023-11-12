package com.beside.hackathon.data.model.user

import com.google.gson.annotations.SerializedName

data class JwtToken(
    @SerializedName("accessKey")
    val accessToken: String,
    @SerializedName("refreshKey")
    val refreshToken: String
)
