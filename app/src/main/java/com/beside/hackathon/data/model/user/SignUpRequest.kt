package com.beside.hackathon.data.model.user

data class SignUpRequest(
    val account: String,
    val password: String,
    val nickname: String,
    val schoolName: String,
    val interest: Interest,
)