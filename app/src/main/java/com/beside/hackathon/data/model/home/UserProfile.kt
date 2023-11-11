package com.beside.hackathon.data.model.home

data class UserProfile(
    val nickname: String,
    val schoolName: String,
    val interest: String,
    val readNewsCount: Int,
    val enteredQuizCount: Int,
    val correctRate: Double,
)