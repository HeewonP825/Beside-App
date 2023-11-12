package com.beside.hackathon.data.model.quizhistory


data class QuizHistory(
    val quizId: Long,
    val startDate: String,
    val endDate: String,
    val subject:String,
    val correctCount: Int,
    val wrongCount: Int,
    val answerLate: Double,
)
